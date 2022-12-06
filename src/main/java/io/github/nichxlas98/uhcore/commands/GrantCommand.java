package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.AdminUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.models.MessageModels.*;
import static io.github.nichxlas98.uhcore.models.ModelsClass.playerAdminLevel;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.MAX_ADMIN_LEVEL;

public class GrantCommand implements CommandExecutor {

    private void listAdminLevels(Player player) {
        player.sendMessage(ChatColor.RED + "[*] You can use: /grant <player> <adminLevel>");
        player.sendMessage(ChatColor.RED + "[*] AdminLevel 1 = " + ChatColor.GOLD + "Junior Admin.");
        player.sendMessage(ChatColor.RED + "[*] AdminLevel 2 = " + ChatColor.AQUA + "Administrator.");
        player.sendMessage(ChatColor.RED + "[*] AdminLevel 3 = " + ChatColor.DARK_AQUA + "Senior Admin.");
        player.sendMessage(ChatColor.RED + "[*] AdminLevel 4 = " + ChatColor.RED + "Manager.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;
        int amount;

        if (!(playerAdminLevel(player) == MAX_ADMIN_LEVEL) || !(player.hasPermission("UhCore.manager")) || !player.isOp()) {
            player.sendMessage(PERMS_ERROR);
            return true;
        }

        if (args.length == 0) {
            listAdminLevels(player);
            return true;
        }

        if (args.length >= 3) {
            listAdminLevels(player);
            return true;
        }

        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.RED + "[*] " + ChatColor.GOLD + args[0] + ChatColor.RED + " is not a player!");
            return true;
        }

        try {
            amount = Integer.parseInt(args[1]);
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[*] Error! You need to specify an Admin Level!");
            listAdminLevels(player);
            return true;
        }

        AdminUtil.setAdminLevel(target.getUniqueId(), amount);
        player.sendMessage(ChatColor.GREEN + "[*] You've granted " + target.getName() + " with an admin level of " + ChatColor.GOLD + amount + ".");
        switch (amount) {
            case 1:
                target.sendMessage(ChatColor.GREEN + "[*] You've been granted " + ChatColor.GOLD + "Junior Admin " + ChatColor.GREEN + "by " + player.getName() + ".");
                break;
            case 2:
                target.sendMessage(ChatColor.GREEN + "[*] You've been granted " + ChatColor.AQUA + "Administrator " + ChatColor.GREEN + "by " + player.getName() + ".");
                break;
            case 3:
                target.sendMessage(ChatColor.GREEN + "[*] You've been granted " + ChatColor.DARK_AQUA + "Senior Admin " + ChatColor.GREEN + "by " + player.getName() + ".");
                break;
            case 4:
                target.sendMessage(ChatColor.GREEN + "[*] You've been granted " + ChatColor.GOLD + "Manager " + ChatColor.GREEN + "by " + player.getName() + ".");
                break;
            default:
                target.sendMessage(ChatColor.GREEN + "[*] Your admin level has been set to " + ChatColor.GOLD + "0 " + ChatColor.GREEN + "by " + player.getName() + ".");
        }
        return true;
    }
}
