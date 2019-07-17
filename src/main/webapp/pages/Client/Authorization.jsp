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

    <%@include file="../../WEB-INF/jspf/header.jspf"%>

    <article>
        <form id="form" onsubmit="return false;">
            <div id="HeadTitle">
                <span id="text"><summary:print>Authorization</summary:print></span>
            </div>
            <div id = "fields">
                <label id="loginLabel">
                    <summary:print>Number of any your contract</summary:print>:
                    <br/>
                    <input type="number" name="login" id="contractNumber"/>
                </label>
                <br/>
                <label id="passwordLabel">
                    <summary:print>Your password</summary:print>:
                    <br/>
                    <input type="password" name="password" id="password" maxlength="20" minlength="8"/>
                </label>
                <br/>
                <input type="submit" id="submit" value="Log In" onClick="authorizeByContract()"/>
            </div>
        </form>
    </article>

    <%@include file="../../WEB-INF/jspf/footer.jspf"%>
</body>
</html>