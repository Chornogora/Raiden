<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>Phone</title>
</head>
<body>
<table border="2">
    <tr>
        <th>Name of Tariff</th>
        <th>Minutes for mobile operators</th>
        <th>Month Price(₴)</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="phone" items="${requestScope.list}">
        <tr id="${phone.id}">
            <td>${phone.name}</td>
            <td>${phone.mobileMinutes}</td>
            <td>${phone.monthPrice}</td>
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