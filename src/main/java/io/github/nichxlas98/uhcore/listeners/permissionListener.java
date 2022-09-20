package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.utils.adminLevelUtil;
import io.github.nichxlas98.uhcore.utils.playerManagerUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

import static io.github.nichxlas98.uhcore.models.modelsClass.adminChat;
import static io.github.nichxlas98.uhcore.utils.adminLevelUtil.MIN_ADMIN_LEVEL;
import static org.bukkit.event.EventPriority.HIGHEST;

public class permissionListener implements Listener {

    private static void playerRank(Player player, ChatColor color, String rankName, String playerName) {
        player.setPlayerListName(ChatColor.GRAY + " (" + color + rankName + ChatColor.GRAY + ") " + playerName);
        player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.GOLD + rankName + ".");
    }

    @EventHandler(priority = HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();
        UUID playerUUID = player.getUniqueId();

        if (!(adminLevelUtil.hasAdminLevel(playerUUID))) {
            player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a Regular player.");
            adminLevelUtil.setAdminLevel(player.getUniqueId(), 0);
            return;
        }

        int playerAdminLevel = adminLevelUtil.getAdminLevel(playerUUID);
        if (adminChat.contains(player)) {
            if (playerAdminLevel < MIN_ADMIN_LEVEL) {
                adminChat.remove(player);
            }
        } else if (playerAdminLevel >= MIN_ADMIN_LEVEL) {
            adminChat.add(player);
        }

        if (playerAdminLevel >= MIN_ADMIN_LEVEL) {
            player.sendMessage(ChatColor.YELLOW + "[*] Logged in with an admin level of " + ChatColor.GOLD + playerAdminLevel + ".");
        } else {
            return;
        }

        switch (playerAdminLevel) {
            case 0:
                if (playerManagerUtil.isSupporter(playerUUID)) {
                    player.setPlayerListName(ChatColor.GRAY + " (" + ChatColor.LIGHT_PURPLE + "S" + ChatColor.GRAY + ") " + playerName);
                    player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.WHITE + "Regular player.");
                    break;
                }
                player.setPlayerListName(ChatColor.GRAY + " (" + ChatColor.YELLOW + "C" + ChatColor.GRAY + ") " + playerName);
                player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.WHITE + "Regular player.");
                break;
            case 1:
                playerRank(player, ChatColor.GOLD, "JA", player.getName());
                break;
            case 2:
                playerRank(player, ChatColor.AQUA, "A", player.getName());
                break;
            case 3:
                playerRank(player, ChatColor.DARK_AQUA, "SA", player.getName());
                break;
            case 4:
                playerRank(player, ChatColor.RED, "MA", player.getName());
                break;
            default:
                //
        }
    }
}