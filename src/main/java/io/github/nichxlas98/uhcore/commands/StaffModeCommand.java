package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.InventoryUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static io.github.nichxlas98.uhcore.items.ItemManager.*;
import static io.github.nichxlas98.uhcore.models.MessageModels.PERMS_ERROR;
import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;
import static io.github.nichxlas98.uhcore.models.ModelsClass.playerAdminLevel;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.*;

public class StaffModeCommand implements CommandExecutor {

    public static void manageInventory(Player player, String system) {
        //True = Load, False = Save
        switch(system.toLowerCase()) {
            case "save":
                InventoryUtil.saveInventory(player);
                return;
            case "load":
                InventoryUtil.loadInventory(player);
                return;
            case "clear":
                player.getInventory().clear();
                player.getInventory().setArmorContents(null);
                break;
            default:
                //
        }
    }

    public static void giveStaffInventory(Player player) {
        player.getInventory().setItem(0, getStaffCompass());
        player.getInventory().setItem(1, getStaffBook());
        player.getInventory().setItem(2, getStaffRod());

        player.getInventory().setItem(6, getStaffIce());
        player.getInventory().setItem(7, getStaffPaper());
        player.getInventory().setItem(8, getStaffWoolon());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;
        UUID playerUUID = player.getUniqueId();

        if (!(playerAdminLevel(player) > MIN_ADMIN_LEVEL)) {
            player.sendMessage(PERMS_ERROR);
            return true;
        }

        if (!(getStaffMode(playerUUID))) {
            manageInventory(player, "save");
            manageInventory(player, "clear");
            giveStaffInventory(player);
            setStaffMode(playerUUID, player, true);
            player.setHealth(20);
            player.sendMessage(ChatColor.GREEN + "[*] Staff Mode Enabled.");
            return true;
        }

        manageInventory(player, "clear");
        manageInventory(player, "load");
        setStaffMode(playerUUID, player, false);
        player.sendMessage(ChatColor.RED + "[*] Staff Mode Disabled.");
        return true;
    }
}
