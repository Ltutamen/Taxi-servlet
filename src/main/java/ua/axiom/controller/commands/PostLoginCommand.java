package ua.axiom.controller.commands;

import ua.axiom.controller.Command;
import ua.axiom.core.annotations.RequestMapping;
import ua.axiom.model.Role;
import ua.axiom.model.actors.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/postloginredirect")
public class PostLoginCommand extends Command<User> {
    private static final Map<Role, String> postLoginRedirectMapping = new HashMap<>();

    static {
        postLoginRedirectMapping.put(Role.GUEST, "redirect:/error");
        postLoginRedirectMapping.put(Role.CLIENT, "redirect:/clientpage");
        postLoginRedirectMapping.put(Role.DRIVER, "redirect:/driverpage");
        postLoginRedirectMapping.put(Role.ADMIN, "redirect:/adminpage");
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        Role role = (Role)request.getSession(false).getAttribute("role");
        return postLoginRedirectMapping.get(role);
    }
}
