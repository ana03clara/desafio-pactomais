package com.pactomais.desafio.repository;

import com.pactomais.desafio.entity.Correntista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrentistaRepository extends JpaRepository<Correntista, Long> {
}
