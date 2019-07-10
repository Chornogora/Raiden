<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html lang="en">
    <head>
        <title>Contract</title>
        <script src="/Raiden_war/js/Adding/ContractAdding.js"></script>
        <link rel="stylesheet" href="/Raiden_war/css/Adding.css"/>
    </head>
    <body>

    <header>
        <span class="pageTitle">Creating new Contract</span>
        <hr class="line" size="3" color="brown"/>
    </header>

    <div id = "form">
        <select id="client">
            <c:forEach var="client" items="${requestScope.clients}">
                <option id="${client.id}">
                    ${client.fullName}(${client.email})
                </option>
            </c:forEach>
        </select>
        <br/>
        <br/>
        <input type="text" id="address" placeholder="address"/>
        <br/>
        <hr size="4" color="brown" />

        <label>Include Internet Tariff
            <input type="checkbox" id="InetIncluded"/>
        </label>
            <table id="internet" border="2">
                <tr>
                    <th>Name of Tariff</th>
                    <th>Speed of Internet</th>
                    <th>Month Price(₴)</th>
                    <th>Add to Contract</th>
                </tr>
                <c:forEach var="inet" items="${requestScope.internetTariffs}">
                    <tr id="${inet.id}">
                        <td>${inet.name}</td>
                        <td>${inet.speed}</td>
                        <td>${inet.monthPrice}</td>
                        <td>
                            <input type="radio" name="internetButton"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        <br/>
        <hr size="4" color="brown" />

        <label>Include Television Tariff
            <input type="checkbox" id="TelevisionIncluded"/>
        </label>
        <table id="television" border="2">
            <tr>
                <th>Name of Tariff</th>
                <th>Number of Channels</th>
                <th>Format</th>
                <th>Month price</th>
                <th>Add to Contract</th>
            </tr>
            <c:forEach var="television" items="${requestScope.televisionTariffs}">
                <tr id="${television.id}">
                    <td>${television.name}</td>
                    <td>${television.channels}</td>
                    <td>${television.format}</td>
                    <td>${television.monthPrice}</td>
                    <td>
                        <input type="radio" name="televisionButton"/>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <br/>
        <hr size="4" color="brown" />

        <label>Include Phone Connection Tariff
            <input type="checkbox" id="PhoneIncluded"/>
        </label>
        <table id="phone" border="2">
            <tr>
                <th>Name of Tariff</th>
                <th>Minutes for mobile operators</th>
                <th>Month Price(₴)</th>
                <th>Add to Contract</th>
            </tr>
            <c:forEach var="phone" items="${requestScope.phoneTariffs}">
                <tr id="${phone.id}">
                    <td>${phone.name}</td>
                    <td>${phone.mobileMinutes}</td>
                    <td>${phone.monthPrice}</td>
                    <td>
                        <input type="radio" name="phoneButton"/>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <br/>
        <hr size="4" color="brown" />

        <label>Include Services
            <input type="checkbox" id="ServiceIncluded"/>
        </label>
        <table id="service" border="2">
            <tr>
                <th>Name of Service</th>
                <th>Measure</th>
                <th>Price</th>
                <th>Add to Contract</th>
            </tr>
            <c:forEach var="service" items="${requestScope.services}">
                <tr id="${service.id}">
                    <td>${service.name}</td>
                    <td>${service.measure}</td>
                    <td>${service.price}</td>
                    <td>
                        <input type="checkbox" name="serviceButton"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <input type="submit" value="Submit" id="submit" onclick="CreateContract()"/>

        <br/>
        <br/>
        <a href="/Raiden_war/administrator/contract" style="z-index: 7">
            <button>Show All Contracts</button>
        </a>
    </div>





    </body>
</html>