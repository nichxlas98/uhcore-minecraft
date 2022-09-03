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
        } else if (playerAdminLevel >= MIN_ADMIN_LEVEL) {
            adminChat.add(player);
        }

        if (!(playerAdminLevel >= MIN_ADMIN_LEVEL)) return;

        if (playerAdminLevel > 0) {
            player.sendMessage(ChatColor.YELLOW + "[*] Logged in with an admin level of " + ChatColor.GOLD + playerAdminLevel + ".");
        }

        switch (playerAdminLevel) {
            case 1:
                playerRank(player, ChatColor.GOLD, "Junior", player.getName());
                break;
            case 2:
                playerRank(player, ChatColor.AQUA, "Administrator", player.getName());
                break;
            case 3:
                playerRank(player, ChatColor.DARK_AQUA, "Senior", player.getName());
                break;
            case 4:
                playerRank(player, ChatColor.RED, "Manager", player.getName());
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

    private static void playerRank(Player player, ChatColor color, String rankName, String playerName) {
        player.setPlayerListName(ChatColor.GRAY + " (" + color + rankName + ChatColor.GRAY + ") " + playerName);
        player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.GOLD + rankName + " Admin.");
    }
}