package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.playerManagerUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.models.modelsClass.pmsBlocked;

public class blockMsgCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[*] You can't do this from the console.");
            return true;
        }
        Player player = (Player) sender;

        if (pmsBlocked.contains(player)) {
            pmsBlocked.remove(player);
            player.sendMessage(ChatColor.GREEN + "[*] Your PMs have been enabled.");
            playerManagerUtil.unblockMessages(player.getUniqueId());
        } else {
            player.sendMessage(ChatColor.RED + "[*] Your PMs have been disabled.");
            pmsBlocked.add(player);
            playerManagerUtil.blockMessages(player.getUniqueId());
        }
        return true;
    }
}
