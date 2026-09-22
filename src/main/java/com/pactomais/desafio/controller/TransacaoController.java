package com.pactomais.desafio.controller;

import com.pactomais.desafio.service.TransacaoService;
import com.pactomais.desafio.transaction.Transacao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transacao> listar() {
        return service.listar();
    }

    @GetMapping("/conta/{contaId}")
    public List<Transacao> listarPorConta(@PathVariable Long contaId) {
        return service.listarPorConta(contaId);
    }
}