package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.models.MessageModels;
import io.github.nichxlas98.uhcore.utils.ServerManagerUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScNameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (MessageModels.senderConsoleError(sender)) return true;
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "[*] You can use /setscoreboardname <scoreboardName> to set your custom scoreboard name.");
            return true;
        }

        player.sendMessage(ChatColor.GRAY + "[*] TIP! Remember to Color Code your Scoreboard Name! (https://htmlcolorcodes.com/minecraft-color-codes/)");

        player.sendMessage(ChatColor.RED + "Set Scoreboard Name to: " + args[0]);
        ServerManagerUtil.setScoreboardName(args[0]);
        return true;
    }
}
