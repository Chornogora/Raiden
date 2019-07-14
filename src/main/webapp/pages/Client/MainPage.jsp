<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "summary" uri = "/WEB-INF/myTags.tld"%>
<%@ page isELIgnored="false"%>

<html>
    <head>
        <title>
            Raiden
        </title>
        <link rel="stylesheet" href="/Raiden_war/css/Client.css"/>
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
        <a class="panelLink" href="">
            <summary:print>About Us</summary:print>
        </a>
        <a class="panelLink" href="/Raiden_war/client/services">
            <summary:print>Services</summary:print>
        </a>
        <a class="panelLink" href="">
            <summary:print>News</summary:print>
        </a>
        <a class="panelLink" href="">
            <summary:print>Contacts</summary:print>
        </a>
        <a class = "language" onclick="changeLocale('en')">En</a>
        <a class = "language" onclick="changeLocale('ru')">Ru</a>
    </header>

   <article>

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