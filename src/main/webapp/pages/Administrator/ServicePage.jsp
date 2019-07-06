<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>Service</title>
</head>
<body>
<table border="2">
    <tr>
        <th>Name of Service</th>
        <th>Measure</th>
        <th>Price</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="work" items="${requestScope.list}">
        <tr id="${work.id}">
            <td>${work.name}</td>
            <td>${work.measure}</td>
            <td>${work.price}</td>
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
