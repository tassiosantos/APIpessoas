package com.api.apipessoa.steps;

import com.api.apipessoa.controllers.EnderecoController;
import com.api.apipessoa.dtos.EnderecoDto;
import com.api.apipessoa.dtos.PessoaDto;
import com.api.apipessoa.mappers.EnderecoMapper;
import com.api.apipessoa.models.Endereco;
import com.api.apipessoa.models.Pessoa;
import com.api.apipessoa.repositories.EnderecoRepository;
import com.api.apipessoa.services.EnderecoService;
import com.api.apipessoa.services.PessoaService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

//@CucumberContextConfiguration
@SpringBootTest(classes = {TestConfiguration.class})
public class EnderecoTestSteps {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private PessoaService pessoaService;
    @InjectMocks
    private EnderecoService enderecoService = new EnderecoService(enderecoRepository, pessoaService);
    @InjectMocks
    private EnderecoController enderecoController = new EnderecoController(enderecoService);

    private EnderecoMapper enderecoMapper = Mappers.getMapper(EnderecoMapper.class);

    private Endereco endereco = new Endereco();
    private EnderecoDto enderecoDto = new EnderecoDto();

    private PessoaDto pessoaDto = new PessoaDto();
    private Pessoa pessoa = new Pessoa();


////    Scenario: Adicionar um endereço principal para uma pessoa
//    @Given("a existencia de uma pessoa com os seguintes dados")
//    public montarEndereco(){
//
//
//    }
//
//
//
//
//    @When("o endpoint /enderecos/adicionar/{id} é chamado")
//    public adicionarEndereco(){
//
//    }
//
//
//    @Then("esse endereco deve ser adicionado a essa pessoa")
//    public verificarEnderecoSalvo(){
//
//    }
//
//
//
////    Scenario: Editar um endereço de uma pessoa
//    @Given("a existencia de alguma pessoa com os seguintes dados")
//    public configurarPessoa(DataTable pessoa){
//
//
//    }
//            | nome          | João       |
//            | sobrenome     | Silva      |
//            | dataNascimento| 1990-01-01 |
//    @And("a existência de um endereço dessa pessoa com as informações")
//            | numero       |   45       |
//            | logradouro   | Rua A      |
//            | cidade       | São Paulo  |
//            | estado       | SP         |
//            | cep          | 01000-000  |
//    @And("a edição do endereço dessa pessoa com os seguintes dados")
//            | numero       |   48       |
//            | logradouro   | Rua C      |
//            | cidade       | São Paulo  |
//            | estado       | SP         |
//            | cep          | 01000-100  |
//    @When("o endpoint enderecos/editar/id é chamado")
//    @Then("o endereço dessa pessoa deve ser editado com sucesso")
//
//
////    Scenario: Buscar os endereços de uma pessoa
//    @Given("o usuário deseja buscar os endereços de uma pessoa pelo id")
//            | nome          | Maria      |
//            | sobrenome     | Oliveira   |
//            | dataNascimento| 1988-03-23 |
//    @When("o endpoint /enderecos/buscar/getall/id é chamado")
//    @Then("os endereços dessa pessoa devem ser buscados")


//    Scenario: Determinar um endereço como principal para uma pessoa
    Endereco enderecoPrincipal, novoEnderecoPrincipal;
    List<Endereco> enderecos =  mockEnderecos();
//    @Given("o usuário definir um endereço de uma pessoa como principal")
//    public void escolherEnderecoPrincipal(){
//        for (Endereco endereco: enderecos) {
//            if(enderecoDto.getPrincipal()){
//                enderecoPrincipal = endereco;
//            }
//        }
//    }
//    @When("o endpoint enderecos/principal/id/pessoaId é chamado")
//    public void alterarEnderecoPrincipal(){
//        when(this.enderecoRepository.findAllByPessoa(any(Long.class))).thenReturn(enderecos);
//        when(this.enderecoRepository.save(any(Endereco.class))).thenReturn(null);
//        novoEnderecoPrincipal = this.enderecoMapper.toEntity(this.enderecoController.definirComoPrincipal(2L, 1L).getBody());
//    }
//    @Then("o endereço principal dessa pessoa é alterado")
//    public void verificaNovoEnderecoPrincipal(){
//        assertNotEquals(enderecoPrincipal.toString(), novoEnderecoPrincipal.toString());
//        assertTrue(novoEnderecoPrincipal.getPrincipal());
//        assertFalse(enderecoPrincipal.getPrincipal());
//    }






    private List<EnderecoDto> mockEnderecosDto(){
        List<EnderecoDto> enderecosDto = new ArrayList<>();
        Pessoa pessoa = new Pessoa("João", "José", LocalDate.now());
        enderecosDto.add(new EnderecoDto(1L, true, pessoa, "SP", "São Paulo", "01000-000", "Rua A", "123"));
        enderecosDto.add(new EnderecoDto(2L, false, pessoa, "RJ", "Rio de Janeiro", "20000-000", "Rua B", "456"));
        enderecosDto.add(new EnderecoDto(3L, false, pessoa, "MG", "Belo Horizonte", "30000-000", "Rua C", "789"));
        return enderecosDto;
    }

    private List<Endereco> mockEnderecos(){
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDto enderecoDto: mockEnderecosDto()) {
            enderecos.add(this.enderecoMapper.toEntity(enderecoDto));
        }
        return enderecos;
    }







}
