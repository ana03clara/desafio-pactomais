package com.pactomais.desafio.entity;

import javax.persistence.Entity;

@Entity
public class ContaPoupanca extends Conta {
    public ContaPoupanca(){
    }

    public ContaPoupanca(String numero, Double saldo){
        super(numero, saldo);
    }

}
