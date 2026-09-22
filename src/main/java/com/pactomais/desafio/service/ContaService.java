package com.pactomais.desafio.service;

import com.pactomais.desafio.entity.Conta;
import com.pactomais.desafio.entity.Correntista;
import com.pactomais.desafio.entity.ContaCorrente;
import com.pactomais.desafio.repository.TransacaoRepository;
import com.pactomais.desafio.repository.CorrentistaRepository;
import com.pactomais.desafio.repository.ContaRepository;
import com.pactomais.desafio.transaction.Transacao;
import com.pactomais.desafio.exception.RegraNegocioException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository repository;
    private final CorrentistaRepository correntistaRepository;
    private final TransacaoRepository transacaoRepository;

    public ContaService(ContaRepository repository, CorrentistaRepository correntistaRepository, TransacaoRepository transacaoRepository){
        this.repository = repository;
        this.correntistaRepository = correntistaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public Conta salvar(Conta conta, Long correntistaId){
        Correntista correntista = correntistaRepository
                .findById(correntistaId)
                .orElseThrow(()-> new RegraNegocioException("Correntista nao encontrado"));
        conta.setCorrentista(correntista);
        return repository.save(conta);
    }

    public List<Conta> listar(){
        return repository.findAll();
    }
    public List<Conta> listarPorCorrentista(Long correntistaId){
        return repository.findByCorrentistaId(correntistaId);
    }

    public Optional<Conta>buscarPorId(Long id){
        return repository.findById(id);
    }

    public Conta depositar(Long contaId,Double valor){
        Conta conta = repository.findById(contaId)
                .orElseThrow(()-> new RegraNegocioException("Conta nao encontrada"));
        if (valor <=0){
            throw new RegraNegocioException("O valor do deposito deve ser maior que zero");
        }
        conta.setSaldo(conta.getSaldo()+valor);

        repository.save(conta);

        Transacao transacao = new Transacao(
                "DEPOSITO",
                valor,
                java.time.LocalDateTime.now(),
                conta
        );
        transacaoRepository.save(transacao);
        return conta;
    }

    public Conta sacar(Long contaId, Double valor){
        Conta conta = repository.findById(contaId)
                .orElseThrow(()->new RegraNegocioException("Conta nao encontrada"));

        if (valor <=0){
            throw new RegraNegocioException("O valor de saque deve ser maior que zero");
        }

        if (conta instanceof ContaCorrente){
            ContaCorrente contaCorrente = (ContaCorrente) conta;

            if (valor > conta.getSaldo() + contaCorrente.getLimite()){
                throw new RegraNegocioException("Saldo e limite insuficientes");
            }
        } else {
            if (valor > conta.getSaldo()){
                throw new RegraNegocioException("Saldo insuficiente");
            }
        }

        conta.setSaldo(conta.getSaldo()- valor);
        repository.save(conta);

        Transacao transacao = new Transacao(
                    "SAQUE",
                valor,
                java.time.LocalDateTime.now(),
                conta
                );

        transacaoRepository.save(transacao);

        return conta;

    }
}
