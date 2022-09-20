package io.github.nichxlas98.uhcore.utils;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldBorder;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import static io.github.nichxlas98.uhcore.models.modelsClass.gameEnabled;

public class borderUtil {

    public static void startBorder(int size) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String theCommand = "worldborder add -200 60";
        WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
        wb.setCenter(0, 0);
        wb.setSize(size);
        wb.setDamageAmount(1D);
        wb.setDamageBuffer(5D);
        wb.setWarningDistance(100);

        BukkitTask task = new BukkitRunnable() {
            public void run() {
                if (gameEnabled) {
                    Bukkit.dispatchCommand(console, theCommand);
                    Bukkit.broadcastMessage(ChatColor.RED + "[*] The border has begun to shrink!");
                    Bukkit.broadcastMessage(ChatColor.GRAY + "[*] The border will continue to shrink every 5 minutes...");
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(UhCore.getPlugin(), 0L, 6000);
    }
}
