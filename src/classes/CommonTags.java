package classes;

/**
 * The <i>CommonTags</i> class is used to build the jsp-pages.
 * The class provides the methods that add the html-page
 * components to the jsp-files.
 *
 * @author Madina
 * @version 1.0
 */
public class CommonTags {

    /**
     * Class CommonTags constructor
     */
    public CommonTags() {
    }

    /**
     * The <i>getLinks()</i> method is used to add the link tags
     * to the jsp-page.
     *
     * @param type is the type of page, it is necessary in order
     *             to know which type of link should be added to
     *             specific page.
     * @return the sequence of the html tags with links.
     */
    public String getLinks(String type) {
        String result = "";

        if (type.equals("index")) {
            result = "<!-- Bootstrap core CSS --> \n"
                    + "<link href='css/bootstrap.min.css' rel='stylesheet'> \n"
                    + "<!-- Custom styles for this template --> \n"
                    + "<link href='css/jumbotron-narrow.css' rel='stylesheet'> \n"
                    + "<!-- Custom core CSS --> \n"
                    + "<link href='css/myStyle.css' rel='stylesheet'> \n";
        } else if (type.equals("account")) {
            result = "<meta charset=\"utf-8\">"
                    + "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
                    + "<meta name='description' content='Home Budget'>"
                    + "<meta name='author' content='Madina Abuova'>"
                    + "<title>Home Budget</title>"
                    + "<!-- Bootstrap core CSS -->"
                    + "<link href='css/bootstrap.min.css' rel='stylesheet'>"
                    + "<!-- Custom styles for this template -->"
                    + "<link href=\"css\\dashboard.css\" rel=\"stylesheet\">";
        } else {
            return result;
        }

        return result;
    }

    /**
     * The <i>getHeader()</i> method is used to add the header tags
     * to the jsp-page.
     *
     * @param type is the type of page, it is necessary in order
     *             to know which header tags should be added to
     *             specific page.
     * @return the sequence of the html tags with header.
     */
    public String getHeader(String type) {

        if (type.equals("login")) {
            return "<ul class='nav nav-pills pull-right'>"
                    + "<li class='active'><a href='index.jsp'>Home</a></li>"
                    + "<li><a href='registration.jsp'>Register</a></li>"
                    + "</ul>"
                    + "<h3 class='text-muted'>Home Budget</h3>";
        } else if (type.equals("reg")) {
            return "<ul class='nav nav-pills pull-right'>"
                    + "<li class='active'><a href='index.jsp'>Home</a></li>"
                    + "</ul>"
                    + "<h3 class='text-muted'>Home Budget</h3>";
        } else if (type.equals("account")) {
            return "<nav class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">"
                    + "<div class=\"container-fluid\">"
                    + "<div class=\"navbar-header\">"
                    + "<a class=\"navbar-brand\" href=\"account.jsp\">Home Budget</a>"
                    + "</div>"
                    + "<div id=\"navbar\" class=\"navbar-collapse collapse\">"
                    + "<ul class=\"nav navbar-nav navbar-right\">"
                    + "<!--<li><a href=\"account.jsp\">Dashboard</a></li>"
                    + "<li><a href=\"#\">Settings</a></li>-->"
                    + "<li><a href=\"form.jsp?form=account\">Profile</a></li>"
                    + "<li><a href=\"Action?type=logout\">Logout</a></li>"
                    + "</ul>"
                    + "</div></div></nav>";
        } else if (type.equals("menu")) {
            return "<div class=\"row-fluid\">"
                    + "<div class=\"col-sm-3 col-md-2 sidebar\">"
                    + "<ul class=\"nav nav-sidebar\">"
                    + "<li class=\"active\"><a href=\"account.jsp\">"
                    + "My Dashboard <span class=\"sr-only\">(current)</span></a></li>"
                    + "<li><a href=\"income.jsp\">My Incomes</a></li>"
                    + "<li><a href=\"expense.jsp\">My Expenses</a></li>"
                    + "</ul>"
                    + "<ul class=\"nav nav-sidebar\">"
                    + "<li><a href=\"\">Nav item</a></li>"
                    + "<li><a href=\"\">Nav item again</a></li>"
                    + "<li><a href=\"\">One more nav</a></li>"
                    + "<li><a href=\"\">Another nav item</a></li>"
                    + "<li><a href=\"\">More navigation</a></li>"
                    + "</ul>"
                    + "</div>";
        }

        return new String("<ul class='nav nav-pills pull-right'>"
                + "<li class='active'><a href='index.jsp'>Home</a></li>"
                + "<li><a href='registration.jsp'>Register</a></li>"
                + "<li><a href='login.jsp'>Login</a></li>"
                + "</ul>"
                + "<h3 class='text-muted'>Home Budget</h3>");
    }

    /**
     * The <i>getJquery()</i> method is used to add the script
     * tags to the jsp-page.
     * <p/>
     * Returns the sequence of the html tags with script.
     *
     * @return the sequence of the html tags with script.
     */
    public String getJquery() {
        return new String("<!-- jQuery (necessary for "
                + "Bootstrap's JavaScript plugins) -->\n"
                + "    <script src="
                + "\"js\\jquery-2.1.1.min.js\"></script>\n"
                + "    <script src=\"js/bootstrap.min.js\"></script>\n" +
                "<script src=\"http://code.highcharts.com/highcharts.js\"></script>\n"
                );
    }

    /**
     * The <i>getFooter()</i> method is used to add the footer tags
     * to the jsp-page.
     * <p/>
     * Returns the sequence of the html tags with footer.
     *
     * @return the sequence of the html tags with footer.
     */
    public String getFooter() {
        return new String("<p>&copy; Copyright 2014 <a href=\""
                + "mailto:mukhanova.madina@gmail.com\">Madina Abuova-Mukhanova</a>"
                + " All Rights Reserved.</p> \n");
    }

}
