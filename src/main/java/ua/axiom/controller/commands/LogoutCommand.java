package ua.axiom.controller.commands;

import ua.axiom.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand extends Command {
    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        request.getSession(false).setAttribute("role", Role.GUEST);
        return "redirect:/";
    }
}
