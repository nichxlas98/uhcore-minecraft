package io.github.nichxlas98.uhcore.utils;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.configuration.file.FileConfiguration;

public class ServerManagerUtil {

    private static final FileConfiguration config = UhCore.getPlugin().getConfig();
    public static final String scoreboardName = getScoreboardName();

    public static void setScoreboardName(String scoreboardName) {
        config.options().copyDefaults(true);
        config.set("scoreboard-name", scoreboardName);
        UhCore.getPlugin().saveConfig();
    }

    public static boolean checkScoreboardName() {
        return UhCore.getPlugin().getConfig().getString("scoreboard-name") != null;
    }

    public static String getScoreboardName() {
        return UhCore.getPlugin().getConfig().getString("scoreboard-name");
    }

    public static void motdEnable() {
        config.options().copyDefaults(true);
        config.set("motd", "enabled");
        UhCore.getPlugin().saveConfig();
    }

    public static void motdDisable() {
        config.options().copyDefaults(true);
        config.set("motd", null);
        UhCore.getPlugin().saveConfig();
    }

    public static boolean checkMotd() {
        return UhCore.getPlugin().getConfig().getString("motd") != null;
    }


    public static void scoreboardEnable() {
        FileConfiguration config = UhCore.getPlugin().getConfig();
        config.options().copyDefaults(true);
        config.set("scoreboard", "enabled");
        UhCore.getPlugin().saveConfig();
    }

    public static void scoreboardDisable() {
        FileConfiguration config = UhCore.getPlugin().getConfig();
        config.options().copyDefaults(true);
        config.set("scoreboard", null);
        UhCore.getPlugin().saveConfig();
    }

    public static boolean checkScoreboard() {
        return UhCore.getPlugin().getConfig().getString("scoreboard") != null;
    }
}
