<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Television</title>
    <script src="/Raiden_war/js/Adding/TelevisionAdding.js"></script>
    <link rel="stylesheet" href="/Raiden_war/css/Adding.css"/>
</head>
<body>

<header>
    <span class="pageTitle">Updating Television Tariff</span>
    <hr class="line" size="3" color="brown"/>
</header>


<form method="POST" action="/Raiden_war/administrator/television/updating">
    <input type="number" name="id" value="${param.id}" style="visibility: hidden;"/>
    <table>
        <tr>
            <td>Name:</td>
            <td>
                <input type="text" name="name" value="${param.name}"/>
            </td>
        </tr>
        <tr>
            <td>Channels:</td>
            <td>
                <input type="number" id="channels" name="channels" onchange="checkValue()" value="${param.channels}"/></td>
            </td>
        </tr>
        <tr>
            <td>Format:</td>
            <td>
                <input type="text" name="format" value="${param.format}" />
            </td>
        </tr>
        <tr>
            <td>Price per month(₴):</td>
            <td><input type="number" id="price" name="price" onchange="checkValue()" step="0.01" min="0" value="${param.price}"/></td>
        </tr>
    </table>
    <input type="submit" value="Update" id="submit"/>
</form>
</body>
</html>