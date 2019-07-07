<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>Phone</title>
    <script>
        function sendUpdateEvent(id){
            let tr = document.getElementById(id);
            let array = tr.getElementsByTagName("td");
            let event = new CustomEvent('updatePhone', {
                bubbles: true,
                cancelable: true,
                detail: {
                    id: id,
                    name: array[0].innerText,
                    minutes: array[1].innerText,
                    monthPrice: array[2].innerText
                }
            });
            this.dispatchEvent(event);
        }

        function sendAddEvent(){
            let event = new CustomEvent('addPhone', {
                bubbles: true,
                cancelable: true,
                detail: null
            });
            this.dispatchEvent(event);
        }

        function sendDeleteEvent(id){
            let event = new CustomEvent('deletePhone', {
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
                <th>Minutes for mobile operators</th>
                <th>Month Price(₴)</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="phone" items="${requestScope.list}">
                <tr id="${phone.id}">
                    <td>${phone.name}</td>
                    <td>${phone.mobileMinutes}</td>
                    <td>${phone.monthPrice}</td>
                    <td>
                        <button onclick="sendUpdateEvent(${phone.id})">Edit</button>
                    </td>
                    <td>
                        <button onclick="sendDeleteEvent(${phone.id})">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <button onclick="sendAddEvent()">Add new Phone Connection Tariff</button>
    </body>
</html>