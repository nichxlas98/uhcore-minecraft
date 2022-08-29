package io.github.nichxlas98.uhcore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import static io.github.nichxlas98.uhcore.models.modelsClass.gameEnabled;
import static io.github.nichxlas98.uhcore.models.modelsClass.maintenanceMode;

public class maintenanceListener implements Listener {

    @EventHandler
    public void serverListEvent(ServerListPingEvent e) {

        if (maintenanceMode) {
            e.setMotd(ChatColor.RED + "[UhCore98] The server is currently undergoing maintenance!");
            e.setMaxPlayers(20);
            return;
        } else {
            e.setMotd("[UhCore98] - Beta v1.0.0-R1");
            e.setMaxPlayers(64);
        }

        if (gameEnabled) {
            e.setMotd(ChatColor.RED + "[UhCore98] A game is currently in progress!");
        }

    }


}
