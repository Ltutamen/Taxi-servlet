package ua.axiom.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String uri) throws IOException, ServletException {
        return "redirect:/misc/error.jsp";
        //  response.sendRedirect("");
    }
}
