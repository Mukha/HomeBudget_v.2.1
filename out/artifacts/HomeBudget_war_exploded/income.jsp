<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.User" %>
<%@ page import="utils.DBUtilUser" %>
<%@ page import="utils.DBUtilCategory" %>
<%@ page import="utils.DBUtilIncome" %>
<%@ page import="entities.Income" %>
<%@ page import="entities.Category" %>
<!-- Page scoped bean for a common header and footer. -->
<jsp:useBean id="tags" scope="page" class='classes.CommonTags' />
<%
    User user = (User) session.getAttribute("Account");
%>
<% DBUtilUser dbu = new DBUtilUser(); %>
<% DBUtilCategory dbc = new DBUtilCategory(); %>
<% DBUtilIncome dbi = new DBUtilIncome();%>
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
            <!--<div class="col-xs-6 col-sm-3 placeholder">
                <img data-src="holder.js/200x200/auto/sky" class="img-responsive" alt="Generic placeholder thumbnail">
                <h4>Label</h4>
                <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
                <img data-src="holder.js/200x200/auto/vine" class="img-responsive" alt="Generic placeholder thumbnail">
                <h4>Label</h4>
                <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
                <img data-src="holder.js/200x200/auto/sky" class="img-responsive" alt="Generic placeholder thumbnail">
                <h4>Label</h4>
                <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
                <img data-src="holder.js/200x200/auto/vine" class="img-responsive" alt="Generic placeholder thumbnail">
                <h4>Label</h4>
                <span class="text-muted">Something else</span>
            </div>-->
        </div>

        <h2 class="sub-header">My incomes</h2>
        <div id="chart"></div>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>№</th>
                    <th>Category</th>
                    <th>Date</th>
                    <th>Amount (KZT) </th>
                    <th>Description</th>
                </tr>
                </thead>
                <tbody>
                <% ArrayList<Income> incomes = (ArrayList) dbi.findIncomes(user.getUserId());
                    Category cat;
                    int count = 1;
                for (Income in : incomes) {
                    cat = (Category) dbc.findById(in.getCategoryId());%>
                <tr>
                    <td><%=count%></td>
                    <td><%=cat.getCategoryName()%></td>
                    <td><%=in.getDate()%></td>
                    <td><%=in.getSize()%></td>
                    <td><%=in.getDescription()%></td>
                </tr>
                <%count++;}%>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>
<%=tags.getJquery()%>
<script type="text/javascript">
    $(document).ready(function () {
        $(function () {
            $('#chart').highcharts({
                chart: {
                    type: 'line'
                },
                title: {
                    text: 'Monthly Incomes'
                },
                subtitle: {
                    text: ''
                },
                xAxis: {
                    categories : ['September', 'October', 'November']
                },
                yAxis: {
                    title: {
                        text: 'KZT'
                    }
                },
                plotOptions: {
                    line: {
                        dataLabels: {
                            enabled: true
                        },
                        enableMouseTracking: false
                    }
                },
                series: [
                        <%
                        ArrayList<Category> cats = (ArrayList) dbc.findAll();
                        Category cTemp = cats.get(cats.size()-1);
                        for (Category category : cats) {

                        out.println("{");
                        out.println(" name:'" + category.getCategoryName() + "',");
                        out.println(" data: [ ");

                        ArrayList<Income> ins = (ArrayList) dbi.findIncomesByCategory(category.getCategoryId());

                        Income temp = ins.get(ins.size()-1);
                            for (Income in : ins) {
                                    out.print(in.getSize());
                                    if (in.getId() != temp.getId()) out.print(", ");
                            }
                        out.println("]");
                            if (cTemp.getCategoryId() == category.getCategoryId()){
                                out.println("}");
                            } else out.println("},");

                        }
                        %>
                ]
            });
        });
    });
</script>
</body>
</html>
