package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.UhCore;
import io.github.nichxlas98.uhcore.utils.PlayerManagerUtil;
import io.github.nichxlas98.uhcore.utils.SpawnUtil;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static io.github.nichxlas98.uhcore.models.ModelsClass.*;
import static io.github.nichxlas98.uhcore.utils.ServerUtils.*;


public class PlayerStateListener implements Listener {


    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.setJoinMessage(null);
        if (!player.hasPlayedBefore()) {
            SpawnUtil.spawnTeleport(player);
        }

        if (PlayerManagerUtil.getMessageStatus(player.getUniqueId())) {
            pmsBlocked.add(player);
            player.sendMessage(ChatColor.AQUA + "[*] Your message status is: " + ChatColor.RED + "blocked.");
        }
    }

    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onDeathEvent(PlayerRespawnEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!(isGameEnabled())) {
                    this.cancel();
                    return;
                }

                Player p = event.getPlayer();
                if (isSpectatorMode()) {
                    p.sendTitle("You died!", "");
                    p.setGameMode(GameMode.SPECTATOR);
                    p.setSpectatorTarget(p.getKiller());
                    p.sendMessage(ChatColor.RED + "[*] You died & were sent to spectator. Better luck next time.");
                } else {
                    SpawnUtil.spawnTeleport(p);
                    p.sendTitle("You died!", "");
                    p.sendMessage(ChatColor.RED + "[*] You died & were sent to spawn. Better luck next time.");
                }
            }
        }.runTaskLater(UhCore.getPlugin(), 2);
    }

    @EventHandler
    public void onBow(EntityDamageByEntityEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            if (e.getDamager() instanceof Arrow && e.getEntity() instanceof Player) {
                Arrow arrow = (Arrow) e.getDamager();
                if (!(arrow.getShooter() instanceof Player)) return;


                ((Player) arrow.getShooter()).playSound(((Player) arrow.getShooter()).getLocation(), Sound.NOTE_PLING, 1, 2);
                ((Player) arrow.getShooter()).sendMessage(ChatColor.AQUA + "[*] " + e.getEntity().getName() + " is on " + ChatColor.RED + Math.floor(((Player) e.getEntity()).getHealth()) + " HP!");
            }
        }
    }

    @EventHandler
    public void killEffectsEvent(PlayerDeathEvent event) {
        if (!(isGameEnabled())) return;
        Player killer = event.getEntity().getKiller();
        Player killed = event.getEntity().getPlayer();
        ItemStack skull = new ItemStack(Material.SKULL_ITEM);
        int chance = RANDOM.nextInt(4) % 2 + 1;

        if (isDoubleHeads()) {
            killed.getWorld().dropItemNaturally(killed.getLocation(), new ItemStack(Material.SKULL_ITEM, 2));
        } else {
            killed.getWorld().dropItemNaturally(killed.getLocation(), skull);
        }

        if (isGoldRush()) {
            for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                players.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, chance));
            }
        }

        if (isPearlUhc()) {
            killer.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, chance));
        }

        if (killer != null) {
            killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 160, 1));
            killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 2));
            killer.sendMessage(ChatColor.GOLD + "[*] You've been given " + killed.getDisplayName() + "'s skull.");
            killer.sendMessage(ChatColor.GRAY + "[*] You've been given " + ChatColor.AQUA + "Speed " + ChatColor.GRAY + "& " + ChatColor.LIGHT_PURPLE + "Regeneration " + ChatColor.GRAY + "for your kill on " + ChatColor.RED + killed.getDisplayName() + ".");
        }
    }

    @EventHandler
    public void goldenHeadEvent(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();
        if (!(item.hasItemMeta())) return;
        if (!(item.getItemMeta().getDisplayName().contains("Golden Head"))) return;

        player.removePotionEffect(PotionEffectType.REGENERATION);
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 1));
        player.sendMessage(ChatColor.GRAY + "[*] You've eaten a " + ChatColor.GOLD + "Golden Head.");
    }

    @EventHandler
    public void godAppleEvent(PlayerItemConsumeEvent e) {
        if (e.getItem().getType() != Material.GOLDEN_APPLE) return;
        if (e.getItem().getDurability() != 1) return;

        Player player = e.getPlayer();
        player.sendMessage(ChatColor.RED + "[*] You cannot use this item, sorry!");
        e.setCancelled(true);
    }


    @EventHandler
    public void playerShearEvent(PlayerShearEntityEvent event) {
        if (!(event.getEntity() instanceof Sheep)) return;
        int chance = RANDOM.nextInt(4) % 2 + 1;
        event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), new ItemStack(Material.STRING, chance));
    }
}

