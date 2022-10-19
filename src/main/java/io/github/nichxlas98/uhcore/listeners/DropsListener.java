package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.models.ModelsClass;
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

import static io.github.nichxlas98.uhcore.models.ModelsClass.RANDOM;
import static io.github.nichxlas98.uhcore.utils.ServerUtils.isCutClean;
import static io.github.nichxlas98.uhcore.utils.ServerUtils.isGameEnabled;


public class DropsListener implements Listener {
    @EventHandler
    public void playerKillEntity(EntityDeathEvent e) {
        if (!(isCutClean())) return;
        if (e.getEntity().getKiller() == null) return;
        int chance = RANDOM.nextInt(4) % 2 + 1;

        switch (e.getEntity().getType()) {

            case SHEEP:
            case PIG:
                e.getDrops().clear();
                e.getDrops().add(new ItemStack(Material.COOKED_BEEF, chance));
                break;
            case COW:
                e.getDrops().clear();
                e.getDrops().add(new ItemStack(Material.COOKED_BEEF, chance));
                e.getDrops().add(new ItemStack(Material.LEATHER, chance));
                break;
            case CHICKEN:
                e.getDrops().clear();
                e.getDrops().add(new ItemStack(Material.ARROW, chance));
                break;
            default:
                //
        }
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!isGameEnabled()) return;
        if (e.getBlock().getType() == Material.LEAVES || e.getBlock().getType() == Material.LEAVES_2) {
            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);

            if (!(ModelsClass.getChance(85))) {
                e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.LEAVES));
                return;
            }
            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.APPLE));
        }

        if (!(isCutClean())) return;
        Block b = e.getBlock();
        Player p = e.getPlayer();
        Map<Enchantment, Integer> enchantmentMap = p.getItemInHand().getEnchantments();
        int chance = RANDOM.nextInt(4) % 2 + 1;

        switch (b.getType()) {

            case IRON_ORE:
                if (enchantmentMap.containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
                    e.setCancelled(true);
                    b.setType(Material.AIR);
                    b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.IRON_INGOT, chance));
                    return;
                }

                e.setCancelled(true);
                b.setType(Material.AIR);
                b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.IRON_INGOT));
                break;
            case GOLD_ORE:
                if (enchantmentMap.containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
                    e.setCancelled(true);
                    b.setType(Material.AIR);
                    b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.GOLD_INGOT, chance));
                    return;
                }
                e.setCancelled(true);
                b.setType(Material.AIR);
                b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.GOLD_INGOT));
                break;
            default:
                //
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

        if (isCutClean()) {
            if (player.getInventory().getItemInHand().equals(wPickaxe)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            }
            if (player.getInventory().getItemInHand().equals(wAxe)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            }
            if (player.getInventory().getItemInHand().equals(wShovel)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            }
            if (player.getInventory().getItemInHand().equals(sPickaxe)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            }
            if (player.getInventory().getItemInHand().equals(sAxe)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            }
            if (player.getInventory().getItemInHand().equals(sShovel)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            }
            if (player.getInventory().getItemInHand().equals(iPickaxe)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            }
            if (player.getInventory().getItemInHand().equals(iAxe)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            }
            if (player.getInventory().getItemInHand().equals(iShovel)) {
                player.getInventory().getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 3);
            }
        }
    }
}
