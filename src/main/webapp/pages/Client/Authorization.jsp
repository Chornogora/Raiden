<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "summary" uri = "/WEB-INF/myTags.tld"%>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>
        Raiden - Authorization
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
        <a class="panelLink" href="/Raiden_war/">
            <summary:print>About Us</summary:print>
        </a>
        <a class="panelLink" href="/Raiden_war/client/services">
            <summary:print>Services</summary:print>
        </a>
        <a class="panelLink" href="/Raiden_war/pages/Client/News.jsp">
            <summary:print>News</summary:print>
        </a>
        <a class="panelLink" href="/Raiden_war/pages/Client/Contacts.jpg">
            <summary:print>Contacts</summary:print>
        </a>
        <a class = "language" onclick="changeLocale('en')">En</a>
        <a class = "language" onclick="changeLocale('ru')">Ru</a>
    </header>

    <article>
        <%--<div>
            <label>
                <summary:print>Enter using any contract number</summary:print>
                <input type="checkbox" checked="checked" id="contract"/>
            </label>
            <label>
                <summary:print>Enter using passport data</summary:print>
                <input type="checkbox" id="passport"/>
            </label>
        </div>--%>

        <div>
            <label>
                <summary:print>Number of any your contract: </summary:print>
                <input type="number" id="contractNumber"/>
            </label>
            <label>
                <summary:print>Your password: </summary:print>
                <input type="password" id="password"/>
            </label>
            <input type="submit" value="Log In" onClick="authorizeByContract()"/>
        </div>
    </article>

    <footer>
            <pre id="Copyright">
                Copyright 2019 Raiden.com
                <summary:print>All rights to any materials published on the site are protected in accordance with the Ukrainian and international</summary:print>
                <summary:print>copyright and related rights. Any use of text, audio, photo and</summary:print>
                <summary:print>video materials are possible only with the written permission of the publisher.</summary:print>
            </pre>
    </footer>
</body>
</html>