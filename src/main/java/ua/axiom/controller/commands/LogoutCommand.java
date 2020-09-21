package ua.axiom.controller.commands;

import ua.axiom.controller.Command;
import ua.axiom.core.annotations.RequestMapping;
import ua.axiom.model.actors.User;
import ua.axiom.service.SessionContextService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/logout")
public class LogoutCommand extends Command<User> {
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
