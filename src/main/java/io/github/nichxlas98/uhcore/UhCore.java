package io.github.nichxlas98.uhcore;

import io.github.nichxlas98.uhcore.commands.*;
import io.github.nichxlas98.uhcore.items.ItemManager;
import io.github.nichxlas98.uhcore.listeners.GUIListener;
import io.github.nichxlas98.uhcore.listeners.fastsListener;
import io.github.nichxlas98.uhcore.listeners.gameWinListener;
import io.github.nichxlas98.uhcore.listeners.playerListener;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public final class UhCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        //TODO: Clean up the startGame & endGame command - make it one command, and introduce args[0]
        //TODO: Clean up the enableFasts & disableFasts command - make it one command, and introduce args[0]
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        ItemManager.init();
        getServer().getPluginManager().registerEvents(new GUIListener(), this);
        getServer().getPluginManager().registerEvents(new playerListener(this), this);
        getServer().getPluginManager().registerEvents(new gameWinListener(this), this);
        getServer().getPluginManager().registerEvents(new fastsListener(), this);
        this.getCommand("fasts").setExecutor(new fastsCommand());
        getCommand("game").setExecutor(new gameCommand(this));
        getCommand("createspawn").setExecutor(new spawnCommand(this));
        getCommand("gotospawn").setExecutor(new gotoSpawnCommand(this));
        getCommand("createborder").setExecutor(new borderCommand(this));

        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        BukkitTask task3 = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(console, "gamerule logAdminCommands false");
                Bukkit.dispatchCommand(console, "scoreboard objectives add health health");
            }
        }.runTaskLater(this, 1200L /*<-- the delay */);



    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
