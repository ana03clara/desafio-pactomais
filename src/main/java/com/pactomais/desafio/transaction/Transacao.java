package com.pactomais.desafio.transaction;

import com.pactomais.desafio.entity.Conta;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transacao")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private Double valor;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public Transacao(){
    }

    public Transacao(String tipo, Double valor, LocalDateTime data, Conta conta){
        this.tipo = tipo;
        this.valor = valor;
        this.data =  data;
        this.conta = conta;
    }
    public Long getId(){
        return id;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public Double getValor(){
        return valor;
    }

    public void setValor(Double valor){
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
