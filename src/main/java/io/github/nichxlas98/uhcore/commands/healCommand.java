package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.AdminLevelUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.utils.AdminLevelUtil.LOW_ADMIN_LEVEL;
import static org.bukkit.ChatColor.*;
import static org.bukkit.ChatColor.GREEN;

public class healCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            int playerAdminLevel = AdminLevelUtil.getAdminLevel(player.getUniqueId());
            String permsError = RED + "[*] You do not have permission to do this!";
            if (playerAdminLevel >= LOW_ADMIN_LEVEL) {
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
            } else {
                player.sendMessage(permsError);
            }
        }


        return true;
    }
}
