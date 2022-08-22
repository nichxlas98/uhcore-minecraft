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


                ItemMeta emptyMeta = empty.getItemMeta();
                emptyMeta.setDisplayName(ChatColor.ITALIC + " ");
                empty.setItemMeta(emptyMeta);


                ItemMeta enchanterMeta = enchanter.getItemMeta();
                enchanterMeta.setDisplayName(ChatColor.RED + "Magician Kit");
                enchanterMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                ArrayList<String> enchanterLore = new ArrayList<>();
                enchanterLore.add(ChatColor.WHITE + "This class gives you 4 books, 15 XP bottles and 18 lapis on startup.");
                enchanterMeta.setLore(enchanterLore);
                enchanter.setItemMeta(enchanterMeta);

                ItemMeta fisherManMeta = fisherMan.getItemMeta();
                fisherManMeta.setDisplayName(ChatColor.AQUA + "Fisherman Kit");
                fisherManMeta.addEnchant(Enchantment.LUCK, 1, true);
                fisherManMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                ArrayList<String> fisherManLore = new ArrayList<>();
                fisherManLore.add(ChatColor.WHITE + "This class gives you a Fishing Rod and a single Golden Apple on startup.");
                fisherManMeta.setLore(fisherManLore);
                fisherMan.setItemMeta(fisherManMeta);


                ItemMeta goldMinerMeta = goldMiner.getItemMeta();
                goldMinerMeta.setDisplayName(ChatColor.GOLD + "Gold Miner Kit");
                goldMinerMeta.addEnchant(Enchantment.LUCK, 1, true);
                goldMinerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                ArrayList<String> goldMinerLore = new ArrayList<>();
                goldMinerLore.add(ChatColor.WHITE + "This class gives you 2 Golden Apples and a stack of Golden Nuggets on startup.");
                goldMinerMeta.setLore(goldMinerLore);
                goldMiner.setItemMeta(goldMinerMeta);

                ItemMeta bowMeta = bow.getItemMeta();
                bowMeta.setDisplayName(ChatColor.AQUA + "Archery Kit");
                bowMeta.addEnchant(Enchantment.LUCK, 1, true);
                bowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                ArrayList<String> bowLore = new ArrayList<>();
                bowLore.add(ChatColor.WHITE + "This class gives you 32 arrows, and a bow on startup!");
                bowMeta.setLore(bowLore);
                bow.setItemMeta(bowMeta);

                ItemMeta ironToolsMeta = ironTools.getItemMeta();
                ironToolsMeta.setDisplayName(ChatColor.GOLD + "Worker Kit");
                ironToolsMeta.addEnchant(Enchantment.LUCK, 1, true);
                ironToolsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                ArrayList<String> ironToolsLore = new ArrayList<>();
                ironToolsLore.add(ChatColor.WHITE + "This class gives you a full set of iron-tools on startup!");
                ironToolsMeta.setLore(ironToolsLore);
                ironTools.setItemMeta(ironToolsMeta);

                ItemStack[] menu_items = {
                        bow, ironTools, goldMiner, fisherMan, enchanter, empty, empty, empty, empty,
                        empty, empty, empty, empty, empty, empty, empty, empty, empty}; // ROW 2 <<<
                gui.setContents(menu_items);
                player.openInventory(gui);
            } else {
                player.sendMessage(ChatColor.RED + "[*] You can't use /kits while KitsUHC is disabled.");
            }




        }

        return true;
    }
}
