package io.github.nichxlas98.uhcore.listeners.modifiers;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static io.github.nichxlas98.uhcore.utils.ServerUtils.isNoClean;

public class NoCleanModifier implements Listener {
    ArrayList<Player> isSafe = new ArrayList<>();

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent e) {
        if (!isNoClean()) return;
        Player killer = e.getEntity().getKiller();
        killer.sendMessage(ChatColor.RED + "[*] You're invincible for the next 60 seconds.");
        isSafe.add(killer);
        new BukkitRunnable() {
            @Override
            public void run() {
                isSafe.remove(killer);
                killer.sendMessage(ChatColor.RED + "[*] You're no longer invincible.");
            }
        }.runTaskLater(UhCore.getPlugin(), 20 * 60);
    }


    @EventHandler
    public void playerDamageEvent(EntityDamageByEntityEvent e) {
        Player player = (Player) e.getEntity();
        Player attacker = (Player) e.getDamager();

        if (!isNoClean()) return;
        if (!(isSafe.contains(player))) return;

        e.setCancelled(true);
        attacker.sendMessage(ChatColor.RED + "[*] You cannot attack this person for 60 seconds!");
    }
}
