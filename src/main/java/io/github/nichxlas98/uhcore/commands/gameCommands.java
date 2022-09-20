package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.adminLevelUtil;
import io.github.nichxlas98.uhcore.utils.spawnUtil;
import io.github.nichxlas98.uhcore.utils.gameManagerUtil;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import static io.github.nichxlas98.uhcore.models.modelsClass.*;
import static io.github.nichxlas98.uhcore.utils.adminLevelUtil.MIN_ADMIN_LEVEL;
import static io.github.nichxlas98.uhcore.utils.gameManagerUtil.graceTime;
import static org.bukkit.Bukkit.getServer;

public class gameCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[*] You cannot do this from the console");
            return true;
        }

        ConsoleCommandSender console = getServer().getConsoleSender();
        Player player = (Player) sender;
        int playerAdminLevel = adminLevelUtil.getAdminLevel(player.getUniqueId());

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
                        graceTime = 10;
                        Bukkit.dispatchCommand(console, "spreadplayers 0 0 150 1000 false @a");
                        gameManagerUtil.gameScoreboard();
                        gameManagerUtil.gameTasks(player);
                        gameManagerUtil.gameKits();
                        gameManagerUtil.gameModifiers(player);
                    } else {
                        player.sendMessage(ChatColor.RED + "[*] The game is already started.");
                    }
                    break;
                case "end":
                    if (gameEnabled) {
                        gameEnabled = false;
                        gracePeriod = false;
                        gameMeetup = false;
                        WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
                        wb.setCenter(0, 0);
                        wb.setSize(5000);

                        player.sendMessage(ChatColor.RED + "[*] You've forcefully ended the game.");
                        for (Player players : getServer().getOnlinePlayers()) {
                            players.sendMessage(ChatColor.RED + "[*] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.RED + " has forcefully ended the game.");
                            players.setGameMode(GameMode.SURVIVAL);
                            players.getInventory().clear();
                            for (PotionEffect effect : player.getActivePotionEffects()) {
                                player.removePotionEffect(effect.getType());
                            }

                            spawnUtil.spawnTeleport(players);
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
