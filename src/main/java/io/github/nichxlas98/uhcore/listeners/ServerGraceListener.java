package io.github.nichxlas98.uhcore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import static io.github.nichxlas98.uhcore.utils.ServerUtils.isGameEnabled;
import static io.github.nichxlas98.uhcore.utils.ServerUtils.isGracePeriod;

public class ServerGraceListener implements Listener {

    @EventHandler
    public void playerDamageByPlayer(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        if (!isGameEnabled()) return;
        if (!(isGracePeriod())) return;

        Player attacker = (Player) e.getDamager();
        attacker.sendMessage(ChatColor.RED + "[*] You cannot damage this player while grace period is active!");
        e.setCancelled(true);
    }

    @EventHandler
    public void playerDamageEvent(EntityDamageEvent e) {
        if (!(e instanceof Player)) return;
        if (!isGameEnabled()) return;
        if (!(isGracePeriod())) return;
        e.setCancelled(true);
    }
}

