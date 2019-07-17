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
    <link rel="stylesheet" href="/Raiden_war/css/News.css"/>
    <script src="/Raiden_war/js/Client/ClientLocalization.js"></script>
</head>
<body>
<div class="topPanel">
        <span id="Raiden">
            Raiden - News
        </span>
    <a id="cabinet" href="/Raiden_war/pages/Client/Authorization.jsp"><summary:print>Personal Cabinet</summary:print></a>
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
    <a class="panelLink" href="/Raiden_war/pages/Client/Contacts.jsp">
        <summary:print>Contacts</summary:print>
    </a>
    <a class = "language" onclick="changeLocale('en')">En</a>
    <a class = "language" onclick="changeLocale('ru')">Ru</a>
</header>

<article style="overflow-y: auto">
    <br>
    <img src="/Raiden_war/images/Money.png" alt="money" class="NewImage"/>
    <p class = "NewText">
        <summary:print>
            Since July 1, the "Subscription fee in half" campaign has become available for all new subscribers of "Raiden" - a 50% discount at 300 Mbit/s.
        </summary:print>
    </p>
    <a href="#" class = "link"><summary:print>Detailed</summary:print>>></a>
    <hr/>
    <img src="/Raiden_war/images/Repair.png" alt="repair" class="NewImage"/>
    <p class = "NewText">
        <summary:print>
            07/20/2019, from 06:00 to 09:00, work will be carried out to improve the Internet service. We apologize for any temporary inconvenience.
        </summary:print>
    </p>
    <a href="#" class = "link"><summary:print>Detailed</summary:print>>></a>
    <hr/>
    <img src="/Raiden_war/images/TV.png" alt="TV" class="NewImage"/>
    <p class = "NewText">
        <summary:print>
            From 01.09.2019, "Raiden" will begin to provide television services, which will allow to provide a comprehensive service "TV + Internet".
        </summary:print>
    </p>
    <a href="#" class = "link"><summary:print>Detailed</summary:print>>></a>
</article>

<footer>
        <pre id="Copyright">
            Copyright 2019 Raiden.com
            <summary:print>All rights to any materials published on the site are protected in accordance with the Ukrainian and international</summary:print>
            <summary:print>copyright and related rights. Any use of text, audio, photo and</summary:print>
            <summary:print>video materials are possible only with the written permission of the publisher.</summary:print>
        </pre>
</footer>


<script>
    for(let elem of document.getElementsByClassName("NewText")){
        elem.innerText = elem.innerText.trim();
    }
</script>
</body>
</html>