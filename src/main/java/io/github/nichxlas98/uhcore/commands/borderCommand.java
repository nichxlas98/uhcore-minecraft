package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import static io.github.nichxlas98.uhcore.models.modelsClass.gameEnabled;

public class borderCommand implements CommandExecutor {

    private final UhCore plugin;

    public borderCommand(UhCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String theCommand = "worldborder add -200 60";
            WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
            wb.setCenter(0, 0);
            wb.setSize(2000);
            wb.setDamageAmount(1D);
            wb.setDamageBuffer(5D);
            wb.setWarningDistance(100);



            BukkitTask task = new BukkitRunnable() {
                public void run() {
                    if (gameEnabled) {
                        Bukkit.dispatchCommand(console, theCommand);
                        Bukkit.broadcastMessage(ChatColor.RED + "[*] The border has begun to shrink!");
                        Bukkit.broadcastMessage(ChatColor.GRAY + "[*] The border will continue to shrink every 5 minutes...");
                    }
                }

            }.runTaskTimer(plugin, 0L, 6000);
        } else {
            sender.sendMessage(ChatColor.RED + "[*] You cannot use this command.");
        }

        return true;
    }
}
