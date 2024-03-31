package com.api.apipessoa.mappers;

import com.api.apipessoa.dtos.EnderecoDto;
import com.api.apipessoa.models.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnderecoMapper {

    EnderecoDto toDto(Endereco endereco);
    Endereco toEntity(EnderecoDto enderecoDto);
}
