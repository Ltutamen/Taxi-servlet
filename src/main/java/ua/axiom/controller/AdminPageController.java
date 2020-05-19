package ua.axiom.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet("/adminpage")
public class AdminPageController extends HttpServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/appPages/adminpage.jsp").forward(request, response);
    }

}
