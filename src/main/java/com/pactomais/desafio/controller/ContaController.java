package com.pactomais.desafio.controller;

import com.pactomais.desafio.entity.Conta;
import com.pactomais.desafio.entity.ContaCorrente;
import com.pactomais.desafio.entity.ContaPoupanca;
import com.pactomais.desafio.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }

    @PostMapping("/corrente/{correntistaId}")
    public ResponseEntity<ContaCorrente> criarContaCorrente(
            @RequestBody ContaCorrente conta,
            @PathVariable Long correntistaId) {

        ContaCorrente criada = (ContaCorrente) service.salvar(conta, correntistaId);

        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @PostMapping("/poupanca/{correntistaId}")
    public ResponseEntity<ContaPoupanca> criarContaPoupanca(
            @RequestBody ContaPoupanca conta,
            @PathVariable Long correntistaId) {

        ContaPoupanca criada = (ContaPoupanca) service.salvar(conta, correntistaId);

        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping
    public List<Conta> listar() {
        return service.listar();
    }

    @GetMapping("/correntista/{correntistaId}")
    public List<Conta> listarPorCorrentista(@PathVariable Long correntistaId) {
        return service.listarPorCorrentista(correntistaId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{contaId}/deposito")
    public ResponseEntity<Conta> depositar(
            @PathVariable Long contaId,
            @RequestParam Double valor) {

        return ResponseEntity.ok(service.depositar(contaId, valor));
    }

    @PostMapping("/{contaId}/saque")
    public ResponseEntity<Conta> sacar(
            @PathVariable Long contaId,
            @RequestParam Double valor) {

        return ResponseEntity.ok(service.sacar(contaId, valor));
    }
}