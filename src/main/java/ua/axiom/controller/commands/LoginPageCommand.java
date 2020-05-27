package ua.axiom.controller.commands;

import ua.axiom.core.Context;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.repository.MultiTableRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LoginPageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String uri) throws ServletException, IOException {
        if (request.getMethod().equals("POST")) {
            return servePost(request, response);
        } else {
            return "forward:/misc/login.jsp";
        }
    }

    private String servePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String usernameParameter = request.getParameter("username");
        String passwordParameter = request.getParameter("password");

        System.out.println("log in: " + usernameParameter + " " + passwordParameter);

        MultiTableRepository<Long, User> userRepository = Context.get(MultiTableRepository.class);

        List<? extends User> userList = userRepository.findByUsername(usernameParameter);

        if (userList.size() == 1) {
            System.out.println("logged in as " + usernameParameter);
            System.out.println("todo decrypt password " + passwordParameter);

            User user = userList.iterator().next();

            request.getSession().setAttribute("role", user.getRole());

            return "redirect:/api/postloginredirect";
        } else {
            //  todo throw error
            return "/misc/login.jsp";
        }
    }
}
