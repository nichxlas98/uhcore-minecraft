package io.github.nichxlas98.uhcore.listeners.modifiers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static io.github.nichxlas98.uhcore.utils.ServerUtils.isNoSwords;

public class NoSwordsModifier implements Listener {

    @EventHandler
    public void heldItemEvent(PlayerItemHeldEvent e) {
        if (!isNoSwords()) return;
        ItemStack woodSword = new ItemStack(Material.WOOD_SWORD);
        ItemStack stoneSword = new ItemStack(Material.STONE_SWORD);
        ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
        ItemStack goldSword = new ItemStack(Material.GOLD_SWORD);
        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        Player player = e.getPlayer();

        if (player.getInventory().getItemInHand().getType().toString().endsWith("SWORD")) {
            player.getInventory().remove(woodSword);
            player.getInventory().remove(stoneSword);
            player.getInventory().remove(ironSword);
            player.getInventory().remove(goldSword);
            player.getInventory().remove(diamondSword);
            player.sendMessage(ChatColor.RED + "[*] You cannot carry a sword while the No Swords modifier is enabled.");
        }
    }

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e) {
        if (!isNoSwords()) return;
        ItemStack woodSword = new ItemStack(Material.WOOD_SWORD);
        ItemStack stoneSword = new ItemStack(Material.STONE_SWORD);
        ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
        ItemStack goldSword = new ItemStack(Material.GOLD_SWORD);
        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        Player player = (Player) e.getWhoClicked();
        Inventory playerInv = player.getInventory();

        if (playerInv.getType().toString().endsWith("SWORD")) {
            player.getInventory().remove(woodSword);
            player.getInventory().remove(stoneSword);
            player.getInventory().remove(ironSword);
            player.getInventory().remove(goldSword);
            player.getInventory().remove(diamondSword);
            player.sendMessage(ChatColor.RED + "[*] You cannot carry a sword while the No Swords modifier is enabled.");
        }
    }
}
