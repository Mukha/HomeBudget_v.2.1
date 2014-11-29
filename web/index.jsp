<%-- 
    Document   : index
    Created on : 08.09.2014, 19:36:13
    Author     : Мадина
--%>
<jsp:useBean id="tags" scope="page" class="classes.CommonTags"/>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home Budget</title>
    <%=tags.getLinks("index")%>
</head>
<body>
<div class="container">
    <div class="header">
        <%=tags.getHeader("index")%>
    </div>

    <div class="jumbotron">
        <h1>Welcome!</h1>

        <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus
            commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>

        <p><a class="btn btn-lg btn-success" href="#" role="button">Sign up today</a></p>
    </div>

    <div class="row marketing">
        <div class="col-lg-6">
            <h4>Subheading</h4>

            <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

            <h4>Subheading</h4>

            <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet
                fermentum.</p>

            <h4>Subheading</h4>

            <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>

        <div class="col-lg-6">
            <h4>Subheading</h4>

            <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

            <h4>Subheading</h4>

            <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet
                fermentum.</p>

            <h4>Subheading</h4>

            <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>
    </div>

    <div class="footer">
        <%=tags.getFooter()%>
    </div>
</div>
<!-- /container -->
<%=tags.getJquery()%>
</body>
</html>
