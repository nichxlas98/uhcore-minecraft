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

import static io.github.nichxlas98.uhcore.models.MessageModels.PERMS_ERROR;
import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;
import static io.github.nichxlas98.uhcore.models.ModelsClass.playerAdminLevel;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.MIN_ADMIN_LEVEL;

public class ModifiersCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;

        if (!(playerAdminLevel(player) >= MIN_ADMIN_LEVEL)) {
            player.sendMessage(PERMS_ERROR);
            return true;
        }

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
        ItemStack spectatorMode = new ItemStack(Material.MAGMA_CREAM);
        ItemStack noClean = new ItemStack(Material.LEATHER);
        ItemStack potUHC = new ItemStack(Material.POTION);

        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(ChatColor.ITALIC + " ");
        empty.setItemMeta(emptyMeta);

        newItem(noClean, ChatColor.AQUA, "NoClean", "After a player obtains a kill, that player is invincible for 60 seconds.");
        newItem(spectatorMode, ChatColor.LIGHT_PURPLE, "Spectator Mode", "Allows players to spectate games after death.");
        newItem(lifeSteal, ChatColor.DARK_RED, "LifeSteal", "During the game, on player-kill, the killer's given an extra heart.");
        newItem(noSwords, ChatColor.YELLOW, "NoSwords", "Disallows all swords from being used.");
        newItem(uhcKits, ChatColor.BLUE, "KitsUHC", "Allows /kit usage during UHC.");
        newItem(goldRush, ChatColor.GOLD, "GoldRush", "All players are given a Golden Ingot on a player's death.");
        newItem(pearlUHC, ChatColor.DARK_PURPLE, "PearlUHC", "Players spawn with three EnderPearls, all players drop an EnderPearl on death.");
        newItem(doubleHeads, ChatColor.GREEN, "Double Heads", "Doubles the Head drops of all players during UHC. ");
        newItem(cutClean, ChatColor.GOLD, "CutClean", "CutClean UHC Mode.");
        newItem(doubleHealth, ChatColor.RED, "Double Health", "Doubles the Health of all players during UHC.");
        newItem(doubleSpeed, ChatColor.AQUA, "Double Speed", "Doubles the Speed of all players during UHC.");
        newItem(potUHC, ChatColor.DARK_BLUE, "PotUHC", "All leaves drops Speed & Healing Potions. When a player obtains a kill, that player is given three EnderPearls.");

        ItemStack[] menu_items = {
                doubleHealth, pearlUHC, cutClean, uhcKits, doubleSpeed, goldRush, doubleHeads, noSwords, lifeSteal,
                spectatorMode, noClean, potUHC, empty, empty, empty, empty, empty, empty}; // ROW 2 <<<
        gui.setContents(menu_items);
        player.openInventory(gui);
        return true;
    }

    private static void newItem(ItemStack item, ChatColor color, String displayName, String lore) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(color + displayName);
        itemMeta.addEnchant(Enchantment.LUCK, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.GRAY + lore);
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);
    }
}
