<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "summary" uri = "/WEB-INF/myTags.tld"%>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>
        Raiden - Account
    </title>
    <link rel="stylesheet" href="/Raiden_war/css/Client.css"/>
    <link rel="stylesheet" href="/Raiden_war/css/ClientCabinet.css"/>
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
    <a class="panelLink" href="Account.jsp">
        <summary:print>My Account</summary:print>
    </a>
    <a class="panelLink" onclick="Logout()">
        <summary:print>Log out</summary:print>
    </a>
    <a class = "language" onclick="changeLocale('en')">En</a>
    <a class = "language" onclick="changeLocale('ru')">Ru</a>
</header>

<article>
    <span id="presentAccount">
       <summary:print>Your account</summary:print>: ${sessionScope.client.account} ₴
    </span>
    <br>
    <form action="/Raiden_war/client/account" method="POST">
        <label id="account">
            <summary:print>Top up account on</summary:print>
            <input type="number" name="amount" id="amount" min="1" value = "1" step="0.01"/>
            ₴
        </label>
        <input id="submit" type="submit" value="<summary:print>Submit</summary:print>"/>
    </form>
</article>

<%@include file="../../WEB-INF/jspf/footer.jspf"%>
</body>
</html>