package io.github.nichxlas98.uhcore.listeners;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class gameStateListener implements Listener {


    private final UhCore plugin;

    public gameStateListener(UhCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void deathEvent(PlayerRespawnEvent e) {
        Player killed = e.getPlayer();
        Player killer = e.getPlayer().getKiller();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

        for (Player players : Bukkit.getServer().getOnlinePlayers())  {
            //TODO: make a proper null check for if the killer equals null;
            assert killer != null;
            if (killer.getGameMode().equals(GameMode.SURVIVAL)) {
                if (players.getGameMode().equals(GameMode.SPECTATOR)) {
                    killer.sendMessage(ChatColor.GREEN + "[*] You win!");
                    killer.sendTitle(ChatColor.GREEN + "You win!", "");
                    players.sendMessage(ChatColor.RED + killer.getName() + " wins!");
                    players.sendMessage(ChatColor.RED + killer.getName() + " wins!");
                    players.sendMessage(ChatColor.RED + killer.getName() + " wins!");
                    players.sendMessage(ChatColor.RED + killer.getName() + " wins!");
                    players.sendMessage(ChatColor.RED + killer.getName() + " wins!");
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Bukkit.dispatchCommand(console, "endgame");
                        }
                    }.runTaskLater(plugin, 600);
                }
            }
        }
    }
}
