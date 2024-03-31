package com.api.apipessoa.repositories;

import com.api.apipessoa.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {


    List<Endereco> findAllByPessoaId(Long pessoaId);
}
