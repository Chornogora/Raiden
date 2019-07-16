<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
    <head>
        <title>Internet</title>
        <script src="/Raiden_war/js/WorkplaceFrame/Internet.js"></script>
    </head>
    <body>
        <table border="2">
            <tr>
                <th onclick="sortByName()">Name of Tariff</th>
                <th>Speed of Internet</th>
                <th onclick="sortByPrice()">Month Price(₴)</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="inet" items="${requestScope.list}">
                <tr id="${inet.id}">
                    <td>${inet.name}</td>
                    <td>${inet.speed}</td>
                    <td>${inet.monthPrice}</td>
                    <td>
                        <button onclick="sendUpdateEvent(${inet.id})">Edit</button>
                    </td>
                    <td>
                        <button onclick="sendDeleteEvent(${inet.id})">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <button onclick="sendAddEvent()">Add new Internet Tariff</button>
    </body>
</html>