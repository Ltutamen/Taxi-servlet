package ua.axiom.security;

import ua.axiom.core.Context;

import java.security.SecureRandom;

public class PasswordEncoderProvider {
    {
        Context.put(new PasswordEncoder(8, new SecureRandom("adad423asd".getBytes())));
    }
}
