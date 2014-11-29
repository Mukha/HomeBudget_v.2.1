<!-- Page scoped bean for a common header and footer. -->
<jsp:useBean id="tags" scope="page" class='classes.CommonTags'/>
<%
    // Get an error messages in the request object.
    String error = (request.getParameter("error") == null)
            ? ""
            : request.getParameter("error");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD html 4.0 transitional//EN">

<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <meta name='description' content='Home Budget'>
    <meta name='author' content='Madina Abuova'>
    <title>Home Budget</title>
    <link href='css/bootstrap.min.css' rel='stylesheet'>
    <link href='css/jumbotron-narrow.css' rel='stylesheet'>
    <link href='css/signin.css' rel='stylesheet'>
</head>

<body>
<div class="container">
    <div class="header">
        <%=tags.getHeader("reg")%>
    </div>

    <div class="container">

        <form class="form-signin" role="form" action="Action" method="GET">
            <h2 class="form-signin-heading">Registration</h2>

            <p style="color:red"><%=error%>
            </p>
            <input type="text" class="form-control" placeholder="First Name" required autofocus name="fname">
            <input type="text" class="form-control" placeholder="Last Name" required autofocus name="lname">
            <input type="email" class="form-control" placeholder="Email address" required autofocus name="email">
            <input type="password" class="form-control" placeholder="Password" required name="password">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
            <input type="hidden" name="type" value="register">
        </form>
        <br><br><br><br><br><br><br><br><br><br><br>
    </div>

    <div class="footer">
        <%=tags.getFooter()%>
    </div>

</div>
<!-- /container -->
</body>
</html>

