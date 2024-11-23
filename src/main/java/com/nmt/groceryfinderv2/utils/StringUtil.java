package com.nmt.groceryfinderv2.utils;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

/**
 * @author LENOVO
 * @project ecommerce-spring-boot-be
 * @date 7/31/2024
 */
public class StringUtil {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int STRING_LENGTH = 8;
    private static final SecureRandom random = new SecureRandom();

    public static String generateUniqueRandomString() {
        Set<Character> usedCharacters = new HashSet<>();
        StringBuilder sb = new StringBuilder(STRING_LENGTH);

        while (sb.length() < STRING_LENGTH) {
            char c = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
            if (!usedCharacters.contains(c)) {
                usedCharacters.add(c);
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
