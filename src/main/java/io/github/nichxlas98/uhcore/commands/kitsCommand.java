package io.github.nichxlas98.uhcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static io.github.nichxlas98.uhcore.models.modelsClass.uhcKits;

public class kitsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (uhcKits) {
                Inventory gui = Bukkit.createInventory(player, 18, ChatColor.RED + "Kits");

                ItemStack ironTools = new ItemStack(Material.GOLD_PICKAXE);
                ItemStack bow = new ItemStack(Material.BOW);
                ItemStack goldMiner = new ItemStack(Material.GOLDEN_APPLE);
                ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
                ItemStack fisherMan = new ItemStack(Material.RAW_FISH);
                ItemStack enchanter = new ItemStack(Material.ENCHANTED_BOOK);
                ItemStack jeweler = new ItemStack(Material.DIAMOND);


                ItemMeta emptyMeta = empty.getItemMeta();
                emptyMeta.setDisplayName(ChatColor.ITALIC + " ");
                empty.setItemMeta(emptyMeta);

                newItem(jeweler, ChatColor.AQUA, "Jeweler Kit", "This class gives you 2 diamonds, and Haste on startup.");
                newItem(enchanter, ChatColor.RED, "Magician Kit", "This class gives you 4 books, 15 XP bottles and 18 lapis on startup.");
                newItem(fisherMan, ChatColor.AQUA, "Fisherman Kit", "This class gives you a Fishing Rod and a single Golden Apple on startup.");
                newItem(goldMiner, ChatColor.GOLD, "Gold Miner Kit", "This class gives you 2 Golden Apples and a stack of Golden Nuggets on startup.");
                newItem(bow, ChatColor.AQUA, "Archery Kit.", "This class gives you 32 arrows, and a bow on startup.");
                newItem(ironTools, ChatColor.GOLD, "Worker Kit", "This class gives you a full set of iron-tools on startup!");

                ItemStack[] menu_items = {
                        bow, ironTools, goldMiner, fisherMan, enchanter, jeweler, empty, empty, empty,
                        empty, empty, empty, empty, empty, empty, empty, empty, empty}; // ROW 2 <<<
                gui.setContents(menu_items);
                player.openInventory(gui);
            } else {
                player.sendMessage(ChatColor.RED + "[*] You can't use /kits while KitsUHC is disabled.");
            }
        }
        return true;
    }

    private static void newItem(ItemStack item, ChatColor color, String displayName, String lore) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(color + displayName);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.WHITE + lore);
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);
    }
}
