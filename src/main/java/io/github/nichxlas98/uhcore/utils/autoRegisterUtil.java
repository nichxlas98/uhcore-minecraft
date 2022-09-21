package io.github.nichxlas98.uhcore.utils;

import io.github.nichxlas98.uhcore.UhCore;
import io.github.nichxlas98.uhcore.commands.*;
import io.github.nichxlas98.uhcore.commands.supporter.nearCommand;
import io.github.nichxlas98.uhcore.listeners.*;
import io.github.nichxlas98.uhcore.listeners.modifiers.lifestealDeathsListener;
import io.github.nichxlas98.uhcore.listeners.modifiers.noCleanListener;
import io.github.nichxlas98.uhcore.listeners.modifiers.noSwordsListener;
import org.bukkit.plugin.PluginManager;

import static org.bukkit.Bukkit.getServer;

public class autoRegisterUtil {

    static UhCore plugin() {
        return UhCore.getPlugin();
    }

    public static void registerCommands() {
        plugin().getCommand("blockpm").setExecutor(new blockMsgCommand());
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
        plugin().getCommand("gotospawn").setExecutor(new spawnGotoCommand());
        plugin().getCommand("near").setExecutor(new nearCommand());
        plugin().getCommand("rank").setExecutor(new rankCommand());
        plugin().getCommand("game").setExecutor(new gameCommands());
        plugin().getCommand("createspawn").setExecutor(new spawnCommand());
        plugin().getCommand("scoreboard").setExecutor(new scoreboardCommand());
        plugin().getCommand("motd").setExecutor(new motdCommand());
    }

    public static void registerEvents() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new lifestealDeathsListener(), plugin());
        manager.registerEvents(new noCleanListener(), plugin());
        manager.registerEvents(new noSwordsListener(), plugin());

        manager.registerEvents(new customChatListener(), plugin());
        manager.registerEvents(new customDropsListener(), plugin());
        manager.registerEvents(new deathMessagesListener(), plugin());
        manager.registerEvents(new gameStateListener(), plugin());
        manager.registerEvents(new GUIListener(), plugin());
        manager.registerEvents(new permissionListener(), plugin());
        manager.registerEvents(new playerStateListener(), plugin());
        manager.registerEvents(new scoreboardListener(), plugin());
        manager.registerEvents(new serverGraceListener(), plugin());
        manager.registerEvents(new serverMeetupListener(), plugin());
        manager.registerEvents(new serverStateListener(), plugin());
    }
}
