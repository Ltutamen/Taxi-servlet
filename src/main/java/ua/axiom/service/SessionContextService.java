package ua.axiom.service;

import ua.axiom.core.App;
import ua.axiom.model.Role;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.ormbased.repository.impl.UserRepositoryORM;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class SessionContextService {
    private static UserRepositoryORM userRepository;

    static {
        initStaticFields();
    }

    /**
     * I don't know how to @Autowire static fields (and Spring does not too)
     */
    private static void initStaticFields() {
        userRepository = App.getApp().getObject(UserRepositoryORM.class);
    }

    public static Long getCurrentUserId(HttpSession session) {
        return (Long) session.getAttribute(SessionParams.USER_ID.toString());
    }

    public static Role getCurrentUserRole(HttpSession session) {
        return (Role) session.getAttribute(SessionParams.ROLE.toString());
    }

    public static <T extends User> T getPersistedCurrentUser(HttpSession session) {
        Long userId = getCurrentUserId(session);

        return (T)userRepository.find(userId);
    }

    public static void voidSession(HttpSession session) {
        Enumeration<String> sessionVars = session.getAttributeNames();
        while (sessionVars.hasMoreElements()) {
            session.removeAttribute(sessionVars.nextElement());
        }
        session.setAttribute(SessionParams.ROLE.toString(), Role.GUEST);
    }


}
