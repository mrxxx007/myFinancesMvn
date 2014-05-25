<!DOCTYPE HTML>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <title>Welcome!</title>
</head>
<body>
    <div class="container">
        <div class="navbar">
            <div class="navbar-inner">
                <a class="brand" href="#">MyFinancer<sup>&#x00A9;</sup></a>
            </div>
        </div>
        <div class="hero-unit pagination-centered" style="width: 400px; margin: 0 auto">
            <h2>Welcome to MyFinancer!</h2>
        </div>
    </div>
    <jsp:forward page="/login.html"></jsp:forward>
</body>
</html>
