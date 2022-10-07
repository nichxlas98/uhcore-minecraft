package io.github.nichxlas98.uhcore.utils;

import java.util.UUID;

import static io.github.nichxlas98.uhcore.utils.DatabaseUtil.config;
import static io.github.nichxlas98.uhcore.utils.DatabaseUtil.yml;

public class PlayerManagerUtil {

    public static void addSupporter(UUID p) {
        config.set("stats." + p + ".supporter", "true");
        DatabaseUtil.saveCustomData(config, yml);
    }

    public static void removeSupporter(UUID p) {
        config.set("stats." + p + ".supporter", null);
        DatabaseUtil.saveCustomData(config, yml);
    }

    public static boolean isSupporter(UUID p) {
        return config.getString("stats." + p + ".supporter") != null;
    }

    public static void blockMessages(UUID p) {
        config.set("stats." + p + ".messageStatus", "blocked");
        DatabaseUtil.saveCustomData(config, yml);
    }

    public static void unblockMessages(UUID p) {
        config.set("stats." + p + ".messageStatus", null);
        DatabaseUtil.saveCustomData(config, yml);
    }

    public static boolean getMessageStatus(UUID p) {
        return config.getString("stats." + p + ".disabledPMs") != null;
    }
}
