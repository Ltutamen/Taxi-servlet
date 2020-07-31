package ua.axiom.controller.commands;

import ua.axiom.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorCommand extends Command {

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        return getDefaultView();
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        return getDefaultView();
    }

    protected String getDefaultView() {
        return "redirect:/misc/error.jsp";
    }
}
