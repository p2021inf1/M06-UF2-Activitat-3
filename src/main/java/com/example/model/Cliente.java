package com.example.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idCliente;
    @Column
    private String nameCli;
    @NaturalId
    private String idFiscal;
    @Column
    private String emailCli;
    @Column
    private String paisCli;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
        private Set<Cuenta> cuentas;

    public Cliente() {
        this.cuentas = new HashSet<>();
    }
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdFiscal() {
        return idFiscal;
    }

    public void setIdFiscal(String idFiscal) {
        this.idFiscal = idFiscal;
    }

    public String getEmailCli() {
        return emailCli;
    }

    public void setEmailCli(String emailCli) {
        this.emailCli = emailCli;
    }

    public String getPaisCli() {
        return paisCli;
    }

    public void setPaisCli(String paisCli) {
        this.paisCli = paisCli;
    }

    public String getNameCli() {
        return nameCli;
    }

    public void setNameCli(String nameCli) {
        this.nameCli = nameCli;
    }

    public Set<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(Set<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public Cliente(String nameCli, String idFiscal, String emailCli, String paisCli) {
        this.nameCli = nameCli;
        this.idFiscal = idFiscal;
        this.emailCli = emailCli;
        this.paisCli = paisCli;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nameCli='" + nameCli + '\'' +
                ", idFiscal='" + idFiscal + '\'' +
                ", emailCli='" + emailCli + '\'' +
                ", paisCli='" + paisCli + '\'' +
                '}';
    }
}
