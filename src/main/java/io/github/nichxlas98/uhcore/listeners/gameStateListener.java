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

    @EventHandler
    public void deathEvent(PlayerRespawnEvent e) {
        Player killed = e.getPlayer();
        Player killer = e.getPlayer().getKiller();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

        for (Player players : Bukkit.getServer().getOnlinePlayers()) {
            //TODO: make a proper null check for if the killer equals null;
            //Check for nearby players, if it finds one, send that player a "you win" thing.

            if (killer == null) return;
            if (!(killer.getGameMode().equals(GameMode.SURVIVAL))) return;
            if (!(players.getGameMode().equals(GameMode.SPECTATOR))) return;

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
                    Bukkit.dispatchCommand(console, "game end");
                }
            }.runTaskLater(UhCore.getPlugin(), 600);
        }
    }
}
