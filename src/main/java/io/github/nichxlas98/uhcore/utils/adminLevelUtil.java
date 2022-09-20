package io.github.nichxlas98.uhcore.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

import static io.github.nichxlas98.uhcore.utils.databaseUtil.config;
import static io.github.nichxlas98.uhcore.utils.databaseUtil.yml;

public class adminLevelUtil {

    public static int MAX_ADMIN_LEVEL = 4;
    public static int HIGH_ADMIN_LEVEL = 3;
    public static int LOW_ADMIN_LEVEL = 2;
    public static int MIN_ADMIN_LEVEL = 1;

    public static void setAdminLevel(UUID p, int adminLevel) {
        config.set("stats." + p + ".adminLevel", adminLevel);
        databaseUtil.saveCustomData(config, yml);
    }

    public static boolean hasAdminLevel(UUID p) {
        return config.getString("stats." + p + ".adminLevel") != null;
    }

    public static int getAdminLevel(UUID p) {
        int adminLevel = 0;
        if (config.getString("stats." + p + ".adminLevel") != null) {
            adminLevel = Integer.parseInt(config.getString("stats." + p + ".adminLevel"));
            return adminLevel;
        } else {
            System.out.println("[*] That player does not have an adminLevel.");
        }
        return adminLevel;
    }

    public static String isManager(Player player, String message) {
        return ChatColor.GRAY + "(" + ChatColor.RED + "Manager" + ChatColor.GRAY + ") " + ChatColor.GOLD + player.getName() + " says: " + ChatColor.WHITE + message;
    }

    public static String isSenior(Player player, String message) {
        return ChatColor.GRAY + "(" + ChatColor.DARK_AQUA + "Senior" + ChatColor.GRAY + ") " + ChatColor.RED + player.getName() + " says: " + ChatColor.WHITE + message;
    }

    public static String isAdmin(Player player, String message) {
        return ChatColor.GRAY + "(" + ChatColor.AQUA + "Administrator" + ChatColor.GRAY + ") " + ChatColor.YELLOW + player.getName() + " says: " + ChatColor.WHITE + message;
    }

    public static String isJunior(Player player, String message) {
        return ChatColor.GRAY + "(" + ChatColor.GOLD + "Junior" + ChatColor.GRAY + ") " + ChatColor.GRAY + player.getName() + " says: " + ChatColor.WHITE + message;
    }

    public static String isCommunity(Player player, String message) {
        return ChatColor.GRAY + "(" + ChatColor.YELLOW + "Community" + ChatColor.GRAY + ") " + ChatColor.GRAY + player.getName() + " says: " + ChatColor.WHITE + message;
    }

    public static String isSupporter(Player player, String message) {
        return ChatColor.GRAY + "(" + ChatColor.LIGHT_PURPLE + "Supporter" + ChatColor.GRAY + ") " + ChatColor.GRAY + player.getName() + " says: " + ChatColor.WHITE + message;
    }
}
