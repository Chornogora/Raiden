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
       <p>"Raiden" - молодой и успешно развивающийся Интернет-провайдер в Украине, который может предоставить бесплатное подключение Интернета, тарифы под любые потребности и высокую стабильность сети. Благодаря высочайшему качеству услуг и сервисного обслуживания мы занимаемся ведущие позиции среди крупнейших провайдеров Украины. Этому подтверждением есть более 5 млн пользователей и каждый день прибавляются новые.
            Наше кредо - это прежде всего качество и комфорт. Мы со всей ответственностью относимся к задачам, стоящим перед нами, своевременно и профессионально выполняем свои обязанности.
           Если Вы хотите выбрать Интернет-провайдер "Raiden", то вот аргументы почему Вы должны сделать именно это:
       </p>
           <pre class="list">
    1. Комфорт и удобство при подключении к Интернету;
    2. Скоростной Интернет более 300 Мбит/с;
    3. Использование передовых технологий для развития своих сетей, оборудования и сервиса;
    4. Доступная цена. Все тарифы разрабатываются, чтобы услуга была доступна
       по цене и насыщенна по наполнению.
       </pre>
       <p>
            Интернет-провайдер "Raiden"  имеет впечатляющее покрытие. Сегодня практически не осталось мест, куда бы не был проведён наш Интернет. Покрытие "Raiden" предоставляет возможности воспользоваться услугами провайдера как жителям многоквартирных домов, так и частных.
            Подключить Интернет от "Raiden" можно быстро и легко. Достаточно позвонить по телефону и наши специалисты в кротчайшие сроки установят в вашей квартире современный роутер, а также выполнит все необходимые настройки и подключит к Интернету.
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