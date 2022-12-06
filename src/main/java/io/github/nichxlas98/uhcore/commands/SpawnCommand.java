package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;
import static io.github.nichxlas98.uhcore.models.ModelsClass.playerAdminLevel;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.MAX_ADMIN_LEVEL;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;

        if (!(playerAdminLevel(player) == MAX_ADMIN_LEVEL)) {
            player.sendMessage(ChatColor.RED + "[*] You do not have permission to use this command.");
            return true;
        }

        Location location = player.getLocation();
        FileConfiguration config = UhCore.getPlugin().getConfig();
        config.options().copyDefaults(true);
        config.set("spawn.world", location.getWorld().getName());
        config.set("spawn.x", String.valueOf(location.getX()));
        config.set("spawn.y", String.valueOf(location.getY()));
        config.set("spawn.z", String.valueOf(location.getZ()));
        config.set("spawn.yaw", String.valueOf(location.getYaw()));
        config.set("spawn.pitch", String.valueOf(location.getPitch()));
        UhCore.getPlugin().saveConfig();
        player.sendMessage(ChatColor.AQUA + "[*] " + "Server spawn location has been saved.");
        return true;
    }
}
