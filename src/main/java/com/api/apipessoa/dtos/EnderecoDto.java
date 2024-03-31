package com.api.apipessoa.dtos;

import com.api.apipessoa.models.Pessoa;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnderecoDto {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    @NotEmpty
    private Boolean principal;


    @NotNull
    @NotEmpty
    protected Pessoa pessoa;

    @NotNull
    @NotEmpty
    private String estado;

    @NotNull
    @NotEmpty
    private String cidade;

    @NotNull
    @NotEmpty
    private String cep;

    @NotNull
    @NotEmpty
    private String logradouro;

    @NotNull
    @NotEmpty
    private String numero;

    public EnderecoDto(Long id, Boolean principal, Pessoa pessoa, String estado, String cidade, String cep, String logradouro, String numero) {
        this.id = id;
        this.principal = principal;
        this.pessoa = pessoa;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
    }

    public EnderecoDto() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id){this.id = id;}

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    @Override
    public String toString() {
        return "Endereco [id=" + this.id + ", pessoa=" + this.pessoa.getNome() + ", logradouro=" + this.logradouro + ", cep=" + this.cep
                + ", numero=" + this.numero + ", cidade=" + this.cidade + " estado = " + this.estado + "]";
    }

}
