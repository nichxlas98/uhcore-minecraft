package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

import static io.github.nichxlas98.uhcore.commands.startGameCommand.gameEnabled;


public class playerListener implements Listener {


    private final UhCore plugin;

    public playerListener(UhCore plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (!player.hasPlayedBefore()) {
            if(plugin.getConfig().getString("spawn.world") != null) {
                World w = Bukkit.getServer().getWorld(plugin.getConfig().getString("spawn.world"));
                double x = Double.parseDouble(plugin.getConfig().getString("spawn.x"));
                double y = Double.parseDouble(plugin.getConfig().getString("spawn.y"));
                double z = Double.parseDouble(plugin.getConfig().getString("spawn.z"));
                float yaw = Float.parseFloat(plugin.getConfig().getString("spawn.yaw"));
                float pitch = Float.parseFloat(plugin.getConfig().getString("spawn.pitch"));

                player.teleport(new Location(w, x, y, z, yaw, pitch));
            }
        }
    }

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onDeathEvent(PlayerRespawnEvent event){
        new BukkitRunnable() {
            @Override
            public void run() {
                if (gameEnabled) {
                    Player p = event.getPlayer();

                    p.sendTitle("You died!", "");
                    p.setGameMode(GameMode.SPECTATOR);

                    p.sendMessage(ChatColor.RED + "[*] You died & were sent to spectator. Better luck next time.");

                }
            }
        }.runTaskLater(plugin, 10);
    }

    @EventHandler
    public void onBow(EntityDamageByEntityEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            if (e.getDamager() instanceof Arrow && e.getEntity() instanceof Player) {
                Arrow arrow  = (Arrow) e.getDamager();
                if (!(arrow.getShooter() instanceof Player)) return;

                ((Player) arrow.getShooter()).playSound(((Player) arrow.getShooter()).getLocation(), Sound.NOTE_PLING, 1, 2);
                ((Player) arrow.getShooter()).sendMessage(ChatColor.AQUA + "[*] " + e.getEntity().getName() + " is on " + ChatColor.RED + ((Player) e.getEntity()).getHealth() + " HP!");
            }
        }
    }

    @EventHandler
    public void killEffectsEvent(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        Player killed = event.getEntity().getPlayer();
        ItemStack skull = new ItemStack(Material.SKULL_ITEM);
        if (killer != null) {
            killer.getInventory().addItem(skull);
            killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 160, 1));
            killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 2));
            killer.sendMessage(ChatColor.GOLD + "[*] You've been given " + killed.getDisplayName() + "'s skull.");
            killer.sendMessage(ChatColor.GRAY + "[*] You've been given " + ChatColor.AQUA + "Speed " + ChatColor.GRAY + "& " + ChatColor.LIGHT_PURPLE + "Regeneration " + ChatColor.GRAY + "for your kill on " + ChatColor.RED + killed.getDisplayName() + ".");
        } else {
            killed.getInventory().addItem(skull);
        }
    }

    @EventHandler
    public void goldenHeadEvent(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();

        //TODO: Fix this doing nothing!
        if (item.hasItemMeta()) {
            if (item.getItemMeta().getDisplayName().contains("Golden Head")) {

                player.removePotionEffect(PotionEffectType.REGENERATION);
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 2));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 1));
                player.sendMessage(ChatColor.GRAY + "[*] You've eaten a " + ChatColor.GOLD + "Golden Head.");
            }
        }
    }

    @EventHandler
    public void godAppleEvent(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();

        if (e.getItem().getType() == Material.GOLDEN_APPLE) {
            if (e.getItem().getDurability() == 1) {
                e.setCancelled(true);
                player.sendMessage(ChatColor.RED + "[*] You cannot use this item, sorry!");
            }
        }
    }


    @EventHandler
    private void onBlockBreak(BlockBreakEvent e){

        if(fastsListener.fastsEnabled) {
            Block b = e.getBlock();
            Player p = e.getPlayer();
            if(b.getType() == Material.IRON_ORE) {
                //TODO: TEST CHECK - REMOVE IF BROKEN!
                Map<Enchantment, Integer> enchantmentMap = p.getItemInHand().getEnchantments();
                if (enchantmentMap.containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
                    e.setCancelled(true);
                    b.setType(Material.AIR);
                    b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.IRON_INGOT, 2));
                    return;
                } //TODO: END OF TEST CHECK

                e.setCancelled(true);
                b.setType(Material.AIR);
                b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.IRON_INGOT));
            }

            if(b.getType() == Material.GOLD_ORE){
                e.setCancelled(true);
                b.setType(Material.AIR);
                b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.GOLD_INGOT));
            }
        }
    }




}

