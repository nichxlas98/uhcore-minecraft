package io.github.nichxlas98.uhcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.models.MessageModels.*;
import static io.github.nichxlas98.uhcore.models.ModelsClass.playerAdminLevel;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.LOW_ADMIN_LEVEL;
import static org.bukkit.ChatColor.*;
import static org.bukkit.ChatColor.GREEN;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;

        if (!(playerAdminLevel(player) >= LOW_ADMIN_LEVEL)) {
            player.sendMessage(PERMS_ERROR);
            return true;
        }

        if (args.length == 0) {
            // Heal the sender.
            if (player.getHealth() >= 20.0) {
                player.sendMessage(RED + "[*] Your health has already been regenerated.");
            } else {
                player.setHealth(20.0);
                player.sendMessage(GREEN + "[*] Your health has been regenerated.");
            }
            return true;
        }

        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (target == null) {
            // Target's not found.
            player.sendMessage(RED + "[*] That player could not be found.");
            return true;
        }

        if (target.getHealth() <= 19.0) {
            target.setHealth(20.0);
            target.sendMessage(GREEN + "[*] You've been healed by " + GOLD + player.getName() + GREEN + "!");
            player.sendMessage(GREEN + "[*] " + GOLD + target.getName() + GREEN + " has been healed!");
        }
        return true;
    }
}
