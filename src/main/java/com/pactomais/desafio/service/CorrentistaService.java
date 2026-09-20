package com.pactomais.desafio.service;

import com.pactomais.desafio.entity.Correntista;
import com.pactomais.desafio.repository.CorrentistaRepository;
import  org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CorrentistaService {
    private final CorrentistaRepository repository;

    public CorrentistaService(CorrentistaRepository repository){
            this.repository = repository;
        }

    public Correntista salvar(Correntista correntista){
        return repository.save(correntista);
    }

    public List<Correntista>listar(){
        return repository.findAll();
    }

    public Optional<Correntista>buscarPorId(Long id){
        return repository.findById(id);
    }

    public void excluir(Long id){
        repository.deleteById(id);
    }
}
