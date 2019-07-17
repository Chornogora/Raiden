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
        <link rel="stylesheet" href="/Raiden_war/css/About.css"/>
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

   <article>
       <img src="/Raiden_war/images/logo.jpeg" alt="photo" id="logo"/>
       <p>
           <summary:print>
               RaidenFirstInfo
           </summary:print>
       </p>
           <pre class="list">
    1. <summary:print>Comfort and convenience when connecting to the Internet</summary:print>;
    2. <summary:print>High-speed Internet more than 300 Mbit/s</summary:print>;
    3. <summary:print>The use of advanced technologies for the development of our networks, equipment and services</summary:print>;
    4. <summary:print>Affordable price. All tariffs are designed to make the service affordable and rich in content</summary:print>.
       </pre>
       <p>
           <summary:print>
               RaidenSecondInfo
           </summary:print>
       </p>
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