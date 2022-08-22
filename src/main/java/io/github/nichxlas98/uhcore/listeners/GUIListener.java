package io.github.nichxlas98.uhcore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {


    public static boolean doubleHP = false;
    public static boolean doubleSpeed = false;
    public static boolean fastsEnabled = false;
    public static boolean doubleHeads = false;
    public static boolean goldRush = false;

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {


        if (e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.AQUA + "Settings")){
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

            }

            e.setCancelled(true);
        }

    }

}
