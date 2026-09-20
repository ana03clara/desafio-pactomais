package com.pactomais.desafio.entity;

import javax.persistence.*;

@Entity
@Table(name = "conta")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private Double saldo;

    @ManyToOne
    @JoinColumn(name = "correntista_id")
    private Correntista correntista;

    public Conta() {
    }

    public Conta(String numero, Double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public Correntista getCorrentista() {
        return correntista;
    }

    public void setCorrentista(Correntista correntista) {
        this.correntista = correntista;
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }


}