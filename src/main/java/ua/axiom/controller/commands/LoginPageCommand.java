package ua.axiom.controller.commands;

import ua.axiom.core.Context;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.repository.MultiTableRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginPageCommand extends Command {
    MultiTableRepository<Long, User> userRepository;

    {
        userRepository = Context.get(MultiTableRepository.class);
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        return "forward:/misc/login.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        String usernameParameter = request.getParameter("username");
        String passwordParameter = request.getParameter("password");

        System.out.println("log in: " + usernameParameter + " " + passwordParameter);

        List<? extends User> userList = userRepository.findByUsername(usernameParameter);

        if (userList.size() == 1) {
            System.out.println("logged in as " + usernameParameter);
            System.out.println("todo decrypt password " + passwordParameter);

            User user = userList.iterator().next();

            //  todo encrypt or palace session id
            request.getSession().setAttribute("role", user.getRole());
            request.getSession().setAttribute("user_id", user.getId());

            return "redirect:/api/postloginredirect";
        } else {
            //  todo throw error
            return "/misc/login.jsp";
        }
    }

}
