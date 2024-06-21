package com.maven.TaskManagementSystem;

import java.security.SecureRandom;
import java.util.Base64;

public class GenerateJWTSecret {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[32];
        random.nextBytes(keyBytes);
        String jwtSecret = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("Generated JWT Secret: " + jwtSecret);
    }
}
