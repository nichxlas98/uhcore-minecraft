package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.AdminLevelUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.utils.AdminLevelUtil.LOW_ADMIN_LEVEL;
import static io.github.nichxlas98.uhcore.utils.AdminLevelUtil.MIN_ADMIN_LEVEL;
import static org.bukkit.ChatColor.*;

public class adminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            int playerAdminLevel = AdminLevelUtil.getAdminLevel(player.getUniqueId());
            String permsError = RED + "[*] You do not have permission to do this!";
            if (playerAdminLevel >= MIN_ADMIN_LEVEL) {
                if (args.length == 0) {
                    player.sendMessage(RED + "[*] You can use:");
                    player.sendMessage(RED + "[*] " + GOLD + "/a <gmc/gms/spec>");
                    player.sendMessage(RED + "[*] " + GOLD + "/a <broadcast> <message>");
                    return true;
                }

                switch (args[0].toLowerCase()) {
                    case "gmc":
                        if (playerAdminLevel > LOW_ADMIN_LEVEL) {
                            player.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(GOLD + "[*] Your gamemode has been altered. " + RED + "(CREATIVE)");
                        } else {
                            player.sendMessage(permsError);
                        }
                        break;
                    case "gms":
                        if (playerAdminLevel > LOW_ADMIN_LEVEL) {
                            player.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(GOLD + "[*] Your gamemode has been altered. " + RED + "(SURVIVAL)");
                        } else {
                            player.sendMessage(permsError);
                        }
                        break;
                    case "spec":
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(GOLD + "[*] Your gamemode has been altered. " + RED + "(SPECTATOR)");
                        break;
                    case "broadcast":
                        StringBuilder sm = new StringBuilder();
                        for (int i = 1; i < args.length; i++){
                            String arg = (args[i] + " ");
                            sm.append(arg);
                        } if (args.length == 1) {
                            player.sendMessage(RED + "[*] You need to use /a broadcast <message>");
                            return true;
                        } for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendMessage(GRAY + "[" + GOLD + "UhCore98" + GRAY + "] " + RED + sm);
                        }
                        break;
                    default:
                        player.sendMessage(RED + "[*] You need to use: /a <gmc/gms/spec> | <heal/feed> (player)");
                }
            } else {
                player.sendMessage(permsError);
            }
        }
        return true;
    }
}
