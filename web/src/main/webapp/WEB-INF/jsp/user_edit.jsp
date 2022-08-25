<%@ page import="by.seledtsova.model.RoleValue" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="user" type="by.seledtsova.dto.UserDto" scope="request"/>
<html>
<head>
    <title>User editing</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<header>
    <nav>
        <jsp:include page="header.jsp"/>
    </nav>
</header>
<body>
<br>
<h3>User editing</h3>
<form action="${pageContext.request.contextPath}/users/edit" method="POST">
    <div>
        <label>
            <input type="hidden" name="id" value="${user.userId}">
        </label>
    </div>
    <div>
        <p>Firstname</p>
        <input name="firstname" type="text" value="${fn:escapeXml(user.firstname)}">
    </div>
    <div>
        <p>Lastname</p>
        <input name="lastname" type="text" value="${fn:escapeXml(user.lastname)}">
    </div>
    <div>
        <p>Login</p>
        <input name="login" type="text" value="${fn:escapeXml(user.login)}">
    </div>
    <div>
        <p>Password</p>
        <input name="password" type="text" value="${fn:escapeXml(user.password)}">
    </div>
    <div>
        <p>Role</p>
    <select required name="role">
        <c:set var="roleValues" value="<%=RoleValue.values()%>"/>
        <c:forEach var="roleValue" items="${roleValues}">
            <option
                    <c:if test="${roleValue.name() eq user.role.name()}">selected</c:if>
                    value="${roleValue.name()}">${roleValue.name()}</option>
        </c:forEach>
    </select>
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
