package io.github.nichxlas98.uhcore.utils;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

public class AdminLevelUtil {

    // CRUD, Create, Read, U?, Delete

    public static void setAdminLevel(UUID p, int adminLevel) {
        FileConfiguration config = UhCore.getPlugin().getConfig();
        config.options().copyDefaults(true);
        config.set("stats." + p + ".adminLevel", adminLevel);
        UhCore.getPlugin().saveConfig();
    }

    public static boolean hasAdminLevel(UUID p) {
        return UhCore.getPlugin().getConfig().getString("stats." + p + ".adminLevel") != null;
    }

    public static int getAdminLevel(UUID p) {
        int adminLevel = 0;
        if (UhCore.getPlugin().getConfig().getString("stats." + p + ".adminLevel") != null) {
            adminLevel = Integer.parseInt(UhCore.getPlugin().getConfig().getString("stats." + p + ".adminLevel"));
            return adminLevel;
        } else {
            System.out.println("That player does not have an adminLevel.");

        }

        return adminLevel;
    }


}
