package ua.axiom.controller.api;

import ua.axiom.core.Context;
import ua.axiom.model.Role;
import ua.axiom.model.actors.User;
import ua.axiom.model.repository.ClientRepository;

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

        ClientRepository clientRepository = Context.get(ClientRepository.class);

        List<? extends User> user = clientRepository.findByUsername(usernameParameter);

        if(user.size() == 1) {
            System.out.println("logged in as " + usernameParameter);
            System.out.println("todo decrypt password");

            User user1 = user.get(0);

            req.getSession().setAttribute("role", Role.CLIENT);

            resp.sendRedirect("/postloginredirect");
            return;
        } else {
            //  todo throw error
        }

        resp.sendRedirect("/");

    }
}
