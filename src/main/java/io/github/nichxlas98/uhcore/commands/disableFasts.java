package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.listeners.fastsListener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class disableFasts implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!fastsListener.fastsEnabled) {
                player.sendMessage(ChatColor.RED + "[*] FastUHC has already been disabled.");
            } else {
                fastsListener.fastsEnabled = false;
                player.sendMessage(ChatColor.RED + "[*] FastUHC has been disabled!");
            }

        }

        return true;
    }
}
