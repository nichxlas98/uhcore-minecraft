package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.utils.AdminlevelUtil;
import io.github.nichxlas98.uhcore.utils.PlayerManagerUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static io.github.nichxlas98.uhcore.models.ModelsClass.adminChat;
import static io.github.nichxlas98.uhcore.utils.AdminlevelUtil.*;

public class ChatListener implements Listener {

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Player player = e.getPlayer();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        int playerAdminLevel = AdminlevelUtil.getAdminLevel(player.getUniqueId());
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
                if (PlayerManagerUtil.isSupporter(player.getUniqueId())) {
                    console.sendMessage(AdminlevelUtil.isSupporter(player, e.getMessage()));
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendMessage(AdminlevelUtil.isSupporter(player, e.getMessage()));
                    }
                    return;
                }
                console.sendMessage(AdminlevelUtil.isCommunity(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(AdminlevelUtil.isCommunity(player, e.getMessage()));
                }
                break;
            case 1:
                console.sendMessage(AdminlevelUtil.isJunior(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(AdminlevelUtil.isJunior(player, e.getMessage()));
                }
                break;
            case 2:
                console.sendMessage(AdminlevelUtil.isAdmin(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(AdminlevelUtil.isAdmin(player, e.getMessage()));
                }
                break;
            case 3:
                console.sendMessage(AdminlevelUtil.isSenior(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(AdminlevelUtil.isSenior(player, e.getMessage()));
                }
                break;
            case 4:
                console.sendMessage(AdminlevelUtil.isManager(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(AdminlevelUtil.isManager(player, e.getMessage()));
                }
                break;
            default:
                //error message
        }
    }

}
