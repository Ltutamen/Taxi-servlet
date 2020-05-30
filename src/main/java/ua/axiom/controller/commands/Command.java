package ua.axiom.controller.commands;

import ua.axiom.model.UserLocale;
import ua.axiom.model.actors.User;
import ua.axiom.service.SessionContextService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Command<T extends User> {
    public String execute(HttpServletRequest request, HttpServletResponse response, String uri)
            throws IOException, ServletException {
        if (request.getMethod().equals("POST")) {
            return executePost(request, response);
        } else if(request.getMethod().equals("GET")) {
            return executeGet(request, response);
        } else {
            throw new IllegalStateException("Wrong request method:" + request.getMethod());
        }
    }

    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<>();

        T user = (T)SessionContextService.getPersistedCurrentUser(request.getSession());

        nonUserSpecificDataFill(model, user.getLocale());
        userSpecificDataFill(model, user);

        for(Map.Entry<String, Object> entry : model.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }

        return processGet(request, response);
    }

    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/misc/error.jsp";
    }

    protected String processGet(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/misc/error.jsp";
    }

    protected void nonUserSpecificDataFill(Map<String, Object> model, UserLocale userLocale) {}

    protected void userSpecificDataFill(Map<String, Object> model, T user) {}

}
