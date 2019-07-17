<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Service</title>
    <script src="/Raiden_war/js/Adding/InternetAdding.js"></script>
    <link rel="stylesheet" href="/Raiden_war/css/Adding.css"/>
</head>
<body>

<header>
    <span class="pageTitle">Updating Service</span>
    <hr class="line" size="3" color="brown"/>
</header>

<form method="POST" action="/Raiden_war/administrator/service/updating">
    <input type="number" name="id" value="${param.id}" style="visibility: hidden;"/>
    <table>
        <tr>
            <td>Name:</td>
            <td>
                <input type="text" name="name" value="${param.name}"/>
            </td>
        </tr>
        <tr>
            <td>Measure:</td>
            <td>
                <input type="text" name="measure" value="${param.measure}"/>
            </td>
        </tr>
        <tr>
            <td>Price(₴):</td>
            <td><input type="number" id="price" name="price" onchange="checkValue()" min="0" step="0.01" value="${param.price}"/></td>
        </tr>
    </table>
    <input type="submit" value="Update" id="submit"/>
</form>
</body>
</html>