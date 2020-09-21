package ua.axiom.service;

import ua.axiom.core.annotations.Autowired;
import ua.axiom.model.Role;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.repository.impl.UsersRepository;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class SessionContextService {
    @Autowired
    private static UsersRepository userRepository;

    public static Long getCurrentUserId(HttpSession session) {
        return (Long) session.getAttribute(SessionParams.USER_ID.toString());
    }

    public static Role getCurrentUserRole(HttpSession session) {
        return (Role) session.getAttribute(SessionParams.ROLE.toString());
    }

    public static <T extends User> T getPersistedCurrentUser(HttpSession session) {
        Long userId = getCurrentUserId(session);

        return (T)userRepository.findOne(userId).iterator().next();
    }

    public static void voidSession(HttpSession session) {
        Enumeration<String> sessionVars = session.getAttributeNames();
        while (sessionVars.hasMoreElements()) {
            session.removeAttribute(sessionVars.nextElement());
        }
        session.setAttribute(SessionParams.ROLE.toString(), Role.GUEST);
    }


}
