package io.github.nichxlas98.uhcore;

import io.github.nichxlas98.uhcore.commands.*;
import io.github.nichxlas98.uhcore.items.ItemManager;
import io.github.nichxlas98.uhcore.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class UhCore extends JavaPlugin {

    private static UhCore plugin;
    public static UhCore getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //TODO: Possible (/duel) command?
        //TODO: More modes (Add an "Ultimate" Mode - special effects on X input)
        //TODO: Add more Kits; - (Jeweler, gives 2 diamonds)
        //TODO: BlockBreakEvent sends error, tools doesn't enchant if the tool is damaged;
        //TODO: Add a "blockpm" command that adds the player to a list, and if the player's in that list, send the sender an error;

        ItemManager.init();
        this.getCommand("gethere").setExecutor(new gethereCommand());
        this.getCommand("goto").setExecutor(new gotoCommand());
        this.getCommand("slap").setExecutor(new slapCommand());
        this.getCommand("pm").setExecutor(new messageCommand());
        this.getCommand("grant").setExecutor(new grantCommand());
        this.getCommand("kits").setExecutor(new kitsCommand());
        this.getCommand("maintenance").setExecutor(new maintenanceCommand());
        this.getCommand("settings").setExecutor(new settingsCommand());
        this.getCommand("a").setExecutor(new aCommand());

        getServer().getPluginManager().registerEvents(new playerListener(this), this);
        getServer().getPluginManager().registerEvents(new gameWinListener(this), this);
        getServer().getPluginManager().registerEvents(new GUIListener(), this);
        getServer().getPluginManager().registerEvents(new fastsListener(), this);
        getServer().getPluginManager().registerEvents(new chatListener(), this);
        getServer().getPluginManager().registerEvents(new graceListener(), this);
        getServer().getPluginManager().registerEvents(new permissionListener(), this);

        getCommand("game").setExecutor(new gameCommand(this));
        getCommand("createspawn").setExecutor(new spawnCommand(this));
        getCommand("gotospawn").setExecutor(new gotoSpawnCommand(this));
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
