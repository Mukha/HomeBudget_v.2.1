<%-- 
    Document   : index
    Created on : 08.09.2014, 19:36:13
    Author     : Мадина
--%>
<jsp:useBean id="tags" scope="page" class="classes.CommonTags"/>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home Budget</title>
    <%=tags.getLinks("index")%>
</head>
<body>
<div class="container">
    <div class="header">
        <%=tags.getHeader("index")%>
    </div>

    <div class="jumbotron">
        <h1>Welcome!</h1>

        <p class="lead">This web-application allows to manage the household budget.</p>
        <!--<img src="img/HouseholdBudget.jpg" class="img-responsive" width="400" height="300">
        <!--<p><a class="btn btn-lg btn-success" href="#" role="button">Sign up today</a></p>-->
    </div>

    <div class="row marketing">
        <div class="col-lg-6">
            <h4>Manage Income</h4>

            <p>Add, delete, retrieve and maintain all your income records.</p>

            <h4>Manage Expenditure</h4>

            <p>Add, delete, retrieve and maintain all your expense records.</p>

            <!--<h4>Statistics</h4>

            <p>Get annual and monthly statistics.</p>-->
        </div>

        <div class="col-lg-6">
            <h4>Statistics</h4>

            <p>Get annual and monthly statistics</p>

            <h4>Forecasting</h4>

            <p>Analyze the cash flow and get the future budget transactions.</p>

            <!--<h4>Subheading</h4>

            <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>-->
        </div>
    </div>

    <div class="footer">
        <%=tags.getFooter()%>
    </div>
</div>
<!-- /container -->
<%=tags.getJquery()%>
</body>
</html>
