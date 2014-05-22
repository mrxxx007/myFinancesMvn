<!DOCTYPE HTML>
<%@ page import="java.util.List" %>
<%@ page import="com.myFinances.model.User" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>

    <script src="jquery.js" type="text/javascript"></script>

    <script src="jquery.js" type="text/javascript"></script>

    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/login.js"></script>

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

    <title>Home</title>


</head>
<body>

<div class="container">

    <div class="navbar">
        <div class="navbar-inner">
            <a class="brand" href="#">MyFinancer<sup>©</sup></a>
            <ul class="nav">

                <li class="active">
                    <form class="form-inline" action="home.html" method="GET">
                        <button type="submit" class="btn btn-link">Home</button>
                    </form>
                </li>

                 <li>
                     <form class="form-inline" action="statistic.html" method="GET">
                         <button type="submit" class="btn btn-link">View statistic</button>
                     </form>
                 </li>

                 <li>
                     <form class="form-inline"  action="saveNote.html" method="GET">
                         <button type="submit" class="btn btn-primary">Add new note</button>
                     </form>
                 </li>

                 <li>
                     <form class="form-inline" action="logout.html" method="GET">
                         <button type="submit" class="btn btn-link">Logout</button>
                     </form>
                 </li>
            </ul>
            <div class="nav-header pull-right">
                You logged as <b><%= ((User)request.getSession().getAttribute("user")).getLogin()%></b><br>
                Your balance:
                <span id="balanceLbl"><%=((User)request.getSession().getAttribute("user")).getBalance()%></span>
                <script type="application/javascript" src="${pageContext.request.contextPath}/resources/balance.js"></script>
            </div>
        </div>
    </div>

    <div>
        <table class="table table-striped">
            <caption><h3>Your all notes</h3></caption>

            <tr>
                <th>Amount</th>
                <th>Category</th>
                <th>Date</th>
                <th>Info</th>
                <th> </th>
                <th> </th>
            </tr>

            <c:forEach var="note" items="${notes}">
                <tr>
                    <td><c:out value="${note.amount}"></c:out></td>
                    <td><c:out value="${note.category.name}"></c:out></td>
                    <td><c:out value="${note.date}"></c:out></td>
                    <td><c:out value="${note.info}"></c:out></td>
                    <td><a href = '<c:url value="/saveNote${note.id}.html"/>'>change this note</a></td>
                    <td><a href = '<c:url value="/removeNote${note.id}.html"/>' title="Remove" class="btn btn-danger btn-mini"> X </a></td>
                </tr>

            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>