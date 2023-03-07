package com.example.DataAccess;

import com.example.model.Cliente;
import com.example.model.Cuenta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class accountDAOimpl implements accountDAO {

    private SessionFactory sesion1;
    private clientDAO cliDAO;

    public accountDAOimpl(SessionFactory sesion1) {
        this.sesion1 = sesion1;
        cliDAO = new clientDAOimpl(sesion1);
    }
    public Cuenta getAccounts(String id) {
        Session sesion = sesion1.getCurrentSession();
        sesion.beginTransaction();
        Cuenta account = sesion.find(Cuenta.class,id);
        sesion.getTransaction().commit();
        return account;
    }

    public List<Cuenta> getAccountsByCli(Cliente cli) {
        Session sesion = sesion1.getCurrentSession();
        sesion.beginTransaction();
        System.out.println(cli.toString());
        Query q = sesion.createQuery("from Cuenta where cliente=:cli");
        q.setParameter("cli",cli);
        List<Cuenta> accounts = q.getResultList();
        sesion.getTransaction().commit();

        return accounts;
    }


    public Cuenta createaccount(String idFi,String name, String email, String pais, String IBAN, double balance) {
        Cuenta acc = new Cuenta();
        acc.setAccountIBAN(IBAN);
        acc.setSaldoCuenta(balance);
        Cliente c = cliDAO.CheckCliente(idFi,name,email,pais);
        acc.setCliente(c);
        Session sesion = sesion1.getCurrentSession();
        sesion.beginTransaction();
        sesion.persist(acc);
        sesion.getTransaction().commit();

        return acc;
    }
}
