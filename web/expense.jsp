<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.User" %>
<%@ page import="utils.DBUtilCategory" %>
<%@ page import="utils.DBUtilExpense" %>
<%@ page import="entities.Expense" %>
<%@ page import="entities.Category" %>
<jsp:useBean id="tags" scope="page" class='classes.CommonTags'/>
<%
    User user = (User) session.getAttribute("Account");
%>
<% DBUtilCategory dbc = new DBUtilCategory(); %>
<% DBUtilExpense dbe = new DBUtilExpense();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD html 4.0 transitional//EN">
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<head>
    <%=tags.getLinks("account")%>
</head>

<body>
<%=tags.getHeader("account")%>

<div class="container-fluid">
    <%=tags.getHeader("menu")%>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

        <h2 class="sub-header">My Expenses</h2>
        <% ArrayList<Expense> expenses = (ArrayList) dbe.findExpenses(user.getUserId());
            if (!expenses.isEmpty()) {
                out.println("<div id = \"chart\" ></div ><hr>");
                out.println("<div id = \"chart2\" ></div >");
            } %>
        <hr>
        <div class="table-responsive">
            <h4 class="sub-header">Table of the expenses</h4>
            <%
                if (expenses.isEmpty()) {
                    out.println("<p>");
                    out.println("<a href=\"Action?type=addexp\" class=\"btn btn-primary btn-lg active\" role=\"button\">" +
                            "Add Expense</a>");
                    out.println("</p>");
                } else {

                    out.println("<table class=\"table table-striped\">" +
                            "<thead>"
                            + "<tr>"
                            + "<th>№</th>"
                            + "<th>Category</th>"
                            + "<th>Date</th>"
                            + "<th>Amount (KZT) </th>"
                            + "<th>Description</th>"
                            + "<th><img src=\"img/edit.png\" width=\"25\" height=\"25\"></th>"
                            + "<th><img src=\"img/delete.png\" width=\"25\" height=\"25\"></th>"
                            + "</tr>"
                            + "</thead>"
                            + "<tbody>");
                    Category cat;
                    int count = 1;
                    for (Expense exp : expenses) {
                        cat = (Category) dbc.findById(exp.getCategoryId());%>
            <tr>
                <td><%=count%>
                </td>
                <td><%=cat.getCategoryName()%>
                </td>
                <td><%=exp.getDate()%>
                </td>
                <td><%=exp.getSize()%>
                </td>
                <td><%=exp.getDescription()%>
                </td>
                <td><a href="Action?type=expense&id=<%=exp.getId()%>">
                    <img src="img/edit.png" width="25" height="25"></a></td>
                <td><a href="Action?type=delexpense&id=<%=exp.getId()%>">
                    <img src="img/delete.png" width="25" height="25"></a></td>
            </tr>
            <%
                    count++;
                }
            %>
            <% out.println("</tbody></table><p><a href=\"Action?type=addexp" +
                    "\" class=\"btn btn-primary btn-lg active\" role=\"button\">Add Expense</a>"+
                    "</p>"+
                    "</div>");
            }%>
        </div>
    </div>
</div>
<%=tags.getJquery()%>
<script type="text/javascript">
    $(document).ready(function () {
        $(function () {
            $('#chart2').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: 1,//null,
                    plotShadow: false
                },
                title: {
                    text: 'Expenses by categories'
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [
                    {
                        type: 'pie',
                        name: 'Expenses',
                        data: [
                        <%
                        ArrayList<Category> cats = (ArrayList) dbc.findAllForExpenses();
                        Category cTemp = cats.get(cats.size()-1);
                        for (Category category : cats) {
                            ArrayList<Expense> exp = (ArrayList) dbe.findExpensesByCategory(category.getCategoryId());
                            if (!exp.isEmpty()) {
                                out.println("['" + category.getCategoryName() + "',");
                                double sum = 0;
                                    for(Expense e : exp) {
                                        if (e.getUserId() == user.getUserId()) {
                                        sum+=e.getSize();}
                                    }

                                if (cTemp.getCategoryId() == category.getCategoryId()){
                                    out.println(sum + "]");
                                } else out.println(sum + "],");
                            }
                        }
                    %>
                        ]
                    }
                ]
            });
        });

        $(function () {
            $('#chart').highcharts({
                chart: {
                    type: 'line'
                },
                title: {
                    text: 'Monthly Expenses'
                },
                subtitle: {
                    text: 'Year: 2014'
                },
                xAxis: {
                    categories: [/*'January', 'February', 'March', 'April',
                     'May', 'June', 'July', 'August', 'September', 'October',
                        'November', 'December'*/]
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
                    ArrayList<Category> cats1 = (ArrayList) dbc.findAllForExpenses();
                    Category cTemp1 = cats1.get(cats1.size()-1);
                    for (Category category : cats1) {

                    ArrayList<Expense> exp = (ArrayList) dbe.findExpensesByCategory(category.getCategoryId());
                    if (!exp.isEmpty()){
                        out.println("{");
                        out.println(" name:'" + category.getCategoryName() + "',");
                        out.println(" data: [ ");

                        Expense temp = exp.get(exp.size()-1);
                            for (Expense e : exp) {
                                    if (e.getUserId() == user.getUserId()){
                                        out.print(e.getSize());
                                        if (e.getId() != temp.getId()) out.print(", ");
                                    }
                            }
                        out.println("]");
                            if (cTemp1.getCategoryId() == category.getCategoryId()){
                                out.println("}");
                            } else out.println("},");
                    }
                    }
                    %>
                ]
            });
        });
    });
</script>
</body>
</html>
