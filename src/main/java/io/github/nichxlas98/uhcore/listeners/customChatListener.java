package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.utils.adminLevelUtil;
import io.github.nichxlas98.uhcore.utils.playerManagerUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static io.github.nichxlas98.uhcore.models.modelsClass.adminChat;
import static io.github.nichxlas98.uhcore.utils.adminLevelUtil.*;

public class customChatListener implements Listener {

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Player player = e.getPlayer();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        int playerAdminLevel = adminLevelUtil.getAdminLevel(player.getUniqueId());
        if (e.getMessage().contains("#")) {
            if (playerAdminLevel >= MIN_ADMIN_LEVEL) {
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

        switch (playerAdminLevel) {
            case 0:
                if (playerManagerUtil.isSupporter(player.getUniqueId())) {
                    console.sendMessage(adminLevelUtil.isSupporter(player, e.getMessage()));
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendMessage(adminLevelUtil.isSupporter(player, e.getMessage()));
                    }
                    return;
                }
                console.sendMessage(adminLevelUtil.isCommunity(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(adminLevelUtil.isCommunity(player, e.getMessage()));
                }
                break;
            case 1:
                console.sendMessage(adminLevelUtil.isJunior(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(adminLevelUtil.isJunior(player, e.getMessage()));
                }
                break;
            case 2:
                console.sendMessage(adminLevelUtil.isAdmin(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(adminLevelUtil.isAdmin(player, e.getMessage()));
                }
                break;
            case 3:
                console.sendMessage(adminLevelUtil.isSenior(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(adminLevelUtil.isSenior(player, e.getMessage()));
                }
                break;
            case 4:
                console.sendMessage(adminLevelUtil.isManager(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(adminLevelUtil.isManager(player, e.getMessage()));
                }
                break;
            default:
                //error message
        }
    }

}
