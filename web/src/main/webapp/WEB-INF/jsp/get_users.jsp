<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="users" type="java.util.List<by.seledtsova.dto.UserDto>" scope="request"/>
<html>
<head>
    <title>List of users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header>
    <nav>
        <jsp:include page="header.jsp"/>
    </nav>
</header>
<br>
<div>
    <div>
        <h3>List of users</h3>
        <hr>
        <div>
            <a href="<%=request.getContextPath()%>/users/edit">Add</a>
        </div>
        <br>
        <table>
            <tr>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Login</th>
                <th>Password</th>
                <th>Role</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${fn:escapeXml(user.firstname)}"/></td>
                    <td><c:out value="${fn:escapeXml(user.lastname)}"/></td>
                    <td><c:out value="${fn:escapeXml(user.login)}"/></td>
                    <td><c:out value="${fn:escapeXml(user.password)}"/></td>
                    <td><c:out value="${fn:escapeXml(user.role)}"/></td>
                    <td>
                        <a href="users/edit?id=<c:out value='${user.userId}'/>">Edit</a>
                    </td>
                    <td>
                        <a href="users/delete?id=<c:out value='${user.userId}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>