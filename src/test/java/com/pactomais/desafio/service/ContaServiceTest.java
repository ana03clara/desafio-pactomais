package com.pactomais.desafio.service;

import com.pactomais.desafio.entity.Conta;
import com.pactomais.desafio.repository.ContaRepository;
import com.pactomais.desafio.repository.CorrentistaRepository;
import com.pactomais.desafio.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.pactomais.desafio.exception.RegraNegocioException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @Mock
    private ContaRepository repository;

    @Mock
    private CorrentistaRepository correntistaRepository;

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private ContaService service;

    @Test
    void deveDepositarValorNaConta() {

        Conta conta = new Conta("001", 1000.0) {
        };

        when(repository.findById(1L)).thenReturn(Optional.of(conta));

        Conta resultado = service.depositar(1L, 200.0);

        assertEquals(1200.0, resultado.getSaldo());
    }

    @Test
    void deveSacarValorDaConta() {

        Conta conta = new Conta("001", 1000.0) {
        };

        when(repository.findById(1L)).thenReturn(Optional.of(conta));

        Conta resultado = service.sacar(1L, 300.0);

        assertEquals(700.0, resultado.getSaldo());
    }
    @Test
    void deveImpedirSaqueAcimaDoSaldoNaContaPoupanca() {

        Conta conta = new Conta("002", 500.0) {
        };

        when(repository.findById(2L)).thenReturn(Optional.of(conta));

        assertThrows(
                RegraNegocioException.class,
                () -> service.sacar(2L, 600.0)
        );
    }


}

