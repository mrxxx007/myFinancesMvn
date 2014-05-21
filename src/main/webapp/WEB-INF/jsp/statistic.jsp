<!DOCTYPE HTML>
<%@ page import="main.com.myFinances.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="main.com.myFinances.service.UserServiceImpl" %>
<%@ page import="main.com.myFinances.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>

    <script src="jquery.js" type="text/javascript"></script>
    <%--<script src="../../bootstrap/js/bootstrap.js"></script>--%>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>

    <script src="${pageContext.request.contextPath}/resources/chart.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/legend.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/statistic.js" type="text/javascript"></script>



    <%--<link href="../../bootstrap/css/bootstrap.css" rel="stylesheet">--%>
    <%--<link href="../../bootstrap/css/bootstrap-responsive.css" rel="stylesheet">--%>

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/resources/legend.css" rel="stylesheet">


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

    <script>
        $(function() {
            $( "#fromdate" ).datepicker();
            $( "#todate" ).datepicker();
        });
    </script>

    <title>View Statistic</title>

</head>

<body>

<%--
    List<Category> categories = UserServiceImpl.getInstance().getAllCategories();
    request.setAttribute("categories", categories);
--%>

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
                    <form class="form-inline" class="active" action="statistic.html" method="GET">
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
        <div>
            <div class="pull-right">
                <input type="button" value="Line chart" class="btn btn-small btn-primary" id="lineBtn" onclick="showLineChart();"/>
                <input type="button" value="Pie chart" class="btn btn-small btn-default" id="pieBtn" onclick="showPieChart();"/>
            </div>
            <div><h3>Fill in gaps what statistic you want to view: </h3></div>

            <div style="float: left">
                <form:form modelAttribute="param" id="statisticForm" class="form-horisontal" method="post">

                    <div class="dropdown">
                        <div class="control-group">
                            <label class="control-label" for="category">Category:</label>
                            <div class="controls">

                                <form:select path = "categoryID" id = "category" name="category">
                                    <form:option value ="-1" label="ALL"/>
                                    <c:forEach var="categ" items="${categories}">
                                        <%--<c: ${categ.name==note.category.name? "selected" :""} /c>--%>
                                        <form:option value ="${categ.id}" label="${categ.name}"/>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="fromdate">Date:</label>
                        <div class="controls">
                            <form:input path = "fromdate" type="text" id="fromdate" name = "fromdate" value="${param.fromdate}"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="todate">Date:</label>
                        <div class="controls">
                            <form:input path = "todate" type="text" id="todate" name = "todate" value="${param.todate}"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="amount">Amount more that:</label>
                        <div class="controls">
                            <form:input path="minamount" type="text" id="amount" name="amount" placeholder="money amount" value = "${param.minamount}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="submit" value="View Statistic With Selected Filters" class="btn btn-primary" onclick="return checkForm()">
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
        <div hidden="false" class="pull-right" id="lineChartDiv">
            <canvas id="lineChart" width="600" height="400"></canvas>
        </div>
        <div id="pieChartDiv" class="pull-right">
            <%--<div>--%>
                <span id="pieLegend" style="float: right; width: 170px"></span>
                <div class="pull-right"><canvas id="pieChart" width="400" height="400"></canvas></div>
            <%--</div>--%>

        </div>
    </div>
    <%--xxxxxxxx--%>
    <div>
        <table class="table table-striped">
            <caption><h3>Statistic Filter Result </h3></caption>

            <tr>
                <th>Amount</th>
                <th>Category</th>
                <th>Date</th>
                <th>Info</th>
                <th> </th>
                <th> </th>
            </tr>

            <c:forEach var="note" items="${filterednotes}">
                <tr>
                    <td><c:out value="${note.amount}"></c:out></td>
                    <td><c:out value="${note.category.name}"></c:out></td>
                    <td><c:out value="${note.date}"></c:out></td>
                    <td><c:out value="${note.info}"></c:out></td>
                    <td><a href = '<c:url value="/saveNote${note.id}.html"/>'>change this note</a></td>
                    <td><a href = '<c:url value="/removeNote${note.id}.html"/>'>remove this note</a></td>
                </tr>

            </c:forEach>
        </table>
    </div>
</div>


<script type="application/javascript">
    var months = ["January","February","March","April","May","June","July","August","September","October","November","December"];

    function showPieChart() {
        $("#lineChartDiv").hide();
        $("#pieChartDiv").show();

        $("#lineBtn").attr("class","btn btn-small btn-default");
        $("#pieBtn").attr("class","btn btn-small btn-primary");

        drawPieChart();
    }

    function showLineChart() {
        $("#pieChartDiv").hide();
        $("#lineChartDiv").show();

        $("#pieBtn").attr("class","btn btn-small btn-default");
        $("#lineBtn").attr("class","btn btn-small btn-primary");

        drawLineChart();
    }

    function parseDate(input) {
        var parts = input.split('-');
        // new Date(year, month [, day [, hours[, minutes[, seconds[, ms]]]]])
        return new Date(parts[0], parts[1]-1, parts[2]); // Note: months are 0-based
    }
    function daysInMonth(year, month) {
        return new Date(year, month, 0).getDate();
    }

    function drawLineChart() {
        var ctx = $("#lineChart").get(0).getContext("2d");
        var labels = new Array();
        var chartArray;

        var startDate = new Date(document.getElementById('fromdate').value);
        var endDate = new Date(document.getElementById('todate').value);

        if (startDate.getMonth() != endDate.getMonth()) {
            for (i = startDate.getMonth(); i <= endDate.getMonth(); i++) {
                labels.push(months[i]);
            }
            chartArray = new Array(12);
            for(i = 0; i < chartArray.length; i++) {
                chartArray[i]=0;
            }
            <c:forEach var="note" items="${filterednotes}">
                dateObj = parseDate('${note.date}');
                chartArray[dateObj.getMonth()-startDate.getMonth()] += ${note.amount};
            </c:forEach>
        } else {
            monthDays = daysInMonth(startDate.getYear(), startDate.getMonth());
            chartArray = new Array(monthDays);
            for(i = 0; i < chartArray.length; i++) {
                chartArray[i]=0;
            }

            for(i = startDate.getDate(); i <= endDate.getDate(); i++) {
                labels.push(i);
            }
            <c:forEach var="note" items="${filterednotes}">
                dateObj = parseDate('${note.date}');
                chartArray[dateObj.getDate() - startDate.getDate()] += ${note.amount};
            </c:forEach>
        }

        var lineChartData = {
            labels: labels,
            datasets: [
                {
                    fillColor : "rgba(151,187,205,0.5)",
                    strokeColor : "rgba(151,187,205,1)",
                    pointColor : "rgba(151,187,205,1)",
                    pointStrokeColor : "#fff",
                    data: chartArray
                }
            ]
        };
        new Chart(ctx).Line(lineChartData);
    }

    function drawPieChart() {
        var ctx = $("#pieChart").get(0).getContext("2d");
        var pieColors = ["#4661EE","#EC5657", "#1BCDD1", "#8FAABB",
            "#B08BEB", "#3EA0DD", "#F5A52A", "#23BFAA"];
        var pieData = new Array();

        <c:forEach var="categ" items="${categories}">
            pieData.push({value: 0, color: "FFFFFF"});
        </c:forEach>

        var total = 0;

        <c:forEach var="note" items="${filterednotes}">
            pieData[${note.category.id} - 1].value += ${note.amount};
            pieData[${note.category.id} - 1].color = pieColors[${note.category.id}];
            pieData[${note.category.id} - 1].title = '${note.category.name}';

            total += ${note.amount};
        </c:forEach>

        for (var i in pieData) {
            pieData[i].title += " (" + Math.round(pieData[i].value / total * 100) + "%)";
        }
        var options =  {animation: 'true',
            labelAlign: 'center',
            labelFontSize: 14
        };
        var pieChart = new Chart(ctx).Pie(pieData, options);

        legend(document.getElementById("pieLegend"), pieData);
    }

    showLineChart();
</script>
</body>
</html>