package io.github.nichxlas98.uhcore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import static io.github.nichxlas98.uhcore.models.ModelsClass.GAME_MEETUP;

public class ServerMeetupListener implements Listener {

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (GAME_MEETUP) {
            player.sendMessage(ChatColor.RED + "[*] You cannot do this while UHC Meetup is ongoing.");
            e.setCancelled(true);
        }
    }


}
