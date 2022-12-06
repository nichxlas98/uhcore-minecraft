package io.github.nichxlas98.uhcore.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {

    private static ItemStack GOLDEN_HEAD;

    private static ItemStack STAFF_COMPASS;

    private static ItemStack STAFF_BOOK;
    private static ItemStack STAFF_ROD;
    private static ItemStack STAFF_ICE;
    private static ItemStack STAFF_PAPER;
    private static ItemStack STAFF_WOOLON;
    private static ItemStack STAFF_WOOLOFF;

    public static void init() {

        createGoldenHead();
        createStaffCompass();
        createStaffBook();
        createStaffRod();
        createStaffIce();
        createGoldenHead();
        createStaffPaper();
        createStaffWoolOn();
        createStaffWoolOff();
    }

    private static void createGoldenHead() {
        ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Golden Head");
        item.setItemMeta(meta);
        GOLDEN_HEAD = item;

        ShapelessRecipe sr = new ShapelessRecipe(item);
        sr.addIngredient(8, Material.GOLD_INGOT);
        sr.addIngredient(1, Material.SKULL_ITEM);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createStaffCompass() {
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + "Staff Compass");
        itemMeta.addEnchant(Enchantment.LUCK, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.GRAY + "A compass which lets you pass through walls.");
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);

        STAFF_COMPASS = item;
    }

    private static void createStaffBook() {
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "Inventory Book");
        itemMeta.addEnchant(Enchantment.LUCK, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.GRAY + "A book which lets you check player inventories.");
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);

        STAFF_BOOK = item;
    }

    private static void createStaffRod() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Teleport Rod");
        itemMeta.addEnchant(Enchantment.LUCK, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.GRAY + "A blaze rod which lets you teleport to random players.");
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);

        STAFF_ROD = item;
    }

    private static void createStaffIce() {
        ItemStack item = new ItemStack(Material.ICE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + "Freeze Block");
        itemMeta.addEnchant(Enchantment.LUCK, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.GRAY + "A block which lets you freeze players in place.");
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);

        STAFF_ICE = item;
    }

    private static void createStaffPaper() {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GRAY + "Online Staff");
        itemMeta.addEnchant(Enchantment.LUCK, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.GRAY + "A paper which lets you check the currently online staff.");
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);

        STAFF_PAPER = item;
    }

    private static void createStaffWoolOn() {
        ItemStack item = new ItemStack(Material.WOOL, 1, (short) 6);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "Enable Vanish");
        itemMeta.addEnchant(Enchantment.LUCK, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.GRAY + "A wool which lets you vanish from the server.");
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);

        STAFF_WOOLON = item;
    }

    private static void createStaffWoolOff() {
        ItemStack item = new ItemStack(Material.WOOL, 1, (short) 7);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GRAY + "Disable Vanish");
        itemMeta.addEnchant(Enchantment.LUCK, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.GRAY + "A wool which lets you re-appear on the server.");
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);

        STAFF_WOOLOFF = item;
    }

    public static ItemStack getGoldenHead() {
        return GOLDEN_HEAD;
    }

    public static ItemStack getStaffCompass() {
        return STAFF_COMPASS;
    }

    public static ItemStack getStaffBook() {
        return STAFF_BOOK;
    }

    public static ItemStack getStaffRod() {
        return STAFF_ROD;
    }

    public static ItemStack getStaffIce() {
        return STAFF_ICE;
    }

    public static ItemStack getStaffPaper() {
        return STAFF_PAPER;
    }

    public static ItemStack getStaffWoolon() {
        return STAFF_WOOLON;
    }

    public static ItemStack getStaffWooloff() {
        return STAFF_WOOLOFF;
    }
}
