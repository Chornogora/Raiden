<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>Phone</title>
    <style>
    th:hover{
        cursor: pointer;
    }
    </style>
    <script src="/Raiden_war/js/WorkplaceFrame/Phone.js"></script>
</head>
    <body>
        <table border="2">
            <tr>
                <th onclick="sortByName()">Name of Tariff</th>
                <th>Minutes for mobile operators</th>
                <th onclick="sortByPrice()">Month Price(₴)</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="phone" items="${requestScope.list}">
                <tr id="${phone.id}">
                    <td>${phone.name}</td>
                    <td>${phone.mobileMinutes}</td>
                    <td>${phone.monthPrice}</td>
                    <td>
                        <button onclick="sendUpdateEvent(${phone.id})">Edit</button>
                    </td>
                    <td>
                        <button onclick="sendDeleteEvent(${phone.id})">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <button onclick="sendAddEvent()">Add new Phone Connection Tariff</button>
    </body>
</html>