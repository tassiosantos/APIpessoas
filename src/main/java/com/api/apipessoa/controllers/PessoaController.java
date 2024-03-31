package com.api.apipessoa.controllers;

import com.api.apipessoa.dtos.PessoaDto;
import com.api.apipessoa.services.PessoaService;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("adicionar")
    public ResponseEntity<PessoaDto> salvarPessoa(@RequestBody PessoaDto pessoaDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.addPerson(pessoaDto));
    }

    @PutMapping("editar/id")
    public ResponseEntity<PessoaDto> editarPessoa(@RequestBody PessoaDto pessoaEditada, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.updatePerson(pessoaEditada, id));

    }


    @GetMapping("buscar/{id}")
    public ResponseEntity<PessoaDto> buscarPessoa(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getPerson(id));
    }

    @GetMapping("buscar/getall")
    public ResponseEntity<List<PessoaDto>> buscarTodasPessoas() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getAll());
    }
}
