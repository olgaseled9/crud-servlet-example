<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Opps...error</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #d53ad3">
        <ul class="navbar-nav">
            <a href="<%=request.getContextPath()%>/items" class="nav-link">Items</a>
        </ul>
    </nav>
</header>
<br>
<div>
    <h3 class="text-center">Opps. We can't seem the page you are looking for!!!</h3>
</div>
<hr>
<!--

    <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
        <c:forEach  items="${exception.stackTrace}" var="ste">    ${ste}
    </c:forEach>
  -->
</body>
</html>