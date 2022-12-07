package io.github.nichxlas98.uhcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;

public class InventoryCheckCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "[*] You need to use /checkinv <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.RED + "[*] " + args[0] + " is not an online player!");
            return true;
        }

        Inventory gui = Bukkit.createInventory(player, 36, ChatColor.RED + "Player Inventory");
        gui.setContents(target.getInventory().getContents());
        player.openInventory(gui);
        player.sendMessage(ChatColor.GREEN + "[*] Viewing " + target.getName() + "'s inventory...");
        return true;
    }
}
