package ua.axiom.security;

import ua.axiom.model.Role;
import ua.axiom.service.SessionContextService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (RoleAccessConfiguration.accessConfig.get(Role.GUEST).contains(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);

        if (session == null || SessionContextService.getCurrentUserRole(session) == null) {
            request.getSession(true).setAttribute("role", Role.GUEST);
            response.sendRedirect("/login");
            return;
        }

        Set<Role> urlAllowedRoles = RoleAccessConfiguration
                .accessConfig
                .entrySet()
                .stream()
                .filter(e -> e
                        .getValue()
                        .contains(request.getRequestURI()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());


        if (urlAllowedRoles.contains(SessionContextService.getCurrentUserRole(session))) {
            chain.doFilter(request, response);
            return;
        }

        response.sendRedirect("/login");
    }

    @Override
    public void destroy() {

    }

    private boolean verifyLoginData(String login, String password) {
        return false;
    }

    private boolean verifyLoginData(String logAndPass) {
        String data[] = logAndPass.split(".");
        return verifyLoginData(data[0], data[1]);
    }

    private void assureRoleExists() {

    }
}
