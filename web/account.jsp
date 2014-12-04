<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.User" %>
<%@ page import="utils.DBUtilUser" %>
<!-- Page scoped bean for a common header and footer. -->
<jsp:useBean id="tags" scope="page" class='classes.CommonTags'/>
<%
    User user = (User) session.getAttribute("Account");
%>
<% DBUtilUser dbUtil = new DBUtilUser(); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD html 4.0 transitional//EN">
<head>
    <%=tags.getLinks("account")%>
</head>

<body>
<%=tags.getHeader("account")%>

<div class="container-fluid">
    <%=tags.getHeader("menu")%>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <h1 class="page-header">Dashboard</h1>

        <div class="row placeholders">
            <p>Welcome, <%=user.getFname()%>!</p>

            <div class="col-xs-6 col-sm-3 placeholder">
                <img src="img/income_icon.jpg" class="img-responsive" width="100" height="100">
                <h4><a href="income.jsp">Income</a></h4>
                <span class="text-muted">Manage your income</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
                <img src="img/expense_icon.gif" class="img-responsive" width="100" height="100">
                <h4><a href="expense.jsp">Expense</a></h4>
                <span class="text-muted">Manage your expenditure</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
                <img src="img/stat.png" class="img-responsive" width="100" height="100">
                <h4><a href="statistics.jsp?type=ivse">Statistics</a></h4>
                <span class="text-muted">Statistics of your budget</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
                <img src="img/forecast.png" class="img-responsive" width="100" height="100">
                <h4><a href="forecast.jsp?type=default">Forecast</a></h4>
                <span class="text-muted">Forecasting figures of your budget</span>
            </div>
        </div>


        </div>
    </div>
</div>
</div>
<%=tags.getJquery()%>
</body>
</html>
