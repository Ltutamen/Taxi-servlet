package ua.axiom.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorCommand extends Command {
    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/misc/error.jsp";
    }

}
