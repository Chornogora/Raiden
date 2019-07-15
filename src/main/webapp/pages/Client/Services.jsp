<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "summary" uri = "/WEB-INF/myTags.tld"%>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>
        Main
    </title>
    <link rel="stylesheet" href="/Raiden_war/css/Client.css"/>
    <link rel="stylesheet" href="/Raiden_war/css/Services.css"/>
    <script src="/Raiden_war/js/Client/ClientLocalization.js"></script>
</head>
<body>
    <div class="topPanel">
            <span id="Raiden">
                Raiden
            </span>
        <a id="cabinet" href="/Raiden_war/pages/Client/Authorization.jsp"><summary:print>Personal Cabinet</summary:print></a>
    </div>

    <div class="mainField">

    </div>

    <header>
        <a class="panelLink" href="/Raiden_war/">
            <summary:print>About Us</summary:print>
        </a>
        <a class="panelLink" href="/Raiden_war/Client/services">
            <summary:print>Services</summary:print>
        </a>
        <a class="panelLink" href="">
            <summary:print>News</summary:print>
        </a>
        <a class="panelLink" href="/Raiden_war/pages/Client/Contacts.jsp">
            <summary:print>Contacts</summary:print>
        </a>
        <a class = "language" onclick="changeLocale('en')">En</a>
        <a class = "language" onclick="changeLocale('ru')">Ru</a>
    </header>

    <article>
        <h1><summary:print>Internet</summary:print></h1>
        <table>
            <tr>
                <th><summary:print>Tariff</summary:print></th>
                <th><summary:print>Speed of Internet</summary:print></th>
                <th><summary:print>Month Price</summary:print></th>
            </tr>
            <c:forEach var="inet" items="${requestScope.internet}">
                <tr>
                    <td id="inetName">${inet.name}</td>
                    <td id="inetSpeed">${inet.speed} Mb/s</td>
                    <td class="monthPrice">${inet.monthPrice} ₴</td>
                </tr>
            </c:forEach>
        </table>

        <hr/>

        <h1><summary:print>Television</summary:print></h1>
        <table>
            <tr>
                <th><summary:print>Tariff</summary:print></th>
                <th><summary:print>Number of Channels</summary:print></th>
                <th><summary:print>Format</summary:print></th>
                <th><summary:print>Month Price</summary:print></th>
            </tr>
            <c:forEach var="television" items="${requestScope.television}">
                <tr>
                    <td>${television.name}</td>
                    <td>${television.channels}</td>
                    <td>${television.format}</td>
                    <td>${television.monthPrice} ₴</td>
                </tr>
            </c:forEach>
        </table>

        <hr/>

        <h1><summary:print>Telephony</summary:print></h1>
        <table>
            <tr>
                <th><summary:print>Tariff</summary:print></th>
                <th><summary:print>Minutes for mobile operators</summary:print></th>
                <th><summary:print>Month Price</summary:print></th>
            </tr>
            <c:forEach var="phone" items="${requestScope.telephony}">
                <tr>
                    <td>${phone.name}</td>
                    <td>${phone.mobileMinutes} <summary:print>min</summary:print></td>
                    <td>${phone.monthPrice} ₴</td>
                </tr>
            </c:forEach>
        </table>

        <hr/>

        <h1><summary:print>Services</summary:print></h1>
        <table>
            <tr>
                <th><summary:print>Name of Service</summary:print></th>
                <th><summary:print>Measure</summary:print></th>
                <th><summary:print>Price</summary:print></th>
            </tr>
            <c:forEach var="work" items="${requestScope.works}">
                <tr>
                    <td>${work.name}</td>
                    <td>${work.measure}</td>
                    <td>${work.price} ₴</td>
                </tr>
            </c:forEach>
        </table>
    </article>

    <div id="document">
        <span>
            <summary:print>If you want to download PDF document with our tariffs, press</summary:print>
        </span>
        <a href="/Raiden_war/docs/tariffs.pdf" id="docLink">
            <summary:print>here</summary:print>
        </a>
    </div>

    <div id="contacts">
        <span>
            <summary:print>If you want to order our services, go to page</summary:print>
        </span>
        <a href="/Raiden_war/client/contacts" id="contactLink">
            <summary:print>contacts</summary:print>
        </a>
    </div>

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