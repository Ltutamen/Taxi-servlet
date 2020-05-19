<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoginPage</title>
</head>
<body>
<h1>Login Page</h1>
<div style="text-align: center;">
    <h2>Signup Details</h2>
    <form action="/login" method="post">
        <br/>Username:<input type="text" name="username">
        <br/>Password:<input type="password" name="password">
        <br/><input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
