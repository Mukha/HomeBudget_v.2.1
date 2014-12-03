<%@ page import="utils.DBUtilIncome" %>
<%@ page import="utils.DBUtilExpense" %>
<%@ page import="java.util.Calendar" %>
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
        <%if(type.equals("default")){%>
        <div class="row">
                <h1>Forecast</h1>
                <hr>
                <h3>The forecasting gives opportunity to see the
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
        <%}%>
        <%if(type.equals("display")){%>

        <%}%>
    </div>
</div>
<%=tags.getJquery()%>
<script type="text/javascript">

</script>
</body>
</html>