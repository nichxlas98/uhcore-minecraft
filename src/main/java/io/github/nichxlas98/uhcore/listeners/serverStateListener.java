package io.github.nichxlas98.uhcore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import static io.github.nichxlas98.uhcore.models.modelsClass.*;

public class serverStateListener implements Listener {

    @EventHandler
    public void serverListEvent(ServerListPingEvent e) {

        if (motd) {
            if (maintenanceMode) {
                e.setMotd(ChatColor.GOLD + "UhCoreMC " + ChatColor.GRAY + "[1.8.9]" + ChatColor.AQUA + " - v1.4.0-R1" + ChatColor.RED + "\nThe server is currently undergoing " + ChatColor.AQUA + "maintenance." + ChatColor.RED);
                e.setMaxPlayers(24);
                return;
            } else {
                e.setMotd(ChatColor.GOLD + "UhCoreMC " + ChatColor.GRAY + "[1.8.9]" + ChatColor.AQUA + " - v1.4.0-R1" + ChatColor.WHITE + "\nMinecraft " + ChatColor.GOLD + "UHC" + ChatColor.WHITE + " at its finest.");
                e.setMaxPlayers(64);
            }

            if (gameEnabled) {
                e.setMotd(ChatColor.GOLD + "UhCoreMC " + ChatColor.GRAY + "[1.8.9]" + ChatColor.AQUA + " - v1.4.0-R1" + ChatColor.GRAY + "\nA game is currently in " + ChatColor.AQUA + "progress!");
            }
        }
    }
}
