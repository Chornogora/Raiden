<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "summary" uri = "/WEB-INF/myTags.tld"%>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>
        Raiden - Cabinet
    </title>
    <link rel="stylesheet" href="/Raiden_war/css/Client.css"/>
    <script src="/Raiden_war/js/Client/ClientLocalization.js"></script>
    <script src="/Raiden_war/js/Client/ClientAuthorization.js"></script>
</head>
<body>
    <div class="topPanel">
        <span id="Raiden">
            Raiden
        </span>
    </div>

    <div class="mainField">

    </div>

    <header>
        <a class="panelLink" href="/Raiden_war/client">
            <summary:print>My Contracts</summary:print>
        </a>
        <a class="panelLink" href="/Raiden_war/pages/Client/Account.jsp">
            <summary:print>My Account</summary:print>
        </a>
        <a class="panelLink" onclick="Logout()">
            <summary:print>Log out</summary:print>
        </a>
        <a class = "language" onclick="changeLocale('en')">En</a>
        <a class = "language" onclick="changeLocale('ru')">Ru</a>
    </header>

    <article>
        <h2><summary:print>My Contracts</summary:print></h2>
        <table border="2">
            <tr>
                <th><summary:print>Contract Number</summary:print></th>
                <th><summary:print>Connection Date</summary:print></th>
                <th><summary:print>Pay Date</summary:print></th>
                <th><summary:print>Status</summary:print></th>
                <th><summary:print>Address</summary:print></th>
                <th><summary:print>Tariffs and Ordered Services</summary:print></th>
                <th><summary:print>Month Price</summary:print></th>
            </tr>
            <c:forEach var="contract" items="${requestScope.contracts}">
                <tr id="${contract.id}">
                    <td>${contract.id}</td>
                    <td>${contract.connected}</td>
                    <td>${contract.control}</td>
                    <td>${contract.status}</td>
                    <td>${contract.address}</td>
                    <td>
                        <c:forEach var="service" items="${contract.services}">
                            ${service.name},
                        </c:forEach>
                    </td>
                    <td>${contract.monthPrice} ₴</td>
                </tr>
            </c:forEach>
        </table>
    </article>

    <%@include file="../../WEB-INF/jspf/footer.jspf"%>
</body>
</html>