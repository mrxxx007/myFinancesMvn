<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>

    <script src="jquery.js" type="text/javascript"></script>

    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/login.js"></script>

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

    <title>Log into FinancerApp</title>
</head>

<body>

<div class="container">
    <div class="navbar">
        <div class="navbar-inner">
            <a class="brand" href="#">MyFinancer<sup>&#x00A9;</sup></a>
        </div>
    </div>

    <%--<div>  </div>--%>

    <div class="hero-unit pagination-centered" style="width: 400px; margin: 0 auto">
        <h3>Sign In </h3>
        <form:form modelAttribute="user" class="form-horizontal pagination-centered" method = "POST">
            <div class="control-group">

                <label class="control-label" for="login">Login:</label>
                <div class="controls">
                    <form:input path="login" type="text" class="form-control"
                                id="login" name="login"
                                placeholder="enter login" value=""/>
                    <form:errors path="login"/>
                </div>

            </div>

            <div class="control-group">
                <label class="control-label" for="password">Password:</label>
                <div class="controls">
                    <form:input path="password" type="password" class="form-control" id="password" name="password"
                           placeholder="enter password" value=""/>
                    <form:errors path="password"/>
                </div>
            </div>

            <div class="control-group">
                <div >
                    <button type="submit" id="loginbtn" name="loginbtn" onclick="return checkForm();" class="btn btn-primary">Login</button>
                    &nbsp;&nbsp;<a href = "register.html" class="btn btn-link">Register </a>
                </div>
            </div>

        </form:form>

    </div>

</div>

</body>
</html>