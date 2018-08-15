package me.bobcatsss.discordverify.utils;

import org.bukkit.ChatColor;

import java.util.Random;

public class Utils {

    public static String c(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String randomID(String chars, int length) {
        Random random = new Random();
        int charLength = chars.length() - 1;
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < length; i++) {
            id.append(chars.charAt(random.nextInt(charLength)));
        }
        return id.toString();
    }
}
