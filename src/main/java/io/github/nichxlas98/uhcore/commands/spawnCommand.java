package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class spawnCommand implements CommandExecutor {

    private final UhCore plugin;

    public spawnCommand(UhCore plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location location = player.getLocation();

            FileConfiguration config = plugin.getConfig();
            config.options().copyDefaults(true);
            config.set("spawn.world", player.getLocation().getWorld().getName());
            config.set("spawn.x", String.valueOf(player.getLocation().getX()));
            config.set("spawn.y", String.valueOf(player.getLocation().getY()));
            config.set("spawn.z", String.valueOf(player.getLocation().getZ()));
            config.set("spawn.yaw", String.valueOf(player.getLocation().getYaw()));
            config.set("spawn.pitch", String.valueOf(player.getLocation().getPitch()));
            plugin.saveConfig();

            player.sendMessage(ChatColor.AQUA + "[*] " + "Server spawn location has been saved.");
        } else {
            System.out.println("Failed! You must be a player to save a spawn location.");
        }

        return true;
    }
}
