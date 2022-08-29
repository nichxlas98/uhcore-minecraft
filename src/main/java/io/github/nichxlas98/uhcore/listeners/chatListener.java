package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.utils.AdminLevelUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static io.github.nichxlas98.uhcore.models.modelsClass.adminChat;

public class chatListener implements Listener {

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Player player = e.getPlayer();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        int adminLevel = AdminLevelUtil.getAdminLevel(player.getUniqueId());

        if (e.getMessage().contains("#")) {
            if (adminLevel > 1) {
                if (adminChat.contains(player)) {
                    console.sendMessage(ChatColor.AQUA + "[*] " + player.getName() + " says: " + e.getMessage());
                    for (Player achat : adminChat) {
                        achat.sendMessage(ChatColor.AQUA + "[*] " + player.getName() + " says: " + e.getMessage().replace("#", ""));
                    }
                    return;
                }
                adminChat.add(player);
                return;
            } else {
                adminChat.remove(player);
            }
        }

        if (adminLevel == 0) {
            console.sendMessage(ChatColor.GRAY + "(" + ChatColor.YELLOW + "Community" + ChatColor.GRAY + ") " + ChatColor.GRAY + player.getName() + " says: " + ChatColor.WHITE + e.getMessage());
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.sendMessage(ChatColor.GRAY + "(" + ChatColor.YELLOW + "Community" + ChatColor.GRAY + ") " + ChatColor.GRAY + player.getName() + " says: " + ChatColor.WHITE + e.getMessage());
            }
            return;
        } if (adminLevel == 1) {
            console.sendMessage(ChatColor.GRAY + "(" + ChatColor.GOLD + "Junior" + ChatColor.GRAY + ") " + ChatColor.GRAY + player.getName() + " says: " + ChatColor.WHITE + e.getMessage());
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.sendMessage(ChatColor.GRAY + "(" + ChatColor.GOLD + "Junior" + ChatColor.GRAY + ") " + ChatColor.GRAY + player.getName() + " says: " + ChatColor.WHITE + e.getMessage());
            }
            return;
        } if (adminLevel == 2) {
            console.sendMessage(ChatColor.GRAY + "(" + ChatColor.AQUA + "Administrator" + ChatColor.GRAY + ") " + ChatColor.YELLOW + player.getName() + " says: " + ChatColor.WHITE + e.getMessage());
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.sendMessage(ChatColor.GRAY + "(" + ChatColor.AQUA + "Administrator" + ChatColor.GRAY + ") " + ChatColor.YELLOW + player.getName() + " says: " + ChatColor.WHITE + e.getMessage());
            }
            return;
        } if (adminLevel == 3) {
            console.sendMessage(ChatColor.GRAY + "(" + ChatColor.DARK_AQUA + "Senior" + ChatColor.GRAY + ") " + ChatColor.RED + player.getName() + " says: " + ChatColor.WHITE + e.getMessage());
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.sendMessage(ChatColor.GRAY + "(" + ChatColor.DARK_AQUA + "Senior" + ChatColor.GRAY + ") " + ChatColor.RED + player.getName() + " says: " + ChatColor.WHITE + e.getMessage());
            }
            return;
        } if (adminLevel == 4) {
            console.sendMessage(ChatColor.GRAY + "(" + ChatColor.RED + "Manager" + ChatColor.GRAY + ") " + ChatColor.GOLD + player.getName() + " says: " + ChatColor.WHITE + e.getMessage());
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.sendMessage(ChatColor.GRAY + "(" + ChatColor.RED + "Manager" + ChatColor.GRAY + ") " + ChatColor.GOLD + player.getName() + " says: " + ChatColor.WHITE + e.getMessage());
            }
        }



    }

}
