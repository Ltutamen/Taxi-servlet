package ua.axiom.controller.api;

import ua.axiom.core.Context;
import ua.axiom.model.Role;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.repository.ClientRepository;
import ua.axiom.persistance.repository.MultiTableRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/login", name = "login")
public class LoginPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/misc/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usernameParameter = req.getParameter("username");
        String passwordParameter = req.getParameter("password");

        System.out.println("log in: " + usernameParameter + " " + passwordParameter);

        MultiTableRepository<Long, User> userRepository = Context.get(MultiTableRepository.class);

        List<? extends User> userList = userRepository.findByUsername(usernameParameter);

        if(userList.size() == 1) {
            System.out.println("logged in as " + usernameParameter);
            System.out.println("todo decrypt password " + passwordParameter);

            User user = userList.iterator().next();

            req.getSession().setAttribute("role", user.getRole());

            resp.sendRedirect("/postloginredirect");
            return;
        } else {
            //  todo throw error
        }

        resp.sendRedirect("/");

    }
}
