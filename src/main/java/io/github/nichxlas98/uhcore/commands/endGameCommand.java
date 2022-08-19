package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.commands.startGameCommand.gameEnabled;

public class endGameCommand implements CommandExecutor {

    private final UhCore plugin;

    public endGameCommand(UhCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(gameEnabled) {
                gameEnabled = false;
                player.sendMessage(ChatColor.RED + "[*] You've forcefully ended the game.");
                for (Player players : Bukkit.getServer().getOnlinePlayers())  {
                    players.sendMessage(ChatColor.RED + "[*] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.RED + " has forcefully ended the game.");
                    if(plugin.getConfig().getString("spawn.world") != null) {
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
                }



            } else {
                player.sendMessage(ChatColor.RED + "[*] There is no game currently running.");
            }

        }
        return true;
    }
}
