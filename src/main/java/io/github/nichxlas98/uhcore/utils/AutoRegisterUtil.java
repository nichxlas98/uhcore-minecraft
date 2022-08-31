package io.github.nichxlas98.uhcore.utils;

import io.github.nichxlas98.uhcore.UhCore;
import io.github.nichxlas98.uhcore.commands.*;
import io.github.nichxlas98.uhcore.commands.supporter.nearCommand;
import io.github.nichxlas98.uhcore.listeners.*;
import org.bukkit.plugin.PluginManager;

import static org.bukkit.Bukkit.getServer;

public class AutoRegisterUtil {

    static UhCore plugin() { return UhCore.getPlugin(); }

    public static void registerCommands() {
        plugin().getCommand("gethere").setExecutor(new gethereCommand());
        plugin().getCommand("goto").setExecutor(new gotoCommand());
        plugin().getCommand("slap").setExecutor(new slapCommand());
        plugin().getCommand("pm").setExecutor(new messageCommand());
        plugin().getCommand("grant").setExecutor(new grantCommand());
        plugin().getCommand("kits").setExecutor(new kitsCommand());
        plugin().getCommand("maintenance").setExecutor(new maintenanceCommand());
        plugin().getCommand("modifiers").setExecutor(new modifiersCommand());
        plugin().getCommand("a").setExecutor(new adminCommand());
        plugin().getCommand("heal").setExecutor(new healCommand());
        plugin().getCommand("feed").setExecutor(new feedCommand());
        plugin().getCommand("gotospawn").setExecutor(new gotoSpawnCommand());
        plugin().getCommand("near").setExecutor(new nearCommand());
        plugin().getCommand("rank").setExecutor(new rankCommand());
        plugin().getCommand("game").setExecutor(new gameCommands());
        plugin().getCommand("createspawn").setExecutor(new spawnCommand(plugin()));
    }

    public static void registerEvents() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new playerStateListener(plugin()), plugin());
        manager.registerEvents(new noSwordsListener(), plugin());
        manager.registerEvents(new gameStateListener(plugin()), plugin());
        manager.registerEvents(new serverStateListener(), plugin());
        manager.registerEvents(new customDeathsListener(), plugin());
        manager.registerEvents(new GUIListener(), plugin());
        manager.registerEvents(new customDropsListener(), plugin());
        manager.registerEvents(new customChatListener(), plugin());
        manager.registerEvents(new graceListener(), plugin());
        manager.registerEvents(new permissionListener(), plugin());
    }

}
