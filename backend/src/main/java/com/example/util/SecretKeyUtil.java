package com.example.util;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyUtil {

    private static final int KEY_LENGTH = 8; // 256位的密钥

    public static String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[KEY_LENGTH];
        secureRandom.nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }
}
