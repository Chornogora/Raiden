<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Admin Page</title>
        <link rel="stylesheet" href="/Raiden_war/css/Admin.css"/>
    </head>
    <body>
        <div id="panel">
            <button class="page">Internet</button>
            <button class="page">Television</button>
            <button class="page">Telephony</button>
            <button class="page">Services</button>
            <button class="page">Clients</button>
            <button class="page">Payments</button>
        </div>
        <button id="exit">Log out</button>

        <iframe id="workplace"></iframe>
        <iframe id="addition" src="InternetAdding.html" scrolling="no"></iframe>
    </body>
</html>