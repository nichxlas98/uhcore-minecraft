package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static io.github.nichxlas98.uhcore.commands.StatsCommand.playerStats;
import static io.github.nichxlas98.uhcore.models.MessageModels.PERMS_ERROR;
import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.HIGH_ADMIN_LEVEL;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.getAdminLevel;

public class InventoryCheckCommand implements CommandExecutor, Listener {

    ArrayList<String> checkingInventory = new ArrayList<>();

    @EventHandler
    public void invCloseEven(InventoryCloseEvent e) {
        if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "Player Inventory")) {
            checkingInventory.remove(e.getPlayer().getName());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;

        if (getAdminLevel(player.getUniqueId()) < HIGH_ADMIN_LEVEL) {
            player.sendMessage(PERMS_ERROR);
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "[*] You need to use /checkinv <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.RED + "[*] " + args[0] + " is not an online player!");
            return true;
        }

        Inventory gui = Bukkit.createInventory(player, 45, ChatColor.RED + "Player Inventory");
        checkingInventory.add(player.getName());
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!checkingInventory.contains(player.getName())) this.cancel();
                PlayerInventory playerInventory = target.getInventory();
                ItemStack[] menu_items = playerInventory.getContents();

                gui.setContents(menu_items);
                gui.setItem(36, playerStats(target));
                if (playerInventory.getHelmet() != null) gui.setItem(37, playerInventory.getHelmet());
                if (playerInventory.getChestplate() != null) gui.setItem(38, playerInventory.getChestplate());
                if (playerInventory.getLeggings() != null) gui.setItem(39, playerInventory.getLeggings());
                if (playerInventory.getBoots() != null) gui.setItem(40, playerInventory.getBoots());
            }
        }.runTaskTimer(UhCore.getPlugin(), 20, 1);
        player.openInventory(gui);
        player.sendMessage(ChatColor.GREEN + "[*] Viewing " + target.getName() + "'s inventory...");
        return true;
    }
}
