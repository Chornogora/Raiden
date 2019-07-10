<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
    <head>
        <title>Internet</title>
        <script>
            let name = "asc";
            let num = "asc";

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

            function sortByName(){
                let table = document.getElementsByTagName("table")[0];
                let temp = table.getElementsByTagName("tr");
                let rows = [];
                let size = temp.length;
                for(let i = 1; i < size; ++i){
                    let row = temp[1];
                    rows[i-1] = row;
                    table.childNodes[1].removeChild(row);
                }

                let func;
                if(name === "asc"){
                    func = (x, y)=>{
                        return (x.getElementsByTagName("td")[0].innerText > y.getElementsByTagName("td")[0].innerText) ? 1 : -1;
                    };
                    name = "desc";
                }else{
                    func = (x, y)=>{
                        return (x.getElementsByTagName("td")[0].innerText < y.getElementsByTagName("td")[0].innerText) ? 1 : -1;
                    };
                    name = "asc";
                }
                num = "asc";
                rows.sort(func);

                for(let row of rows){
                    table.childNodes[1].appendChild(row);
                }

            }

            function sortByPrice(){
                let table = document.getElementsByTagName("table")[0];
                let temp = table.getElementsByTagName("tr");
                let rows = [];
                let size = temp.length;
                for(let i = 1; i < size; ++i){
                    let row = temp[1];
                    rows[i-1] = row;
                    table.childNodes[1].removeChild(row);
                }

                let func;
                if(num === "asc"){
                    func = (x, y)=>{
                        return (parseInt(x.getElementsByTagName("td")[2].innerText) - parseInt(y.getElementsByTagName("td")[2].innerText));
                    };
                    num = "desc";
                }else{
                    func = (x, y)=>{
                        return (parseInt(y.getElementsByTagName("td")[2].innerText) - parseInt(x.getElementsByTagName("td")[2].innerText));
                    };
                    num = "asc";
                }
                name = "asc";
                rows.sort(func);

                for(let row of rows){
                    table.childNodes[1].appendChild(row);
                }

            }
        </script>
    </head>
    <body>
        <table border="2">
            <tr>
                <th onclick="sortByName()">Name of Tariff</th>
                <th>Speed of Internet</th>
                <th onclick="sortByPrice()">Month Price(₴)</th>
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