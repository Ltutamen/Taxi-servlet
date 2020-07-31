package ua.axiom.controller.commands;

import ua.axiom.controller.Command;
import ua.axiom.core.Context;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.repository.impl.MultiTableRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class LoginPageCommand extends Command {
    MultiTableRepository<Long, User> userRepository;

    {
        userRepository = Context.get(MultiTableRepository.class);
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        return getView();
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        String usernameParameter = request.getParameter("username");
        String passwordParameter = request.getParameter("password");

        List<? extends User> userList = userRepository.findByFields(Collections.singletonList("username"), Collections.singletonList(usernameParameter));

        if (userList.size() == 1) {
            User user = userList.iterator().next();

            //  todo encrypt or palace session id
            request.getSession().setAttribute("role", user.getRole());
            request.getSession().setAttribute("user_id", user.getId());

            return "redirect:/api/postloginredirect";
        } else {
            //  todo throw error
            return getView();
        }
    }

    protected String getView() {
        return "forward:/misc/login.jsp";
    }


}
