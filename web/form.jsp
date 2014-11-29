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
<%
    // Get an error messages in the request object.
    String form = (request.getParameter("form") == null)
            ? ""
            : request.getParameter("form");
%>
<% DBUtilCategory dbc = new DBUtilCategory(); %>
<% DBUtilIncome dbi = new DBUtilIncome();%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><%=tags.getLinks("account")%>
</head>
<body>
<%=tags.getHeader("account")%>

<div class="container-fluid">
    <%=tags.getHeader("menu")%>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <%if(form.equals("addinc")){%>
        <h2 class="sub-header">Add income</h2>
        <form class="form-signin" role="form" action="Action" method="GET">
            <h2 class="form-signin-heading">Type income details</h2>
            Date:
                <input  type="text" placeholder="Date" class="form-control"  id="date" style="width: 20%" name="date">
            <br>
            Amount (KZT): <input type="text" class="form-control" placeholder="Amount" style="width: 35%"><br>
            Category:
            <select class="form-control" name="category" style="width: 65%">
                <%ArrayList<Category> categories = (ArrayList) dbc.findAllForIncomes();
                for (Category cat : categories) {
                    out.println("<option value=" + cat.getCategoryId() +
                            ">" + cat.getCategoryName() + "</option>");
                }
                %>
            </select><br>
            Description: <textarea type="text" class="form-control" placeholder="Description" style="width: 65%">
            </textarea>
            <br>
            <button class="btn btn-lg btn-primary btn-block" type="submit" style="width: 25%">Submit</button>
            <input type="hidden" name="type" value="newinc">
        </form>
        <%}%>

        <% if(form.equals("addexp")){%>
        <h2 class="sub-header">Add expense</h2>
        <form class="form-signin" role="form" action="Action" method="GET">
            <h2 class="form-signin-heading">Type expense details</h2>
            Date:
            <input  type="text" placeholder="Date" class="form-control"  id="date1" style="width: 20%" name="date">
            <br>
            Amount (KZT): <input type="text" class="form-control" placeholder="Amount" style="width: 35%"><br>
            Category:
            <select class="form-control" name="category" style="width: 65%">
                <%ArrayList<Category> categories = (ArrayList) dbc.findAllForExpenses();
                    for (Category cat : categories) {
                        out.println("<option value=" + cat.getCategoryId() +
                                ">" + cat.getCategoryName() + "</option>");
                    }
                %>
            </select><br>
            Description: <textarea type="text" class="form-control" placeholder="Description" style="width: 65%">
        </textarea>
            <br>
            <button class="btn btn-lg btn-primary btn-block" type="submit" style="width: 25%">Submit</button>
            <input type="hidden" name="type" value="newinc">
        </form>
        <%}%>

    </div>
</div>
<%=tags.getJquery()%>
<script src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#date').datepicker({
            format: "dd-mm-yyyy"
        });
        $('#date1').datepicker({
            format: "dd-mm-yyyy"
        });
    });
</script>
</body>
</html>
