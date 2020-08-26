<%@ page import="java.util.Map" %>
<%@ page import="ua.axiom.model.actors.Car" %><%--
  Created by IntelliJ IDEA.
  User: myself
  Date: 4/29/20
  Time: 7:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="/public/css/login-page.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>Taxi company</title>
</head>

<%@ include file="../../misc/navbar.jsp" %>

<body>
<div class="container">
    <div class="row">
        <div class="card col-4" style="width: 18rem;">
            <div class="card-header">
                <p><%= request.getAttribute("word.class")%>: <%= ((Car)request.getAttribute("car")).getaClass()%> </p>
                <p><%= request.getAttribute("word.car-model")%>: <%= ((Car)request.getAttribute("car")).getModelName()%></p>
                <p><%= request.getAttribute("word.balance")%>: <%= request.getAttribute("balance")%></p>
            </div>
            <div class="card-body">
                <ul class="list-group">
                    <a class="list-group-item">
                        <%= request.getAttribute("sentence.your-cars")%>
                    </a>
                    <a class="list-group-item">
                        <%= request.getAttribute("word.withdraw")%>
                    </a>
                    <a class="list-group-item">
                        <%= request.getAttribute("sentence.change-car")%>
                    </a>
                    <a class="list-group-item" href="/driver/newCar">
                        add car
                    </a>
                </ul>
            </div>
        </div>
        <div class="card col-8">
            <div class="card-header">
                <%= request.getAttribute("sentence.accessible-orders")%>
            </div>
            <div class="card-body">
                <ul class="list-group">
                    <c:forEach items="${requestScope.orders}" var="order">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-4"><%= request.getAttribute("word.from") %> <c:out value="${order.getDeparture()}"/> %> </div>
                                <div class="col-4"><%= request.getAttribute("word.to") %> <c:out value="${order.getDestination()}"/> </div>
                                <div class="col-4">
                                    <form method="post" action="/driverpage/takeorder">
                                        <button type="submit" class="btn btn-warning" value="${order.id}" name="orderId"><%= request.getAttribute("sentence.take-order")%></button>
                                    </form>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
