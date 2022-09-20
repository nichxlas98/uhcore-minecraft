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
        if (e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "Modifiers")) {
            Player player = (Player) e.getWhoClicked();

            switch (e.getCurrentItem().getType()) {
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
                    if (cutCleanEnabled) {
                        cutCleanEnabled = false;
                        player.sendMessage(ChatColor.RED + "[*] CutClean has been disabled.");
                    } else {
                        cutCleanEnabled = true;
                        player.sendMessage(ChatColor.GREEN + "[*] CutClean has been enabled.");
                    }
                    break;
                case SKULL_ITEM:
                    if (doubleHeads) {
                        doubleHeads = false;
                        player.sendMessage(ChatColor.RED + "[*] Double Heads has been disabled.");
                    } else {
                        doubleHeads = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Double Heads has been enabled.");
                    }
                    break;
                case GOLD_NUGGET:
                    if (goldRush) {
                        goldRush = false;
                        player.sendMessage(ChatColor.RED + "[*] Gold Rush has been disabled.");

                    } else {
                        goldRush = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Gold Rush has been enabled.");
                    }
                    break;
                case GOLDEN_CARROT:
                    if (uhcKits) {
                        uhcKits = false;
                        player.sendMessage(ChatColor.RED + "[*] Kits UHC has been disabled.");
                    } else {
                        uhcKits = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Kits UHC has been enabled.");
                    }
                    break;
                case ENDER_PEARL:
                    if (pearlUHC) {
                        pearlUHC = false;
                        player.sendMessage(ChatColor.RED + "[*] Pearl UHC has been disabled.");
                    } else {
                        pearlUHC = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Pearl UHC has been enabled.");
                    }
                    break;
                case IRON_SWORD:
                    if (noSwords) {
                        noSwords = false;
                        player.sendMessage(ChatColor.RED + "[*] No Swords has been disabled.");
                    } else {
                        noSwords = true;
                        player.sendMessage(ChatColor.GREEN + "[*] No Swords has been enabled.");
                    }
                    break;
                case EYE_OF_ENDER:
                    if (lifeStealMode) {
                        lifeStealMode = false;
                        player.sendMessage(ChatColor.RED + "[*] LifeSteal has been disabled.");
                    } else {
                        lifeStealMode = true;
                        player.sendMessage(ChatColor.GREEN + "[*] LifeSteal has been enabled.");
                    }
                    break;
                case MAGMA_CREAM:
                    if (spectatorMode) {
                        spectatorMode = false;
                        player.sendMessage(ChatColor.RED + "[*] Spectator Mode has been disabled.");
                    } else {
                        spectatorMode = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Spectator Mode has been enabled.");
                    }
                    break;
                case LEATHER:
                    if (noClean) {
                        noClean = false;
                        player.sendMessage(ChatColor.RED + "[*] NoClean has been disabled.");
                    } else {
                        noClean = true;
                        player.sendMessage(ChatColor.GREEN + "[*] NoClean has been enabled.");
                    }
                    break;
            }
            e.setCancelled(true);
        }

        if (e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "Kits")) {
            Player player = (Player) e.getWhoClicked();
            String playerName = player.getName();
            if (workerKit.contains(playerName) || bowKit.contains(playerName) || goldMinerKit.contains(playerName) || fisherManKit.contains(playerName) || enchanterKit.contains(playerName) || jewelerKIt.contains(playerName)) {
                player.sendMessage(ChatColor.RED + "[*] Unequipped all classes.");
                enchanterKit.remove(playerName);
                goldMinerKit.remove(playerName);
                fisherManKit.remove(playerName);
                workerKit.remove(playerName);
                bowKit.remove(playerName);
                jewelerKIt.remove(playerName);
                e.setCancelled(true);
                return;
            }

            switch (e.getCurrentItem().getType()) {
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
                case RAW_FISH:
                    player.sendMessage(ChatColor.GREEN + "[*] Equipped the Fisherman Kit.");
                    fisherManKit.add(playerName);
                    break;
                case ENCHANTED_BOOK:
                    player.sendMessage(ChatColor.GREEN + "[*] Equipped the Magician Kit.");
                    enchanterKit.add(playerName);
                    break;
                case DIAMOND:
                    player.sendMessage(ChatColor.GREEN + "[*] Equipped the Jeweler Kit.");
                    break;
            }

            e.setCancelled(true);

        }

    }

}
