package io.github.nichxlas98.uhcore.commands.supporter;

import io.github.nichxlas98.uhcore.utils.RankUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class nearCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (RankUtil.isSupporter(player.getUniqueId())) {
                List<Entity> near = player.getNearbyEntities(20.0D, 20.0D, 20.0D);
                for (Entity entity : near) {
                    if (entity instanceof Player) {
                        player.sendMessage(ChatColor.GREEN + "[*] We've found the following players nearby:");
                        player.sendMessage(ChatColor.AQUA + "[*] " + entity);
                    } else {
                        player.sendMessage(ChatColor.RED + "[*] We could not find any players nearby.");
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "[*] You must be a Supporter to use this command!");
            }
        }
        return true;
    }
}
