package com.pactomais.desafio.controller;

import  com.pactomais.desafio.entity.Correntista;
import com.pactomais.desafio.service.CorrentistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/correntista")

public class CorrentistaController {
    private final CorrentistaService service;

    public CorrentistaController(CorrentistaService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Correntista> salvar(@RequestBody Correntista correntista) {
        Correntista salvo= service.salvar(correntista);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public List<Correntista>listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Correntista> buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
