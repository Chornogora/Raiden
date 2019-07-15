<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>Dialog</title>
    <link rel="stylesheet" href="/Raiden_war/css/Admin.css"/>
    <link rel="stylesheet" href="/Raiden_war/css/AdminChat.css"/>
    <script src="/Raiden_war/js/AdminChat.js"></script>
</head>
<body>
    <form method="POST" action="/Raiden_war/administrator/logout">
        <button id="exit" onclick="logout()">Log out</button>
    </form>
    <a id="ToMain" href="/Raiden_war/pages/Administrator/AdminPage.jsp">Main Page</a>

    <div id = "ChatBox"></div>
    <textarea id="input"></textarea>
    <img src="/Raiden_war/images/Send.png" alt="Send" id="Send" onclick="SendMessage()"/>
</body>
</html>
