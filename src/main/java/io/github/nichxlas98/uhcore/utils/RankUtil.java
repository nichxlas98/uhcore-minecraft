package io.github.nichxlas98.uhcore.utils;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

public class RankUtil {

    public static void addSupporter(UUID p) {
        FileConfiguration config = UhCore.getPlugin().getConfig();
        config.options().copyDefaults(true);
        config.set("stats." + p + ".supporter", "true");
        UhCore.getPlugin().saveConfig();
    }

    public static void removeSupporter(UUID p) {
        FileConfiguration config = UhCore.getPlugin().getConfig();
        config.options().copyDefaults(true);
        config.set("stats." + p + ".supporter", null);
        UhCore.getPlugin().saveConfig();
    }

    public static boolean isSupporter(UUID p) {
        return UhCore.getPlugin().getConfig().getString("stats." + p + ".supporter") != null;
    }





}
