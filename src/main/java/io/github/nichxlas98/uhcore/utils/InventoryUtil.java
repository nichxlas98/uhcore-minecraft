package io.github.nichxlas98.uhcore.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static io.github.nichxlas98.uhcore.utils.DatabaseUtil.*;

public class InventoryUtil {

    public static void saveInventory(Player player) {
        ItemStack[] inventoryItems = player.getInventory().getContents();
        List<String> itemsToSave = new ArrayList<>();

        for (ItemStack item : inventoryItems) {
            if (item == null) continue;
            itemsToSave.add(item.getType().name() + ";" + item.getAmount());
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