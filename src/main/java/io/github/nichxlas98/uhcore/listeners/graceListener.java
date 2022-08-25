package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.models.modelsClass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import static io.github.nichxlas98.uhcore.models.modelsClass.gameEnabled;

public class graceListener implements Listener {

    @EventHandler
    public void playerDamagebyPlayer(EntityDamageByEntityEvent e) {
        if (e instanceof Player) {
            Player damager = (Player) e.getDamager();
            if (gameEnabled) {
                if (modelsClass.gracePeriod) {
                    e.setCancelled(true);
                    damager.sendMessage(ChatColor.RED + "[*] You cannot damage this player while grace period is active!");
                }
            }
        }
    }

    @EventHandler
    public void playerDamageEvent(EntityDamageEvent e) {
        if (e instanceof Player) {
            if (gameEnabled)
                if (modelsClass.gracePeriod) {
                    e.setCancelled(true);
                }
        }
    }
}

