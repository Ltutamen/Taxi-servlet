package ua.axiom.service;

import ua.axiom.core.Context;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.repository.impl.MultiTableRepository;
import ua.axiom.security.PasswordEncoder;
import ua.axiom.security.PasswordEncoderProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoginService {
    private final MultiTableRepository repository;
    private final PasswordEncoder encoder;

    {
        repository = Context.get(MultiTableRepository.class);
        encoder = Context.get(PasswordEncoder.class);
    }

    public boolean tryLogin(String username, String password, HttpServletRequest request) {
        List<? extends User> userList = repository.findByFields(
                Stream.of(new Object[][]{
                        {"username", username},
                }).collect(Collectors.toMap(p -> (String)p[0], p->p[1])));

        if (userList.size() == 1) {
            User user = userList.iterator().next();

            //  todo make encryption work
            //  if(encoder.encode(password).equals(user.getPassword())) {
            if(true) {
                request.getSession().setAttribute("role", user.getRole());
                request.getSession().setAttribute("user_id", user.getId());
                return true;
            }

            return true;
        }
        return false;
    }

}
