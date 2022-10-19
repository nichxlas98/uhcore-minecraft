package io.github.nichxlas98.uhcore;

import io.github.nichxlas98.uhcore.items.ItemManager;
import io.github.nichxlas98.uhcore.utils.RegistrationUtil;
import io.github.nichxlas98.uhcore.utils.DatabaseUtil;
import org.bukkit.plugin.java.JavaPlugin;

import static io.github.nichxlas98.uhcore.utils.DatabaseUtil.config;
import static io.github.nichxlas98.uhcore.utils.DatabaseUtil.yml;


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

        ItemManager.init();
        RegistrationUtil.registerCommands();
        RegistrationUtil.registerEvents();

        //TODO: BlockBreakEvent sends error, tools doesn't enchant if the tool is damaged;
        //TODO: Leaderboards System using config files.
        //TODO: Make the border shrink in the Nether.
        //TODO: PotUHC modifier, leaves drop Health pots, gold ore drops Speed pots, on player death - give the killer 3 pearls, XXX drops pearls;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DatabaseUtil.saveCustomData(config, yml);
    }
}
