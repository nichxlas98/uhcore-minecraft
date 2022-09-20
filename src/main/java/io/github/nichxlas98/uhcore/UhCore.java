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

        //TODO: More modes (Add an "Ultimate" Mode - special effects on X input)
        //TODO: BlockBreakEvent sends error, tools doesn't enchant if the tool is damaged;
        //TODO: Leaderboards System using config files.
        //TODO: Make the border shrink in the Nether.
        //TODO: PotUHC modifier, leaves drop Health pots, gold ore drops Speed pots, on player death - give the killer 3 pearls, XXX drops pearls;
        //TODO: NoClean modifier, on player kill, if NoClean is enabled - add the player to a list, if the player's in that list, cancel damage - after 60 seconds, remove them from the list.


        //Changes:
        //Added a custom YML file for saving player data, called playerData.yml
        //Added a persistent player PMs system, player PMs will now stay blocked even on server restarts until they're unblocked.
        //Fixed the "/near" command registering regular mobs as nearby players.
        //Refined "/near" to only show the number of nearby players and not the exact player names.
        //Added & refined a "NoClean" modifier.
        //Fixed a bug causing players to keep their inventory on death.
        //Fixed an issue allowing players to break blocks with custom drops regardless of server setting.
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        maintenanceMode = false;
        gameEnabled = false;
        databaseUtil.saveCustomData(config, yml);
    }
}
