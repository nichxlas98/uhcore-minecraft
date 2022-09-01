package io.github.nichxlas98.uhcore.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {

    public static ItemStack GoldenHead;

    public static void init() {
        createGoldenHead();
    }

    private static void createGoldenHead() {
        ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Golden Head");
        item.setItemMeta(meta);
        GoldenHead = item;

        ShapelessRecipe sr = new ShapelessRecipe(item);
        sr.addIngredient(8, Material.GOLD_INGOT);
        sr.addIngredient(1, Material.SKULL_ITEM);
        Bukkit.getServer().addRecipe(sr);

    }
}
