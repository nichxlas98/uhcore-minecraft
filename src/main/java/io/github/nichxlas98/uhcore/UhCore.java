package io.github.nichxlas98.uhcore;

import io.github.nichxlas98.uhcore.items.ItemManager;
import io.github.nichxlas98.uhcore.utils.RegistrationUtil;
import io.github.nichxlas98.uhcore.utils.DatabaseUtil;
import io.github.nichxlas98.uhcore.utils.ServerManagerUtil;
import org.bukkit.plugin.java.JavaPlugin;

import static io.github.nichxlas98.uhcore.utils.DatabaseUtil.*;


public final class UhCore extends JavaPlugin {

    private static UhCore plugin;

    private static void setPlugin(JavaPlugin javaPlugin) {
        plugin = (UhCore) javaPlugin;
    }

    public static UhCore getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        setPlugin(this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        DatabaseUtil.saveCustomData(config, yml);
        DatabaseUtil.saveCustomData(configBackup, ymlBackup);

        ItemManager.init();
        RegistrationUtil.initializeCommands();
        RegistrationUtil.initializeEvents();

        if (ServerManagerUtil.checkScoreboardName()) return;
        ServerManagerUtil.setScoreboardName("UhCoreMC");

        //TODO: Store player items in a config/database when using /staffmode, since if a server forcefully shuts down while players are in staff mode, their items will be removed.
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DatabaseUtil.saveCustomData(config, yml);
        DatabaseUtil.saveCustomData(configBackup, ymlBackup);
    }
}
