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
    <div class="card col-lg-5 col-8">
        <div class="card-header">
            Additional driver details
        </div>
        <div class="card-body">
            <form action="/driver/newCar" method="post">
                <div class="list-group list-group-flush">
                    <input type="text" name="carModel" placeholder="Car model"/>
                </div>
                <div class="list-group list-group-flush">
                    <label>
                        <input type="radio" name="carType" value="BUSINESS" checked> Business
                    </label>
                    <label>
                        <input type="radio" name="carType" value="PREMIUM" > Premium
                    </label>
                    <label>
                        <input type="radio" name="carType" value="BUDGET" > Budget
                    </label>
                </div>
                <div class="list-group list-group-flush">
                    <input type="submit">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
