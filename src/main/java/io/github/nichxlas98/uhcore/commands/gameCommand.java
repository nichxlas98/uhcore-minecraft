package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.UhCore;
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
            Player player = (Player) sender;
            String playerName = player.getName();

            switch(args[0].toLowerCase()) {
                case "start":
                    if (!gameEnabled) {
                        gameEnabled = true;
                        Bukkit.dispatchCommand(console, "spreadplayers 0 0 150 2000 false @a");
                        for (Player players : Bukkit.getServer().getOnlinePlayers())  {

                            if (uhcKits) {
                                if (workerKit.contains(playerName)) {
                                    player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
                                    player.getInventory().addItem(new ItemStack(Material.IRON_AXE));
                                    player.getInventory().addItem(new ItemStack(Material.IRON_SPADE));
                                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 6000, 1));
                                    player.sendMessage(ChatColor.GOLD + "[*] You're using the Worker kit!");
                                }

                                if (bowKit.contains(playerName)) {
                                    player.getInventory().addItem(new ItemStack(Material.BOW));
                                    player.getInventory().addItem(new ItemStack(Material.ARROW, 32));
                                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 1));
                                    player.sendMessage(ChatColor.AQUA + "[*] You're using the Archer kit!");
                                }


                                if (goldMinerKit.contains(playerName)) {
                                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
                                    player.getInventory().addItem(new ItemStack(Material.GOLD_NUGGET, 81));
                                    player.sendMessage(ChatColor.GOLD + "[*] You're using the Gold Miner ki!");
                                }



                                if (fisherManKit.contains(playerName)) {
                                    ItemStack fishingRod = new ItemStack(Material.FISHING_ROD);
                                    ItemMeta fishingRodMeta = fishingRod.getItemMeta();
                                    fishingRodMeta.addEnchant(Enchantment.DURABILITY, 3, true);
                                    fishingRod.setItemMeta(fishingRodMeta);


                                    player.getInventory().addItem(fishingRod);
                                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 60000, 2));
                                    player.sendMessage(ChatColor.AQUA + "[*] You're using the Fisherman kit!");
                                }

                                if (enchanterKit.contains(playerName)) {
                                    player.getInventory().addItem(new ItemStack(Material.LAPIS_BLOCK, 18));
                                    player.getInventory().addItem(new ItemStack(Material.BOOK, 4));
                                    player.getInventory().addItem(new ItemStack(Material.EXP_BOTTLE, 15));
                                    player.sendMessage(ChatColor.AQUA + "[*] You're using the Magician kit!");
                                }

                            }


                            if (doubleHP) {
                                players.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 1000000, 4, false, false));
                                players.sendMessage(ChatColor.ITALIC + "[*] Double HP is enabled, all players were given double health.");
                            }

                            if (doubleSpeed) {
                                players.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1, 1000000, false, false));
                                players.sendMessage(ChatColor.ITALIC + "[*] Double Speed is enabled, all players were given Speed II.");
                            }

                            players.setGameMode(GameMode.SURVIVAL);
                            players.sendMessage(ChatColor.GREEN + "[*] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GREEN + " has started the game.");
                        }

                        BukkitTask task3 = new BukkitRunnable() {
                            @Override
                            public void run() {
                                for (Player players : Bukkit.getServer().getOnlinePlayers())  {
                                    players.sendMessage(ChatColor.GOLD + "[*] The border will start shrinking in " + ChatColor.RED + "5 minutes!");
                                }
                                Bukkit.dispatchCommand(console, "cb");
                            }
                        }.runTaskLater(plugin, 6000L /*<-- the delay */);

                        BukkitTask task2 = new BukkitRunnable() {
                            @Override
                            public void run() {
                                for (Player players : Bukkit.getServer().getOnlinePlayers())  {
                                    players.sendMessage(ChatColor.GOLD + "[*] An hour has passed, thus all players will be teleported to 0, 0 in " + ChatColor.RED + "5 minutes!");
                                }
                            }
                        }.runTaskLater(plugin, 60000L /*<-- the delay */);


                        BukkitTask task = new BukkitRunnable() {
                            @Override
                            public void run() {
                                Bukkit.dispatchCommand(console, "tp @a 0, ~, 0");
                                for (Player players : Bukkit.getServer().getOnlinePlayers())  {
                                    players.teleport(player.getWorld().getHighestBlockAt(player.getLocation()).getLocation().add(0, 1, 0));
                                    players.sendMessage(ChatColor.GOLD + "[*] An hour has passed, thus all players will be teleported to 0, 0.");
                                }
                            }
                        }.runTaskLater(plugin, 72000L /*<-- the delay */);
                    } else {
                        player.sendMessage(ChatColor.RED + "[*] The game is already started.");
                    }
                    break;
                case "end":
                    if(gameEnabled) {
                        gameEnabled = false;
                        player.sendMessage(ChatColor.RED + "[*] You've forcefully ended the game.");
                        for (Player players : Bukkit.getServer().getOnlinePlayers())  {
                            players.sendMessage(ChatColor.RED + "[*] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.RED + " has forcefully ended the game.");
                            players.setGameMode(GameMode.SURVIVAL);

                            if(plugin.getConfig().getString("spawn.world") != null){
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

        }

        return true;
    }
}
