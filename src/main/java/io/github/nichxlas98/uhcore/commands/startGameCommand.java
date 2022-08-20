package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class startGameCommand implements CommandExecutor {

    public static boolean gameEnabled = false;

    private final UhCore plugin;

    public startGameCommand(UhCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Player player = (Player) sender;

            if (!gameEnabled) {
                gameEnabled = true;
                Bukkit.dispatchCommand(console, "spreadplayers 0 0 150 2000 false @a");
                for (Player players : Bukkit.getServer().getOnlinePlayers())  {
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

        }

        return true;
    }
}
