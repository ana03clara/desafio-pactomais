package com.pactomais.desafio.repository;

import com.pactomais.desafio.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    List<Conta>findByCorrentistaId(Long correntistaId);
}
