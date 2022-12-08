package io.github.nichxlas98.uhcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.items.ItemManager.getStaffWooloff;
import static io.github.nichxlas98.uhcore.items.ItemManager.getStaffWoolon;
import static io.github.nichxlas98.uhcore.listeners.PlayerStaffListener.playerVanished;
import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;
import static io.github.nichxlas98.uhcore.models.ModelsClass.playerAdminLevel;
import static io.github.nichxlas98.uhcore.models.ModelsClass.staffMode;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.MIN_ADMIN_LEVEL;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;

        if (playerVanished.contains(player)) {
            playerVanished.remove(player);
            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                if (playerAdminLevel(online) > MIN_ADMIN_LEVEL) continue;
                online.showPlayer(player);
            }
            if (staffMode.contains(player)) player.getInventory().setItem(8, getStaffWoolon());
            player.sendMessage(ChatColor.GREEN + "[*] You're no longer in Vanish.");
            return true;
        }

        playerVanished.add(player);
        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            if (playerAdminLevel(online) > MIN_ADMIN_LEVEL) continue;
            online.hidePlayer(player);
        }

        if (staffMode.contains(player)) player.getInventory().setItem(8, getStaffWooloff());
        player.sendMessage(ChatColor.GRAY + "[*] You're now in Vanish.");
        return true;
    }
}
