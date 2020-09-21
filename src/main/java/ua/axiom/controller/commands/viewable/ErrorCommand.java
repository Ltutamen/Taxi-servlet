package ua.axiom.controller.commands.viewable;

import ua.axiom.controller.Command;
import ua.axiom.core.annotations.RequestMapping;
import ua.axiom.model.actors.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/error")
public class ErrorCommand extends Command<User> {

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
