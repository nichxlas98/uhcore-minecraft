package io.github.nichxlas98.uhcore.utils;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SpawnUtil {
    public static UhCore plugin() {
        return UhCore.getPlugin();
    }

    public static void spawnTeleport(Player player) {
        if (plugin().getConfig().getString("spawn.world") != null) {
            World w = Bukkit.getServer().getWorld(plugin().getConfig().getString("spawn.world"));
            double x = Double.parseDouble(plugin().getConfig().getString("spawn.x"));
            double y = Double.parseDouble(plugin().getConfig().getString("spawn.y"));
            double z = Double.parseDouble(plugin().getConfig().getString("spawn.z"));
            float yaw = Float.parseFloat(plugin().getConfig().getString("spawn.yaw"));
            float pitch = Float.parseFloat(plugin().getConfig().getString("spawn.pitch"));

            player.teleport(new Location(w, x, y, z, yaw, pitch));
            player.sendMessage(ChatColor.AQUA + "[*] " + "You've been teleported to the server spawn-point.");
        } else {
            player.sendMessage(ChatColor.RED + "[*] There is no spawn point yet.");
            System.out.println("There is no spawn point set.");
        }
    }
}
