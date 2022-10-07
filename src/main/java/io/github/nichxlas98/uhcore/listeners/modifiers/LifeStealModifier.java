package io.github.nichxlas98.uhcore.listeners.modifiers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

import static io.github.nichxlas98.uhcore.models.ModelsClass.LIFE_STEAL;

public class LifeStealModifier implements Listener {

    HashMap<String, Integer> playerKills = new HashMap<>();

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent e) {
        if (LIFE_STEAL) {
            Player player = e.getEntity().getPlayer();
            Player killer = player.getKiller();
            if (player.getKiller() == null) return;
            if (!playerKills.containsKey(killer.getName())) {
                playerKills.put(killer.getName(), 0);
            }

            int kills = playerKills.get(killer.getName());
            if (!(playerKills.containsKey(killer.getName()))) {
                playerKills.put(killer.getName(), 1);
                kills = 1;
            } else {
                playerKills.put(killer.getName(), kills++);
            }
            killer.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 10000000, kills));
            killer.sendMessage(ChatColor.RED + "[*] You've obtained one extra heart from your kill on " + ChatColor.AQUA + player.getName());
        }
    }
}
