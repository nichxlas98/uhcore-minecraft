package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.DeathUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

import static io.github.nichxlas98.uhcore.models.MessageModels.PERMS_ERROR;
import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.*;

public class StatsCommand implements CommandExecutor {

    public static ItemStack playerStats(Player target) {
        String name = ChatColor.GOLD + target.getPlayer().getName();
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setDisplayName(name);
        meta.setOwner(target.getName());
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.GRAY + "Rank: " + getServerRank(target));
        itemLore.add(ChatColor.GRAY + "Health: " + Math.round(target.getHealth()));
        itemLore.add(ChatColor.GRAY + "Hunger: " + Math.round(target.getFoodLevel()));
        itemLore.add(ChatColor.GRAY + "Kills: " + target.getStatistic(Statistic.PLAYER_KILLS));
        itemLore.add(ChatColor.GRAY + "Admin Level: " + ChatColor.AQUA + getAdminLevel(target.getUniqueId()));
        meta.setLore(itemLore);
        skull.setItemMeta(meta);
        return skull;
    }

    private ItemStack connectionStats(Player target) {
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta itemMeta = compass.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "Connection Stats");
        ArrayList<String> compassLore = new ArrayList<>();
        compassLore.add(ChatColor.GRAY + "Last IP: " + target.getAddress().getAddress());
        compassLore.add(ChatColor.GRAY + "Last Location: " + "Unknown");
        itemMeta.setLore(compassLore);
        compass.setItemMeta(itemMeta);
        return compass;
    }

    private ItemStack deathStats(Player target) {
        ItemStack clock = new ItemStack(Material.WATCH);
        ItemMeta itemMeta = clock.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "Last Death Stats");
        ArrayList<String> clockLore = new ArrayList<>();
        clockLore.add(ChatColor.GRAY + "Last Killer: " + DeathUtil.getKiller(target));
        clockLore.add(ChatColor.GRAY + "Last Death: " + DeathUtil.getLastDeathTime(target));
        clockLore.add(ChatColor.GRAY + "Last Location: " + DeathUtil.getLastDeathLocation(target));
        clockLore.add(ChatColor.GRAY + "Last Coords: " + DeathUtil.getLastDeathCoords(target));
        itemMeta.setLore(clockLore);
        clock.setItemMeta(itemMeta);
        return clock;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;

        if (getAdminLevel(player.getUniqueId())  < HIGH_ADMIN_LEVEL) {
            player.sendMessage(PERMS_ERROR);
            return true;
        }

        if (args.length < 2) {
            player.sendMessage(ChatColor.RED + "[*] You can type /checkstats <player> [brief/full]");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.RED + "[*] " + args[0] + " is not an online player.");
            return true;
        }

        if (args[1].equalsIgnoreCase("brief")) {
            player.sendMessage(ChatColor.GOLD + "- " + target.getName());
            player.sendMessage(ChatColor.GRAY + "- Rank: " + getServerRank(target));
            player.sendMessage(ChatColor.GRAY + "- Health: " + Math.round(target.getHealth()));
            player.sendMessage(ChatColor.GRAY + "- Hunger: " + Math.round(target.getFoodLevel()));
            player.sendMessage(ChatColor.GRAY + "- Admin Level: " + ChatColor.AQUA + getAdminLevel(target.getUniqueId()));
            player.sendMessage(ChatColor.GRAY + "- Last IP: " + target.getAddress().getAddress());
            return true;
        }

        if (!(args[1].equalsIgnoreCase("full"))) return true;
        Inventory gui = Bukkit.createInventory(player, 9, ChatColor.RED + "Player Stats");

        ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(ChatColor.ITALIC + " ");
        empty.setItemMeta(emptyMeta);

        ItemStack[] menu_items = {
                playerStats(target), empty, empty, empty, empty, empty, deathStats(target), connectionStats(target), empty
        };
        gui.setContents(menu_items);
        player.openInventory(gui);
        player.sendMessage(ChatColor.GREEN + "[*] Viewing " + target.getName() + "'s stats...");
        return true;
    }
}
