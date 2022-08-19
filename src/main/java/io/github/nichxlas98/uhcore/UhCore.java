package io.github.nichxlas98.uhcore;

import io.github.nichxlas98.uhcore.commands.*;
import io.github.nichxlas98.uhcore.items.ItemManager;
import io.github.nichxlas98.uhcore.listeners.fastsListener;
import io.github.nichxlas98.uhcore.listeners.gameWinListener;
import io.github.nichxlas98.uhcore.listeners.playerListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class UhCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        ItemManager.init();
        getServer().getPluginManager().registerEvents(new playerListener(this), this);
        getServer().getPluginManager().registerEvents(new gameWinListener(), this);
        getServer().getPluginManager().registerEvents(new fastsListener(), this);
        this.getCommand("enablefasts").setExecutor(new enableFasts());
        this.getCommand("disablefasts").setExecutor(new disableFasts());
        getCommand("endgame").setExecutor(new endGameCommand(this));
        getCommand("startgame").setExecutor(new startGameCommand(this));
        getCommand("createspawn").setExecutor(new spawnCommand(this));
        getCommand("gotospawn").setExecutor(new gotoSpawnCommand(this));
        getCommand("createborder").setExecutor(new borderCommand(this));

    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
