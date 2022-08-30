package io.github.nichxlas98.uhcore.utils;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import static io.github.nichxlas98.uhcore.models.modelsClass.*;
import static io.github.nichxlas98.uhcore.models.modelsClass.enchanterKit;

public class uhcUtil {

    public static void uhcModifiers(Player player) {
        for (Player players : Bukkit.getServer().getOnlinePlayers()) {
            if (doubleHP) {
                players.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 1000000, 4, false, false));
                players.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 5, 10));
                players.sendMessage(ChatColor.ITALIC + "[*] Double HP is enabled, all players were given double health.");
            } if (doubleSpeed) {
                players.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false));
                players.sendMessage(ChatColor.ITALIC + "[*] Double Speed is enabled, all players were given Speed II.");
            } if (pearlUHC) {
                players.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 3));
                players.sendMessage(ChatColor.ITALIC + "[*] Pearl UHC is enabled, all players were given 3 pearls.");
            }
            players.setGameMode(GameMode.SURVIVAL);
            players.sendMessage(ChatColor.GREEN + "[*] " + ChatColor.AQUA + player.getName() + ChatColor.GREEN + " has started the game.");
        }
    }

    public static void uhcTasks(Player player) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        // after 5 minutes, create the border;
        BukkitTask task3 = new BukkitRunnable() {
            @Override
            public void run() {
                if (gameEnabled) {
                    BorderUtil.startBorder(2000);
                    gracePeriod = false;
                }
            }
        }.runTaskLater(UhCore.getPlugin(), 6000L /*<-- the delay */);

        // after 30 minutes, warn all players about a 0,0 teleportation;
        BukkitTask task2 = new BukkitRunnable() {
            @Override
            public void run() {
                if (gameEnabled) {
                    for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                        players.sendMessage(ChatColor.GOLD + "[*] 30 minutes has passed, thus all players will be teleported to 0, 0 in " + ChatColor.RED + "5 minutes!");
                    }
                }
            }
        }.runTaskLater(UhCore.getPlugin(), 36000L /*<-- the delay */);

        // after 35 minutes, teleport everyone to 0, 0;
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                if (gameEnabled) {
                    Bukkit.dispatchCommand(console, "tp @a 0, ~, 0");
                    for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                        players.teleport(player.getWorld().getHighestBlockAt(player.getLocation()).getLocation().add(0, 1, 0));
                        players.sendMessage(ChatColor.GOLD + "[*] 35 minutes has passed, thus all players have been teleported to 0, 0.");
                    }
                }
            }
        }.runTaskLater(UhCore.getPlugin(), 42000L /*<-- the delay */);
    }

    public static void uhcKits() {
        if (uhcKits) {
            for (String p : workerKit) {
                p = p.replace("[]", "");
                Player equipped = Bukkit.getServer().getPlayer(p);
                if (equipped == null) {
                    continue;
                }
                equipped.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
                equipped.getInventory().addItem(new ItemStack(Material.IRON_AXE));
                equipped.getInventory().addItem(new ItemStack(Material.IRON_SPADE));
                equipped.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 6000, 1));
                equipped.sendMessage(ChatColor.GOLD + "[*] You're using the Worker kit!");
            }
            for (String p : bowKit) {
                p = p.replace("[]", "");
                Player equipped = Bukkit.getServer().getPlayer(p);
                if (equipped == null) {
                    continue;
                }
                equipped.getInventory().addItem(new ItemStack(Material.BOW));
                equipped.getInventory().addItem(new ItemStack(Material.ARROW, 32));
                equipped.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 1));
                equipped.sendMessage(ChatColor.AQUA + "[*] You're using the Archer kit!");
            }
            for (String p : goldMinerKit) {
                p = p.replace("[]", "");
                Player equipped = Bukkit.getServer().getPlayer(p);
                if (equipped == null) {
                    continue;
                }
                equipped.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
                equipped.getInventory().addItem(new ItemStack(Material.GOLD_NUGGET, 81));
                equipped.sendMessage(ChatColor.GOLD + "[*] You're using the Gold Miner ki!");
            }
            for (String p : fisherManKit) {
                p = p.replace("[]", "");
                Player equipped = Bukkit.getServer().getPlayer(p);
                if (equipped == null) {
                    continue;
                }
                ItemStack fishingRod = new ItemStack(Material.FISHING_ROD);
                ItemMeta fishingRodMeta = fishingRod.getItemMeta();
                fishingRodMeta.addEnchant(Enchantment.DURABILITY, 3, true);
                fishingRod.setItemMeta(fishingRodMeta);

                equipped.getInventory().addItem(fishingRod);
                equipped.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 60000, 2));
                equipped.sendMessage(ChatColor.AQUA + "[*] You're using the Fisherman kit!");
            }
            for (String p : enchanterKit) {
                p = p.replace("[]", "");
                Player equipped = Bukkit.getServer().getPlayer(p);
                if (equipped == null) {
                    continue;
                }
                equipped.getInventory().addItem(new ItemStack(Material.LAPIS_BLOCK, 18));
                equipped.getInventory().addItem(new ItemStack(Material.BOOK, 4));
                equipped.getInventory().addItem(new ItemStack(Material.EXP_BOTTLE, 15));
                equipped.sendMessage(ChatColor.AQUA + "[*] You're using the Magician kit!");
            }
        }
    }
}
