package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.ServerManagerUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.models.MessageModels.PERMS_ERROR;
import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;
import static io.github.nichxlas98.uhcore.models.ModelsClass.playerAdminLevel;
import static io.github.nichxlas98.uhcore.utils.AdminlevelUtil.MAX_ADMIN_LEVEL;

public class MotdCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;

        if (!(playerAdminLevel(player) == MAX_ADMIN_LEVEL)) {
            player.sendMessage(PERMS_ERROR);
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "[*] Please use: /motd <toggle>");
            return true;
        }

        if (!(args[0].equalsIgnoreCase("toggle"))) return true;
        if (ServerManagerUtil.checkMotd()) {
            player.sendMessage(ChatColor.RED + "[*] SERVER MOTD has been disabled.");
            ServerManagerUtil.motdDisable();
        } else {
            player.sendMessage(ChatColor.AQUA + "[*] SERVER MOTD has been enabled.");
            ServerManagerUtil.motdEnable();
        }
        return true;
    }
}
