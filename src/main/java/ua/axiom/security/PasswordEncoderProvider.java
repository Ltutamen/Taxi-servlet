package ua.axiom.security;

import ua.axiom.core.annotations.Bean;

import java.security.SecureRandom;

public class PasswordEncoderProvider {

    @Bean
    public static PasswordEncoder passwordEncoderBeanProvider() {
        return new PasswordEncoder(8, new SecureRandom("adad423asd".getBytes()));
    }
}
