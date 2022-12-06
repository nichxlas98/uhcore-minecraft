package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.utils.AdminUtil;
import io.github.nichxlas98.uhcore.utils.PlayerManagerUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static io.github.nichxlas98.uhcore.models.ModelsClass.adminChat;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.*;

public class ChatListener implements Listener {

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Player player = e.getPlayer();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        int playerAdminLevel = AdminUtil.getAdminLevel(player.getUniqueId());
        if (e.getMessage().contains("#")) {
            if (!(playerAdminLevel >= MIN_ADMIN_LEVEL)) {
                adminChat.remove(player);
                return;
            }

            if (!(adminChat.contains(player))) {
                adminChat.add(player);
                return;
            }

            console.sendMessage(ChatColor.AQUA + "[*] " + player.getName() + " says: " + e.getMessage());
            for (Player aChat : adminChat) {
                aChat.sendMessage(ChatColor.AQUA + "[*] " + player.getName() + " says: " + e.getMessage().replace("#", ""));
            }
            return;
        }

        switch (playerAdminLevel) {
            case 0:
                if (PlayerManagerUtil.getSupporter(player.getUniqueId())) {
                    console.sendMessage(AdminUtil.isDonator(player, e.getMessage()));
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendMessage(AdminUtil.isDonator(player, e.getMessage()));
                    }
                    return;
                }
                console.sendMessage(AdminUtil.isCommunity(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(AdminUtil.isCommunity(player, e.getMessage()));
                }
                break;
            case 1:
                console.sendMessage(AdminUtil.isJunior(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(AdminUtil.isJunior(player, e.getMessage()));
                }
                break;
            case 2:
                console.sendMessage(AdminUtil.isAdmin(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(AdminUtil.isAdmin(player, e.getMessage()));
                }
                break;
            case 3:
                console.sendMessage(AdminUtil.isSenior(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(AdminUtil.isSenior(player, e.getMessage()));
                }
                break;
            case 4:
                console.sendMessage(AdminUtil.isManager(player, e.getMessage()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(AdminUtil.isManager(player, e.getMessage()));
                }
                break;
            default:
                //error message
        }
    }

}
