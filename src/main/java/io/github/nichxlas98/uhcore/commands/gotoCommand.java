package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.AdminLevelUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gotoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (AdminLevelUtil.getAdminLevel(player.getUniqueId()) >= 1) {

                if (args.length > 0) {
                    //retrieve the first argument as a player
                    Player target = Bukkit.getServer().getPlayer(args[0]);

                    if (target == null) {
                        player.sendMessage(ChatColor.RED + "[*] We couldn't find that player.");
                        return true;
                    }

                    if (target == player) {
                        player.sendMessage(ChatColor.RED + "[*] You cannot teleport to yourself, silly.");
                        return true;
                    }

                    player.teleport(target);
                    target.sendMessage(ChatColor.GOLD + "[*] " + player.getName() + " has teleported to you.");
                    player.sendMessage(ChatColor.GOLD + "[*] You've teleported to " + target.getName());
                } else {
                    //if there are no arguments
                    player.sendMessage(ChatColor.RED + "[*] You need to use: /goto <player>");
                }
            } else {
                player.sendMessage(ChatColor.RED + "[*] You do not have permission to do this!");
            }
        } else {
            System.out.println(ChatColor.RED + "[*] You cannot do this from the console.");
        }

        return true;
    }
}