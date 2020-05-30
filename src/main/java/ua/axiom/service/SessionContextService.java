package ua.axiom.service;

import ua.axiom.core.Context;
import ua.axiom.model.Role;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.repository.MultiTableRepository;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class SessionContextService {
    private static MultiTableRepository<Long, User> userRepository;

    static {
        userRepository = Context.get(MultiTableRepository.class);
    }

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
