package io.github.nichxlas98.uhcore.utils;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static io.github.nichxlas98.uhcore.utils.ServerUtils.isGameEnabled;

public class BorderUtil {

    public static void startBorder(int size) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String theCommand = "worldborder add -200 60";

        ArrayList<String> wbList = new ArrayList<>();
        wbList.add("world");
        wbList.add("world_nether");

        for (String s : wbList) {
            WorldBorder wb = Bukkit.getWorld(s).getWorldBorder();
            wb.setCenter(0, 0);
            wb.setSize(size);
            wb.setDamageAmount(1D);
            wb.setDamageBuffer(5D);
            wb.setWarningDistance(100);
        }

        new BukkitRunnable() {
            public void run() {
                if (!(isGameEnabled())) {
                    this.cancel();
                    return;
                }

                Bukkit.dispatchCommand(console, theCommand);
                Bukkit.broadcastMessage(ChatColor.RED + "[*] The border has begun to shrink!");
                Bukkit.broadcastMessage(ChatColor.GRAY + "[*] The border will continue to shrink every 5 minutes...");
            }
        }.runTaskTimer(UhCore.getPlugin(), 0L, 6000);
    }

    public static void resetBorder() {
        ArrayList<String> wbList = new ArrayList<>();
        wbList.add("world");
        wbList.add("world_nether");

        for (String s : wbList) {
            WorldBorder wb = Bukkit.getWorld(s).getWorldBorder();
            wb.setCenter(0, 0);
            wb.setSize(5000);
        }
    }
}
