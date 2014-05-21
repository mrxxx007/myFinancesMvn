<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>

    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/register.js"></script>

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

    <title>Register</title>
</head>

<body>

<div class="container">
    <div class="navbar">
        <div class="navbar-inner">
            <a class="brand" href="#">MyFinancer<sup>&#x00A9;</sup></a>
        </div>
    </div>

    <div class="hero-unit pagination-centered" style="width: 400px; margin: 0 auto">
        <h3>Registration in MyFinancer </h3>
        <form:form modelAttribute="user" class="form-horizontal pagination-centered" method="POST">

            <div class="control-group">
                <label for="login" class="col-sm-2 control-label">Your login:</label>

                <div class="col-sm-10">
                    <form:input path="login" type="text" class="form-control" id="login" name="login"
                                placeholder="enter login"
                                pattern="^\w+$"
                                data-pattern-error="Only letters and numbers allowed"
                            />
                    <form:errors path="login"/>

                </div>
            </div>

            <div class="control-group">
                <label for="password" class="col-sm-2 control-label">Your password: </label>

                <div class="col-sm-10">
                    <form:input path="password" type="password" class="form-control"
                                id="password" name="password" placeholder="enter password"
                                value=""
                                pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"
                                onchange="this.setCustomValidity(this.validity.patternMismatch ?
                            'Password must contain at least 6 characters, including UPPER/lowercase and numbers' : '');
                            if(this.checkValidity()) form.repeatpassword.pattern = this.value;"/>

                </div>
            </div>

            <div class="control-group">
                <label for="repeatpassword" class="col-sm-2 control-label">Repeat password: </label>

                <div class="col-sm-10">
                    <input type="password" class="form-control" id="repeatpassword" name="repeatpassword"
                           value=""
                           placeholder="repeat password"
                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}">

                </div>
            </div>

            <div class="col-sm-10">
                <a href = "login.html" class="btn btn-link"><< Back </a>
                &nbsp;&nbsp;<button type="submit" onclick="return checkForm(this);" class="btn btn-primary">Register</button>
            </div>

        </form:form>

    </div>
</div>
>
</body>
</html>