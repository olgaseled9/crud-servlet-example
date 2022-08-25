<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="items" type="java.util.List<by.seledtsova.dto.ItemDto>" scope="request"/>
<html>
<head>
    <title>List of items</title>
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
        <h3>List of items</h3>
        <hr>
        <div>
            <a href="<%=request.getContextPath()%>/items/edit">Add</a>
        </div>
        <br>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td><c:out value="${fn:escapeXml(item.name)}"/></td>
                    <td><c:out value="${fn:escapeXml(item.description)}"/></td>
                    <td>
                        <a href="items/edit?id=<c:out value='${item.id}'/>">Edit</a>
                    </td>
                    <td>
                        <a href="items/delete?id=<c:out value='${item.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>