package io.github.nichxlas98.uhcore.commands;


import io.github.nichxlas98.uhcore.utils.AdminLevelUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class healCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (AdminLevelUtil.getAdminLevel(player.getUniqueId()) >= 2) {

                if (args.length == 0) {
                    // Heal the sender.
                    if (player.getHealth() >= 20.0) {
                        player.sendMessage(ChatColor.RED + "[*] Your health has already been regenerated.");
                    } else {
                        player.setHealth(20.0);
                        player.sendMessage(ChatColor.GREEN + "[*] Your health has been regenerated.");
                    }
                    return true;
                }

                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target == null) {
                    // Target's not found.
                    player.sendMessage(ChatColor.RED + "[*] That player could not be found.");
                    return true;
                }


                if (target.getHealth() <= 19.0) {
                    target.setHealth(20.0);
                    target.sendMessage(ChatColor.GREEN + "[*] You've been healed by " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + "!");
                    player.sendMessage(ChatColor.GREEN + "[*] " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " has been healed!");
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

