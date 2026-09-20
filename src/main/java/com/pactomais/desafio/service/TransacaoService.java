package com.pactomais.desafio.service;

import com.pactomais.desafio.transaction.Transacao;
import com.pactomais.desafio.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {
    private final TransacaoRepository repository;

    public TransacaoService(TransacaoRepository repository){
        this.repository= repository;
    }

    public Transacao salvar(Transacao transacao){
        return repository.save(transacao);
    }

    public List<Transacao> listar(){
        return repository.findAll();
    }
    public List<Transacao>listarPorConta(Long contaId){
        return repository.findByContaId(contaId);
    }

}
