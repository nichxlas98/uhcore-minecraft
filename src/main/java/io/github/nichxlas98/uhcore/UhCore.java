package io.github.nichxlas98.uhcore;

import io.github.nichxlas98.uhcore.items.itemManager;
import io.github.nichxlas98.uhcore.utils.autoRegisterUtil;
import io.github.nichxlas98.uhcore.utils.databaseUtil;
import org.bukkit.plugin.java.JavaPlugin;

import static io.github.nichxlas98.uhcore.models.modelsClass.gameEnabled;
import static io.github.nichxlas98.uhcore.models.modelsClass.maintenanceMode;
import static io.github.nichxlas98.uhcore.utils.databaseUtil.config;
import static io.github.nichxlas98.uhcore.utils.databaseUtil.yml;


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

        databaseUtil.saveCustomData(config, yml);

        itemManager.init();
        autoRegisterUtil.registerCommands();
        autoRegisterUtil.registerEvents();

        //TODO: BlockBreakEvent sends error, tools doesn't enchant if the tool is damaged;
        //TODO: Leaderboards System using config files.
        //TODO: Make the border shrink in the Nether.
        //TODO: PotUHC modifier, leaves drop Health pots, gold ore drops Speed pots, on player death - give the killer 3 pearls, XXX drops pearls;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        maintenanceMode = false;
        gameEnabled = false;
        databaseUtil.saveCustomData(config, yml);
    }
}
