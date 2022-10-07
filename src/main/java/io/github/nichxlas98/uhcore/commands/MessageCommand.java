package io.github.nichxlas98.uhcore.commands;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;
import static io.github.nichxlas98.uhcore.models.ModelsClass.pmsBlocked;

public class MessageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        senderConsoleError(sender);
        Player player = (Player) sender;

        if (args.length > 1) {
            //retrieve the first argument as a player
            Player target = Bukkit.getServer().getPlayer(args[0]);
            String sm;

            if (target == null) {
                player.sendMessage(ChatColor.RED + "[*] We couldn't find that player.");
                return true;
            }

            if (pmsBlocked.contains(target)) {
                player.sendMessage(ChatColor.RED + "[*] That player has their PMs blocked.");
                return true;
            }

            // combine the arguments the player typed
            StringBuilder smBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                String arg = (args[i] + " ");
                smBuilder.append(arg);
            }
            sm = smBuilder.toString();

            //send the actual message
            target.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 2);
            target.sendMessage(ChatColor.GOLD + "(( PM from " + player.getName() + ": " + sm + "))");
            player.sendMessage(ChatColor.GOLD + "(( PM to " + target.getName() + ": " + sm + "))");
        } else {
            //if there are no arguments
            player.sendMessage(ChatColor.RED + "[*] You need to use: /pm <player> <message> OR /pm <block>");
        }
        return true;
    }
}
