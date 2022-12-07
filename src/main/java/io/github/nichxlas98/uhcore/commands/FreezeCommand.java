package io.github.nichxlas98.uhcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.models.MessageModels.PERMS_ERROR;
import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;
import static io.github.nichxlas98.uhcore.models.ModelsClass.playerAdminLevel;
import static io.github.nichxlas98.uhcore.models.ModelsClass.playerFrozen;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.MIN_ADMIN_LEVEL;
import static io.github.nichxlas98.uhcore.utils.FrozenUtil.setFrozen;

public class FreezeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;

        if (!(playerAdminLevel(player) > MIN_ADMIN_LEVEL)) {
            player.sendMessage(PERMS_ERROR);
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "[*] You need to use: /freeze <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.RED + "[*] " + args[0] + " is not a player!");
            return true;
        }

        if (target == player) {
            player.sendMessage(ChatColor.RED + "[*] You cannot freeze yourself.");
            return true;
        }

        if (playerFrozen.contains(target)) {
            player.sendMessage(ChatColor.AQUA + "[*] You've unfrozen " + target.getDisplayName());
            target.sendMessage(ChatColor.RED + "[*] You've been unfrozen!");
            setFrozen(target, false);
            return true;
        }

        player.sendMessage(ChatColor.AQUA + "[*] You've frozen " + target.getDisplayName());
        target.sendMessage(ChatColor.RED + "[*] You've been frozen!");
        setFrozen(target, true);
        return true;
    }
}
