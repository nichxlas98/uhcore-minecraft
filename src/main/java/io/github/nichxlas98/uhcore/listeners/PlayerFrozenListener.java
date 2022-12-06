package io.github.nichxlas98.uhcore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static io.github.nichxlas98.uhcore.models.ModelsClass.playerFrozen;
import static io.github.nichxlas98.uhcore.utils.FrozenUtil.isFrozen;

public class PlayerFrozenListener implements Listener {


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (isFrozen(event.getPlayer().getUniqueId())) {
            playerFrozen.add(event.getPlayer());
        }
    }

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!playerFrozen.contains(player)) return;

        player.teleport(player.getLocation());
        player.sendMessage(ChatColor.RED + "[*] You cannot move while frozen.");
    }

    @EventHandler
    public void playerBreakEvent(BlockBreakEvent event) {
        if (!(event instanceof Player)) return;
        Player player = event.getPlayer();
        if (!playerFrozen.contains(player)) return;

        event.setCancelled(true);
        player.sendMessage(ChatColor.RED + "[*] You cannot break blocks while frozen.");
    }

    @EventHandler
    public void playerPlaceEvent(BlockPlaceEvent event) {
        if (!(event instanceof Player)) return;
        Player player = event.getPlayer();
        if (!playerFrozen.contains(player)) return;

        event.setCancelled(true);
        player.sendMessage(ChatColor.RED + "[*] You cannot place blocks while frozen.");
    }

    @EventHandler
    public void playerDamageEvent(EntityDamageByEntityEvent event) {
        if (!(event instanceof Player)) return;
        if (event.getDamager().getType() != EntityType.PLAYER) return;
        if (event.getEntity().getType() != EntityType.PLAYER) return;

        Player playerAttacker = (Player) event.getDamager();
        Player playerDamaged = (Player) event.getEntity();

        if (!(playerFrozen.contains(playerDamaged) || !(playerFrozen.contains(playerAttacker)))) return;
        event.setCancelled(true);
        playerAttacker.sendMessage(ChatColor.RED + "[*] You cannot do this while frozen.");
    }

    @EventHandler
    public void playerDropItemEvent(PlayerDropItemEvent event) {
        if (!(playerFrozen.contains(event.getPlayer()))) return;
        event.setCancelled(true);
        event.getPlayer().sendMessage(ChatColor.RED + "[*] You cannot drop items while frozen.");
    }
}
