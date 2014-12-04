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

        <%
            User user = (User) session.getAttribute("Account");
            String month = request.getParameter("month");
            int year = Calendar.getInstance().get(Calendar.YEAR);
            String query = Integer.toString(year);
            double yearInc = dbi.findIncomesByYear(query,user.getUserId());
            double yearExp = dbe.findExpenseByYear(query,user.getUserId());
            double mInc = 0;
            double mExp = 0;


        %>

        <%if (type.equals("month")){%>
        <div class="row placeholders">

            <h3>The budget statistics</h3>
                <form class="form" role="form" action="calc" method="GET">
                    <h4 align="left">Choose the month to retrieve the statistics:</h4>
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
                    <input type="hidden" name="type" value="monthstat">
                </form>
                <img src="img/inc_vs_exp.jpg" width="450" height="300" align="right">
        </div>
        <%}%>

        <%if (type.equals("ivse")){
            if (yearInc==0 && yearExp==0) {%>
        <h2>There are no income and expenses for <%=year%> year. </h2>
        <br>
        <a class="btn btn-default" href="Action?type=addinc" role="button" h>Add income</a>
        <a class="btn btn-default" href="Action?type=addexp" role="button" h>Add expense</a>
        <% } else { %>
        <h2 align="center">The current budget statistics</h2>
        <div id="chart"></div>
        <div class="row placeholders">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Budget</th>
                            <th>Amount (KZT)</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr><td>Income</td><td><%=yearInc%></td></tr>
                        <tr><td>Expenses</td><td><%=yearExp%></td></tr>
                    </tbody>
            </table>
        </div>
         <%}}%>
        <%if (type.equals("display")){

            if (month!=null) {
                mInc = dbi.findIncomesByMonth(month,user.getUserId());
                mExp = dbe.findExpenseByMonth(month,user.getUserId());
            }

            if (mInc==0 && mExp==0) {%>
                <h2>There are no income and expenses for this month. </h2>
                <br>
                <a class="btn btn-default" href="statistics.jsp?type=month" role="button" h>Choose another month</a>
            <% } else { %>

        <h2 align="center">The budget statistics</h2>
        <div id="chart2"></div>
        <div class="row placeholders">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Budget</th>
                    <th>Amount (KZT)</th>
                </tr>
                </thead>
                <tbody>
                <tr><td>Income</td><td><%=mInc%></td></tr>
                <tr><td>Expenses</td><td><%=mExp%></td></tr>
                </tbody>
            </table>
            <a class="btn btn-default" href="statistics.jsp?type=month" role="button" h>Choose another month</a>
        </div>
        <%}}%>
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
                    alpha: 45,
                    beta: 0
                }
            },
            title: {
                text: 'Income vs Expenses, <%=year%>'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    depth: 35,
                    dataLabels: {
                        enabled: true,
                        format: '{point.name}'
                    }
                }
            },
            series: [{
                type: 'pie',
                name: 'Browser share',
                data: [
                    ['Incomes',   <%=yearInc%>],
                    ['Expenses',   <%=yearExp%>]
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
                    alpha: 45,
                    beta: 0
                }
            },
            title: {
                text: 'Income vs Expenses, <%=month%>'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    depth: 35,
                    dataLabels: {
                        enabled: true,
                        format: '{point.name}'
                    }
                }
            },
            series: [{
                type: 'pie',
                name: 'Browser share',
                data: [
                    ['Incomes',   <%=mInc%>],
                    ['Expenses',   <%=mExp%>]
                ]
            }]
        });
    });
</script>
</body>
</html>