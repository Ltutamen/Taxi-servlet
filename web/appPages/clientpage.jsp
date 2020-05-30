<%@ page import="java.util.Map" %><%--
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

<%@ include file="../misc/navbar.jsp" %>

<body>
<div class="container">
    <div class="row">
        <div class="card col-4">
            <div class="card-header">
                <p><%= request.getAttribute("sentence.your-balance")%> <%= request.getAttribute("client-balance") %></p>
            </div>
            <div class="card-body">
                <div class="list-group">
                    <a href="/neworder" class="list-group-item list-group-item-action"> <%= request.getAttribute("sentence.new-order") %> </a>
                    <a href="/api/orderHistory" class="list-group-item list-group-item-action"> <%= request.getAttribute("sentence.order-history") %> </a>
                    <a href="#" class="list-group-item list-group-item-action"> <%= request.getAttribute("sentence.promocodes") %> </a>
                    <a href="/api/replenish" class="list-group-item list-group-item-action"> <%= request.getAttribute("sentence.replenish-balance") %></a>
                    <a href="#" class="list-group-item list-group-item-action"> <%= request.getAttribute("sentence.delete-account") %></a>
                </div>
            </div>
        </div>
        <div class="card col-8">
            <div class="card-header">
                <h1> <%= request.getAttribute("sentence.your-orders") %></h1>
                <label>
                    <%= request.getAttribute("word.balance") %> : <%= request.getAttribute("client-balance") %>
                </label>
            </div>
            <div class="card-body">
                <ul class="list-group">
                    <li class="list-group-item">

                        <c:forEach items="${requestScope.pending_orders}" var="order">
                            <div class="card-body">
                                <div class="row">
                                    <label class="card-text col-3"><%= request.getAttribute("word.from") %>: "${order.departure} </label>
                                    <label class="card-text col-3"><%= request.getAttribute("word.to") %>: "${order.destination} </label>
                                    <label class="card-text col-3"><%= request.getAttribute("word.class") %>: "${order.cClass} </label>
                                    <label class="card-text col-3"><%= request.getAttribute("word.fee") %>: "${order.price} </label>
                                </div>
                                <div class="row">
                                    <form method="post" action="/clientpage/cancelorder">
                                        <button type="submit" class="btn btn-warning" value="${order.id}" name="orderId"><%= request.getAttribute("word.cancel") %></button>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>

                    </li>
                    <li class="list-group-item">
                        <%--
                        <c:forEach items="${taken-orders}" var="order">
                            <div class="card text-white bg-success">
                                <div class="card-body">
                                    <div class="row">
                                        <label class="card-text col-3"><%= ((Map<String, Object>)request.getAttribute("model")).get("word.from") %>: <%= ((Map<String, Object>)request.getAttribute("model")).get("departure") %></label>
                                        <label class="card-text col-3"><%= ((Map<String, Object>)request.getAttribute("model")).get("word.to") %>: <%= ((Map<String, Object>)request.getAttribute("model")).get("destination") %></label>
                                        <label class="card-text col-3"><%= ((Map<String, Object>)request.getAttribute("model")).get("word.class") %>: <%= ((Map<String, Object>)request.getAttribute("model")).get("cClass") %></label>
                                        <label class="card-text col-3"><%= ((Map<String, Object>)request.getAttribute("model")).get("word.fee") %>: <%= ((Map<String, Object>)request.getAttribute("model")).get("price") %></label>
                                    </div>
                                    <div class="row">
                                        <form method="post" action="/clientpage/confirm">
                                            <button type="submit" class="btn btn-warning" value="${order.id}" name="orderId"><%= ((Map<String, Object>)request.getAttribute("model")).get("sentence.sentence-confirm-msg") %></button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        --%>
                    </li>
                    <li class="list-group-item align-content-center">
                        <p> <%= request.getAttribute("word.page") %> : <%= request.getAttribute("page") %> / <%= request.getAttribute("max-pages") %> </p>
                        <div class="row text-center">
                            <form action="/clientpage/prevpage" method="post">
                                <button type="submit" class="btn btn-light btn-lg">
                                    &#8592
                                </button>
                            </form>
                            <form action="/clientpage/nextpage" method="post">
                                <button type="submit" class="btn btn-light btn-lg">
                                    &#8594
                                </button>
                            </form>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

    </div>

</div>
</body>
</html>
