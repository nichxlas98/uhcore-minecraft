package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.models.modelsClass;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Random;

import static io.github.nichxlas98.uhcore.models.modelsClass.fastsEnabled;


public class fastsListener implements Listener {


    @EventHandler
    public void playerKillEntity(EntityDeathEvent e) {
        Random rand = new Random();
        int chance = rand.nextInt(4) % 2 + 1;
        if (fastsEnabled) {
            if (e.getEntity().getKiller() != null) {
                if (e.getEntity() instanceof Sheep || e.getEntity() instanceof Pig) {
                    e.getDrops().clear();
                    e.getDrops().add(new ItemStack(Material.COOKED_BEEF, chance));
                }

                if (e.getEntity() instanceof Cow) {
                    e.getDrops().clear();
                    e.getDrops().add(new ItemStack(Material.COOKED_BEEF, chance));
                    e.getDrops().add(new ItemStack(Material.LEATHER, chance));
                }

                if (e.getEntity() instanceof Chicken) {
                    e.getDrops().clear();
                    e.getDrops().add(new ItemStack(Material.ARROW, chance));
                }

            }
        }

    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if (e.getBlock().getType() == Material.LEAVES || e.getBlock().getType() == Material.LEAVES_2) {
            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);

            if (modelsClass.getChance(25)) {
                e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.APPLE));
            } else {
                e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.LEAVES));
            }
        }

        if(fastsEnabled) {
            Block b = e.getBlock();
            Player p = e.getPlayer();
            Random rand = new Random();
            int chance = rand.nextInt(4) % 2 + 1;
            if(b.getType() == Material.IRON_ORE) {
                Map<Enchantment, Integer> enchantmentMap = p.getItemInHand().getEnchantments();
                if (enchantmentMap.containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
                    e.setCancelled(true);
                    b.setType(Material.AIR);
                    b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.IRON_INGOT, chance));
                    return;
                }

                e.setCancelled(true);
                b.setType(Material.AIR);
                b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.IRON_INGOT));
            }

            if(b.getType() == Material.GOLD_ORE){
                Map<Enchantment, Integer> enchantmentMap = p.getItemInHand().getEnchantments();
                if (enchantmentMap.containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
                    e.setCancelled(true);
                    b.setType(Material.AIR);
                    b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.GOLD_INGOT, chance));
                    return;
                }
                e.setCancelled(true);
                b.setType(Material.AIR);
                b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.GOLD_INGOT));
            }
        }
    }

    @EventHandler
    public void fastCrafts(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();
        ItemStack wPickaxe = new ItemStack(Material.WOOD_PICKAXE);
        ItemStack wAxe = new ItemStack(Material.WOOD_AXE);
        ItemStack wShovel = new ItemStack(Material.WOOD_SPADE);

        ItemStack sPickaxe = new ItemStack(Material.STONE_PICKAXE);
        ItemStack sAxe = new ItemStack(Material.STONE_AXE);
        ItemStack sShovel = new ItemStack(Material.STONE_SPADE);

        ItemStack iPickaxe = new ItemStack(Material.IRON_PICKAXE);
        ItemStack iAxe = new ItemStack(Material.IRON_AXE);
        ItemStack iShovel = new ItemStack(Material.IRON_SPADE);

        if (fastsEnabled) {

            if(player.getInventory().getItemInHand().equals(wPickaxe)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            } if(player.getInventory().getItemInHand().equals(wAxe)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            } if(player.getInventory().getItemInHand().equals(wShovel)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            }


            if(player.getInventory().getItemInHand().equals(sPickaxe)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            } if(player.getInventory().getItemInHand().equals(sAxe)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            } if(player.getInventory().getItemInHand().equals(sShovel)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            }


            if(player.getInventory().getItemInHand().equals(iPickaxe)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            } if(player.getInventory().getItemInHand().equals(iAxe)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            } if(player.getInventory().getItemInHand().equals(iShovel)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            }



        }


    }
}
