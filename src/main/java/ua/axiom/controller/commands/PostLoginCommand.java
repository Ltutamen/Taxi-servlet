package ua.axiom.controller.commands;

import ua.axiom.controller.commands.Command;
import ua.axiom.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostLoginCommand extends Command {
    private static final Map<Role, String> postLoginRedirectMapping = new HashMap<>();

    static {
        postLoginRedirectMapping.put(Role.GUEST, "redirect:/error");
        postLoginRedirectMapping.put(Role.CLIENT, "redirect:/clientpage");
        postLoginRedirectMapping.put(Role.DRIVER, "redirect:/driverpage");
        postLoginRedirectMapping.put(Role.ADMIN, "redirect:/adminpage");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String uri) throws IOException, ServletException {
        Role role = (Role)request.getSession(false).getAttribute("role");
        return postLoginRedirectMapping.get(role);
    }
}
