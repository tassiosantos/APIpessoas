package com.api.apipessoa.services;

import com.api.apipessoa.mappers.PessoaMapper;
import com.api.apipessoa.mappers.PessoaMapperImpl;
import com.api.apipessoa.models.Pessoa;
import com.api.apipessoa.dtos.PessoaDto;
import com.api.apipessoa.repositories.PessoaRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaMapper pessoaMapper;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = Mappers.getMapper(PessoaMapper.class);
    }


    public PessoaDto addPerson(PessoaDto pessoaDto){
        return pessoaMapper.toDto(pessoaRepository.save(pessoaMapper.toEntity(pessoaDto)));
    }

    public PessoaDto updatePerson(PessoaDto pessoaEditada, Long id){
        Pessoa pessoa = this.pessoaRepository.findById(id).get();
        pessoa = this.pessoaMapper.toEntity(pessoaEditada);
        return this.pessoaMapper.toDto(pessoaRepository.save(pessoa));
    }

    public PessoaDto getPerson(Long id){
        Optional<Pessoa> optPessoa = pessoaRepository.findById(id);
        return pessoaMapper.toDto(optPessoa.get());
    }

    public List<PessoaDto> getAll() {
        List<PessoaDto> listaPessoaDto = new ArrayList<>();
        List<Pessoa> listaPessoas = pessoaRepository.findAll();
        for (Pessoa pessoa: listaPessoas) {
            listaPessoaDto.add(pessoaMapper.toDto(pessoa));
        }
        return listaPessoaDto;
    }
}
