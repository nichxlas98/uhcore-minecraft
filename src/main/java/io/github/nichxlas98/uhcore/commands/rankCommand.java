package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.RankUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class rankCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "[*] You can use: /rank <player> <supporter-true/supporter-false>");
            return true;
        }

        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.RED + "[*] " + ChatColor.GOLD + args[0] + ChatColor.RED + " is not a player!");
            return true;
        }

        if (args[1].equalsIgnoreCase("supporter-true")) {
            if (RankUtil.isSupporter(player.getUniqueId())) {
                player.sendMessage(ChatColor.RED + "[*] That player is already a Supporter.");
                return true;
            }
            target.sendMessage(ChatColor.GREEN + "[*] You've been granted " + ChatColor.GOLD + "Supporter " + ChatColor.GREEN + "rank.");
            player.sendMessage(ChatColor.GREEN + "[*] You've granted " + target.getName() + " with the Supporter rank.");
            RankUtil.addSupporter(player.getUniqueId());
            return true;
        }

        if (args[1].equalsIgnoreCase("supporter-false")) {
            if (!(RankUtil.isSupporter(player.getUniqueId()))) {
                player.sendMessage(ChatColor.RED + "[*] That player is not a Supporter.");
                return true;
            }
            player.sendMessage(ChatColor.GREEN + "[*] You've removed " + target.getName() + " from the Supporter rank.");
            RankUtil.removeSupporter(player.getUniqueId());
            return true;
        }
        return true;
    }
}
