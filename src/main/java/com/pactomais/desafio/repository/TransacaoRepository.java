package com.pactomais.desafio.repository;

import com.pactomais.desafio.transaction.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao>findByContaId(Long contaId);
}
