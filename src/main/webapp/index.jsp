<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>DAM2 Bank</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Banco DAM2</h1>
</div>
<div align="center">
            <table border="1" cellpadding="5">
                <form action="create" method="post" style="display:inline">
                <tr>
                    <th>Nombre Cliente: </th>
                    <td>
                        <input type="text" name="Nombre Cliente" size="150"/>
                    </td>
                </tr>
                <tr>
                    <th>Id Fiscal (DNI, NIE): </th>
                    <td>
                        <input type="text" name="Id Fiscal" size="9"/>
                    </td>
                </tr>
                <tr>
                    <th>Email cliente: </th>
                    <td>
                        <input type="text" name="Email Cliente" size="45"/>
                    </td>
                </tr>
                <tr>
                    <th>Pais: </th>
                    <td>
                        <input type="text" name="Pais" size="15"/>
                    </td>
                </tr>
                <tr>
                    <th>Cuenta: </th>
                    <td>
                        <input type="text" name="IBAN Cliente" size="24"/>
                    </td>
                </tr>
                <tr>
                    <th>Ingreso inicial (€): </th>
                    <td>
                        <input type="number" name="Ingreso inicial" size="24"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                                <button class="submitC" type="submit" value="Crear cliente" name="create"/>Crear cliente</button>
                    </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                                <button class="submitL" type="submit" value="Mostrar clientes" name="look" />Mostrar clientes</button>
                        </td>
                    </tr>
                </form>
            </table>
</div>
</body>
</html>
