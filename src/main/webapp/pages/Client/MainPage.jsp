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

    <%@include file="../../WEB-INF/jspf/header.jspf"%>

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

    <%@include file="../../WEB-INF/jspf/footer.jspf"%>
    </body>
</html>