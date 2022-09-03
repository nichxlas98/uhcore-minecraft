package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.AdminLevelUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static io.github.nichxlas98.uhcore.models.modelsClass.maintenanceMode;
import static io.github.nichxlas98.uhcore.utils.AdminLevelUtil.MAX_ADMIN_LEVEL;

public class maintenanceCommand implements CommandExecutor {

    public ArrayList<String> isNotAdmin = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        if (AdminLevelUtil.getAdminLevel(player.getUniqueId()) == MAX_ADMIN_LEVEL) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "[*] You need to use /maintenance <true/false>");
                return true;
            }

            if (args[0].equalsIgnoreCase("true")) {
                if (Bukkit.getServer().hasWhitelist()) {
                    player.sendMessage(ChatColor.RED + "[*] Maintenance mode is already enabled.");
                    return true;
                }

                maintenanceMode = true;
                player.sendMessage(ChatColor.GOLD + "[*] Maintenance mode has been enabled.");
                Bukkit.getServer().setWhitelist(true);
                for (Player online : Bukkit.getOnlinePlayers()) {
                    if (!(AdminLevelUtil.getAdminLevel(online.getUniqueId()) >= 2)) {
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
                maintenanceMode = false;
                Bukkit.getServer().setWhitelist(false);
                player.sendMessage(ChatColor.GREEN + "[*] Maintenance mode has been disabled.");
                return true;
            }
        } else {
            player.sendMessage(ChatColor.RED + "[*] You do not have permission to do this!");
        }
        return true;
    }
}
