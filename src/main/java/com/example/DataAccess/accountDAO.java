package com.example.DataAccess;

import com.example.model.Cliente;
import com.example.model.Cuenta;

import java.util.List;

public interface accountDAO {

    public Cuenta getAccounts(String id);
    public List<Cuenta> getAccountsByCli(Cliente cli);
    public Cuenta createaccount(String idFi,String name, String email, String pais, String IBAN, double balance);
}
