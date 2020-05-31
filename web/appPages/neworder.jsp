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
        <div class="card col-4" style="width: 18rem;">
            <div class="card-header">
                <p><%= request.getAttribute("sentence.new-order-request-msg")%></p>
            </div>
            <div class="card-body">
                <form method="POST" action="/api/neworder" name="new-order">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <input type="type" name="departure" placeholder="<%= request.getAttribute("word.from")%>"/>
                        </li>
                        <li class="list-group-item">
                            <input type="text" name="destination" placeholder="<%= request.getAttribute("word.to")%>"/>
                        </li>
                        <li class="dropdown list-group-item pagination-centered">
                            <label>
                                <p><%= request.getAttribute("word.class")%></p>
                            </label>
                            <%--
                            {{#car-classes}}
                            <div class="radio">
                                <label><input type="radio" name="aClass" checked value="{{toString}}">{{toString}}</label>
                            </div>
                            {{/car-classes}}
                            --%>
                        </li>
                        <li class="list-group-item pagination-centered">
                            <button type="submit" class="btn btn-primary"><%= request.getAttribute("word.submit")%></button>
                        </li>
                        <%--
                        {{#error}}
                        <div class="alert alert-danger" role="alert">
                            {{error}}
                        </div>
                        {{/error}}
                        --%>
                    </ul>
                </form>
            </div>
        </div>
        <!--    right part of the screen, balance and orders    -->
        <div class="card col-8">
       <%--     {{#promos-list}}
            <div class="card text-white bg-primary">
                <div class="card-header">Discount - {{multiplier}} percent off!</div>
                <div class="card-body">
                    <div class="row">
                        <form action="/api/neworder/discount">
                            <button class="btn btn-secondary" type="submit" value="{{id}}" name="discountId">
                                Use
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            {{/promos-list}}
            --%>
        </div>
    </div>
</div>

</body>
</html>