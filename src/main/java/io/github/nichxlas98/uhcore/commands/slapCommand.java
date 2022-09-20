package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.adminLevelUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.utils.adminLevelUtil.MIN_ADMIN_LEVEL;

public class slapCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            int playerAdminLevel = adminLevelUtil.getAdminLevel(player.getUniqueId());
            if (playerAdminLevel >= MIN_ADMIN_LEVEL) {

                if (args.length > 0) {
                    //retrieve the first argument as a player
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target == null) {
                        player.sendMessage(ChatColor.RED + "[*] We couldn't find that player.");
                        return true;
                    }

                    Location targetLocation = target.getLocation().add(0, 5, 0);
                    target.teleport(targetLocation);
                    target.sendMessage(ChatColor.GOLD + "[*] " + player.getName() + " has slapped you!");
                    target.sendMessage(ChatColor.GOLD + "[*] " + target.getName() + " has been slapped!");
                } else {
                    //if there are no arguments
                    player.sendMessage(ChatColor.RED + "[*] You need to use: /slap <player>");
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
