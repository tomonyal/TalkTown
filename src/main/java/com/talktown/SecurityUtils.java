package com.talktown;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.text.StringEscapeUtils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class SecurityUtils {
    // XSS対策
    public static String sanitize(String input) {
        return StringEscapeUtils.escapeHtml4(input);
    }

    // CSRF対策
    private static class CsrfToken {
        private static SecureRandom random = new SecureRandom();

        private CsrfToken() {
            // Private constructor to hide the implicit public one
        }

        public static String generateToken() {
            return new BigInteger(130, random).toString(32);
        }
    }

    public static String generateCsrfToken() {
        return CsrfToken.generateToken();
    }

    // パスワードのハッシュ化
    public static String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    // パスワードの検証
    public static boolean checkPassword(String rawPassword, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), hashedPassword);
        return result.verified;
    }

    // ブルートフォース対策
    public static final int MAX_ATTEMPT = 3;
    private static ConcurrentHashMap<String, Integer> attempts = new ConcurrentHashMap<>();

    public static void loginSucceeded(String key) {
        attempts.remove(key);
    }

    public static void loginFailed(String key) throws SQLException {
        int attemptsCount = attempts.getOrDefault(key, 0);
        attemptsCount++;
        attempts.put(key, attemptsCount);

        if (attemptsCount >= MAX_ATTEMPT) {
            UserDAO userDAO = new UserDAO();
            userDAO.lockUser(key);
        }
    }

    public static boolean isBlocked(String key) {
        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.findUserByUsername(key);
            return user != null && user.isLocked();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getAttempts(String key) {
        return attempts.getOrDefault(key, 0);
    }
}
