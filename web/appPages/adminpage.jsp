<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: myself
  Date: 4/29/20
  Time: 7:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminPage</title>
</head>
<body>
    <h1>Admin page <%= ((Map<String, Object>)request.getAttribute("model")).get("hehe")%></h1>

</body>
</html>
