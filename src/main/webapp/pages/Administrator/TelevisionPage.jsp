<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>Television</title>
</head>
<body>
<table border="2">
    <tr>
        <th>Name of Tariff</th>
        <th>Number of Channels</th>
        <th>Format</th>
        <th>Month price</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="television" items="${requestScope.list}">
        <tr id="${television.id}">
            <td>${television.name}</td>
            <td>${television.channels}</td>
            <td>${television.format}</td>
            <td>${television.monthPrice}</td>
            <td>
                <button>Edit</button>
            </td>
            <td>
                <button>Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
