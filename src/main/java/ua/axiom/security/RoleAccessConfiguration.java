package ua.axiom.security;

import ua.axiom.model.Role;

import java.util.*;
import java.util.stream.Collectors;

public class RoleAccessConfiguration {
    public static final Map<Role, Set<String>> accessConfig = new HashMap<>();
    public static String prefix = "";

    static {
        accessConfig.put(Role.GUEST, new HashSet<>(Arrays.asList("/", "/login", "/error", "/register")));
        accessConfig.put(Role.ADMIN, new HashSet<>(Arrays.asList("/adminpage", "/logout", "/api/postloginredirect")));
        accessConfig.put(Role.CLIENT, new HashSet<>(Arrays.asList("/clientpage", "/logout", "/api/postloginredirect", "/neworder", "/api/neworder", "/clientpage/confirm", "/clientpage/cancelorder")));
        accessConfig.put(Role.DRIVER, new HashSet<>(Arrays.asList("/driverpage", "/logout", "/api/postloginredirect", "/driverpage/takeorder", "/driverpage/confirmation")));

        accessConfig.entrySet()
                .forEach(e -> e
                        .setValue(e
                                .getValue()
                                .stream()
                                .map(s -> prefix + s)
                                .collect(Collectors.toSet())));

    }

}