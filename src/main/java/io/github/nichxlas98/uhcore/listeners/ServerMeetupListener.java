package io.github.nichxlas98.uhcore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import static io.github.nichxlas98.uhcore.utils.ServerUtils.isGameMeetup;

public class ServerMeetupListener implements Listener {

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent e) {
        if (!(isGameMeetup())) return;
        Player player = e.getPlayer();
        player.sendMessage(ChatColor.RED + "[*] You cannot do this while UHC Meetup is ongoing.");
        e.setCancelled(true);
    }
}
