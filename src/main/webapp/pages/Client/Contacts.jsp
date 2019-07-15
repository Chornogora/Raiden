<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "summary" uri = "/WEB-INF/myTags.tld"%>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>
        Raiden - Contacts
    </title>
    <link rel="stylesheet" href="/Raiden_war/css/Client.css"/>
    <link rel="stylesheet" href="/Raiden_war/css/ClientContacts.css"/>
    <script src="/Raiden_war/js/Client/ClientLocalization.js"></script>
    <script src="/Raiden_war/js/Client/ClientContacts.js"></script>
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

<article style="overflow-x: hidden">
    <span id="PhoneNumbers"><summary:print>You can call us by using these Phone Numbers:</summary:print></span>
    <div id="phones">
        <div id="Vodafone">
            <img src="/Raiden_war/images/Vodafone.png" alt="Vodafone" class="OperatorLogo"/>
            <span class="PhoneNumber">+38 (050) 989-41-93</span>
        </div>
        <br/>
        <div id="Kyivstar">
            <img src="/Raiden_war/images/Kyivstar.png" alt="Kyivstar" class="OperatorLogo"/>
            <span class="PhoneNumber">+38 (097) 980-25-87</span>
        </div>
    </div>
    <span id="ChatText"><summary:print>Try to contact with us online: </summary:print></span>
    <div id = "ChatBox">
        <button id="Start" onclick="StartChat()"><summary:print>Begin Chat</summary:print></button>
        <img src="https://thumbs.gfycat.com/SkinnySeveralAsianlion-size_restricted.gif" alt="Waiting" id="Waiting" />
        <span id="WaitingText"><summary:print>Waiting for free administrator</summary:print></span>
    </div>
    <textarea id="input"></textarea>
    <img src="/Raiden_war/images/Send.png" alt="Send" id="Send" onclick="SendMessage()"/>
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
