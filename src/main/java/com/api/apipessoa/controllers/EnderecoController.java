package com.api.apipessoa.controllers;

import com.api.apipessoa.dtos.EnderecoDto;
import com.api.apipessoa.services.EnderecoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService){this.enderecoService = enderecoService;}

    @PostMapping("adicionar/{id}")
    public ResponseEntity<EnderecoDto> salvarEndereco(@RequestBody EnderecoDto enderecoDto, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.addAdress(enderecoDto, id));
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<EnderecoDto> editarEndereco(@RequestBody EnderecoDto enderecoDto, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.updateAdress(enderecoDto, id));
    }

    @GetMapping("buscar/{id}")
    public  ResponseEntity<EnderecoDto> buscarEndereco(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK).body(enderecoService.getAdress(id));
    }

    @GetMapping("buscar/getall/{pessoaId}")
    public ResponseEntity<List<EnderecoDto>> buscarTodosEnderecosPessoa(@PathVariable Long pessoaId){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.getAll(pessoaId));
    }

    @PutMapping("princial/{id}/{pessoaId}")
    public ResponseEntity<EnderecoDto> definirComoPrincipal(@PathVariable Long id, @PathVariable Long pessoaId){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.setMainAdress(id, pessoaId));
    }



}
