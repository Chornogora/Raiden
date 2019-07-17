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
    <a id="cabinet" href="/Raiden_war/pages/Client/Authorization.jsp"><summary:print>Personal Cabinet</summary:print></a>
</div>

<div class="mainField">

</div>

<%@include file="../../WEB-INF/jspf/header.jspf"%>

<article style="overflow-x: hidden">
    <span id="PhoneNumbers"><summary:print>You can call us by using these phone numbers</summary:print>:</span>
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
    <span id="ChatText"><summary:print>Try to contact with us online</summary:print>:</span>
    <div id = "ChatBox">
        <button id="Start" onclick="StartChat()"><summary:print>Begin Chat</summary:print></button>
        <img src="https://thumbs.gfycat.com/SkinnySeveralAsianlion-size_restricted.gif" alt="Waiting" id="Waiting" />
        <span id="WaitingText"><summary:print>Waiting for free administrator</summary:print></span>
    </div>
    <textarea id="input"></textarea>
    <img src="/Raiden_war/images/Send.png" alt="Send" id="Send" onclick="SendMessage()"/>
</article>

<%@include file="../../WEB-INF/jspf/footer.jspf"%>
</body>
</html>
