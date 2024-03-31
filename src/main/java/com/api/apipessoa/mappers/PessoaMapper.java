package com.api.apipessoa.mappers;


import com.api.apipessoa.dtos.PessoaDto;
import com.api.apipessoa.models.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PessoaMapper {

    PessoaDto toDto(Pessoa pessoa);

    Pessoa toEntity(PessoaDto pessoaDto);

}
