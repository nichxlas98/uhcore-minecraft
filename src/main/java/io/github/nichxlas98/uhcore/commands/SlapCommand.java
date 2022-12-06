package io.github.nichxlas98.uhcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.models.MessageModels.PERMS_ERROR;
import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;
import static io.github.nichxlas98.uhcore.models.ModelsClass.playerAdminLevel;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.MIN_ADMIN_LEVEL;

public class SlapCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;

        if (!(playerAdminLevel(player) >= MIN_ADMIN_LEVEL)) {
            player.sendMessage(PERMS_ERROR);
            return true;
        }

        if (!(args.length > 0)) {
            player.sendMessage(ChatColor.RED + "[*] You need to use: /slap <player>");
            return true;
        }

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
        return true;
    }
}
