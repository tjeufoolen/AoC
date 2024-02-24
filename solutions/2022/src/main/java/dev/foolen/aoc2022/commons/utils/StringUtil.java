package dev.foolen.aoc2022.commons.utils;

public class StringUtil {
    public static String getLastFromString(String str) {
        String[] parts = str.split("");
        return parts[parts.length - 1];
    }

    public static char getLastCharacterFromString(String str) {
        String[] parts = str.split("");
        return parts[parts.length - 1].charAt(0);
    }
}
