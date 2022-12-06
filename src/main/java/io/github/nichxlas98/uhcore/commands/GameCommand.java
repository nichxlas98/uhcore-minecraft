package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.SpawnUtil;
import io.github.nichxlas98.uhcore.utils.GameManagerUtil;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import static io.github.nichxlas98.uhcore.models.MessageModels.*;
import static io.github.nichxlas98.uhcore.models.ModelsClass.*;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.MIN_ADMIN_LEVEL;
import static io.github.nichxlas98.uhcore.utils.BorderUtil.resetBorder;
import static io.github.nichxlas98.uhcore.utils.ServerUtils.*;
import static org.bukkit.Bukkit.getServer;

public class GameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        ConsoleCommandSender console = getServer().getConsoleSender();
        Player player = (Player) sender;

        if (!(playerAdminLevel(player) >= MIN_ADMIN_LEVEL)) {
            player.sendMessage(PERMS_ERROR);
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "[*] You need to use: /game <start/end>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "start":
                if (!isGameEnabled()) {
                    Bukkit.dispatchCommand(console, "spreadplayers 0 0 150 1000 false @a");

                    setGameEnabled(true);
                    setGracePeriod(true);
                    resetGraceTime();
                    GameManagerUtil.gameScoreboard();
                    GameManagerUtil.gameTasks(player);
                    GameManagerUtil.gameKits();
                    GameManagerUtil.gameModifiers(player);
                } else {
                    player.sendMessage(ChatColor.RED + "[*] The game is already started.");
                }
                break;
            case "end":
                if (isGameEnabled()) {
                    setGameEnabled(false);
                    setGracePeriod(false);
                    setGameMeetup(false);
                    resetBorder();

                    player.sendMessage(ChatColor.RED + "[*] You've forcefully ended the game.");
                    for (Player players : getServer().getOnlinePlayers()) {
                        for (PotionEffect effect : player.getActivePotionEffects()) {
                            player.removePotionEffect(effect.getType());
                        }

                        players.sendMessage(ChatColor.RED + "[*] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.RED + " has forcefully ended the game.");
                        players.setGameMode(GameMode.SURVIVAL);
                        players.getInventory().clear();

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
        return true;
    }
}
