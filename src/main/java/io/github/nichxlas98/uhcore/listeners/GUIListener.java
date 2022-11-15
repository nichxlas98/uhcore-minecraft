package io.github.nichxlas98.uhcore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static io.github.nichxlas98.uhcore.models.ModelsClass.*;
import static io.github.nichxlas98.uhcore.utils.ServerUtils.*;

public class GUIListener implements Listener {

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) return;
        String inv = e.getClickedInventory().getTitle();
        Player player = (Player) e.getWhoClicked();

        if (inv.equalsIgnoreCase(ChatColor.RED + "Modifiers")) {
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case GOLDEN_APPLE:
                    if (isDoubleHp()) {
                        setDoubleHp(false);
                        player.sendMessage(ChatColor.RED + "[*] Double Health has been disabled.");
                    } else {
                        setDoubleHp(true);
                        player.sendMessage(ChatColor.GREEN + "[*] Double Health has been enabled.");
                    }
                    break;
                case FEATHER:
                    if (isDoubleSpeed()) {
                        setDoubleSpeed(false);
                        player.sendMessage(ChatColor.RED + "[*] Double Speed has been disabled.");
                    } else {
                        setDoubleSpeed(true);
                        player.sendMessage(ChatColor.GREEN + "[*] Double Speed has been enabled.");
                    }
                    break;
                case SUGAR:
                    if (isCutClean()) {
                        setCutClean(false);
                        player.sendMessage(ChatColor.RED + "[*] CutClean has been disabled.");
                    } else {
                        setCutClean(true);
                        player.sendMessage(ChatColor.GREEN + "[*] CutClean has been enabled.");
                    }
                    break;
                case SKULL_ITEM:
                    if (isDoubleHeads()) {
                        setDoubleHeads(false);
                        player.sendMessage(ChatColor.RED + "[*] Double Heads has been disabled.");
                    } else {
                        setDoubleHeads(true);
                        player.sendMessage(ChatColor.GREEN + "[*] Double Heads has been enabled.");
                    }
                    break;
                case GOLD_NUGGET:
                    if (isGoldRush()) {
                        setGoldRush(false);
                        player.sendMessage(ChatColor.RED + "[*] Gold Rush has been disabled.");

                    } else {
                        setGoldRush(true);
                        player.sendMessage(ChatColor.GREEN + "[*] Gold Rush has been enabled.");
                    }
                    break;
                case GOLDEN_CARROT:
                    if (isUhcKits()) {
                        setUhcKits(false);
                        player.sendMessage(ChatColor.RED + "[*] Kits UHC has been disabled.");
                    } else {
                        setUhcKits(true);
                        player.sendMessage(ChatColor.GREEN + "[*] Kits UHC has been enabled.");
                    }
                    break;
                case ENDER_PEARL:
                    if (isPearlUhc()) {
                        setPearlUhc(false);
                        player.sendMessage(ChatColor.RED + "[*] Pearl UHC has been disabled.");
                    } else {
                        setPearlUhc(true);
                        player.sendMessage(ChatColor.GREEN + "[*] Pearl UHC has been enabled.");
                    }
                    break;
                case IRON_SWORD:
                    if (isNoSwords()) {
                        setNoSwords(false);
                        player.sendMessage(ChatColor.RED + "[*] No Swords has been disabled.");
                    } else {
                        setNoSwords(true);
                        player.sendMessage(ChatColor.GREEN + "[*] No Swords has been enabled.");
                    }
                    break;
                case EYE_OF_ENDER:
                    if (isLifeSteal()) {
                        setLifeSteal(false);
                        player.sendMessage(ChatColor.RED + "[*] LifeSteal has been disabled.");
                    } else {
                        setLifeSteal(true);
                        player.sendMessage(ChatColor.GREEN + "[*] LifeSteal has been enabled.");
                    }
                    break;
                case MAGMA_CREAM:
                    if (isSpectatorMode()) {
                        setSpectatorMode(false);
                        player.sendMessage(ChatColor.RED + "[*] Spectator Mode has been disabled.");
                    } else {
                        setSpectatorMode(true);
                        player.sendMessage(ChatColor.GREEN + "[*] Spectator Mode has been enabled.");
                    }
                    break;
                case LEATHER:
                    if (isNoClean()) {
                        setNoClean(false);
                        player.sendMessage(ChatColor.RED + "[*] NoClean has been disabled.");
                    } else {
                        setNoClean(true);
                        player.sendMessage(ChatColor.GREEN + "[*] NoClean has been enabled.");
                    }
                    break;
                case POTION:
                    if (isPotUhc()) {
                        setPotUHC(false);
                        player.sendMessage(ChatColor.RED + "[*] PotUHC has been disabled.");
                    } else {
                        setPotUHC(true);
                        player.sendMessage(ChatColor.RED + "[*] PotUHC has been enabled.");
                    }
                    break;
                default:
                    //
            }
        }

        if (inv.equalsIgnoreCase(ChatColor.RED + "Kits")) {
            if (kitSelected.containsKey(player.getUniqueId())) {
                kitSelected.replace(player.getUniqueId(), NONE_SELECTED);
                player.sendMessage(ChatColor.RED + "[*] Unequipped all kits. Use /kits to select a new kit.");
                player.closeInventory();
                return;
            }

            switch (e.getCurrentItem().

                    getType()) {
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
                default:
                    //
            }
        }
    }
}
