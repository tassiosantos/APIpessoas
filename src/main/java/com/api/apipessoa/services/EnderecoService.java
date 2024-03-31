package com.api.apipessoa.services;

import com.api.apipessoa.dtos.EnderecoDto;
import com.api.apipessoa.mappers.EnderecoMapper;
import com.api.apipessoa.mappers.PessoaMapper;
import com.api.apipessoa.models.Endereco;
import com.api.apipessoa.repositories.EnderecoRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
    private PessoaMapper pessoaMapper;

    @Autowired
    private PessoaService pessoaService;

    public EnderecoService(EnderecoRepository enderecoRepository, PessoaService pessoaService) {
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = Mappers.getMapper(EnderecoMapper.class);
        this.pessoaMapper = Mappers.getMapper(PessoaMapper.class);
        this.pessoaService = pessoaService;
    }

    public EnderecoDto addAdress(EnderecoDto enderecoDto, Long pessoaId) {

        enderecoDto.setPessoa(this.pessoaMapper.toEntity(this.pessoaService.getPerson(pessoaId)));
        return this.enderecoMapper.toDto(this.enderecoRepository.save(this.enderecoMapper.toEntity(enderecoDto)));
    }

    public EnderecoDto updateAdress(EnderecoDto enderecoDto, Long id) {
        Endereco endereco = this.enderecoRepository.findById(id).get();
        endereco = this.enderecoMapper.toEntity(enderecoDto);
        return this.enderecoMapper.toDto(enderecoRepository.save(endereco));
    }

    public EnderecoDto getAdress(Long id) {
        Optional<Endereco> optEndreco = enderecoRepository.findById(id);
        return enderecoMapper.toDto(optEndreco.get());
    }

    public List<EnderecoDto> getAll(Long pessoaId) {
        List< Endereco> listaEnderecos = this.enderecoRepository.findAllByPessoaId(pessoaId);
        return getDtoList(listaEnderecos);
    }

    public EnderecoDto setMainAdress(Long id, Long pessoaId) {
        List< Endereco> listaEndereco = this.enderecoRepository.findAllByPessoaId(pessoaId);
        EnderecoDto enderecoPrincipalDto = new EnderecoDto();
        for (Endereco endereco: listaEndereco) {
            System.out.println(endereco.getId());
            if(endereco.getId() == id){

                endereco.setPrincipal(true);
                this.enderecoRepository.save(endereco);
                enderecoPrincipalDto = this.enderecoMapper.toDto(endereco);
            }else{
                endereco.setPrincipal(false);
                this.enderecoRepository.save(endereco);
            }
        }
        return enderecoPrincipalDto;
    }

    private List<EnderecoDto> getDtoList(List<Endereco> enderecos){
        List<EnderecoDto> listaEnderecoDto = new ArrayList<>();
        for (Endereco endereco: enderecos) {
            listaEnderecoDto.add(this.enderecoMapper.toDto(endereco));

        }
        return listaEnderecoDto;
    }



}
