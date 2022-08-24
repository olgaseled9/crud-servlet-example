<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="item" type="by.seledtsova.dto.ItemDto" scope="request"/>
<html>
<head>
    <title>Item editing</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #d53ad3">
        <ul class="navbar-nav">
            <a href="<%=request.getContextPath()%>/items" class="nav-link">Items</a>
        </ul>
    </nav>
</header>
<body>
<br>
<h3>Item editing</h3>
<form action="${pageContext.request.contextPath}/items/edit" method="POST">
    <div class="error">
        <c:forEach var="error" items="${item.errors}">
            <div>${fn:escapeXml(error)}</div>
        </c:forEach>
    </div>
    <div>
        <label>
            <input type="hidden" name="id" value="${item.id}">
        </label>
    </div>
    <div>
        <p>Name</p>
        <input name="name" type="text" value="${fn:escapeXml(item.name)}">
    </div>
    <div>
        <p>Description</p>
        <input name="description" type="text" value="${fn:escapeXml(item.description)}">
    </div>
    <br>
    <button type="submit">Save</button>
    <button type="button" onclick="goBack()">Cancel</button>
    <script>
        document.querySelectorAll('event').forEach(event => {
            event.addEventListener('submit', (e) => {
                if (event.classList.contains('is-submitting')) {
                    e.preventDefault();
                }
                event.classList.add('is-submitting');
            });
        });
    </script>
</form>
<script>
    function goBack() {
        window.history.back();
    }
</script>
</body>
</html>
