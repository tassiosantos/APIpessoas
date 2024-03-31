package com.api.apipessoa.models;

import com.api.apipessoa.dtos.EnderecoDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Entity
public class Endereco {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "principal")
    private Boolean principal;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    protected Pessoa pessoa;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;

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
