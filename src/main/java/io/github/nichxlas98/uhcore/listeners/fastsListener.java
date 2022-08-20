package io.github.nichxlas98.uhcore.listeners;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class fastsListener implements Listener {

    public static boolean fastsEnabled = false;

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
