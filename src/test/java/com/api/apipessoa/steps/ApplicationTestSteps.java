package com.api.apipessoa.steps;

import com.api.apipessoa.controllers.EnderecoController;
import com.api.apipessoa.controllers.PessoaController;
import com.api.apipessoa.dtos.EnderecoDto;
import com.api.apipessoa.dtos.PessoaDto;
import com.api.apipessoa.mappers.EnderecoMapper;
import com.api.apipessoa.mappers.PessoaMapper;
import com.api.apipessoa.mappers.PessoaMapperImpl;
import com.api.apipessoa.models.Endereco;
import com.api.apipessoa.models.Pessoa;
import com.api.apipessoa.repositories.EnderecoRepository;
import com.api.apipessoa.repositories.PessoaRepository;
import com.api.apipessoa.services.EnderecoService;
import com.api.apipessoa.services.PessoaService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;

import io.cucumber.spring.CucumberContextConfiguration;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@CucumberContextConfiguration
@SpringBootTest(classes = {TestConfiguration.class})
public class ApplicationTestSteps {
    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private EnderecoRepository enderecoRepository;


    private PessoaMapper pessoaMapper = Mappers.getMapper(PessoaMapper.class);
    private EnderecoMapper enderecoMapper = Mappers.getMapper(EnderecoMapper.class);
    @InjectMocks
    private PessoaService pessoaService = new PessoaService(pessoaRepository);
    @InjectMocks
    private PessoaController pessoaController = new PessoaController(pessoaService);
    @InjectMocks
    private EnderecoService enderecoService = new EnderecoService(enderecoRepository, pessoaService);
    @InjectMocks
    private EnderecoController enderecoController = new EnderecoController(enderecoService);
    private PessoaDto pessoaDto = new PessoaDto();
    private Pessoa pessoa = new Pessoa();
    private Endereco endereco = new Endereco();
    private EnderecoDto enderecoDto = new EnderecoDto();

//    Scenario: Adicionar uma pessoa com sucesso
    @Given("a inserção de uma pessoa com os seguintes dados")
    public void criarPessoa(DataTable pessoaAdicionada) {
        Map<String, String> dadosPessoa = pessoaAdicionada.asMap(String.class, String.class);
        String nome = dadosPessoa.get("nome");
        String sobrenome = dadosPessoa.get("sobrenome");
        LocalDate dataNascimento = LocalDate.parse(dadosPessoa.get("dataNascimento"));
        pessoaDto = new PessoaDto();
        pessoa = new Pessoa(nome, sobrenome, dataNascimento);
    }

    @When("^o endpoint pessoas/adicionar é chamado")
    public void adicionarPessoa() {
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);
        pessoaDto = this.pessoaController.salvarPessoa(this.pessoaDto).getBody();
    }
    @Then("essa pessoa deve ser adicionada")
    public void verificarPessoaAdicionada() {
        assertNotNull(pessoaDto);
    }

//    Scenario: Editar uma pessoa com sucesso
    PessoaDto pessoaDtoEditada = new PessoaDto();
    @Given("a inserção de uma pessoa que será editada com os seguintes dados")
    public void criarPessoaParaEditar(DataTable pessoaAdicionada) {
        Map<String, String> dadosPessoa = pessoaAdicionada.asMap(String.class, String.class);
        String nome = dadosPessoa.get("nome");
        String sobrenome = dadosPessoa.get("sobrenome");
        LocalDate dataNascimento = LocalDate.parse(dadosPessoa.get("dataNascimento"));
        pessoaDto = new PessoaDto(nome, sobrenome, dataNascimento);
        pessoaDto.setId(1L);
        pessoa = new Pessoa();
    }

    @And("a edição dessa pessoa com os seguintes dados")
    public void receberPessoaParaEditada(DataTable pessoaEditada) {
        Map<String, String> dadosPessoa = pessoaEditada.asMap(String.class, String.class);
        String nome = dadosPessoa.get("nome");
        String sobrenome = dadosPessoa.get("sobrenome");
        LocalDate dataNascimento = LocalDate.parse(dadosPessoa.get("dataNascimento"));
        pessoaDtoEditada = new PessoaDto(nome, sobrenome, dataNascimento);
    }

    @When("^o endpoint pessoas/editar/id é chamado")
    public void editarPessoa(){
        Long id = 1L;
        pessoa = pessoaMapper.toEntity(pessoaDtoEditada);
        Optional<Pessoa> optPessoa = Optional.of(pessoaMapper.toEntity(pessoaDto));
        when(pessoaRepository.findById(any(Long.class))).thenReturn(optPessoa);
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);
        pessoaDto = this.pessoaController.editarPessoa(this.pessoaDtoEditada, id).getBody();
    }

    @Then("essa pessoa deve ser editada")
    public void verificarPessoaEditada() {
        assertEquals (pessoaDto.toString(), pessoaDtoEditada.toString());
    }



//    Scenario: Buscar uma pessoa
    @Given("o usuário busca uma pessoa pelo id")
    public void configurarPessoa(DataTable pessoaAdicionada) {
        Map<String, String> dadosPessoa = pessoaAdicionada.asMap(String.class, String.class);
        String nome = dadosPessoa.get("nome");
        String sobrenome = dadosPessoa.get("sobrenome");
        LocalDate dataNascimento = LocalDate.parse(dadosPessoa.get("dataNascimento"));
        pessoaDto = new PessoaDto(nome, sobrenome, dataNascimento);
        pessoaDto.setId(1L);
        pessoa = new Pessoa();
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaMapper.toEntity(pessoaDto));
        pessoa = pessoaMapper.toEntity(this.pessoaController.salvarPessoa(this.pessoaDto).getBody());
    }

    @When("^o endpoint /pessoas/id é chamado")
    public void buscarPessoa() {
        Long id = 1L;
        Optional<Pessoa> optPessoa = Optional.of(pessoa);
        when(pessoaRepository.findById(any(Long.class))).thenReturn(optPessoa);
        pessoaDto = this.pessoaController.buscarPessoa(id).getBody();
    }
    @Then("essa pessoa deve ser buscada")
    public void essaPessoaDeveSerBuscada() {
        assertNotNull(pessoaDto);
    }

//    Scenario: Buscar várias pessoas
    List<Pessoa> listaPessoas = new ArrayList<>();
    List<PessoaDto> listaPessoasDto = new ArrayList<>();
    @Given("o usuário deseja buscar uma lista de pessoas")
    public void configurarPessoas(DataTable pessoasAdicionadas) {
        List<Map<String, String>> dadosPessoas = pessoasAdicionadas.asMaps(String.class, String.class);
        for (Map<String, String> dadosPessoa : dadosPessoas){
            PessoaDto pessoaLista = new PessoaDto(dadosPessoa.get("nome"), dadosPessoa.get("sobrenome"), LocalDate.parse(dadosPessoa.get("dataNascimento")));
            listaPessoasDto.add(pessoaLista);
            listaPessoas.add(pessoaMapper.toEntity(pessoaLista));
        }
    }

    List<PessoaDto> pessoasSalvas;
    @When("^o endpoint /pessoas/getall é chamado")
    public void buscarListaPessoa() {
        when(pessoaRepository.findAll()).thenReturn(listaPessoas);
        pessoasSalvas = this.pessoaController.buscarTodasPessoas().getBody();

    }
    @Then("a lista de pessoas adicionadas deve ser chamada")
    public void pessoasDevemSerBuscadas() {
        assertEquals(pessoasSalvas.size(), 5);
    }

//    Scenario: Determinar um endereço como principal para uma pessoa
    Endereco enderecoPrincipal = new Endereco();
    Endereco novoEnderecoPrincipal = new Endereco();
    List<Endereco> enderecos =  mockEnderecos();
    @Given("o usuário definir um endereço de uma pessoa como principal")
    public void escolherEnderecoPrincipal(){
        for (Endereco endereco: enderecos) {
            if(endereco.getPrincipal()){
                enderecoPrincipal = endereco;
            }
        }
    }
    @When("^o endpoint enderecos/principal/id/pessoaId é chamado")
    public void alterarEnderecoPrincipal(){
        when(this.enderecoRepository.findAllByPessoaId(any(Long.class))).thenReturn(enderecos);
        when(this.enderecoRepository.save(any(Endereco.class))).thenReturn(any(Endereco.class));
        novoEnderecoPrincipal = this.enderecoMapper.toEntity(this.enderecoController.definirComoPrincipal(2L, 1L).getBody());
//        System.out.println(novoEnderecoPrincipal.getCidade());
    }
    @Then("o endereço principal dessa pessoa é alterado")
    public void verificaNovoEnderecoPrincipal(){
//        assertNotEquals(enderecoPrincipal.toString(), novoEnderecoPrincipal.toString());
        assertTrue(novoEnderecoPrincipal.getPrincipal());
        assertFalse(enderecoPrincipal.getPrincipal());
    }


    private List<EnderecoDto> mockEnderecosDto(){
        List<EnderecoDto> enderecosDto = new ArrayList<>();
        pessoaDto = new PessoaDto(1L,"João", "José", LocalDate.now());
        pessoa = pessoaMapper.toEntity(pessoaDto);
//        System.out.println(pessoa.getNome());

        enderecosDto.add(new EnderecoDto(1L, true, pessoa, "SP", "São Paulo", "01000-000", "Rua A", "123"));
        enderecosDto.add(new EnderecoDto(2L, false, pessoa, "RJ", "Rio de Janeiro", "20000-000", "Rua B", "456"));
        enderecosDto.add(new EnderecoDto(3L, false, pessoa, "MG", "Belo Horizonte", "30000-000", "Rua C", "789"));
        return enderecosDto;
    }

    private List<Endereco> mockEnderecos(){
        List<Endereco> enderecos = new ArrayList<>();
        List<EnderecoDto> enderecosDtos = mockEnderecosDto();
        Endereco endereco = new Endereco();
        for (EnderecoDto enderecoDto: enderecosDtos) {
            endereco = this.enderecoMapper.toEntity(enderecoDto);
            endereco.setId(enderecoDto.getId());
            enderecos.add(endereco);
        }
        return enderecos;
    }

}


