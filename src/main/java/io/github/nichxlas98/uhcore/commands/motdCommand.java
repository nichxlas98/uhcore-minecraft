package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.adminLevelUtil;
import io.github.nichxlas98.uhcore.utils.serverManagerUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class motdCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (adminLevelUtil.getAdminLevel(player.getUniqueId()) == 4) {

                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "[*] Please use: /motd <toggle>.");
                    return true;
                }

                if (args[0].equalsIgnoreCase("toggle")) {
                    if (serverManagerUtil.checkMotd()) {
                        player.sendMessage(ChatColor.RED + "[*] UhCoreMC MOTD has been disabled.");
                        serverManagerUtil.motdDisable();
                    } else {
                        player.sendMessage(ChatColor.AQUA + "[*] UhCoreMC MOTD has been enabled.");
                        serverManagerUtil.motdEnable();
                    }
                }

            } else {
                player.sendMessage(ChatColor.RED + "[*] You do not have permission to do this..");
            }
        }

        return true;
    }
}
