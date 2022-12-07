package io.github.nichxlas98.uhcore.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import static io.github.nichxlas98.uhcore.utils.DeathUtil.saveLastDeath;
import static io.github.nichxlas98.uhcore.utils.InventoryUtil.saveInventory;
import static io.github.nichxlas98.uhcore.utils.InventoryUtil.saveInventoryEC;

public class PlayerLogListener implements Listener {


    @EventHandler
    public void playerDamageEvent(EntityDamageEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) return;
        Player player = (Player) event.getEntity();
        if (!(player.getHealth() < 1)) return;

        saveInventory(player);
        saveInventoryEC(player);
    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event) {
        saveLastDeath(event.getEntity().getPlayer());
    }
}
