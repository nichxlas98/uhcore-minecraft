package io.github.nichxlas98.uhcore.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static io.github.nichxlas98.uhcore.utils.DatabaseUtil.*;

public class DeathUtil {


    private static String getWorldName(Player player) {
        String worldName;
        switch (player.getWorld().getName().toLowerCase()) {
            case "world":
                worldName = "Overworld";
                break;
            case "world_nether":
                worldName = "The Nether";
                break;
            case "world_the_end":
                worldName = "The End";
                break;
            default:
                worldName = "Unknown";
        }

        return worldName;
    }

    private static String deathLocation(Player player) {
        String deathLoc;
        double X = Math.floor(player.getLocation().getX());
        double Y = Math.floor(player.getLocation().getZ());
        double Z = Math.floor(player.getLocation().getY());

        deathLoc = "[X: " + X + "], [Y: " + Y + "], [Z: " + Z + "]";
        return deathLoc;
    }

    public static void saveLastDeath(Player player) {
        String deathDate = new SimpleDateFormat("[dd/MM/yy]").format(Calendar.getInstance().getTime());
        String deathTime = new SimpleDateFormat("[HH:mm:ss]").format(Calendar.getInstance().getTime());

        config.set("stats." + player.getUniqueId() + ".lastDeathTime", deathDate + " / " + deathTime);
        config.set("stats." + player.getUniqueId() + ".lastDL", player.getLocation());
        config.set("stats." + player.getUniqueId() + ".lastDeathLocation", getWorldName(player));
        config.set("stats." + player.getUniqueId() + ".lastDeathCoords", deathLocation(player));
        config.set("stats." + player.getUniqueId() + ".lastDeathCause", player.getLastDamageCause().toString());
        saveCustomData(config, yml);
        if (player.getKiller() == null) return;
        config.set("stats." + player.getUniqueId() + ".lastKiller", player.getKiller().getName());
        saveCustomData(config, yml);
    }

    public static String getKiller(Player player) {
        if (config.get("stats." + player.getUniqueId() + ".lastKiller") != null) {
            return (String) config.get("stats." + player.getUniqueId() + ".lastKiller");
        }
        return "None";
    }

    public static String getLastDeathLocation(Player player) {
        if (config.get("stats." + player.getUniqueId() + ".lastDeathLocation") != null) {
            return (String) config.get("stats." + player.getUniqueId() + ".lastDeathLocation");
        }

        player.sendMessage(ChatColor.RED + "[*] No death location found...");
        return "Unknown";
    }

    public static String getLastDeathCoords(Player player) {
        if (config.get("stats." + player.getUniqueId() + ".lastDeathCoords") != null) {
            return (String) config.get("stats." + player.getUniqueId() + ".lastDeathCoords");
        }

        return "Unknown";
    }

    public static Location getLastDL(Player player) {
        if (config.get("stats." + player.getUniqueId() + ".lastDL") != null) {
            return (Location) config.get("stats." + player.getUniqueId() + ".lastDL");
        }

        player.sendMessage(ChatColor.RED + "[*] No death location found...");
        return player.getLocation();
    }

    public static String getLastDeathTime(Player player) {
        if (config.get("stats." + player.getUniqueId() + ".lastDeathTime") != null) {
            return (String) config.get("stats." + player.getUniqueId() + ".lastDeathTime");
        }
        return "None";
    }
}
