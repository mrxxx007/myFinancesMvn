<!DOCTYPE HTML>
<%@ page import="main.com.myFinances.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="main.com.myFinances.service.UserServiceImpl" %>
<%@ page import="main.com.myFinances.model.User" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


    <!-- fuelux styles-->

    <link href="http://www.fuelcdn.com/fuelux/2.4.0/css/fuelux.css" rel="stylesheet" />
    <link href="http://www.fuelcdn.com/fuelux/2.4.0/css/fuelux-responsive.css" rel="stylesheet" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
    <script src="http://www.fuelcdn.com/fuelux/2.4.0/loader.min.js" type="text/javascript"></script>

    <!-- calendar jQueryUI-->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script type="application/javascript">
        $(function() {
            $( "#date" ).datepicker();
            $("#category").combobox();

        });
        function showAddIncome() {
            $("#spentMoney").hide();
            $("#incomeMoney").show();
            $("#spentBtn").attr("class","btn btn-default");
            $("#incomeBtn").attr("class","btn btn-primary");
        }

        function showAddSpent() {
            $("#spentMoney").show();
            $("#incomeMoney").hide();
            $("#spentBtn").attr("class","btn btn-primary");
            $("#incomeBtn").attr("class","btn btn-default");
        }

    </script>

    <title>Add note</title>

</head>

<body>

<div class="container">

    <div class="navbar">
        <div class="navbar-inner">
            <a class="brand" href="#">MyFinancer<sup>©</sup></a>
            <ul class="nav">

                <li>
                    <form class="form-inline" action="home.html" method="GET">
                        <button type="submit" class="btn btn-link">Home</button>
                    </form>
                </li>

                <li>
                    <form class="form-inline" action="statistic.html" method="GET">
                        <button type="submit" class="btn btn-link">View statistic</button>
                    </form>
                </li>

                <li class="active">
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



    <c:if test="${showButtons eq 'true'}">
        <input type="button" id="spentBtn" value="Spent money" class="btn btn-primary" onclick="showAddSpent()">
        <input type="button" id="incomeBtn" value="Income money" class="btn btn-default" onclick="showAddIncome()">
        <hr>
    </c:if>
    <div><h3>Fill the form: </h3></div>
    <div id="spentMoney">
        <form:form modelAttribute="note" id="saveNoteForm" class="form-horisontal" method="post">

            <div class="control-group">
                <label class="control-label" for="amount">Amount:</label>
                <div class="controls">
                    <form:hidden path="id" />
                    <form:input path = "amount" type="text" id="amount" name="amount" placeholder="money amount" value="${note.amount}"/>
                </div>
            </div>

            <div class="dropdown">
                <div class="control-group">
                    <label class="control-label" for="category">Category:</label>
                    <div class="controls">

                        <form:select path = "category.id" id = "category" name="category">

                            <c:forEach var="categ" items="${categories}">
                                <%--<c: ${categ.name==note.category.name? "selected" :""} /c>--%>
                                <form:option value ="${categ.id}" label="${categ.name}"/>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>
            </div>


            <div class="control-group">
                <label class="control-label" for="date">Date:</label>
                <div class="controls">
                   <form:input path = "date" type="text" id="date" name = "date" />
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="info">Info:</label>
                <div class="controls">
                    <form:input path = "info" type="text" id="info" name="info" placeholder="outlay info" value="${note.info}"/>
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <input type="submit" value="Save note" class="btn btn-primary">
                </div>
            </div>

        </form:form>
    </div>

    <div id="incomeMoney" hidden="true">
        <form method="post" action="/saveSpent.html" class="form-horisontal">
            <label class="control-label" for="amount">Amount:</label>
            <input type="text" path="amount" name="amount" value="0.0">
            <div class="controls">
                <input type="submit" value="Save note" class="btn btn-primary">
            </div>
        </form>
    </div>

</div>

<script type="application/javascript">
    var dateVal = $("#date").val();
    if (dateVal != "") {
        var parts = dateVal.split('-');
        $("#date").val(parts[1]+"/"+parts[2]+"/"+parts[0]);
    }
</script>
</body>
</html>