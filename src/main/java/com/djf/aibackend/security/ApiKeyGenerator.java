package com.djf.aibackend.security;
import java.security.SecureRandom;
import java.util.Base64;

public class ApiKeyGenerator {

    private static final int KEY_LENGTH = 32; // 32 bytes = 256 bits

    public static String generateApiKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[KEY_LENGTH];
        secureRandom.nextBytes(key);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(key);
    }
}
