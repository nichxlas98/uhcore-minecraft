package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.models.ScoreHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class scoreboardListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        ScoreHelper helper = ScoreHelper.createScore(player);
        helper.setTitle("    &6UhCore&cMC    ");
        helper.setSlot(5, "&7&m--------------");
        helper.setSlot(4, "&c» &fState: &6Waiting");
        helper.setSlot(3, "&c» &fGrace: &bNone");
        helper.setSlot(2, "");
        helper.setSlot(1, "&7&m--------------");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (ScoreHelper.hasScore(player)) {
            ScoreHelper.removeScore(player);
        }
    }

}
