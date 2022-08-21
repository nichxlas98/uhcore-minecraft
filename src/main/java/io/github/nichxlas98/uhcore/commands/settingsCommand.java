package io.github.nichxlas98.uhcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class settingsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Inventory gui = Bukkit.createInventory(player, 9, ChatColor.AQUA + "Settings");

            ItemStack doubleHealth = new ItemStack(Material.GOLDEN_APPLE);
            ItemStack doubleSpeed = new ItemStack(Material.FEATHER);
            ItemStack fastUHC = new ItemStack(Material.GOLD_NUGGET);
            ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);


            ItemMeta emptyMeta = empty.getItemMeta();
            emptyMeta.setDisplayName(ChatColor.ITALIC + " ");
            empty.setItemMeta(emptyMeta);


            ItemMeta fastUHCMeta = fastUHC.getItemMeta();
            fastUHCMeta.setDisplayName(ChatColor.GOLD + "FastUHC");
            ArrayList<String> fastUHCLore = new ArrayList<>();
            fastUHCLore.add(ChatColor.ITALIC + "Fast UHC Mode.");
            fastUHCMeta.setLore(fastUHCLore);
            fastUHC.setItemMeta(fastUHCMeta);

            ItemMeta doubleHealthMeta = doubleHealth.getItemMeta();
            doubleHealthMeta.setDisplayName(ChatColor.RED + "Double Health");
            ArrayList<String> doubleHealthLore = new ArrayList<>();
            doubleHealthLore.add(ChatColor.ITALIC + "Doubles the Health of all players during UHC.");
            doubleHealthMeta.setLore(doubleHealthLore);
            doubleHealth.setItemMeta(doubleHealthMeta);


            ItemMeta doubleSpeedMeta = doubleSpeed.getItemMeta();
            doubleSpeedMeta.setDisplayName(ChatColor.AQUA + "Double Speed");
            ArrayList<String> doubleSpeedLore = new ArrayList<>();
            doubleSpeedLore.add(ChatColor.ITALIC + "Doubles the Speed of all players during UHC.");
            doubleSpeedMeta.setLore(doubleSpeedLore);
            doubleSpeed.setItemMeta(doubleSpeedMeta);


            ItemStack[] menu_items = {empty, empty, doubleHealth, empty, fastUHC, empty, doubleSpeed, empty, empty};
            gui.setContents(menu_items);
            player.openInventory(gui);

        }

        return true;
    }
}
