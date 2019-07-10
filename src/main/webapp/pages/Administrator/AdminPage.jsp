<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
    <head>
        <title>Admin Page</title>
        <link rel="stylesheet" href="/Raiden_war/css/Admin.css"/>
        <script src="/Raiden_war/js/AdminPage.js"></script>
    </head>
    <body>

        <!-- header of administrator page. contains links -->
        <div id="panel">
            <a target="addition" href="InternetAdding.html">
                <button class="page" onclick="openInternet()">
                    Internet
                </button>
            </a>
            <a target="addition" href="TelevisionAdding.html">
                <button class="page" onclick="openTelevision()">
                    Television
                </button>
            </a>
            <a target="addition" href="PhoneAdding.html">
                <button class="page" onclick="openPhone()">
                    Telephony
                </button>
            </a>
             <a target="addition" href="ServiceAdding.html">
                 <button class="page" onclick="openService()">
                     Services
                 </button>
             </a>
            <a target="addition" href="Clients/ClientRegistration.html">
                <button class="page" onclick="openClient()">
                    Clients
                </button>
            </a>
            <a target="workplace" href="/Raiden_war/administrator/contract">
                <button class="page">Contracts</button>
            </a>
        </div>
        <form method="POST" action="/Raiden_war/administrator/logout">
            <button id="exit" onclick="logout()">Log out</button>
        </form>

        <iframe id="workplace" name="workplace"></iframe>
        <iframe id="addition" name="addition" scrolling="no"></iframe>

    </body>
</html>