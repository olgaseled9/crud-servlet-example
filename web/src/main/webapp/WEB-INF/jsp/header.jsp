<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<nav class="navbar navbar-expand-md navbar-dark" style="background-color: #d53ad3">
    <ul class="navbar-nav">
        <a href="<%=request.getContextPath()%>/items" class="nav-link">Items</a>
    </ul>
    <ul class="navbar-nav">
        <a href="<%=request.getContextPath()%>/users" class="nav-link">Users</a>
    </ul>
</nav>
</body>
</html>
