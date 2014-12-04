/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import entities.Expense;
import entities.Income;
import entities.User;
import interfaces.IDBUtilInterface;
import utils.DBUtilCategory;
import utils.DBUtilExpense;
import utils.DBUtilIncome;
import utils.DBUtilUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The <i>Action</i> servlet is used to
 * process user requests and send appropriate responses.
 *
 * @author Madina
 * @version 1.0
 */
@WebServlet(name = "Action", urlPatterns = {"/Action"})
public class Action extends HttpServlet {

    DBUtilUser dbUtil = new DBUtilUser();
    DBUtilIncome dbi = new DBUtilIncome();
    DBUtilExpense dbe = new DBUtilExpense();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws java.io.IOException            if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String type = request.getParameter("type");
            ArrayList<User> users;
            HttpSession session;
            if (type.equals("login")) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                session = request.getSession();

                users = (ArrayList) dbUtil.findAll();
                boolean flag = false;
                for (User user : users) {
                    if (user.getEmail().equals(email) &&
                            user.getPassword().equals(password)) {
                        flag = true;
                        session.setAttribute("Account", user);
                        response.sendRedirect("account.jsp");
                    }
                }
                if (!flag) {
                    response.sendRedirect("login.jsp?error=Incorrect email address or password!");
                }
            } else if (type.equals("logout")) {
                 session = request.getSession();
                if (session != null) {
                    session.removeAttribute("Account");
                }
                session.invalidate();
                response.sendRedirect("index.jsp");
            } else if (type.equals("register")) {
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                session = request.getSession();

                boolean flag = true;
                if (dbUtil.equals(email,password)) {
                    flag = false;
                }

                if (flag) {
                    User u = new User();
                    u.setFname(fname);
                    u.setLname(lname);
                    u.setEmail(email);
                    u.setPassword(password);
                    //double n = System.currentTimeMillis()/1000L;
                    //u.setUserId((int)n);

                    dbUtil.insert(u);
                    int id = dbUtil.getLastID();
                    User user = (User) dbUtil.findById(id);

                    session.setAttribute("Account", user);
                    response.sendRedirect("account.jsp");

                } else {
                    response.sendRedirect("registration.jsp?error" +
                            "=The user with the given email already exists!");
                }
            } else if (type.equals("addinc")) {
                response.sendRedirect("form.jsp?form=addinc");
            } else if (type.equals("addexp")) {
                response.sendRedirect("form.jsp?form=addexp");
            } else if (type.equals("newinc")) {
                int categoryId = Integer.parseInt(request.getParameter("category"));
                String date = request.getParameter("date");
                double size = Double.parseDouble(request.getParameter("size"));
                String description = request.getParameter("desc");

                session = request.getSession();

                Income income = new Income();
                income.setCategoryId(categoryId);
                income.setDate(date);
                income.setSize(size);
                income.setDescription(description);
                User user = (User)session.getAttribute("Account");
                income.setUserId(user.getUserId());
                dbi.insert(income);
                response.sendRedirect("income.jsp");
            } else if (type.equals("newexp")) {
                int categoryId = Integer.parseInt(request.getParameter("category"));
                String date = request.getParameter("date");
                double size = Double.parseDouble(request.getParameter("size"));
                String description = request.getParameter("desc");

                session = request.getSession();

                Expense exp = new Expense();
                exp.setCategoryId(categoryId);
                exp.setDate(date);
                exp.setSize(size);
                exp.setDescription(description);
                User user = (User)session.getAttribute("Account");
                exp.setUserId(user.getUserId());
                dbe.insert(exp);
                response.sendRedirect("expense.jsp");
            } else if (type.equals("delincome")) {
                int id = Integer.parseInt(request.getParameter("id"));

                Income temp = (Income) dbi.findById(id);
                dbi.delete(temp);
                response.sendRedirect("income.jsp");

            } else if (type.equals("delexpense")) {
                int id = Integer.parseInt(request.getParameter("id"));

                Expense temp = (Expense) dbi.findById(id);
                dbi.delete(temp);
                response.sendRedirect("expense.jsp");
            } else if (type.equals("income")) {
                int id = Integer.parseInt(request.getParameter("id"));
                response.sendRedirect("form.jsp?form=updinc&id="+id);
            } else if (type.equals("expense")) {
                int id = Integer.parseInt(request.getParameter("id"));
                response.sendRedirect("form.jsp?form=updexp&id="+id);
            } else if (type.equals("updexp")) {
                int categoryId = Integer.parseInt(request.getParameter("category"));
                String date = request.getParameter("date");
                double size = Double.parseDouble(request.getParameter("size"));
                String description = request.getParameter("description");
                int id = Integer.parseInt(request.getParameter("id"));

                Expense exp = (Expense) dbe.findById(id);
                exp.setCategoryId(categoryId);
                exp.setDate(date);
                exp.setSize(size);
                exp.setDescription(description);
                dbe.update(exp);
                response.sendRedirect("expense.jsp");
            } else if (type.equals("updinc")) {
                int categoryId = Integer.parseInt(request.getParameter("category"));
                String date = request.getParameter("date");
                double size = Double.parseDouble(request.getParameter("size"));
                String description = request.getParameter("description");
                int id = Integer.parseInt(request.getParameter("id"));

                Income inc = (Income) dbi.findById(id);
                inc.setCategoryId(categoryId);
                inc.setDate(date);
                inc.setSize(size);
                inc.setDescription(description);
                dbi.update(inc);
                response.sendRedirect("income.jsp");
            } else if (type.equals("account")) {
                int id = Integer.parseInt(request.getParameter("id"));
                response.sendRedirect("form.jsp?form=updacc&id="+id);
            } else if (type.equals("updacc")) {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = (User) dbUtil.findById(id);

                user.setFname(request.getParameter("fname"));
                user.setLname(request.getParameter("lname"));
                user.setEmail(request.getParameter("email"));
                user.setPassword(request.getParameter("pass"));
                dbUtil.update(user);
                session = request.getSession();
                session.removeAttribute("Account");
                session.setAttribute("Account", user);
                response.sendRedirect("form.jsp?form=account");
            } else {}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws java.io.IOException            if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException if a servlet-specific error occurs
     * @throws java.io.IOException            if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
