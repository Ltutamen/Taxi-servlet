package ua.axiom.security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface UrlSecuritySettings {
    String prefix = "/servfv2_war_exploded";

    List<String> allAllowedUrls = Stream.of(
            "/login", "/index", "/", "/logout"
    ).map(prefix::concat).collect(Collectors.toList());

    List<String> securedUrls = Stream.of(
            "/clientpage", "/driverpage", "/adminpage")
            .map(prefix::concat).collect(Collectors.toList());

}
