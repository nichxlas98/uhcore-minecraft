package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.models.modelsClass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

public class customDeathsListener implements Listener {


    @EventHandler
    public void onDeathEvent(PlayerDeathEvent e) {
        Player killed = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();
        DamageCause playerDeathCause = killed.getLastDamageCause().getCause();

        if (playerDeathCause == DamageCause.BLOCK_EXPLOSION) {

            if (modelsClass.getChance(50)) {
                e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " pulled a kamikaze.");
            } else {
                e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " somehow blew himself up.");
            }
        } else if(playerDeathCause == DamageCause.CONTACT) {
            e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " pricked himself to death.");
        } else if(playerDeathCause == DamageCause.DROWNING) {
            if (modelsClass.getChance(50)) {
                e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " couldn't swim.");
            } else {
                e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " ran out of breath & drowned.");
            }
        } else if(playerDeathCause == DamageCause.ENTITY_ATTACK && e.getEntity().getKiller() != null) {
            if (modelsClass.getChance(50)) {
                e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " was out-matched in battle by " + ChatColor.RED + killer.getName() + ".");
            } else {
                e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " couldn't keep up with " + ChatColor.RED + killer.getName() + ".");
            }
        } else if (playerDeathCause == DamageCause.ENTITY_EXPLOSION) {
            e.setDeathMessage(ChatColor.RED + killed.getName() + ChatColor.YELLOW + " exploded.");
        } else if (playerDeathCause == DamageCause.FALL) {
            if (modelsClass.getChance(50)) {
                e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " fell and broke his legs.");
            } else {
                e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " pulled a Luke Bryan.");
            }
        } else if (playerDeathCause == DamageCause.FIRE) {
            e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " played with fire.");
        }  else if (playerDeathCause == DamageCause.LAVA) {
            e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " tried to swim in lava.");
        } else if (playerDeathCause == DamageCause.PROJECTILE && e.getEntity().getKiller() != null) {
            if (modelsClass.getChance(50)) {
                e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " was beamed by " + ChatColor.RED + killer.getName() + ".");
            } else {
                e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " was sniped by " + ChatColor.RED + killer.getName() + ".");
            }
        } else if (playerDeathCause == DamageCause.FIRE_TICK) {
            e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " was brutally burned to death.");
        } else {
            e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " died.");
        }
    }
}
