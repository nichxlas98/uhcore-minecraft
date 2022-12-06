package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.AdminUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static io.github.nichxlas98.uhcore.models.MessageModels.PERMS_ERROR;
import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;
import static io.github.nichxlas98.uhcore.models.ModelsClass.playerAdminLevel;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.MAX_ADMIN_LEVEL;
import static io.github.nichxlas98.uhcore.utils.ServerUtils.setMaintenanceMode;

public class MaintenanceCommand implements CommandExecutor {

    public ArrayList<String> isNotAdmin = new ArrayList<>();
    boolean disabled = true;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;

        if (disabled) {
            isDisabled(player);
            return true;
        }

        if (playerAdminLevel(player) == MAX_ADMIN_LEVEL) {
            player.sendMessage(PERMS_ERROR);
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "[*] You need to use /maintenance <true/false>");
            return true;
        }

        if (args[0].equalsIgnoreCase("true")) {
            if (Bukkit.getServer().hasWhitelist()) {
                player.sendMessage(ChatColor.RED + "[*] Maintenance mode is already enabled.");
                return true;
            }

            setMaintenanceMode(true);
            player.sendMessage(ChatColor.GOLD + "[*] Maintenance mode has been enabled.");
            Bukkit.getServer().setWhitelist(true);
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!(AdminUtil.getAdminLevel(online.getUniqueId()) >= 2)) {
                    isNotAdmin.add(online.getName());
                }
            }

            for (String p : isNotAdmin) {
                p = p.replace("[]", "");
                Player equipped = Bukkit.getServer().getPlayer(p);
                if (equipped == null) {
                    player.sendMessage(ChatColor.RED + "[*] All players have been kicked from the server.");
                    return true;
                }
                equipped.kickPlayer(Color.RED + "This server's currently undergoing maintenance.");
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("false")) {
            setMaintenanceMode(false);
            Bukkit.getServer().setWhitelist(false);
            player.sendMessage(ChatColor.GREEN + "[*] Maintenance mode has been disabled.");
            return true;
        }
        return true;
    }

    private void isDisabled(Player sender) {
        sender.sendMessage(ChatColor.RED + "[*] This command is temporarily disabled until further notice!");
    }
}
