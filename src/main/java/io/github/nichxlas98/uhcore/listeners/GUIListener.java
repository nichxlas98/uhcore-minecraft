package io.github.nichxlas98.uhcore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static io.github.nichxlas98.uhcore.models.ModelsClass.*;

public class GUIListener implements Listener {
    
    @EventHandler
    public void clickEvent(InventoryClickEvent e) {
        if (e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "Modifiers")) {
            Player player = (Player) e.getWhoClicked();

            switch (e.getCurrentItem().getType()) {
                case GOLDEN_APPLE:
                    if (DOUBLE_HP) {
                        DOUBLE_HP = false;
                        player.sendMessage(ChatColor.RED + "[*] Double Health has been disabled.");
                    } else {
                        DOUBLE_HP = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Double Health has been enabled.");
                    }
                    break;
                case FEATHER:
                    if (DOUBLE_SPEED) {
                        DOUBLE_SPEED = false;
                        player.sendMessage(ChatColor.RED + "[*] Double Speed has been disabled.");
                    } else {
                        DOUBLE_SPEED = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Double Speed has been enabled.");
                    }
                    break;
                case SUGAR:
                    if (CUT_CLEAN) {
                        CUT_CLEAN = false;
                        player.sendMessage(ChatColor.RED + "[*] CutClean has been disabled.");
                    } else {
                        CUT_CLEAN = true;
                        player.sendMessage(ChatColor.GREEN + "[*] CutClean has been enabled.");
                    }
                    break;
                case SKULL_ITEM:
                    if (DOUBLE_HEADS) {
                        DOUBLE_HEADS = false;
                        player.sendMessage(ChatColor.RED + "[*] Double Heads has been disabled.");
                    } else {
                        DOUBLE_HEADS = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Double Heads has been enabled.");
                    }
                    break;
                case GOLD_NUGGET:
                    if (GOLD_RUSH) {
                        GOLD_RUSH = false;
                        player.sendMessage(ChatColor.RED + "[*] Gold Rush has been disabled.");

                    } else {
                        GOLD_RUSH = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Gold Rush has been enabled.");
                    }
                    break;
                case GOLDEN_CARROT:
                    if (UHC_KITS) {
                        UHC_KITS = false;
                        player.sendMessage(ChatColor.RED + "[*] Kits UHC has been disabled.");
                    } else {
                        UHC_KITS = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Kits UHC has been enabled.");
                    }
                    break;
                case ENDER_PEARL:
                    if (PEARL_UHC) {
                        PEARL_UHC = false;
                        player.sendMessage(ChatColor.RED + "[*] Pearl UHC has been disabled.");
                    } else {
                        PEARL_UHC = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Pearl UHC has been enabled.");
                    }
                    break;
                case IRON_SWORD:
                    if (NO_SWORDS) {
                        NO_SWORDS = false;
                        player.sendMessage(ChatColor.RED + "[*] No Swords has been disabled.");
                    } else {
                        NO_SWORDS = true;
                        player.sendMessage(ChatColor.GREEN + "[*] No Swords has been enabled.");
                    }
                    break;
                case EYE_OF_ENDER:
                    if (LIFE_STEAL) {
                        LIFE_STEAL = false;
                        player.sendMessage(ChatColor.RED + "[*] LifeSteal has been disabled.");
                    } else {
                        LIFE_STEAL = true;
                        player.sendMessage(ChatColor.GREEN + "[*] LifeSteal has been enabled.");
                    }
                    break;
                case MAGMA_CREAM:
                    if (SPECTATOR_MODE) {
                        SPECTATOR_MODE = false;
                        player.sendMessage(ChatColor.RED + "[*] Spectator Mode has been disabled.");
                    } else {
                        SPECTATOR_MODE = true;
                        player.sendMessage(ChatColor.GREEN + "[*] Spectator Mode has been enabled.");
                    }
                    break;
                case LEATHER:
                    if (NO_CLEAN) {
                        NO_CLEAN = false;
                        player.sendMessage(ChatColor.RED + "[*] NoClean has been disabled.");
                    } else {
                        NO_CLEAN = true;
                        player.sendMessage(ChatColor.GREEN + "[*] NoClean has been enabled.");
                    }
                    break;
                default:
                    //
            }
            e.setCancelled(true);
        }

        if (e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "Kits")) {
            Player player = (Player) e.getWhoClicked();

            if (kitSelected.containsKey(player.getUniqueId())) {
                kitSelected.replace(player.getUniqueId(), NONE_SELECTED);
                player.sendMessage(ChatColor.RED + "[*] Unequipped all kits. Use /kits to select a new kit.");
                player.closeInventory();
                return;
            }

            switch (e.getCurrentItem().getType()) {
                case GOLD_PICKAXE:
                    player.sendMessage(ChatColor.GREEN + "[*] Equipped the Worker Kit.");
                    kitSelected.replace(player.getUniqueId(), WORKER_KIT);
                    break;
                case BOW:
                    player.sendMessage(ChatColor.GREEN + "[*] Equipped the Archery Kit.");
                    kitSelected.replace(player.getUniqueId(), BOW_KIT);
                    break;
                case GOLDEN_APPLE:
                    player.sendMessage(ChatColor.GREEN + "[*] Equipped the Gold Miner Kit.");
                    kitSelected.replace(player.getUniqueId(), GOLD_MINER_KIT);
                    break;
                case RAW_FISH:
                    player.sendMessage(ChatColor.GREEN + "[*] Equipped the Fisherman Kit.");
                    kitSelected.replace(player.getUniqueId(), FISHER_MAN_KIT);
                    break;
                case ENCHANTED_BOOK:
                    player.sendMessage(ChatColor.GREEN + "[*] Equipped the Magician Kit.");
                    kitSelected.replace(player.getUniqueId(), ENCHANTER_KIT);
                    break;
                case DIAMOND:
                    player.sendMessage(ChatColor.GREEN + "[*] Equipped the Jeweler Kit.");
                    kitSelected.replace(player.getUniqueId(), JEWELER_KIT);
                    break;
            }
            e.setCancelled(true);
        }
    }
}
