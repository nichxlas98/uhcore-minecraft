package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.AdminLevelUtil;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (AdminLevelUtil.getAdminLevel(player.getUniqueId()) > 0) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "[*] You need to use: /g <(s)pectator/(su)rvival/(c)reative>");
                    return true;
                }

                if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("s")) {
                    if (AdminLevelUtil.getAdminLevel(player.getUniqueId()) >= 1) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(ChatColor.GOLD + "[*] Your gamemode has been altered. " + ChatColor.RED + "(SPECTATOR");
                    } else {
                        player.sendMessage(ChatColor.RED + "[*] You do not have permission to do this!");
                    } return true;
                }

                if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) {
                    if (AdminLevelUtil.getAdminLevel(player.getUniqueId()) > 2) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(ChatColor.GOLD + "[*] Your gamemode has been altered. " + ChatColor.RED + "(CREATIVE");
                    } else {
                        player.sendMessage(ChatColor.RED + "[*] You do not have permission to do this!");
                    } return true;
                }

                if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("su")) {
                    if (AdminLevelUtil.getAdminLevel(player.getUniqueId()) > 2) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(ChatColor.GOLD + "[*] Your gamemode has been altered. " + ChatColor.RED + "(CREATIVE");
                    } else {
                        player.sendMessage(ChatColor.RED + "[*] You do not have permission to do this!");
                    } return true;
                }


                if (AdminLevelUtil.getAdminLevel(player.getUniqueId()) >= 2) {

                    return true;
                }
            } else {
                player.sendMessage(ChatColor.RED + "[*] You do not have permission to do this!");
            }
        } else {
            System.out.println(ChatColor.RED + "[*] You cannot do this from the console.");
        } return true;
    }
}
