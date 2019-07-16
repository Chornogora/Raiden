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
    <link rel="stylesheet" href="/Raiden_war/css/ClientAuthorization.css"/>
    <script src="/Raiden_war/js/Client/ClientLocalization.js"></script>
    <script src="/Raiden_war/js/Client/ClientAuthorization.js"></script>
    <script src="/Raiden_war/js/Util.js"></script>
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
        <form id="form" onsubmit="return false;">
            <div id="HeadTitle">
                <span id="text"><summary:print>Authorization</summary:print></span>
            </div>
            <div id = "fields">
                <label id="loginLabel">
                    <summary:print>Number of any your contract: </summary:print>
                    <br/>
                    <input type="number" name="login" id="contractNumber""/>
                </label>
                <br/>
                <label id="passwordLabel">
                    <summary:print>Your password: </summary:print>
                    <br/>
                    <input type="password" name="password" id="password" maxlength="20" minlength="8"/>
                </label>
                <br/>
                <input type="submit" id="submit" value="Log In" onClick="authorizeByContract()"/>
            </div>
        </form>
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