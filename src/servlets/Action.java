/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import entities.User;
import interfaces.IDBUtilInterface;
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
            ArrayList<User> users = new ArrayList<User>();
            if (type.equals("login")) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                HttpSession session = request.getSession();

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
                 HttpSession session = request.getSession();
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
                HttpSession session = request.getSession();

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

                    dbUtil.insert(u);
                    int id = dbUtil.getLastID();
                    User user = (User) dbUtil.findById(id);

                    session.setAttribute("Account", user);
                    response.sendRedirect("account.jsp");

                } else {
                    response.sendRedirect("registration.jsp?error" +
                            "=The user with the given email already exists!");
                }
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
