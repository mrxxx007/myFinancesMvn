<%@ page import="main.com.myFinances.service.UserServiceImpl" %>
<%@ page import="main.com.myFinances.model.Notes" %>
<%@ page import="java.util.List" %>
<%@ page import="main.com.myFinances.service.UserService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>

    <script src="jquery.js" type="text/javascript"></script>
    <script src="../../bootstrap/js/bootstrap.js"></script>

    <link href="../../bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="../../bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <title>Add note</title>
</head>
<body>

<%
    String login = (String) request.getSession().getAttribute("sessionID");
    List<Notes> notes = UserServiceImpl.getInstance().getAllNotesByUser(UserServiceImpl.getInstance().getUser(login));
    request.setAttribute("notes", notes);
%>

<div class="container">

    <div class="navbar">
        <div class="navbar-inner">
            <a class="brand" href="brand.jsp">MyFinancer</a>
            <ul class="nav">

                <li>
                    <form class="form-inline" action="home.jsp" method="GET">
                        <button type="submit" class="btn btn-link">Home</button>
                    </form>
                </li>

                <li>
                    <form class="form-inline" action="statistic.jsp" method="GET">
                        <button type="submit" class="btn btn-link">View statistic</button>
                    </form>
                </li>

                <li>
                    <form class="form-inline"  action="saveNote.jsp" method="GET">
                        <button type="submit" class="btn btn-primary">Add new note</button>
                    </form>
                </li>

                <li>
                    <form class="form-inline" action="logout.html" method="GET">
                        <button type="submit" class="btn btn-link">Logout</button>
                    </form>
                </li>
            </ul>
        </div>
    </div>


    <div>
        <h2>
            MyFinancer<br>
            We are perfect, join us!
        </h2>
    </div>

</div>
</body>
</html>