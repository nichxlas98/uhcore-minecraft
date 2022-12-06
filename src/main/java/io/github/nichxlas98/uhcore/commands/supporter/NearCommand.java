package io.github.nichxlas98.uhcore.commands.supporter;

import io.github.nichxlas98.uhcore.utils.PlayerManagerUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;

public class NearCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;

        if (!(PlayerManagerUtil.getSupporter(player.getUniqueId()))) {
            player.sendMessage(ChatColor.RED + "[*] You must be a Supporter to use this command!");
            return true;
        }

        List<Entity> near = player.getNearbyEntities(20.0D, 20.0D, 20.0D);
        if (near.isEmpty()) {
            player.sendMessage(ChatColor.RED + "[*] We could not find any players nearby.");
            return true;
        }

        player.sendMessage(ChatColor.GREEN + "[*] We've found the following players nearby:");
        for (Entity entity : near) {
            if (!(entity instanceof Player)) return true;
            player.sendMessage(ChatColor.AQUA + "[*] " + near.size() + ".");
        }
        return true;
    }
}
