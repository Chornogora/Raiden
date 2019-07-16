<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>Television</title>
    <script src="/Raiden_war/js/WorkplaceFrame/Television.js"></script>
</head>
<body>
<table border="2">
    <tr>
        <th onclick="sortByName()">Name of Tariff</th>
        <th>Number of Channels</th>
        <th>Format</th>
        <th onclick="sortByPrice()">Month price</th>
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
                <button onclick="sendUpdateEvent(${television.id})">Edit</button>
            </td>
            <td>
                <button onclick="sendDeleteEvent(${television.id})">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<button onclick="sendAddEvent()">Add new Television Tariff</button>
</body>
</html>
