package io.github.nichxlas98.uhcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.models.MessageModels.*;
import static io.github.nichxlas98.uhcore.models.ModelsClass.playerAdminLevel;
import static io.github.nichxlas98.uhcore.utils.AdminlevelUtil.MIN_ADMIN_LEVEL;

public class GetHereCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        senderConsoleError(sender);

        Player player = (Player) sender;

        if (!(playerAdminLevel(player) >= MIN_ADMIN_LEVEL)) {
            player.sendMessage(PERMS_ERROR);
            return true;
        }

        if (args.length > 0) {
            //retrieve the first argument as a player
            Player target = Bukkit.getServer().getPlayer(args[0]);

            if (target == null) {
                player.sendMessage(ChatColor.RED + "[*] We couldn't find that player.");
                return true;
            }

            if (target == player) {
                player.sendMessage(ChatColor.RED + "[*] You cannot teleport yourself, silly.");
                return true;
            }

            target.teleport(player);
            target.sendMessage(ChatColor.GOLD + "[*] " + player.getName() + " has teleported you to them.");
            player.sendMessage(ChatColor.GOLD + "[*] " + target.getName() + " has been teleported to you.");
        } else {
            //if there are no arguments
            player.sendMessage(ChatColor.RED + "[*] You need to use: /gethere <player>");
        }
        return true;
    }
}
