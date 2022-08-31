package io.github.nichxlas98.uhcore;

import io.github.nichxlas98.uhcore.items.ItemManager;
import io.github.nichxlas98.uhcore.utils.AutoRegisterUtil;
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

        ItemManager.init();
        AutoRegisterUtil.registerCommands();
        AutoRegisterUtil.registerEvents();

        //TODO: Possible (/duel) command?
        //TODO: More modes (Add an "Ultimate" Mode - special effects on X input)
        //TODO: Add more Kits; - (Jeweler, gives 2 diamonds)
        //TODO: BlockBreakEvent sends error, tools doesn't enchant if the tool is damaged;
        //TODO: Add a "blockpm" command that adds the player to a list, and if the player's in that list, send the sender an error;
        //TODO: Leaderboards System using config files.
        //TODO: Custom Death Messages.

    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
