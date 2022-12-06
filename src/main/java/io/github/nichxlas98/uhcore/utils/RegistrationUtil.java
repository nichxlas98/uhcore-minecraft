package io.github.nichxlas98.uhcore.utils;

import io.github.nichxlas98.uhcore.UhCore;
import io.github.nichxlas98.uhcore.commands.*;
import io.github.nichxlas98.uhcore.commands.supporter.NearCommand;
import io.github.nichxlas98.uhcore.listeners.*;
import io.github.nichxlas98.uhcore.listeners.modifiers.LifeStealModifier;
import io.github.nichxlas98.uhcore.listeners.modifiers.NoCleanModifier;
import io.github.nichxlas98.uhcore.listeners.modifiers.NoSwordsModifier;
import io.github.nichxlas98.uhcore.listeners.modifiers.PotModifier;
import org.bukkit.plugin.PluginManager;

import static org.bukkit.Bukkit.getServer;

public class RegistrationUtil {

    private static UhCore plugin() {
        return UhCore.getPlugin();
    }

    public static void initializeCommands() {
        plugin().getCommand("blockpm").setExecutor(new BlockMsgCommand());
        plugin().getCommand("gethere").setExecutor(new GetHereCommand());
        plugin().getCommand("goto").setExecutor(new GoToCommand());
        plugin().getCommand("slap").setExecutor(new SlapCommand());
        plugin().getCommand("pm").setExecutor(new MessageCommand());
        plugin().getCommand("grant").setExecutor(new GrantCommand());
        plugin().getCommand("kits").setExecutor(new KitsCommand());
        plugin().getCommand("maintenance").setExecutor(new MaintenanceCommand());
        plugin().getCommand("modifiers").setExecutor(new ModifiersCommand());
        plugin().getCommand("a").setExecutor(new AdminCommand());
        plugin().getCommand("heal").setExecutor(new HealCommand());
        plugin().getCommand("feed").setExecutor(new FeedCommand());
        plugin().getCommand("gotospawn").setExecutor(new SpawnGoToCommand());
        plugin().getCommand("near").setExecutor(new NearCommand());
        plugin().getCommand("rank").setExecutor(new RankCommand());
        plugin().getCommand("game").setExecutor(new GameCommand());
        plugin().getCommand("createspawn").setExecutor(new SpawnCommand());
        plugin().getCommand("scoreboard").setExecutor(new ScoreboardCommand());
        plugin().getCommand("motd").setExecutor(new MotdCommand());
        plugin().getCommand("setscoreboardname").setExecutor(new ScNameCommand());
        plugin().getCommand("staffmode").setExecutor(new StaffModeCommand());
        plugin().getCommand("freeze").setExecutor(new FreezeCommand());
    }

    public static void initializeEvents() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new LifeStealModifier(), plugin());
        manager.registerEvents(new NoCleanModifier(), plugin());
        manager.registerEvents(new NoSwordsModifier(), plugin());
        manager.registerEvents(new PotModifier(), plugin());

        manager.registerEvents(new ChatListener(), plugin());
        manager.registerEvents(new DropsListener(), plugin());
        manager.registerEvents(new DeathsListener(), plugin());
        manager.registerEvents(new GameStateListener(), plugin());
        manager.registerEvents(new GUIListener(), plugin());
        manager.registerEvents(new PermissionListener(), plugin());
        manager.registerEvents(new PlayerStateListener(), plugin());
        manager.registerEvents(new ScoreboardListener(), plugin());
        manager.registerEvents(new ServerGraceListener(), plugin());
        manager.registerEvents(new ServerMeetupListener(), plugin());
        manager.registerEvents(new ServerStateListener(), plugin());
        manager.registerEvents(new PlayerStaffListener(), plugin());
        manager.registerEvents(new PlayerFrozenListener(), plugin());
    }
}
