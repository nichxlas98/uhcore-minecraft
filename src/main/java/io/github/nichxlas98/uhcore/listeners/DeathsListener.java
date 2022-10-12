package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.models.ModelsClass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathsListener implements Listener {

    @EventHandler
    public void onDeathEvent(PlayerDeathEvent e) {
        Player killed = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();
        DamageCause playerDeathCause = killed.getLastDamageCause().getCause();

        playerDeathMessages(e, killed, killer, playerDeathCause);
    }

    private static void playerDeathMessages(PlayerDeathEvent e, Player killed, Player killer, DamageCause playerDeathCause) {

        switch (playerDeathCause) {
            case BLOCK_EXPLOSION:
                if (ModelsClass.getChance(50)) {
                    e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " pulled a kamikaze.");
                } else {
                    e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " somehow blew himself up.");
                }
                break;
            case CONTACT:
                e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " pricked himself to death.");
                break;
            case DROWNING:
                if (ModelsClass.getChance(50)) {
                    e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " couldn't swim.");
                } else {
                    e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " ran out of breath & drowned.");
                }
                break;
            case ENTITY_ATTACK:
                if (ModelsClass.getChance(50)) {
                    e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " was out-matched in battle by " + ChatColor.RED + killer.getName() + ".");
                } else {
                    e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " couldn't keep up with " + ChatColor.RED + killer.getName() + ".");
                }
                break;
            case ENTITY_EXPLOSION:
                e.setDeathMessage(ChatColor.RED + killed.getName() + ChatColor.YELLOW + " exploded.");
                break;
            case FALL:
                if (ModelsClass.getChance(50)) {
                    e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " fell and broke his legs.");
                } else {
                    e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " pulled a Luke Bryan.");
                }
                break;
            case FIRE:
                e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " played with fire.");
                break;
            case LAVA:
                e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " tried to swim in lava.");
                break;
            case PROJECTILE:
                if (ModelsClass.getChance(50)) {
                    e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " was beamed by " + ChatColor.RED + killer.getName() + ".");
                } else {
                    e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " was sniped by " + ChatColor.RED + killer.getName() + ".");
                }
                break;
            case FIRE_TICK:
                e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " was brutally burned to death.");
                break;
            default:
                e.setDeathMessage(ChatColor.RED + "[*] " + killed.getName() + ChatColor.YELLOW + " died.");
        }
    }
}
