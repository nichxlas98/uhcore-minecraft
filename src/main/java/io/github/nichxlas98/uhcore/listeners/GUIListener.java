package io.github.nichxlas98.uhcore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static io.github.nichxlas98.uhcore.models.modelsClass.*;

public class GUIListener implements Listener {


    @EventHandler
    public void clickEvent(InventoryClickEvent e) {


        if (e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "Settings")){
        Player player = (Player) e.getWhoClicked();

            switch(e.getCurrentItem().getType()){
                case GOLDEN_APPLE:
                    if (doubleHP) {
                        doubleHP = false;
                        player.sendMessage(ChatColor.RED + "[*] Double Health has been disabled.");
                    } else {
                        doubleHP = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Double Health has been enabled.");
                    }
                    break;
                case FEATHER:
                    if (doubleSpeed) {
                        doubleSpeed = false;
                        player.sendMessage(ChatColor.RED + "[*] Double Speed has been disabled.");
                    } else {
                        doubleSpeed = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Double Speed has been enabled.");
                    }
                    break;
                case SUGAR:
                    if (fastsEnabled) {
                        fastsEnabled = false;
                        player.sendMessage(ChatColor.RED + "[*] FastUHC has been disabled.");
                    } else {
                        fastsEnabled = true;
                        player.sendMessage(ChatColor.GREEN + "[*] FastUHC has been enabled.");
                    }
                case SKULL_ITEM:
                    if (doubleHeads) {
                        doubleHeads = false;
                        player.sendMessage(ChatColor.RED + "[*] Double Heads has been disabled.");
                    } else {
                        doubleHeads = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Double Heads has been enabled.");
                    }
                case GOLD_INGOT:
                    if (goldRush) {
                        goldRush = false;
                        player.sendMessage(ChatColor.RED + "[*] Gold Rush has been disabled.");
                    } else {
                        goldRush = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Gold Rush has been enabled.");
                    }
                case GOLDEN_CARROT:
                    if (uhcKits) {
                        uhcKits = false;
                        player.sendMessage(ChatColor.RED + "[*] Kits UHC has been disabled.");
                    } else {
                        uhcKits = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Kits UHC has been enabled.");
                    }

            }

            e.setCancelled(true);
        }

        if (e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "Kits")){
            Player player = (Player) e.getWhoClicked();
            String playerName = player.getName();
            if (workerKit.contains(playerName) || bowKit.contains(playerName) || goldMinerKit.contains(playerName) || fisherManKit.contains(playerName) || enchanterKit.contains(playerName)) {
                player.sendMessage(ChatColor.RED + "[*] Unequipped classes.");
                goldMinerKit.remove(playerName);
                fisherManKit.remove(playerName);
                workerKit.remove(playerName);
                bowKit.remove(playerName);
            }

            switch(e.getCurrentItem().getType()){
                case GOLD_PICKAXE:
                    player.sendMessage(ChatColor.GREEN + "[*] Equipped the Worker Kit.");
                    workerKit.add(player.getName());
                    break;
                case BOW:
                    player.sendMessage(ChatColor.GREEN + "[*] Equipped the Archery Kit.");
                    bowKit.add(player.getName());
                    break;
                case GOLDEN_APPLE:
                    player.sendMessage(ChatColor.GREEN + "[*] Equipped the Gold Miner Kit.");
                    goldMinerKit.add(playerName);
                    break;
                case FISHING_ROD:
                    player.sendMessage(ChatColor.GREEN + "[*] Equipped the Fisherman Kit.");
                    fisherManKit.add(playerName);
                case ENCHANTED_BOOK:
                    player.sendMessage(ChatColor.GREEN + "[*] Equipped the Magician Kit.");
            }

            e.setCancelled(true);

        }

    }

}
