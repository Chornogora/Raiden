<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>Service</title>
    <script src="/Raiden_war/js/WorkplaceFrame/Service.js"></script>
</head>
<body>
<table border="2">
    <tr>
        <th onclick="sortByName()">Name of Service</th>
        <th>Measure</th>
        <th onclick="sortByPrice()">Price</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="work" items="${requestScope.list}">
        <tr id="${work.id}">
            <td>${work.name}</td>
            <td>${work.measure}</td>
            <td>${work.price}</td>
            <td>
                <button onclick="sendUpdateEvent(${work.id})">Edit</button>
            </td>
            <td>
                <button onclick="sendDeleteEvent(${work.id})">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<button onclick="sendAddEvent()">Add new Service</button>

</body>
</html>
