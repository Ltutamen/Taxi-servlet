package ua.axiom.controller.commands;

import ua.axiom.service.SessionContextService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand extends Command {
    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        SessionContextService.voidSession(request.getSession());
        return "redirect:/";
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        throw new IllegalStateException("No GET for <redirect>");
    }
}
