package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.utils.AdminUtil;
import io.github.nichxlas98.uhcore.utils.PlayerManagerUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

import static io.github.nichxlas98.uhcore.models.ModelsClass.adminChat;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.MIN_ADMIN_LEVEL;
import static org.bukkit.event.EventPriority.HIGHEST;

public class PermissionListener implements Listener {

    private static void playerRank(Player player, ChatColor color, String rankName, String playerName) {
        player.setPlayerListName(ChatColor.GRAY + " (" + color + rankName + ChatColor.GRAY + ") " + playerName);
        player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.GOLD + rankName + ".");
    }

    @EventHandler(priority = HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();
        UUID playerUUID = player.getUniqueId();

        if (!(AdminUtil.hasAdminLevel(playerUUID))) {
            player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a Regular player.");
            AdminUtil.setAdminLevel(playerUUID, 0);
            AdminUtil.setStaffMode(playerUUID, player, false);
            return;
        }

        int playerAdminLevel = AdminUtil.getAdminLevel(playerUUID);
        if (!(adminChat.contains(player)) && playerAdminLevel >= MIN_ADMIN_LEVEL) adminChat.add(player);
        if (playerAdminLevel < MIN_ADMIN_LEVEL) adminChat.remove(player);

        if (playerAdminLevel >= MIN_ADMIN_LEVEL) {
            player.sendMessage(ChatColor.YELLOW + "[*] Logged in with an admin level of " + ChatColor.GOLD + playerAdminLevel + ".");
        }

        switch (playerAdminLevel) {

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
                if (PlayerManagerUtil.getSupporter(playerUUID)) {
                    player.setPlayerListName(ChatColor.GRAY + " (" + ChatColor.LIGHT_PURPLE + "S" + ChatColor.GRAY + ") " + playerName);
                    player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.WHITE + "Regular player.");
                    return;
                }
                player.setPlayerListName(ChatColor.GRAY + " (" + ChatColor.YELLOW + "C" + ChatColor.GRAY + ") " + playerName);
                player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.WHITE + "Regular player.");
        }
    }
}