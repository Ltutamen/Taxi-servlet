package ua.axiom.controller;

import ua.axiom.core.Context;
import ua.axiom.service.GuiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//  @WebServlet(value = "/index", name = "mainPage")
public class MainPage extends HttpServlet {
    private GuiService guiService = Context.get(GuiService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        guiService.setNavbarData(req);

        req.setAttribute("aa", "Company-name");

        getServletContext().getRequestDispatcher("/appPages/index.jsp").forward(req, resp);
    }

}
