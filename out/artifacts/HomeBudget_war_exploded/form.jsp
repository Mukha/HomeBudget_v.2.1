<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.User" %>
<%@ page import="utils.DBUtilUser" %>
<%@ page import="utils.DBUtilCategory" %>
<%@ page import="utils.DBUtilIncome" %>
<%@ page import="entities.Income" %>
<%@ page import="entities.Category" %>
<%@ page import="entities.Expense" %>
<%@ page import="utils.DBUtilExpense" %>
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
<% DBUtilExpense dbe = new DBUtilExpense();%>
<% DBUtilUser dbu = new DBUtilUser();%>
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
                <input  type="text" placeholder="Date" class="form-control" id="date" style="width: 20%" name="date">
            <br>
            Amount (KZT): <input type="text" class="form-control" name="size" placeholder="Amount" style="width: 35%"><br>
            Category:
            <select class="form-control" name="category" style="width: 65%">
                <%ArrayList<Category> categories = (ArrayList) dbc.findAllForIncomes();
                for (Category cat : categories) {
                    out.println("<option value=" + cat.getCategoryId() +
                            ">" + cat.getCategoryName() + "</option>");
                }
                %>
            </select><br>
            Description: <textarea type="text" class="form-control" name="desc" placeholder="Description" style="width: 65%">
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

        <% if(form.equals("updinc")){
        int id = Integer.parseInt(request.getParameter("id"));
        Income inc = (Income) dbi.findById(id);
        %>
        <h2 class="sub-header">Update income</h2>
        <form class="form-signin" role="form" action="Action" method="GET">
            <h2 class="form-signin-heading">Type expense details</h2>
            Date:
            <input  type="text" placeholder="<%=inc.getDate()%>" class="form-control"  id="date2" style="width: 20%" name="date">
            <br>
            Amount (KZT): <input type="text" class="form-control" name="size"
                                 placeholder="<%=inc.getSize()%>" style="width: 35%"><br>
            Category:
            <select class="form-control" name="category" style="width: 65%">
                <%ArrayList<Category> categories = (ArrayList) dbc.findAllForIncomes();
                    for (Category cat : categories) {
                        out.println("<option value=" + cat.getCategoryId() +
                                ">" + cat.getCategoryName() + "</option>");
                    }
                %>
            </select><br>
            Description: <input type="text" class="form-control" name="description"
                                placeholder="<%=inc.getDescription()%>" style="width: 65%">

            <br>
            <button class="btn btn-lg btn-primary btn-block" type="submit" style="width: 25%">Submit</button>
            <input type="hidden" name="type" value="updinc">
            <input type="hidden" name="id" value="<%=inc.getId()%>">
        </form>
        <%}%>

        <% if(form.equals("updexp")){
            int id = Integer.parseInt(request.getParameter("id"));
            Expense exp = (Expense) dbe.findById(id);
        %>
        <h2 class="sub-header">Update expense</h2>
        <form class="form-signin" role="form" action="Action" method="GET">
            <h2 class="form-signin-heading">Type expense details</h2>
            Date:
            <input  type="text" placeholder="<%=exp.getDate()%>" class="form-control"  id="date3" style="width: 20%" name="date">
            <br>
            Amount (KZT): <input type="text" class="form-control" name="size"
                                 placeholder="<%=exp.getSize()%>" style="width: 35%"><br>
            Category:
            <select class="form-control" name="category" style="width: 65%">
                <%ArrayList<Category> categories = (ArrayList) dbc.findAllForExpenses();
                    for (Category cat : categories) {
                        out.println("<option value=" + cat.getCategoryId() +
                                ">" + cat.getCategoryName() + "</option>");
                    }
                %>
            </select><br>
            Description: <input type="text" class="form-control" name="description"
                                placeholder="<%=exp.getDescription()%>" style="width: 65%">

            <br>
            <button class="btn btn-lg btn-primary btn-block" type="submit" style="width: 25%">Submit</button>
            <input type="hidden" name="type" value="updexp">
            <input type="hidden" name="id" value="<%=exp.getId()%>">
        </form>
        <%}%>

        <%if (form.equals("account")) {%>

        <h2 class="sub-header">Profile details</h2>
        <form class="form-signin" role="form" action="Action" method="GET">
            <h2 class="form-signin-heading">You can change your account details</h2>
            First name:
            <input  type="text" value="<%=user.getFname()%>"
                    class="form-control" style="width: 65%" name="fname" disabled>
            <br>
            First name:
            <input  type="text" value="<%=user.getLname()%>"
                    class="form-control" style="width: 65%" name="lname" disabled>
            <br>
            Email: <input type="text" class="form-control" name="email"
                                value="<%=user.getEmail()%>" style="width: 65%" disabled>
            <br>
            <button class="btn btn-lg btn-primary btn-block" type="submit" style="width: 25%">Change account details</button>
            <input type="hidden" name="id" value="<%=user.getUserId()%>">
            <input type="hidden" name="type" value="account">
        </form>

        <%}%>

        <%if (form.equals("updacc")) {%>

        <h2 class="sub-header">Change profile details</h2>
        <form class="form-signin" role="form" action="Action" method="GET">
            First name:
            <input  type="text" value="<%=user.getFname()%>"
                    class="form-control" style="width: 65%" name="fname">
            <br>
            First name:
            <input  type="text" value="<%=user.getLname()%>"
                    class="form-control" style="width: 65%" name="lname">
            <br>
            Email: <input type="text" class="form-control" name="email"
                          value="<%=user.getEmail()%>" style="width: 65%">
            <br>
            New Password: <input type="password" class="form-control" name="pass"
                          style="width: 65%">
            <br>

            <button class="btn btn-lg btn-primary btn-block" type="submit" style="width: 25%">Submit</button>
            <input type="hidden" name="id" value="<%=user.getUserId()%>">
            <input type="hidden" name="type" value="updacc">
        </form>

        <%}%>
    </div>
</div>
<%=tags.getJquery()%>
<script src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#date').datepicker({
            format: "yyyy-mm-dd"
        });
        $('#date1').datepicker({
            format: "yyyy-mm-dd"
        });
        $('#date2').datepicker({
            format: "yyyy-mm-dd"
        });
        $('#date3').datepicker({
            format: "yyyy-mm-dd"
        });
    });
</script>
</body>
</html>
