package ua.axiom.controller.commands;

import ua.axiom.controller.Command;
import ua.axiom.core.Context;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.repository.impl.MultiTableRepository;
import ua.axiom.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoginPageCommand extends Command {
    //  MultiTableRepository<Long, User> userRepository;

    private LoginService loginService;

    {
        loginService = Context.get(LoginService.class);
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        return getView();
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        String usernameParameter = request.getParameter("username");
        String passwordParameter = request.getParameter("password");

        boolean logged = loginService.tryLogin(usernameParameter, passwordParameter, request);

        if(logged) {
            return "redirect:/api/postloginredirect";
        }

        return getView();
    }

    protected String getView() {
        return "forward:/misc/login.jsp";
    }


}
