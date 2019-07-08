<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>Clients</title>
    <script>
        function sendUpdateEvent(id){
            let tr = document.getElementById(id);
            let array = tr.getElementsByTagName("td");
            let event = new CustomEvent('updateClient', {
                bubbles: true,
                cancelable: true,
                detail: {
                    id: id,
                    FirstName: array[0].innerText.split(" ")[0],
                    LastName: array[0].innerText.split(" ")[1],
                    Series: array[1].innerText,
                    Number: array[2].innerText,
                    phone: array[4].innerText,
                    email: array[5].innerText
                }
            });
            this.dispatchEvent(event);
        }

        function sendAddEvent(){
            let event = new CustomEvent('addClient', {
                bubbles: true,
                cancelable: true,
                detail: null
            });
            this.dispatchEvent(event);
        }

        function sendDeleteEvent(id){
            let event = new CustomEvent('deleteClient', {
                bubbles: true,
                cancelable: true,
                detail: id
            });
            this.dispatchEvent(event);
        }

        function sendBlockEvent(id){
            let event = new CustomEvent('blockClient', {
                bubbles: true,
                cancelable: true,
                detail: {id: id, status: getStatus(id)}
            });
            this.dispatchEvent(event);
        }

        function getStatus(id){
            let tr = document.getElementById(id);
            let result = tr.getElementsByTagName("td")[6].innerText;
            return result;
        }
    </script>
</head>
<body>
<table border="2">
    <tr>
        <th>Full Name</th>
        <th>Passport Series</th>
        <th>Passport Number</th>
        <th>Account(₴)</th>
        <th>Phone Number</th>
        <th>Email</th>
        <th>Status</th>
    </tr>
    <c:forEach var="client" items="${requestScope.clients}">
        <tr id="${client.id}">
            <td>${client.fullName}</td>
            <td>${client.passportSeries}</td>
            <td>${client.passportNumber}</td>
            <td>${client.account}</td>
            <td>${client.phoneNumber}</td>
            <td>${client.email}</td>
            <td>${client.status}</td>
            <td>
                <button onclick="sendUpdateEvent(${client.id})">Edit</button>
            </td>
            <td>
                <button onclick="sendDeleteEvent(${client.id})">Delete</button>
            </td>
            <td>
                <button onclick="sendBlockEvent(${client.id})">Block/Unblock</button>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<button onclick="sendAddEvent()">Register new Client</button>
</body>
</html>