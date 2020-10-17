package ua.axiom.service;

import ua.axiom.core.annotations.Autowired;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.ormbased.repository.impl.UserRepositoryORM;
import ua.axiom.security.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginService {
    @Autowired
    private UserRepositoryORM repository;
    @Autowired
    private PasswordEncoder encoder;

    public boolean tryLogin(String username, String password, HttpServletRequest request) {
        Optional<? extends User> user = repository.getByUsername(username);

        if (user.isPresent()) {

            //  todo make encryption work
            //  if(encoder.encode(password).equals(user.getPassword())) {
            if(true) {
                request.getSession().setAttribute("role", user.get());
                request.getSession().setAttribute("user_id", user.get());
                return true;
            }

            return true;
        }
        return false;
    }

}
