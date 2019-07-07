<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>Service</title>
    <script>
        function sendUpdateEvent(id){
            let tr = document.getElementById(id);
            let array = tr.getElementsByTagName("td");
            let event = new CustomEvent('updateService', {
                bubbles: true,
                cancelable: true,
                detail: {
                    id: id,
                    name: array[0].innerText,
                    measure: array[1].innerText,
                    price: array[2].innerText
                }
            });
            this.dispatchEvent(event);
        }

        function sendAddEvent(){
            let event = new CustomEvent('addService', {
                bubbles: true,
                cancelable: true,
                detail: null
            });
            this.dispatchEvent(event);
        }

        function sendDeleteEvent(id){
            let event = new CustomEvent('deleteService', {
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
        <th>Name of Service</th>
        <th>Measure</th>
        <th>Price</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="work" items="${requestScope.list}">
        <tr id="${work.id}">
            <td>${work.name}</td>
            <td>${work.measure}</td>
            <td>${work.price}</td>
            <td>
                <button onclick="sendUpdateEvent(${work.id})">Edit</button>
            </td>
            <td>
                <button onclick="sendDeleteEvent(${work.id})">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<button onclick="sendAddEvent()">Add new Service</button>

</body>
</html>
