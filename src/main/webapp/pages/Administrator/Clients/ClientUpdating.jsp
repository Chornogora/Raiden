<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Internet</title>
    <script src="/Raiden_war/js/Client/Client.js"></script>
    <link rel="stylesheet" href="/Raiden_war/css/Adding.css"/>
</head>
<body>

<header>
    <span class="pageTitle">Updating Client</span>
    <hr class="line" size="3" color="brown"/>
</header>

<form>
    <input type="number" name="client_id" value="${param.id}" style="visibility: hidden"/>
    <table>
        <tr>
            <td>Last Name:</td>
            <td>
                <input type="text" name="LastName" minlength="2" pattern="\w{2,}" value="${param.LastName}"/>
            </td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td>
                <input type="text" name="FirstName" minlength="2" pattern="\w{2,}" value="${param.FirstName}"/>
            </td>
        </tr>
        <tr>
            <td>Passport Series:</td>
            <td>
                <input type="text" name="Series" maxlength="2" pattern="[A-Z]{,2}" value="${param.Series}"/>
            </td>
        </tr>
        <tr>
            <td>Passport Number:</td>
            <td><input type="number" name="Number" minlength="6" maxlength="9" value="${param.Number}"/></td>
        </tr>
        <tr>
            <td>Phone Number:</td>
            <td><input type="number" name="Phone" minlength="11" maxlength="13" value="${param.phone}"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="email" name="Email" value="${param.email}"/></td>
        </tr>
    </table>
    <input type="submit" value="Register" onclick="update()"/>
</form>
</body>
</html>