package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.utils.AdminLevelUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.event.EventPriority.HIGHEST;

public class permissionListener implements Listener {


    //IDEA: Player commands depend on AdminLevel?
    @EventHandler(priority = HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (!(AdminLevelUtil.hasAdminLevel(player.getUniqueId()))) {
            player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a Regular player.");
            AdminLevelUtil.setAdminLevel(player.getUniqueId(), 0);
            return;
        }


        if (AdminLevelUtil.getAdminLevel(player.getUniqueId()) >= 0) {
            int adminLevel = AdminLevelUtil.getAdminLevel(player.getUniqueId());
            if (adminLevel > 0) {
                player.sendMessage(ChatColor.YELLOW + "[*] Logged in with an admin level of " + ChatColor.GOLD + adminLevel + ".");
            }

            switch (adminLevel) {
                case 1:
                    player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.GOLD + "Junior Admin.");
                    break;
                case 2:
                    player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.GOLD + "Administrator.");
                    break;
                case 3:
                    player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.GOLD + "Senior Admin.");
                    break;
                case 4:
                    player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.GOLD + "Manager.");
                    break;
                default:
                    player.sendMessage(ChatColor.YELLOW + "[*] You're signed in as a " + ChatColor.WHITE + "Regular player.");
            }

/*
            if (adminLevel == 1) {
                player.sendMessage(ChatColor.WHITE + "[*] You're signed in as a " + ChatColor.GOLD + "Junior Admin.");
                return;
            } if (adminLevel == 2) {
                player.sendMessage(ChatColor.WHITE + "[*] You're signed in as a " + ChatColor.GOLD + "Administrator.");
                return;
            } if (adminLevel == 3) {
                player.sendMessage(ChatColor.WHITE + "[*] You're signed in as a " + ChatColor.GOLD + "Senior Admin.");
                return;
            } if (adminLevel == 4) {
                player.sendMessage(ChatColor.WHITE + "[*] You're signed in as a " + ChatColor.GOLD + "Manager.");
            }

 */
        }
    }

}