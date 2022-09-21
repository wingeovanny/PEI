package com.challenge.entity;

import javax.persistence.*;

@Entity
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuentaId")
    @SequenceGenerator(name = "cuentaId", initialValue = 1, allocationSize = 100000)
    private long id;

    private String banco;

    private double saldo;

    public Cuenta() {
    }

    public Cuenta(String banco, double saldo) {
        this.banco = banco;
        this.saldo = saldo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
