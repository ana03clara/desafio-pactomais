package com.pactomais.desafio.entity;

import javax.persistence.Entity;

@Entity
public class ContaCorrente extends Conta {
    private Double limite;

    public ContaCorrente(){
    }

    public ContaCorrente(String numero, Double saldo,Double limite){
        super(numero, saldo);
        this.limite = limite;
    }

    public Double getLimite(){
        return limite;
    }

    public void setLimite(Double limite){
        this.limite = limite;
    }
}
