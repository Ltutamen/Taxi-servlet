package ua.axiom.controller;

import ua.axiom.core.Context;
import ua.axiom.service.CommandProviderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/**", "/"}, name = "mainPage")
public class Servlet extends HttpServlet {
    {
        commandProviderService = Context.get(CommandProviderService.class);
    }

    private CommandProviderService commandProviderService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service " + req.getRequestURI());
        String requestURI = req.getRequestURI();

        String next = commandProviderService.getCommand(requestURI).execute(req, resp, requestURI);

        if(next.contains("redirect:")) {
            resp.sendRedirect(next.replace("redirect:", ""));

        } else if(next.contains("forward:")) {
            getServletContext().getRequestDispatcher(next.replace("forward:", "")).forward(req, resp);

        } else {
            throw new IllegalArgumentException("Wrong result string: " + next);
        }

    }
}
