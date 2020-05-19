package ua.axiom.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TokenFactory {
    private static final String secret = "fsfssdfsfsf234revfwcczverfwrgs";

    public static String getToken(String username) {
        StringBuilder payload = new StringBuilder(username);

        StringBuilder payloadAndSecret = new StringBuilder(payload).append(secret);

        // Static getInstance method is called with hashing SHA
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return new String(md.digest(payloadAndSecret.toString().getBytes(StandardCharsets.UTF_8)));
    }
}
