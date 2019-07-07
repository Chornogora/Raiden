<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>Television</title>
    <script>
        function sendUpdateEvent(id){
            let tr = document.getElementById(id);
            let array = tr.getElementsByTagName("td");
            let event = new CustomEvent('updateTelevision', {
                bubbles: true,
                cancelable: true,
                detail: {
                    id: id,
                    name: array[0].innerText,
                    channels: array[1].innerText,
                    format: array[2].innerText,
                    monthPrice: array[3].innerText
                }
            });
            this.dispatchEvent(event);
        }

        function sendAddEvent(){
            let event = new CustomEvent('addTelevision', {
                bubbles: true,
                cancelable: true,
                detail: null
            });
            this.dispatchEvent(event);
        }

        function sendDeleteEvent(id){
            let event = new CustomEvent('deleteTelevision', {
                bubbles: true,
                cancelable: true,
                detail: id
            });
            this.dispatchEvent(event);
        }
    </script>
</head>
<body>
<table border="2">
    <tr>
        <th>Name of Tariff</th>
        <th>Number of Channels</th>
        <th>Format</th>
        <th>Month price</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="television" items="${requestScope.list}">
        <tr id="${television.id}">
            <td>${television.name}</td>
            <td>${television.channels}</td>
            <td>${television.format}</td>
            <td>${television.monthPrice}</td>
            <td>
                <button onclick="sendUpdateEvent(${television.id})">Edit</button>
            </td>
            <td>
                <button onclick="sendDeleteEvent(${television.id})">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<button onclick="sendAddEvent()">Add new Television Tariff</button>
</body>
</html>
