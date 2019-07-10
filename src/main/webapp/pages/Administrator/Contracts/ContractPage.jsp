<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html lang="en">
<head>
    <title>Contract</title>
    <script src="/Raiden_war/js/Contract.js"></script>
    <link rel="stylesheet" href="/Raiden_war/css/Adding.css"/>
</head>
<body>

    <table border="2">
        <tr>
            <th>Contract Number</th>
            <th>Client</th>
            <th>Status</th>
            <th>Address</th>
            <th>Connection Date</th>
            <th>Tariffs and Ordered Services</th>
        </tr>
        <c:forEach var="contract" items="${requestScope.list}">
            <tr id="${contract.id}">
                <td>${contract.id}</td>
                <td>${contract.client.fullName}</td>
                <td>${contract.status}</td>
                <td>${contract.address}</td>
                <td>${contract.connected}</td>
                <td>
                    <c:forEach var="service" items="${contract.services}">
                        ${service.name}
                    </c:forEach>
                </td>
                <td>
                    <button onclick="deleteContract(${contract.id})">Delete</button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/Raiden_war/administrator/contract/adding">
        <button>Add new Contract</button>
    </a>
</body>
</html>