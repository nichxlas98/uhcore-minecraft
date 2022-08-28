package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.UhCore;
import io.github.nichxlas98.uhcore.utils.AdminLevelUtil;
import io.github.nichxlas98.uhcore.utils.BorderUtil;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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

public class gameCommand implements CommandExecutor {

    private final UhCore plugin;

    public gameCommand(UhCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            int size;
            Player player = (Player) sender;
            if (AdminLevelUtil.getAdminLevel(player.getUniqueId()) >= 0) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "[*] You need to use: /game <start/end>");
                    return true;
                }

                switch (args[0].toLowerCase()) {
                    case "start":
                        if (!gameEnabled) {
                            gameEnabled = true;
                            gracePeriod = true;
                            Bukkit.dispatchCommand(console, "spreadplayers 0 0 150 1000 false @a");

                            try {
                                size = Integer.parseInt(args[1]);
                            } catch (Exception e) {
                                player.sendMessage(ChatColor.RED + "[*] Error! You need to specify a a size!");
                                return true;
                            }

                            // after 5 minutes, create the border;
                            BukkitTask task3 = new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (gameEnabled) {
                                        BorderUtil.startBorder(size);
                                        gracePeriod = false;
                                    }
                                }
                            }.runTaskLater(plugin, 6000L /*<-- the delay */);

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
                            }.runTaskLater(plugin, 36000L /*<-- the delay */);

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
                            }.runTaskLater(plugin, 42000L /*<-- the delay */);

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
                                } for (String p : bowKit) {
                                    p = p.replace("[]", "");
                                    Player equipped = Bukkit.getServer().getPlayer(p);
                                    if (equipped == null) {
                                        continue;
                                    }
                                    equipped.getInventory().addItem(new ItemStack(Material.BOW));
                                    equipped.getInventory().addItem(new ItemStack(Material.ARROW, 32));
                                    equipped.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 1));
                                    equipped.sendMessage(ChatColor.AQUA + "[*] You're using the Archer kit!");
                                } for (String p : goldMinerKit) {
                                    p = p.replace("[]", "");
                                    Player equipped = Bukkit.getServer().getPlayer(p);
                                    if (equipped == null) {
                                        continue;
                                    }
                                    equipped.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
                                    equipped.getInventory().addItem(new ItemStack(Material.GOLD_NUGGET, 81));
                                    equipped.sendMessage(ChatColor.GOLD + "[*] You're using the Gold Miner ki!");
                                } for (String p : fisherManKit) {
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
                                } for (String p : enchanterKit) {
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
                            } for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                                if (doubleHP) {
                                    players.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 1000000, 4, false, false));
                                    players.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 5, 10));
                                    players.sendMessage(ChatColor.ITALIC + "[*] Double HP is enabled, all players were given double health.");
                                }
                                if (doubleSpeed) {
                                    players.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false));
                                    players.sendMessage(ChatColor.ITALIC + "[*] Double Speed is enabled, all players were given Speed II.");
                                }
                                if (pearlUHC) {
                                    players.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 3));
                                    players.sendMessage(ChatColor.ITALIC + "[*] Pearl UHC is enabled, all players were given 3 pearls.");
                                }
                                players.setGameMode(GameMode.SURVIVAL);
                                players.sendMessage(ChatColor.GREEN + "[*] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GREEN + " has started the game.");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "[*] The game is already started.");
                        }
                        break;

                    case "end":
                        if (gameEnabled) {
                            gameEnabled = false;
                            gracePeriod = false;

                            WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
                            wb.setCenter(0, 0);
                            wb.setSize(5000);

                            player.sendMessage(ChatColor.RED + "[*] You've forcefully ended the game.");
                            for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                                players.sendMessage(ChatColor.RED + "[*] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.RED + " has forcefully ended the game.");
                                players.setGameMode(GameMode.SURVIVAL);
                                Bukkit.dispatchCommand(console, "clear @a");
                                Bukkit.dispatchCommand(console, "effect @a clear");
                                if (plugin.getConfig().getString("spawn.world") != null) {
                                    World w = Bukkit.getServer().getWorld(plugin.getConfig().getString("spawn.world"));
                                    double x = Double.parseDouble(plugin.getConfig().getString("spawn.x"));
                                    double y = Double.parseDouble(plugin.getConfig().getString("spawn.y"));
                                    double z = Double.parseDouble(plugin.getConfig().getString("spawn.z"));
                                    float yaw = Float.parseFloat(plugin.getConfig().getString("spawn.yaw"));
                                    float pitch = Float.parseFloat(plugin.getConfig().getString("spawn.pitch"));

                                    players.teleport(new Location(w, x, y, z, yaw, pitch));
                                    players.sendMessage(ChatColor.AQUA + "[*] " + "You've been teleported to the server spawn-point.");
                                } else {
                                    System.out.println("There is no spawn point set.");
                                }
                                return true;
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "[*] There is no game currently running.");
                        }
                        break;
                    default:
                        // Invalid args
                        player.sendMessage(ChatColor.RED + "[*] You need to use: /game <start/end>");
                        return true;
                }
            } else {
                player.sendMessage(ChatColor.RED + "[*] You do not have permission to use this command.");
            }
        }
        return true;
    }
}
