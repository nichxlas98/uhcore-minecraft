package io.github.nichxlas98.uhcore.utils;

import java.util.UUID;

import static io.github.nichxlas98.uhcore.utils.DatabaseUtil.config;

public class PlayerManagerUtil {

    public static void setSupporter(UUID p, boolean isSupporter) {
        if (isSupporter) {
            config.set("stats." + p + ".supporter", "true");
            DatabaseUtil.saveCustomData(config, DatabaseUtil.yml);
            return;
        }

        config.set("stats." + p + ".supporter", "false");
        DatabaseUtil.saveCustomData(config, DatabaseUtil.yml);
    }

    public static boolean getSupporter(UUID p) {
        return config.getString("stats." + p + ".supporter") != null || config.getString("stats." + p + ".supporter").equalsIgnoreCase("true");
    }


    public static void setMessageStatus(UUID p, boolean status) {
        if (status) {
            config.set("stats." + p + ".messageStatus", "enabled");
            DatabaseUtil.saveCustomData(config, DatabaseUtil.yml);
            return;
        }

        config.set("stats." + p + ".messageStatus", "disabled");
        DatabaseUtil.saveCustomData(config, DatabaseUtil.yml);
    }

    public static boolean getMessageStatus(UUID p) {
        return config.getString("stats." + p + ".messageStatus").equals("enabled");
    }
}
