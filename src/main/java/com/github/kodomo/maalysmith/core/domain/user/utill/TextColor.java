package com.github.kodomo.maalysmith.core.domain.user.utill;

import org.bukkit.ChatColor;

public class TextColor {

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
