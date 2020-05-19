package ua.axiom.controller.api;

import ua.axiom.model.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/postloginredirect", name = "postloginredirect")
public class PostLoginController extends HttpServlet {
    private static final Map<Role, String> postLoginRedirectMapping = new HashMap<>();

    static {
        postLoginRedirectMapping.put(Role.GUEST, "/error");
        postLoginRedirectMapping.put(Role.CLIENT, "/clientpage");
        postLoginRedirectMapping.put(Role.DRIVER, "/driverpage");
        postLoginRedirectMapping.put(Role.ADMIN, "/adminpage");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Role role = (Role)req.getSession(false).getAttribute("role");
        resp.sendRedirect(postLoginRedirectMapping.get(role));
    }
}
