package com.example.controller;

import java.io.*;
import java.util.List;

import com.example.DataAccess.accountDAO;
import com.example.DataAccess.accountDAOimpl;
import com.example.DataAccess.clientDAO;
import com.example.DataAccess.clientDAOimpl;
import com.example.model.Cliente;
import com.example.model.Cuenta;
import com.mysql.cj.util.StringUtils;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.SessionFactory;

@WebServlet(name = "Create Customer", value = "/create")
public class CreateCustomer extends HttpServlet {
    private String hdr1;
    private String hdr2;
    private String message;
    private accountDAO accDAO;
    private String customer;

    public CreateCustomer() {}

    public void init() {

        hdr1 = "Banco DAM2";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idFiscal = request.getParameter("Id Fiscal");
        String cuentaCli = request.getParameter("IBAN Cliente");

        String id;

        SessionFactory sf = HibernateUtil.getSessionFactory();

        if (request.getParameter("look") != null &&
            request.getParameter("look").equals("Mostrar clientes")) {
            accountDAO accDAO = new accountDAOimpl(sf);
            clientDAO cliDAO = new clientDAOimpl(sf);
            if (!cuentaCli.isEmpty()) {
                hdr2 = "Busqueda por cuenta de cliente";
                id = cuentaCli;
                if (id.isEmpty()) {
                    hdr2 = "Error de validacion";
                    message = "ID Fiscal o cuenta formato IBAN no informados";
                    doResponse(response,hdr1,hdr2,message);
                } else {
                    Cuenta acc = accDAO.getAccounts(id);
                    if (acc == null) {
                        message = "No existe esta cuenta de cliente";
                    } else {
                        message = "ID Fiscal: " + acc.getCliente().getIdFiscal() + " , " +
                                  "Nombre: " + acc.getCliente().getNameCli() + " , " +
                                "Cuenta IBAN: " + acc.getAccountIBAN() + " , " +
                                  "Saldo (euros): " + acc.getSaldoCuenta() + " ";
                    }

                    doResponse(response,hdr1,hdr2,message);
                }

            } else {
                hdr2 = "Busqueda de cuentas de un cliente";
                id = idFiscal;

                if (id.isEmpty()) {
                    hdr2 = "Error de validacion";
                    message = "ID Fiscal o cuenta formato IBAN no informados";
                    doResponse(response,hdr1,hdr2,message);
                } else {
                    Cliente cli = cliDAO.getCliente(id);
                    List<Cuenta> accs = accDAO.getAccountsByCli(cli);

                    if (accs == null) {
                        message = "No existe esta cuenta de cliente";
                        doResponse(response,hdr1,hdr2,message);
                    } else {
                        doTable(response,hdr1,hdr2,accs,idFiscal,cliDAO);
                    }
                }
            }
          } else {
            String name = request.getParameter("Nombre Cliente");
            String EmailCli = request.getParameter("Email Cliente");
            String paisCli = request.getParameter("Pais");
            String ingresoCli = request.getParameter("Ingreso inicial");

            if (idFiscal.isEmpty() || cuentaCli.isEmpty() || name.isEmpty()
                    || EmailCli.isEmpty() || paisCli.isEmpty() || ingresoCli.isEmpty()) {
                hdr2 = "Error de validacion";
                message = "Faltan datos para abrir la cuenta";
            } else {

                if (!StringUtils.isStrictlyNumeric(ingresoCli)) {
                    hdr2 = "Error de validacion";
                    message = "El saldo no es numerico";
                } else {
                    double dingresoCli = Double.parseDouble(ingresoCli);
                    accountDAO accDAO = new accountDAOimpl(sf);
                    Cuenta numCue = accDAO.getAccounts(cuentaCli);
                    if (numCue == null) {
                        Cuenta account = accDAO.createaccount(idFiscal, name, EmailCli, paisCli,cuentaCli,dingresoCli);
                        hdr2 = "Alta de client@ y cuenta nueva";
                        message = "Client@ creado con exito >>>> " +
                                " ID Fiscal: " + account.getCliente().getIdFiscal() +
                                " , IBAN cuenta: " + account.getAccountIBAN();
                    } else {
                        hdr2 = "Alta de client@ y cuenta nueva";
                        message = "Cuenta de cliente pertenece a otro cliente";
                    }
                }
            }
            doResponse(response,hdr1,hdr2,message);
        }
    }

    public void doResponse(HttpServletResponse response, String hdr1, String hdr2, String message) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<p align=\"center\"> </p>");
        out.println("<p align=\"center\"> </p>");
        out.println("<div align=\"center\">");
        out.println("<link rel=\"stylesheet\" href=\"global.css\">");
        out.println("<table border=\"1\" align=center>");
        out.println("<html><body>");
        out.println("<h1>" + hdr1 + "</h1>");
        out.println("<h2>" + hdr2 + "</h2>");
        out.println(message);
        out.println("</br>");
        out.print("<br><a href=\"index.jsp\"><button type=\"button\">Pagina principal</button></a>");
        out.println("</body></html>");
        out.println("</table>");
        out.println("</center>");
        out.println("</div>");
    }

    public void doTable(HttpServletResponse response, String hdr1, String hdr2, List<Cuenta> accs,
                        String idFiscal, clientDAO cliDAO) throws IOException {
        Cliente cli = cliDAO.getCliente(idFiscal);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<p align=\"center\"> </p>");
        out.println("<p align=\"center\"> </p>");
        out.println("<div align=\"center\">");
        out.println("<link rel=\"stylesheet\" href=\"global.css\">");
        out.println("<table border=\"1\" align=center>");
        out.println("<html><body>");
        out.println("<h1>" + hdr1 + "</h1>");
        out.println("<h2>" + hdr2 + "</h2>");
        out.println("<h3>" + "Cliente: " + idFiscal + " - " + cli.getNameCli() + "</h3>");
        out.println ("<table>");
        out.println ("<tbody>");
        for (Cuenta acc : accs) {
            out.println("<tr>" +
                    "<td>"                  +
                    "cuenta IBAN: "         +
                    acc.getAccountIBAN() 	+
                    ", saldo (euros): "     +
                    acc.getSaldoCuenta()    +
                    "</td>"                 +
                    "</tr>");
        }
        out.println ("</table>");
        out.println ("</tbody>");
        out.println("</br>");
        out.print("<br><a href=\"index.jsp\"><button type=\"button\">Pagina principal</button></a>");
        out.println("</table>");
        out.println("</center>");
        out.println("</div>");
        out.println("</body></html>");
    }

    public void destroy() {

    }
}