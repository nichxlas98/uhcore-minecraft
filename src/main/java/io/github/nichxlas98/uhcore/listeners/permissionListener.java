package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.utils.AdminLevelUtil;
import io.github.nichxlas98.uhcore.utils.RankUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static io.github.nichxlas98.uhcore.models.modelsClass.adminChat;
import static io.github.nichxlas98.uhcore.utils.AdminLevelUtil.MIN_ADMIN_LEVEL;
import static org.bukkit.event.EventPriority.HIGHEST;

public class permissionListener implements Listener {

    @EventHandler(priority = HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        int playerAdminLevel = AdminLevelUtil.getAdminLevel(player.getUniqueId());
        String playerName = player.getName();

        if (!(AdminLevelUtil.hasAdminLevel(player.getUniqueId()))) {
            player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a Regular player.");
            AdminLevelUtil.setAdminLevel(player.getUniqueId(), 0);
            return;
        }

        if (adminChat.contains(player)) {
            if (playerAdminLevel < MIN_ADMIN_LEVEL) {
                adminChat.remove(player);
            }
        } else {
            if (playerAdminLevel >= MIN_ADMIN_LEVEL) {
                adminChat.add(player);
            }
        }

        if (playerAdminLevel >= MIN_ADMIN_LEVEL) {
            if (playerAdminLevel > 0) {
                player.sendMessage(ChatColor.YELLOW + "[*] Logged in with an admin level of " + ChatColor.GOLD + playerAdminLevel + ".");
            }

            switch (playerAdminLevel) {
                case 1:
                    player.setPlayerListName(ChatColor.GRAY + " (" + ChatColor.GOLD + "Junior" + ChatColor.GRAY + ") " + playerName);
                    player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.GOLD + "Junior Admin.");
                    break;
                case 2:
                    player.setPlayerListName(ChatColor.GRAY + " (" + ChatColor.AQUA + "Administrator" + ChatColor.GRAY + ") " + playerName);
                    player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.GOLD + "Administrator.");
                    break;
                case 3:
                    player.setPlayerListName(ChatColor.GRAY + " (" + ChatColor.DARK_AQUA + "Senior" + ChatColor.GRAY + ") " + playerName);
                    player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.GOLD + "Senior Admin.");
                    break;
                case 4:
                    player.setPlayerListName(ChatColor.GRAY + " (" + ChatColor.RED + "Manager" + ChatColor.GRAY + ") " + playerName);
                    player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.GOLD + "Manager.");
                    break;
                default:
                    if (RankUtil.isSupporter(player.getUniqueId())) {
                        player.setPlayerListName(ChatColor.GRAY + " (" + ChatColor.LIGHT_PURPLE + "Supporter" + ChatColor.GRAY + ") " + playerName);
                        player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.WHITE + "Regular player.");
                        return;
                    }
                    player.setPlayerListName(ChatColor.GRAY + " (" + ChatColor.YELLOW + "Community" + ChatColor.GRAY + ") " + playerName);
                    player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.WHITE + "Regular player.");
            }
        }
    }
}