package io.github.nichxlas98.uhcore;

import io.github.nichxlas98.uhcore.items.ItemManager;
import io.github.nichxlas98.uhcore.utils.AutoRegisterUtil;
import org.bukkit.plugin.java.JavaPlugin;

import static io.github.nichxlas98.uhcore.models.modelsClass.gameEnabled;
import static io.github.nichxlas98.uhcore.models.modelsClass.maintenanceMode;


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

        //TODO: More modes (Add an "Ultimate" Mode - special effects on X input)
        //TODO: BlockBreakEvent sends error, tools doesn't enchant if the tool is damaged;
        //TODO: Leaderboards System using config files.
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        maintenanceMode = false;
        gameEnabled = false;
    }
}
