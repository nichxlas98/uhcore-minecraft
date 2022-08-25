package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class borderCommand implements CommandExecutor {

    private final UhCore plugin;

    public borderCommand(UhCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String theCommand = "worldborder add -100 60";

            Bukkit.dispatchCommand(console, "worldborder set 2000");

            BukkitTask task = new BukkitRunnable() {
                public void run() {
                    Bukkit.dispatchCommand(console, theCommand);
                    Bukkit.broadcastMessage(ChatColor.RED + "[*] The border has begun to shrink!");
                    Bukkit.broadcastMessage(ChatColor.GRAY + "[*] The border will continue to shrink every 5 minutes...");

                }

            }.runTaskTimer(plugin, 0L, 6000);
        } else {
            sender.sendMessage(ChatColor.RED + "[*] You cannot use this command.");
        }

        return true;
    }
}
