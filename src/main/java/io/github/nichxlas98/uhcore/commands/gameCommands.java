package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.AdminLevelUtil;
import io.github.nichxlas98.uhcore.utils.SpawnUtil;
import io.github.nichxlas98.uhcore.utils.uhcUtil;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.models.modelsClass.*;
import static io.github.nichxlas98.uhcore.utils.AdminLevelUtil.MIN_ADMIN_LEVEL;

public class gameCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) { sender.sendMessage(ChatColor.RED + "[*] You cannot do this from the console"); return true; }

        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Player player = (Player) sender;
        int playerAdminLevel = AdminLevelUtil.getAdminLevel(player.getUniqueId());

        if (playerAdminLevel >= MIN_ADMIN_LEVEL) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "[*] You need to use: /game <start/end>");
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "start":
                    if (!gameEnabled) {
                        gameEnabled = true;
                        gracePeriod = true;
                        Bukkit.dispatchCommand(console, "spreadplayers 0 0 150 1000 false @a");
                        uhcUtil.uhcTasks(player);
                        uhcUtil.uhcKits();
                        uhcUtil.uhcModifiers(player);
                    } else {
                        player.sendMessage(ChatColor.RED + "[*] The game is already started.");
                    }
                    break;
                case "end":
                    if (gameEnabled) {
                        gameEnabled = false;
                        gracePeriod = false;

                        WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
                        wb.setCenter(0, 0);
                        wb.setSize(5000);

                        player.sendMessage(ChatColor.RED + "[*] You've forcefully ended the game.");
                        for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                            players.sendMessage(ChatColor.RED + "[*] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.RED + " has forcefully ended the game.");
                            players.setGameMode(GameMode.SURVIVAL);
                            Bukkit.dispatchCommand(console, "clear @a");
                            Bukkit.dispatchCommand(console, "effect @a clear");
                            SpawnUtil.spawnTeleport(players);
                            return true;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "[*] There is no game currently running.");
                    }
                    break;
                default:
                    // Invalid args
                    player.sendMessage(ChatColor.RED + "[*] You need to use: /game <start/end>");
                    return true;
            }
        } else {
            player.sendMessage(ChatColor.RED + "[*] You do not have permission to use this command.");
        }
        return true;
    }
}
