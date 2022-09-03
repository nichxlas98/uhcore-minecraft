package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.AdminLevelUtil;
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

import static io.github.nichxlas98.uhcore.utils.AdminLevelUtil.MIN_ADMIN_LEVEL;

public class modifiersCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (AdminLevelUtil.getAdminLevel(player.getUniqueId()) >= MIN_ADMIN_LEVEL) {
                Inventory gui = Bukkit.createInventory(player, 18, ChatColor.RED + "Modifiers");

                ItemStack doubleHealth = new ItemStack(Material.GOLDEN_APPLE);
                ItemStack doubleSpeed = new ItemStack(Material.FEATHER);
                ItemStack cutClean = new ItemStack(Material.SUGAR);
                ItemStack doubleHeads = new ItemStack(Material.SKULL_ITEM);
                ItemStack pearlUHC = new ItemStack(Material.ENDER_PEARL);
                ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
                ItemStack goldRush = new ItemStack(Material.GOLD_NUGGET);
                ItemStack uhcKits = new ItemStack(Material.GOLDEN_CARROT);
                ItemStack noSwords = new ItemStack(Material.IRON_SWORD);
                ItemStack lifeSteal = new ItemStack(Material.EYE_OF_ENDER);


                ItemMeta emptyMeta = empty.getItemMeta();
                emptyMeta.setDisplayName(ChatColor.ITALIC + " ");
                empty.setItemMeta(emptyMeta);

                newItem(lifeSteal, ChatColor.DARK_RED, "LifeSteal", "During the game, on player-kill, the killer's given an extra heart.");

                newItem(noSwords, ChatColor.YELLOW, "NoSwords", "Disallows all swords from being used.");

                newItem(uhcKits, ChatColor.BLUE, "KitsUHC", "Allows /kit usage during UHC.");

                newItem(goldRush, ChatColor.GOLD, "GoldRush", "All players are given a Golden Ingot on a player's death.");

                newItem(pearlUHC, ChatColor.DARK_PURPLE, "PearlUHC", "Players spawn with 3 EnderPearls, all players drop an EnderPearl on death.");

                newItem(doubleHeads, ChatColor.GREEN, "Double Heads", "Doubles the Head drops of all players during UHC. ");

                newItem(cutClean, ChatColor.GOLD, "CutClean", "CutClean UHC Mode.");

                newItem(doubleHealth, ChatColor.RED, "Double Health", "Doubles the Health of all players during UHC.");

                newItem(doubleSpeed, ChatColor.AQUA, "Double Speed", "Doubles the Speed of all players during UHC.");


                ItemStack[] menu_items = {
                        doubleHealth, pearlUHC, cutClean, uhcKits, doubleSpeed, goldRush, doubleHeads, noSwords, lifeSteal,
                        empty, empty, empty, empty, empty, empty, empty, empty, empty}; // ROW 2 <<<
                gui.setContents(menu_items);
                player.openInventory(gui);
            } else {
                player.sendMessage(ChatColor.RED + "[*] You do not have permission to use this command.");
            }
        } else {
            System.out.println(ChatColor.RED + "[*] You cannot do this from the console.");
        }
        return true;
    }

    private static void newItem(ItemStack noSwords, ChatColor yellow, String displayName, String lore) {
        ItemMeta noSwordsMeta = noSwords.getItemMeta();
        noSwordsMeta.setDisplayName(yellow + displayName);
        noSwordsMeta.addEnchant(Enchantment.LUCK, 1, true);
        noSwordsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> noSwordsLore = new ArrayList<>();
        noSwordsLore.add(ChatColor.GRAY + lore);
        noSwordsMeta.setLore(noSwordsLore);
        noSwords.setItemMeta(noSwordsMeta);
    }
}
