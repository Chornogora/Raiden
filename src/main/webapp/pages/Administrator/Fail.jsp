<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        a{
            color: blue;
        }

        a:hover{
            color:darkviolet;
        }
    </style>
</head>
<body>
<span>Something get wrong: ${requestScope.info}</span>
<br/>
<a onclick="history.back()">Back</a>
</body>
</html>
