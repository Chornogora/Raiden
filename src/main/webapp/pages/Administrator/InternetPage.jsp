<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
    <head>
        <title>Internet</title>
        <script>
            function sendUpdateEvent(id){
                let tr = document.getElementById(id);
                let array = tr.getElementsByTagName("td");
                let event = new CustomEvent('updateInternet', {
                    bubbles: true,
                    cancelable: true,
                    detail: {
                        id: id,
                        name: array[0].innerText,
                        speed: array[1].innerText,
                        monthPrice: array[2].innerText
                    }
                });
                this.dispatchEvent(event);
            }

            function sendAddEvent(){
                let event = new CustomEvent('addInternet', {
                    bubbles: true,
                    cancelable: true,
                    detail: null
                });
                this.dispatchEvent(event);
            }

            function sendDeleteEvent(id){
                let event = new CustomEvent('deleteInternet', {
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
                <th>Speed of Internet</th>
                <th>Month Price(₴)</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="inet" items="${requestScope.list}">
                <tr id="${inet.id}">
                    <td>${inet.name}</td>
                    <td>${inet.speed}</td>
                    <td>${inet.monthPrice}</td>
                    <td>
                        <button onclick="sendUpdateEvent(${inet.id})">Edit</button>
                    </td>
                    <td>
                        <button onclick="sendDeleteEvent(${inet.id})">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <button onclick="sendAddEvent()">Add new Internet Tariff</button>
    </body>
</html>