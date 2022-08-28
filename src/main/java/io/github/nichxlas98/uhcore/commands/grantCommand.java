package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.AdminLevelUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class grantCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            int amount;
            if (AdminLevelUtil.getAdminLevel(player.getUniqueId()) == 4 || player.hasPermission("UhCore.manager")) {

                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "[*] You can use: /grant <player> <adminLevel>");
                    player.sendMessage(ChatColor.RED + "[*] AdminLevel 1 = " + ChatColor.GOLD + "Junior Admin.");
                    player.sendMessage(ChatColor.RED + "[*] AdminLevel 2 = " + ChatColor.AQUA + "Administrator.");
                    player.sendMessage(ChatColor.RED + "[*] AdminLevel 3 = " + ChatColor.DARK_AQUA + "Senior Admin.");
                    return true;
                }

                if (args.length >= 3) {
                    player.sendMessage(ChatColor.RED + "[*] You can use: /grant <player> <adminLevel>");
                    player.sendMessage(ChatColor.RED + "[*] AdminLevel 1 = " + ChatColor.GOLD + "Junior Admin.");
                    player.sendMessage(ChatColor.RED + "[*] AdminLevel 2 = " + ChatColor.AQUA + "Administrator.");
                    player.sendMessage(ChatColor.RED + "[*] AdminLevel 3 = " + ChatColor.DARK_AQUA + "Senior Admin.");
                    return true;
                }

                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "[*] " + ChatColor.GOLD + args[0] + ChatColor.RED+  " is not a player!");
                    return true;
                }

                try {
                    amount = Integer.parseInt(args[1]);
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + "[*] Error! You need to specify an Admin Level!");
                    player.sendMessage(ChatColor.RED + "[*] AdminLevel 1 = " + ChatColor.GOLD + "Junior Admin.");
                    player.sendMessage(ChatColor.RED + "[*] AdminLevel 2 = " + ChatColor.AQUA + "Administrator.");
                    player.sendMessage(ChatColor.RED + "[*] AdminLevel 3 = " + ChatColor.DARK_AQUA + "Senior Admin.");
                    return true;
                }

                AdminLevelUtil.setAdminLevel(target.getUniqueId(), amount);
                player.sendMessage(ChatColor.GREEN + "[*] You've granted " + target.getName() + " with an admin level of " + ChatColor.GOLD + amount + ".");
                switch (amount) {
                    case 1:
                        player.performCommand("lp user " + target.getName() + " parent set junior");
                        target.sendMessage(ChatColor.GREEN + "[*] You've been granted " + ChatColor.GOLD + "Junior Admin " + ChatColor.GREEN + "by " + player.getName() + ".");
                        break;
                    case 2:
                        player.performCommand("lp user " + target.getName() + " parent set admin");
                        target.sendMessage(ChatColor.GREEN + "[*] You've been granted " + ChatColor.AQUA + "Administrator " + ChatColor.GREEN + "by " + player.getName() + ".");
                        break;
                    case 3:
                        player.performCommand("lp user " + target.getName() + " parent set senior");
                        target.sendMessage(ChatColor.GREEN + "[*] You've been granted " + ChatColor.DARK_AQUA + "Senior Admin " + ChatColor.GREEN + "by " + player.getName() + ".");
                        break;
                    case 4:
                        player.performCommand("lp user " + target.getName() + " parent set manager");
                        target.sendMessage(ChatColor.GREEN + "[*] You've been granted " + ChatColor.GOLD + "Manager " + ChatColor.GREEN + "by " + player.getName() + ".");
                        break;
                    default:
                        player.performCommand("lp user " + target.getName() + " parent set default");
                        target.sendMessage(ChatColor.GREEN + "[*] Your admin level has been set to " + ChatColor.GOLD + "0 " + ChatColor.GREEN + "by " + player.getName() + ".");
                }

            } else {
                player.sendMessage(ChatColor.RED + "[*] You do not have permission to do this.");
            }
        } else {
            System.out.println(ChatColor.RED + "[*] You cannot do this from the console.");
        }
        return true;
    }
}
