package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.utils.AdminLevelUtil;
import io.github.nichxlas98.uhcore.utils.RankUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static io.github.nichxlas98.uhcore.models.modelsClass.adminChat;
import static io.github.nichxlas98.uhcore.utils.AdminLevelUtil.*;

public class customChatListener implements Listener {

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Player player = e.getPlayer();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        int playerAdminLevel = AdminLevelUtil.getAdminLevel(player.getUniqueId());
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
                if (RankUtil.isSupporter(player.getUniqueId())) {
                    console.sendMessage(AdminLevelUtil.isSupporter(player, e.getMessage()));
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendMessage(AdminLevelUtil.isSupporter(player, e.getMessage()));
                    }
                    return;
                }

                console.sendMessage(AdminLevelUtil.isCommunity(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(AdminLevelUtil.isCommunity(player, e.getMessage()));
                }
                break;
            case 1:
                console.sendMessage(AdminLevelUtil.isJunior(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(AdminLevelUtil.isJunior(player, e.getMessage()));
                }
                break;
            case 2:
                console.sendMessage(AdminLevelUtil.isAdmin(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(AdminLevelUtil.isAdmin(player, e.getMessage()));
                }
                break;
            case 3:
                console.sendMessage(AdminLevelUtil.isSenior(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(AdminLevelUtil.isSenior(player, e.getMessage()));
                }
                break;
            case 4:
                console.sendMessage(AdminLevelUtil.isManager(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(AdminLevelUtil.isManager(player, e.getMessage()));
                }
                break;
            default:
                //error message
        }
    }

}
