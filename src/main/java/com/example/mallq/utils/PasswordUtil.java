package com.example.mallq.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
    public static String generateSalt(){
        byte [] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    // 使用 SHA-256 + salt 对密码进行哈希
    public static String hashWithSalt(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest((password + salt).getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    // 验证密码是否匹配（需要原始 salt）
    public static boolean verify(String inputPassword, String storedHash, String salt) throws NoSuchAlgorithmException {
        String hashedInput = hashWithSalt(inputPassword, salt);
        return hashedInput.equals(storedHash);
    }
}


