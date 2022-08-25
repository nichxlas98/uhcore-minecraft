package io.github.nichxlas98.uhcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class settingsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("UhCore.admin")) {
                Inventory gui = Bukkit.createInventory(player, 18, ChatColor.RED + "Settings");

                ItemStack doubleHealth = new ItemStack(Material.GOLDEN_APPLE);
                ItemStack doubleSpeed = new ItemStack(Material.FEATHER);
                ItemStack fastUHC = new ItemStack(Material.SUGAR);
                ItemStack doubleHeads = new ItemStack(Material.SKULL_ITEM);
                ItemStack pearlUHC = new ItemStack(Material.ENDER_PEARL);
                ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
                ItemStack goldRush = new ItemStack(Material.GOLD_NUGGET);
                ItemStack uhcKits = new ItemStack(Material.GOLDEN_CARROT);


                ItemMeta emptyMeta = empty.getItemMeta();
                emptyMeta.setDisplayName(ChatColor.ITALIC + " ");
                empty.setItemMeta(emptyMeta);


                ItemMeta uhcKitsMeta = uhcKits.getItemMeta();
                uhcKitsMeta.setDisplayName(ChatColor.BLUE + "KitsUHC");
                uhcKitsMeta.addEnchant(Enchantment.LUCK, 1, true);
                uhcKitsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                ArrayList<String> uhcKitsLore = new ArrayList<>();
                uhcKitsLore.add(ChatColor.ITALIC + "Allows /kit usage during UHC.");
                uhcKitsMeta.setLore(uhcKitsLore);
                uhcKits.setItemMeta(uhcKitsMeta);

                ItemMeta goldRushMeta = goldRush.getItemMeta();
                goldRushMeta.setDisplayName(ChatColor.GOLD + "Gold Rush");
                goldRushMeta.addEnchant(Enchantment.LUCK, 1, true);
                goldRushMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                ArrayList<String> goldRushLore = new ArrayList<>();
                goldRushLore.add(ChatColor.ITALIC + "All players are given a Golden Ingot on a player's death.");
                goldRushMeta.setLore(goldRushLore);
                goldRush.setItemMeta(goldRushMeta);

                ItemMeta pearlUHCMeta = pearlUHC.getItemMeta();
                pearlUHCMeta.setDisplayName(ChatColor.DARK_PURPLE + "PearlUHC");
                pearlUHCMeta.addEnchant(Enchantment.LUCK, 1, true);
                pearlUHCMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                ArrayList<String> pearlUHCLore = new ArrayList<>();
                pearlUHCLore.add(ChatColor.ITALIC + "All player spawn with 3 ender-pearls, and players drop an ender-pearl on death.");
                pearlUHCMeta.setLore(pearlUHCLore);
                pearlUHC.setItemMeta(pearlUHCMeta);

                ItemMeta doubleHeadsMeta = doubleHeads.getItemMeta();
                doubleHeadsMeta.setDisplayName(ChatColor.GREEN + "Double Heads");
                doubleHeadsMeta.addEnchant(Enchantment.LUCK, 1, true);
                doubleHeadsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                ArrayList<String> doubleHeadsLore = new ArrayList<>();
                doubleHeadsLore.add(ChatColor.ITALIC + "Doubles the Head drops of all players during UHC. ");
                doubleHeadsMeta.setLore(doubleHeadsLore);
                doubleHeads.setItemMeta(doubleHeadsMeta);

                ItemMeta fastUHCMeta = fastUHC.getItemMeta();
                fastUHCMeta.setDisplayName(ChatColor.GOLD + "FastUHC");
                fastUHCMeta.addEnchant(Enchantment.LUCK, 1, true);
                fastUHCMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                ArrayList<String> fastUHCLore = new ArrayList<>();
                fastUHCLore.add(ChatColor.ITALIC + "Fast UHC Mode.");
                fastUHCMeta.setLore(fastUHCLore);
                fastUHC.setItemMeta(fastUHCMeta);

                ItemMeta doubleHealthMeta = doubleHealth.getItemMeta();
                doubleHealthMeta.setDisplayName(ChatColor.RED + "Double Health");
                doubleHealthMeta.addEnchant(Enchantment.LUCK, 1, true);
                doubleHealthMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                ArrayList<String> doubleHealthLore = new ArrayList<>();
                doubleHealthLore.add(ChatColor.ITALIC + "Doubles the Health of all players during UHC.");
                doubleHealthMeta.setLore(doubleHealthLore);
                doubleHealth.setItemMeta(doubleHealthMeta);


                ItemMeta doubleSpeedMeta = doubleSpeed.getItemMeta();
                doubleSpeedMeta.setDisplayName(ChatColor.AQUA + "Double Speed");
                doubleSpeedMeta.addEnchant(Enchantment.LUCK, 1, true);
                doubleSpeedMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                ArrayList<String> doubleSpeedLore = new ArrayList<>();
                doubleSpeedLore.add(ChatColor.ITALIC + "Doubles the Speed of all players during UHC.");
                doubleSpeedMeta.setLore(doubleSpeedLore);
                doubleSpeed.setItemMeta(doubleSpeedMeta);


                ItemStack[] menu_items = {
                        doubleHealth, pearlUHC, fastUHC, uhcKits, doubleSpeed, goldRush, doubleHeads, empty, empty,
                        empty, empty, empty, empty, empty, empty, empty, empty, empty}; // ROW 2 <<<
                gui.setContents(menu_items);
                player.openInventory(gui);

            } else {
                player.sendMessage(ChatColor.RED + "[*] You do not have permission to use this command.");
            }
        }

        return true;
    }
}
