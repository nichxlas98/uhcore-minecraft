package io.github.nichxlas98.uhcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.commands.StaffModeCommand.manageInventory;
import static io.github.nichxlas98.uhcore.models.MessageModels.PERMS_ERROR;
import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.HIGH_ADMIN_LEVEL;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.getAdminLevel;
import static io.github.nichxlas98.uhcore.utils.DeathUtil.getLastDL;

public class ReviveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;

        if (getAdminLevel(player.getUniqueId()) < HIGH_ADMIN_LEVEL) {
            player.sendMessage(PERMS_ERROR);
            return true;
        }


        if (args.length < 2) {
            player.sendMessage(ChatColor.RED + "[*] You need to use /revive <player> <hard/light>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.RED + "[*] That player isn't online.");
            return true;
        }

        if (args[1].equalsIgnoreCase("light")) {
            manageInventory(target, "clear");
            manageInventory(target, "load");
            player.sendMessage(ChatColor.AQUA + "[*] You've revived " + target.getName());
            target.sendMessage(ChatColor.GREEN + "[*] You've been revived.");
            return true;
        }

        if (args[1].equalsIgnoreCase("hard")) {
            manageInventory(target, "clear");
            manageInventory(target, "load");
            target.teleport(getLastDL(target));
            player.sendMessage(ChatColor.AQUA + "[*] You've revived " + target.getName());
            target.sendMessage(ChatColor.GREEN + "[*] You've been revived.");
        }


        return true;
    }
}
