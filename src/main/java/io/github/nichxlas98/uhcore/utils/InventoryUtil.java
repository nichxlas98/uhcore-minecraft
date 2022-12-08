package io.github.nichxlas98.uhcore.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static io.github.nichxlas98.uhcore.utils.DatabaseUtil.*;

public class InventoryUtil {

    private static String reverseString(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] words = string.split("_");

        for (int j = words.length-1; j >= 0; j--) {
            stringBuilder.append(words[j]).append('_');
        }
        return stringBuilder.toString();
    }
    public static void saveInventoryEC(Player player) {
        ArrayList<String> item_names = new ArrayList<>();
        item_names.add("helmet");
        item_names.add("chestplate");
        item_names.add("leggings");
        item_names.add("boots");
        item_names.add("sword");
        item_names.add("axe");
        item_names.add("pickaxe");
        item_names.add("shovel");
        item_names.add("rod");
        item_names.add("bow");
        item_names.add("apple");
        item_names.add("item");
        item_names.add("book");
        int index = 0;
        ItemStack[] items = new ItemStack[27];
        for (ItemStack i : player.getInventory().getContents()) {
            if (i == null) continue;
            String item = reverseString(i.getType().name().toLowerCase());
            if (!(item_names.contains(item.split("_")[0]))) continue;
            items[index] = i;
            index++;
        }

        for (ItemStack i : player.getInventory().getArmorContents()) {
            if (i == null) continue;
            String item = reverseString(i.getType().name().toLowerCase());
            if (!(item_names.contains(item.split("_")[0]))) continue;
            items[index] = i;
            index++;
        }

        player.getEnderChest().setContents(items);
    }

    public static void loadInventoryEC(Player player) {
        player.getInventory().setContents(player.getEnderChest().getContents());
        player.getEnderChest().clear();
    }


    public static void saveInventory(Player player) {
        ItemStack[] inventoryItems = player.getInventory().getContents();
        List<String> itemsToSave = new ArrayList<>();
        ArrayList<String> item_names = new ArrayList<>();
        item_names.add("helmet");
        item_names.add("chestplate");
        item_names.add("leggings");
        item_names.add("boots");
        item_names.add("sword");
        item_names.add("axe");
        item_names.add("pickaxe");
        item_names.add("shovel");
        item_names.add("rod");
        item_names.add("bow");
        item_names.add("apple");
        item_names.add("item");
        item_names.add("book");

        for (ItemStack i : inventoryItems) {
            if (i == null) continue;
            String item = reverseString(i.getType().name().toLowerCase());
            if (item_names.contains(item.split("_")[0])) continue;
            itemsToSave.add(i.getType().name() + ";" + i.getAmount());
        }

        configBackup.set(player.getUniqueId() + ".Items", itemsToSave);
        DatabaseUtil.saveCustomData(configBackup, ymlBackup);
    }

    public static void loadInventory(Player player) {
        List<String> itemsSaved = configBackup.getStringList(player.getUniqueId() + ".Items");
        if (itemsSaved.isEmpty()) return;

        for (String item : itemsSaved) {

            Material itemMaterial = Material.getMaterial(item.split(";")[0]);
            int itemAmount = Integer.parseInt(item.split(";")[1]);

            player.getInventory().addItem(new ItemStack(itemMaterial, itemAmount));
        }
    }
}