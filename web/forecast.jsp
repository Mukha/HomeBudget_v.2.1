<%@ page import="utils.DBUtilIncome" %>
<%@ page import="utils.DBUtilExpense" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="entities.User" %>
<jsp:useBean id="tags" scope="page" class='classes.CommonTags'/>
<%
    // Get an error messages in the request object.
    String type = (request.getParameter("type") == null)
            ? ""
            : request.getParameter("type");
    User user = (User) session.getAttribute("Account");
    double mInc=0, mExp=0, temp=0, n = 0;
    String month = "", m = "";
%>
<% DBUtilIncome dbi = new DBUtilIncome();%>
<% DBUtilExpense dbe = new DBUtilExpense();%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><%=tags.getLinks("account")%>
</head>
<body>
<%=tags.getHeader("account")%>

<div class="container-fluid">
    <%=tags.getHeader("menu")%>

    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <%if(type.equals("default")){%>
        <div class="row">
                <h1>Forecast</h1>
                <hr>
                <h3 align="justify">The forecasting gives opportunity to see the
                difference between income and expenses. The forecasting shows
                how much money you have saved or how much money you have lost.
                In addition, it predicts the future cash flows.</h3>
                <br>
                <h4>In order to get forecasting figures choose the month to be analyzed:</h4>
                <form class="form" role="form" action="calc" method="GET">

                    <br>

                    <select class="form-control" name="monthnum" style="width: 50%">
                        <option value="01">January</option>
                        <option value="02">February</option>
                        <option value="03">March</option>
                        <option value="04">April</option>
                        <option value="05">May</option>
                        <option value="06">June</option>
                        <option value="07">July</option>
                        <option value="08">August</option>
                        <option value="09">September</option>
                        <option value="10">October</option>
                        <option value="11">November</option>
                        <option value="12">December</option>
                    </select>

                    <br>
                    <button class="btn btn-lg btn-primary btn-block" type="submit" style="width: 15%">Submit</button>
                    <input type="hidden" name="type" value="forecast">
                </form>
            </div>
            <%} if (type.equals("display")){
                month = request.getParameter("month");

                if (month.equals("01")){
                    m = "January";
                } else if (month.equals("02")){
                    m = "February";
                } else if (month.equals("03")){
                    m = "March";
                } else if (month.equals("04")){
                    m = "April";
                } else if (month.equals("05")){
                    m = "May";
                } else if (month.equals("06")){
                    m = "June";
                } else if (month.equals("07")){
                    m = "July";
                } else if (month.equals("08")){
                    m = "August";
                } else if (month.equals("09")){
                    m = "September";
                } else if (month.equals("10")){
                    m = "October";
                } else if (month.equals("11")){
                    m = "November";
                } else {
                    m = "December";
                }

                if (month!=null) {
                    mInc = dbi.findIncomesByMonth(month,user.getUserId());
                    mExp = dbe.findExpenseByMonth(month,user.getUserId());
                }

                if (mInc==0 && mExp==0) {%>
                    <h2>There are no income and expenses for this month. </h2>
                    <br>
                    <a class="btn btn-default" href="forecast.jsp?type=default" role="button" h>Choose another month</a>
            <% } else {
                temp = mInc-mExp;
                if (temp > 0) {
                    n = temp + mInc;
            %>
        <h2>Current budget forecast</h2>
        <hr>
        <h4>This chart illustrates the cash flow for the chosen month.</h4>
        <h5>You have <%=temp%> (KZT) saved this month.</h5>
        <div id="chart"></div>
            <h4>This is the <i>approximate</i> budget plan for the next month (saved cash included).</h4>
        <div id="chart2"></div>
        <%} else if(temp <  0){
            n = mInc - temp;
        %>
        <h2>Current budget forecast</h2>
        <hr>
        <h4>This chart illustrates the cash flow for the chosen month.</h4>
        <h5>You have <%=temp%> (KZT) lost this month.</h5>
        <div id="chart"></div>
        <h4>This is the <i>approximate</i> budget plan for the next month (lost cash included).</h4>
        <div id="chart3"></div>
        <%}}}%>
    </div>
</div>
<%=tags.getJquery()%>
<script type="text/javascript">
    $(function () {
        $('#chart').highcharts({
            chart: {
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45
                }
            },
            title: {
                text: 'Cash flow for <%=m%>'
            },
            subtitle: {
                text: ''
            },
            plotOptions: {
                pie: {
                    innerSize: 100,
                    depth: 45
                }
            },
            series: [{
                name: 'Delivered amount',
                data: [
                    ['Income', <%=mInc%>],
                    ['Expenses', <%=mExp%>]

                ]
            }]
        });
    });
</script>
<script type="text/javascript">
    $(function () {
        $('#chart2').highcharts({
            chart: {
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45
                }
            },
            title: {
                text: 'Approximate cash flow for the next month'
            },
            subtitle: {
                text: ''
            },
            plotOptions: {
                pie: {
                    innerSize: 100,
                    depth: 45
                }
            },
            series: [{
                name: 'Delivered amount',
                data: [
                    ['Income', <%=n%>],
                    ['Expenses', <%=mExp%>]

                ]
            }]
        });
    });
</script>
<script type="text/javascript">
    $(function () {
        $('#chart2').highcharts({
            chart: {
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45
                }
            },
            title: {
                text: 'Approximate cash flow for the next month'
            },
            subtitle: {
                text: ''
            },
            plotOptions: {
                pie: {
                    innerSize: 100,
                    depth: 45
                }
            },
            series: [{
                name: 'Delivered amount',
                data: [
                    ['Income', <%=n%>],
                    ['Expenses', <%=mExp%>]

                ]
            }]
        });
    });
</script>
</body>
</html>