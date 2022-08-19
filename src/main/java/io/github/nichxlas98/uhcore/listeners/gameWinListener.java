package io.github.nichxlas98.uhcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class gameWinListener implements Listener {


    @EventHandler
    public void deathEvent(PlayerRespawnEvent e) {
        Player killed = e.getPlayer();
        Player killer = e.getPlayer().getKiller();
        // On a player death event, if the killer is online > add the killed player to a "Losers" list.
        // If the killer's game-mode is Survival, if all online players are in spectator mode, killer.sendMessage

        for (Player players : Bukkit.getServer().getOnlinePlayers())  {
            if (killer.getGameMode().equals(GameMode.SURVIVAL)) {
                if (players.getGameMode().equals(GameMode.SPECTATOR)) {
                    killer.sendMessage(ChatColor.GREEN + "[*] You win!");
                }

            }

        }


    }

}
