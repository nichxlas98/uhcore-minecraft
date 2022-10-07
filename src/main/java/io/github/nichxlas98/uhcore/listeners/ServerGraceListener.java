package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.models.ModelsClass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import static io.github.nichxlas98.uhcore.models.ModelsClass.GAME_ENABLED;

public class ServerGraceListener implements Listener {

    @EventHandler
    public void playerDamageByPlayer(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        if (!GAME_ENABLED) return;
        Player attacker = (Player) e.getDamager();
        if (ModelsClass.GRACE_PERIOD) {
            e.setCancelled(true);
            attacker.sendMessage(ChatColor.RED + "[*] You cannot damage this player while grace period is active!");
        }
    }

    @EventHandler
    public void playerDamageEvent(EntityDamageEvent e) {
        if (!(e instanceof Player)) return;
        if (!GAME_ENABLED) return;
        if (ModelsClass.GRACE_PERIOD) {
            e.setCancelled(true);
        }
    }
}

