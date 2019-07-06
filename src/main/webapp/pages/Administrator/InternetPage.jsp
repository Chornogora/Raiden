<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
    <head>
        <title>Internet</title>
    </head>
    <body>
        <table border="2">
            <tr>
                <th>Name of Tariff</th>
                <th>Speed of Internet</th>
                <th>Month Price(₴)</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="inet" items="${requestScope.list}">
                <tr id="${inet.id}">
                    <td>${inet.name}</td>
                    <td>${inet.speed}</td>
                    <td>${inet.monthPrice}</td>
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
