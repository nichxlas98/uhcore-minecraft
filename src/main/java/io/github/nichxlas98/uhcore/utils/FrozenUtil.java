package io.github.nichxlas98.uhcore.utils;

import org.bukkit.entity.Player;

import java.util.UUID;

import static io.github.nichxlas98.uhcore.models.ModelsClass.playerFrozen;
import static io.github.nichxlas98.uhcore.utils.DatabaseUtil.*;

public class FrozenUtil {

    public static boolean isFrozen(UUID p) {
        return config.get("stats." + p + "isFrozen") != null;
    }

    public static void setFrozen(UUID p, Player player, boolean isFrozen) {
        if (isFrozen) {
            playerFrozen.add(player);
            config.set("stats." + p + "isFrozen", "true");
            saveCustomData(config, yml);
            return;
        }

        playerFrozen.remove(player);
        config.set("stats." + p + "isFrozen", null);
        saveCustomData(config, yml);
    }

}
