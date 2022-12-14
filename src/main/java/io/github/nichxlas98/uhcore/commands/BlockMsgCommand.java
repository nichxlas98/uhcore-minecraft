package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.PlayerManagerUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;
import static io.github.nichxlas98.uhcore.models.ModelsClass.pmsBlocked;

public class BlockMsgCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;

        if (pmsBlocked.contains(player)) {
            pmsBlocked.remove(player);
            player.sendMessage(ChatColor.GREEN + "[*] Your PMs have been enabled.");
            PlayerManagerUtil.setMessageStatus(player.getUniqueId(), true);
        } else {
            pmsBlocked.add(player);
            player.sendMessage(ChatColor.RED + "[*] Your PMs have been disabled.");
            PlayerManagerUtil.setMessageStatus(player.getUniqueId(), false);
        }
        return true;
    }
}
