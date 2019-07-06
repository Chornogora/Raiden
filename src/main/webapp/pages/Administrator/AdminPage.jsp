<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <button class="page" onclick="document.getElementById('workplace').src='/Raiden_war/administrator/internet'">
                    Internet
                </button>
            </a>
            <a target="addition" href="TelevisionAdding.html">
                <button class="page" onclick="document.getElementById('workplace').src='TelevisionPage.jsp'">
                    Television
                </button>
            </a>
            <a target="addition" href="PhoneAdding.html">
                <button class="page" onclick="document.getElementById('workplace').src='PhonePage.jsp'">
                    Telephony
                </button>
            </a>
                 <a target="addition" href="ServiceAdding.html">
                     <button class="page" onclick="document.getElementById('workplace').src='ServicePage.jsp'">
                         Services
                     </button>
                 </a>
            <a><button class="page">Clients</button></a>
            <a><button class="page">Payments</button></a>
        </div>
        <form method="POST" action="/Raiden_war/administrator/logout">
            <button id="exit">Log out</button>
        </form>


        <iframe id="workplace" name="workplace" src="/Raiden_war/administrator/internet"></iframe>
        <iframe id="addition" name="addition" src="InternetAdding.html" scrolling="no"></iframe>
    </body>
</html>