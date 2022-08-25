<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<br>
<br>
<h1 class="text-center" style="color: #c04a7a">CRUD SERVLET EXAMPLE</h1>
<hr>
<a href="${pageContext.request.contextPath}/items"><h2 class="text-center" style="color: #04072f">List of Items</h2></a>
<a href="${pageContext.request.contextPath}/users"><h2 class="text-center" style="color: #04072f">List of Users</h2></a>
</body>
</html>