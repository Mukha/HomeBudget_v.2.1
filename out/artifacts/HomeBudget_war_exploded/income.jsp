<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.User" %>
<%@ page import="utils.DBUtilUser" %>
<%@ page import="utils.DBUtilCategory" %>
<%@ page import="utils.DBUtilIncome" %>
<%@ page import="entities.Income" %>
<%@ page import="entities.Category" %>
<jsp:useBean id="tags" scope="page" class='classes.CommonTags'/>
<%
    User user = (User) session.getAttribute("Account");
%>
<% DBUtilUser dbu = new DBUtilUser(); %>
<% DBUtilCategory dbc = new DBUtilCategory(); %>
<% DBUtilIncome dbi = new DBUtilIncome();%>

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

        <h2 class="sub-header">My incomes</h2>
        <% ArrayList<Income> incomes = (ArrayList) dbi.findIncomes(user.getUserId());
            if (!incomes.isEmpty()) {
                out.println("<div id = \"chart\" ></div ><hr>");
                out.println("<div id = \"chart2\" ></div >");
            } %>
        <hr>
        <div class="table-responsive">

            <%
                if (incomes.isEmpty()) {
                    out.println("<h4>You have no income.</h4><br>");
                    out.println("<p>");
                    out.println("<a href=\"Action?type=addinc&id=" + user.getUserId() + "\" class=\"btn btn-primary btn-lg active\" role=\"button\">" +
                            "Add income</a>");
                    out.println("</p>");
                } else {

                    out.println("<h4 class=\"sub-header\">Table of the incomes</h4><table class=\"table table-striped\">" +
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
                    for (Income in : incomes) {
                        cat = (Category) dbc.findById(in.getCategoryId());%>
            <tr>
                <td><%=count%>
                </td>
                <td><%=cat.getCategoryName()%>
                </td>
                <td><%=in.getDate()%>
                </td>
                <td><%=in.getSize()%>
                </td>
                <td><%=in.getDescription()%>
                </td>
                <td><a href="Action?type=income&id=<%=in.getId()%>">
                    <img src="img/edit.png" width="25" height="25"></a></td>
                <td><a href="Action?type=delincome&id=<%=in.getId()%>">
                    <img src="img/delete.png" width="25" height="25"></a></td>
            </tr>
            <%
                    count++;
                }
            %>
            <% out.println("</tbody></table><p><a href=\"Action?type=addinc&id=" +
                    user.getUserId() +
                    "\" class=\"btn btn-primary btn-lg active\" role=\"button\">Add income</a>"+
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
                    text: 'Incomes by categories'
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
                        name: 'Incomes',
                        data: [<%
                        ArrayList<Category> cats = (ArrayList) dbc.findAllForIncomes();
                        Category cTemp = cats.get(cats.size()-1);
                        for (Category category : cats) {
                            ArrayList<Income> ins = (ArrayList) dbi.findIncomesByCategory(category.getCategoryId());
                            if (!ins.isEmpty()) {
                                double sum = 0;
                                out.println("['" + category.getCategoryName() + "',");
                                    for(Income in : ins) {
                                        if (in.getUserId() == user.getUserId()) {
                                        sum+=in.getSize();}
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
                    text: 'Monthly Incomes'
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
                    ArrayList<Category> cats1 = (ArrayList) dbc.findAllForIncomes();
                    Category cTemp1 = cats1.get(cats1.size()-1);
                    for (Category category : cats1) {

                    ArrayList<Income> ins = (ArrayList) dbi.findIncomesByCategory(category.getCategoryId());
                    if (!ins.isEmpty()){
                        out.println("{");
                        out.println(" name:'" + category.getCategoryName() + "',");
                        out.println(" data: [ ");

                        Income temp = ins.get(ins.size()-1);
                            for (Income in : ins) {
                                    if (in.getUserId() == user.getUserId()){
                                        out.print(in.getSize());
                                        if (in.getId() != temp.getId()) out.print(", ");
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
