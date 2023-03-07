package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name="Cuentas")
public class Cuenta {
    @Id
    private String accountIBAN;
    @Column
    private double saldoCuenta;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;


    public String getAccountIBAN() {
        return accountIBAN;
    }

    public void setAccountIBAN(String accountIBAN) {
        this.accountIBAN = accountIBAN;
    }

    public double getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setSaldoCuenta(double saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cuenta() {
    }

    public Cuenta(Cliente cliente,String accountIBAN, double saldoCuenta) {
        this.cliente = cliente;
        this.accountIBAN = accountIBAN;
        this.saldoCuenta = saldoCuenta;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "accountIBAN='" + accountIBAN + '\'' +
                ", saldoCuenta=" + saldoCuenta +
                ", cliente=" + cliente +
                '}';
    }
}
