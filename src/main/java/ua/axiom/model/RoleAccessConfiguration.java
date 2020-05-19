package ua.axiom.model;

import java.util.*;
import java.util.stream.Collectors;

public class RoleAccessConfiguration {
    public static Map<Role, Set<String>> accessConfig = new HashMap<>();
    public static String prefix = "";

    static {
        accessConfig.put(Role.GUEST, new HashSet<>(Arrays.asList("/", "/login", "/error", "/register")));
        accessConfig.put(Role.ADMIN, new HashSet<>(Arrays.asList("/adminpage", "/logout", "/postloginredirect")));
        accessConfig.put(Role.CLIENT, new HashSet<>(Arrays.asList("/clientpage", "/logout", "/postloginredirect")));
        accessConfig.put(Role.DRIVER, new HashSet<>(Arrays.asList("/driverpage", "/logout", "/postloginredirect")));

        accessConfig.entrySet()
                .forEach(e -> e
                        .setValue(e
                                .getValue()
                                .stream()
                                .map(s -> prefix + s)
                                .collect(Collectors.toSet())));

    }

}
