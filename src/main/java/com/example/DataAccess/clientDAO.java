package com.example.DataAccess;

import com.example.model.Cliente;

public interface clientDAO {


    public Cliente getCliente(String idFi);

    public Cliente CheckCliente(String idFi, String name, String email, String pais);

    public Cliente addCliente(String idFi, String name, String email, String pais);


}
