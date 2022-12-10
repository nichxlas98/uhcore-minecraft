package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.utils.ServerManagerUtil;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;

import static io.github.nichxlas98.uhcore.utils.ServerManagerUtil.scoreboardName;
import static io.github.nichxlas98.uhcore.utils.ServerUtils.isGameEnabled;
import static io.github.nichxlas98.uhcore.utils.ServerUtils.isMaintenanceMode;

public class ServerStateListener implements Listener {

    @EventHandler
    public void serverListEvent(ServerListPingEvent e) throws IOException {
        if (!(ServerManagerUtil.checkMotd())) return;

        GitHub github = new GitHubBuilder().withOAuthToken("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").build();
        String[] version = github.getRepository("nichxlas98/uhcore-minecraft").getLatestRelease().toString().replace("(", "@").replace(")", "@").split("@");
        //System.out.println(version[3]); - [3] = repo version tag

        if (isMaintenanceMode()) {
            e.setMotd(ChatColor.GOLD + scoreboardName + ChatColor.GRAY + " [1.8.9]" + ChatColor.AQUA + " - " + version[3] + ChatColor.RED + "\nThe server is currently undergoing " + ChatColor.AQUA + "maintenance." + ChatColor.RED);
            e.setMaxPlayers(24);
            return;
        } else {
            e.setMotd(ChatColor.GOLD + scoreboardName + ChatColor.GRAY + " [1.8.9]" + ChatColor.AQUA + " - " + version[3] + ChatColor.WHITE + "\nMinecraft " + ChatColor.GOLD + "UHC" + ChatColor.WHITE + " at its finest.");
            e.setMaxPlayers(64);
        }

        if (!(isGameEnabled())) return;
        e.setMotd(ChatColor.GOLD + scoreboardName + ChatColor.GRAY + " [1.8.9]" + ChatColor.AQUA + " - " + version[3] + ChatColor.GRAY + "\nA game is currently in " + ChatColor.AQUA + "progress!");
    }
}
