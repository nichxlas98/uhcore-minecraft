package io.github.nichxlas98.uhcore;

import io.github.nichxlas98.uhcore.commands.*;
import io.github.nichxlas98.uhcore.items.ItemManager;
import io.github.nichxlas98.uhcore.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class UhCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //TODO: Code Cleanup Session;
        //TODO: Possible (/duel) command?
        //TODO: More modes (Add an "Ultimate" Mode - special effects on X input)
        //TODO: Add more Kits; (Enchanter, gives you enchant books) - (Jeweler, gives 2 diamonds)
        //TODO: Fix Worker kit giving you 2 pairs of tools
        //TODO: Fix Gold Miner not giving you anything
        //TODO: BlockBreakEvent sends error, tools doesn't enchant if the tool is damaged;

        ItemManager.init();
        getServer().getPluginManager().registerEvents(new GUIListener(), this);
        getServer().getPluginManager().registerEvents(new playerListener(this), this);
        getServer().getPluginManager().registerEvents(new gameWinListener(this), this);
        getServer().getPluginManager().registerEvents(new fastsListener(), this);
        getServer().getPluginManager().registerEvents(new graceListener(), this);
        getCommand("game").setExecutor(new gameCommand(this));
        getCommand("createspawn").setExecutor(new spawnCommand(this));
        getCommand("gotospawn").setExecutor(new gotoSpawnCommand(this));
        getCommand("createborder").setExecutor(new borderCommand(this));
        this.getCommand("kits").setExecutor(new kitsCommand());
        this.getCommand("settings").setExecutor(new settingsCommand());
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
