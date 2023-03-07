package com.example.DataAccess;

import com.example.model.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class clientDAOimpl implements clientDAO {

    private SessionFactory sesion1;

    public clientDAOimpl(SessionFactory sesion1) {
        this.sesion1 = sesion1;
    }

    public Cliente getCliente(String idFi) {
        Session sesion=sesion1.getCurrentSession();
        sesion.beginTransaction();
        String s1 = "from Cliente c where c.idFiscal =:idFi";
        Query q1 = sesion.createQuery(s1);
        q1.setParameter("idFi",idFi);
        Cliente cli = (Cliente) q1.uniqueResult();
        sesion.getTransaction().commit();
        return cli;
    }

    public Cliente addCliente(String idFi, String name, String email, String pais) {
          Session sesion=sesion1.getCurrentSession();
          sesion.beginTransaction();
          Cliente cli = new Cliente(name,idFi,email,pais);
          sesion.persist(cli);
          sesion.getTransaction().commit();
        return cli;
    }

    public Cliente CheckCliente(String idFi, String name, String email, String pais) {
        Cliente cli = getCliente(idFi);
        if (cli == null) {
            cli = addCliente(idFi,name,email,pais);
        }
        return cli;
    }

}
