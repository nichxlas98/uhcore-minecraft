package io.github.nichxlas98.uhcore.utils;

import io.github.nichxlas98.uhcore.UhCore;
import io.github.nichxlas98.uhcore.models.ScoreHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static io.github.nichxlas98.uhcore.models.ModelsClass.*;

public class GameManagerUtil {

    public static int graceTime = 10;

    public static void gameModifiers(Player player) {
        for (Player players : Bukkit.getServer().getOnlinePlayers()) {
            if (DOUBLE_HP) {
                players.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 1000000, 4, false, false));
                players.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 5, 10));
                players.sendMessage(ChatColor.ITALIC + "[*] Double HP is enabled, all players were given double health.");
            }
            if (DOUBLE_SPEED) {
                players.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false));
                players.sendMessage(ChatColor.ITALIC + "[*] Double Speed is enabled, all players were given Speed II.");
            }
            if (PEARL_UHC) {
                addItem(players, Material.ENDER_PEARL, 3);
                players.sendMessage(ChatColor.ITALIC + "[*] Pearl UHC is enabled, all players were given 3 pearls.");
            }
            if (NO_SWORDS) {
                players.sendMessage(ChatColor.ITALIC + "[*] No Swords is enabled, all players are denied from using swords!");
            }
            if (AdminlevelUtil.getAdminLevel(players.getUniqueId()) == 0) {
                players.setGameMode(GameMode.SURVIVAL);
            }
            players.sendMessage(ChatColor.GREEN + "[*] " + ChatColor.AQUA + player.getName() + ChatColor.GREEN + " has started the game.");
        }
    }

    public static void gameTasks(Player player) {
        // after 5 minutes, create the border;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (GAME_ENABLED) {
                    BorderUtil.startBorder(2000);
                    GRACE_PERIOD = false;
                } else {
                    this.cancel();
                }
            }
        }.runTaskLater(UhCore.getPlugin(), 6000L /*<-- the delay */);

        // after 30 minutes, warn all players about a 0,0 teleportation;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (GAME_ENABLED) {
                    for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                        players.sendMessage(ChatColor.GOLD + "[*] 30 minutes has passed, thus all players will be teleported to 0, 0 in " + ChatColor.RED + "5 minutes!");
                    }
                } else {
                    this.cancel();
                }
            }
        }.runTaskLater(UhCore.getPlugin(), 36000L /*<-- the delay */);

        // after 35 minutes, teleport everyone to 0, 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (GAME_ENABLED) {
                    for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                        GAME_MEETUP = true;
                        players.teleport(Bukkit.getWorld("world").getHighestBlockAt(player.getLocation()).getLocation().add(0, 1, 0));
                        players.sendMessage(ChatColor.GOLD + "[*] 35 minutes has passed, thus all players have been teleported to 0, 0.");
                    }
                } else {
                    GAME_MEETUP = false;
                    this.cancel();
                }
            }
        }.runTaskLater(UhCore.getPlugin(), 42000L /*<-- the delay */);
    }

    public static void gameKits() {
        if (UHC_KITS) {
            for (Player equipped : Bukkit.getServer().getOnlinePlayers()) {
                switch (kitSelected.get(equipped.getUniqueId())) {

                    case 1:
                        addItem(equipped, Material.IRON_PICKAXE, 1);
                        addItem(equipped, Material.IRON_AXE, 1);
                        addItem(equipped, Material.IRON_SPADE, 1);
                        equipped.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 6000, 1));
                        equipped.sendMessage(ChatColor.GOLD + "[*] You're using the Worker kit!");
                        break;
                    case 2:
                        addItem(equipped, Material.BOW, 1);
                        addItem(equipped, Material.ARROW, 32);
                        equipped.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 1));
                        equipped.sendMessage(ChatColor.AQUA + "[*] You're using the Archer kit!");
                        break;
                    case 3:
                        addItem(equipped, Material.GOLDEN_APPLE, 2);
                        addItem(equipped, Material.GOLD_NUGGET, 81);
                        equipped.sendMessage(ChatColor.GOLD + "[*] You're using the Gold Miner ki!");
                        break;
                    case 4:
                        ItemStack fishingRod = new ItemStack(Material.FISHING_ROD);
                        ItemMeta fishingRodMeta = fishingRod.getItemMeta();
                        fishingRodMeta.addEnchant(Enchantment.DURABILITY, 3, true);
                        fishingRod.setItemMeta(fishingRodMeta);

                        equipped.getInventory().addItem(fishingRod);
                        equipped.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 60000, 2));
                        equipped.sendMessage(ChatColor.AQUA + "[*] You're using the Fisherman kit!");
                        break;
                    case 5:
                        addItem(equipped, Material.LAPIS_BLOCK, 18);
                        addItem(equipped, Material.BOOK, 4);
                        addItem(equipped, Material.EXP_BOTTLE, 15);
                        equipped.sendMessage(ChatColor.AQUA + "[*] You're using the Magician kit!");
                        break;
                    case 6:
                        addItem(equipped, Material.DIAMOND, 2);
                        addItem(equipped, Material.DIAMOND_SPADE, 1);
                        equipped.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 6000, 1));
                        equipped.sendMessage(ChatColor.AQUA + "[*] You're using the Jeweler kit!");
                        break;
                    default:
                        equipped.sendMessage(ChatColor.RED + "[*] You have not selected a kit.");
                }
            }
        }
    }

    private static void addItem(Player player, Material material, int amount) {
        player.getInventory().addItem(new ItemStack(material, amount));
    }

    public static void gameScoreboard() {
        int ticks = 20 * 60;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (ServerManagerUtil.checkScoreboard()) {
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (GAME_ENABLED) {
                            graceTime--;
                            if (ScoreHelper.hasScore(players)) {
                                ScoreHelper helper = ScoreHelper.getByPlayer(players);
                                helper.setTitle("    &6UhCore&cMC    ");
                                helper.setSlot(5, "&7&m--------------");
                                helper.setSlot(4, "&c» &fState: &6Running");
                                helper.setSlot(3, "&c» &fGrace: &b" + graceTime + " mins");
                                helper.setSlot(2, "");
                                helper.setSlot(1, "&7&m--------------");
                            }

                            if (graceTime == 0) {
                                if (ScoreHelper.hasScore(players)) {
                                    ScoreHelper helper = ScoreHelper.getByPlayer(players);
                                    helper.setTitle("    &6UhCore&cMC    ");
                                    helper.setSlot(5, "&7&m--------------");
                                    helper.setSlot(4, "&c» &fState: &6Running");
                                    helper.setSlot(3, "&c» &fGrace: &bEnded");
                                    helper.setSlot(2, "");
                                    helper.setSlot(1, "&7&m--------------");
                                    this.cancel();
                                }
                            }
                        } else {
                            ScoreHelper helper = ScoreHelper.getByPlayer(players);
                            helper.setTitle("    &6UhCore&cMC    ");
                            helper.setSlot(5, "&7&m--------------");
                            helper.setSlot(4, "&c» &fState: &6Waiting");
                            helper.setSlot(3, "&c» &fGrace: &bNone");
                            helper.setSlot(2, "");
                            helper.setSlot(1, "&7&m--------------");
                            this.cancel();
                        }
                    }
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(UhCore.getPlugin(), 0,ticks);
    }
}
