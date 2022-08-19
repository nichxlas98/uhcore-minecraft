package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gotoSpawnCommand implements CommandExecutor {

    private final UhCore plugin;

    public gotoSpawnCommand(UhCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                //TODO: let the player TP to spawn (after permission implementation)
            }
            if(plugin.getConfig().getString("spawn.world") != null) {
                World w = Bukkit.getServer().getWorld(plugin.getConfig().getString("spawn.world"));
                double x = Double.parseDouble(plugin.getConfig().getString("spawn.x"));
                double y = Double.parseDouble(plugin.getConfig().getString("spawn.y"));
                double z = Double.parseDouble(plugin.getConfig().getString("spawn.z"));
                float yaw = Float.parseFloat(plugin.getConfig().getString("spawn.yaw"));
                float pitch = Float.parseFloat(plugin.getConfig().getString("spawn.pitch"));

                player.teleport(new Location(w, x, y, z, yaw, pitch));
                player.sendMessage(ChatColor.AQUA + "[*] " + "You've been teleported to the server spawn-point.");
            } else {
                System.out.println("There is no spawn point set.");
            }

        } else {
            System.out.println("You cannot do this from the console..");
        }

        return true;
    }
}
