package io.github.nichxlas98.uhcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.models.MessageModels.*;
import static io.github.nichxlas98.uhcore.models.ModelsClass.playerAdminLevel;
import static io.github.nichxlas98.uhcore.utils.AdminlevelUtil.LOW_ADMIN_LEVEL;
import static io.github.nichxlas98.uhcore.utils.AdminlevelUtil.MIN_ADMIN_LEVEL;
import static org.bukkit.ChatColor.*;

public class AdminCommand implements CommandExecutor {

    private boolean isSpectating = false;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (senderConsoleError(sender)) return true;
        Player player = (Player) sender;
        Location playerLocation = null;

        if (!(playerAdminLevel(player) >= MIN_ADMIN_LEVEL)) {
            player.sendMessage(PERMS_ERROR);
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(RED + "[*] You can use:");
            player.sendMessage(RED + "[*] " + GOLD + "/a <gmc/gms>");
            player.sendMessage(RED + "[*] " + GOLD + "/a <spec> <target>");
            player.sendMessage(RED + "[*] " + GOLD + "/a <off> - Toggles off player spectating.");
            player.sendMessage(RED + "[*] " + GOLD + "/a <broadcast> <message>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "gmc":
                if (!(playerAdminLevel(player) > LOW_ADMIN_LEVEL)) {
                    player.sendMessage(PERMS_ERROR);
                    return true;
                }

                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(GOLD + "[*] Your gamemode has been altered. " + RED + "(CREATIVE)");
                break;
            case "gms":
                if (!(playerAdminLevel(player) > LOW_ADMIN_LEVEL)) {
                    player.sendMessage(PERMS_ERROR);
                    return true;
                }

                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(GOLD + "[*] Your gamemode has been altered. " + RED + "(SURVIVAL)");
                break;
            case "spec":
                if (args.length < 2) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(GOLD + "[*] Your gamemode has been altered. " + RED + "(SPECTATOR)");
                    return true;
                }

                Player target = Bukkit.getServer().getPlayer(args[1]);
                if (target == null) return true;

                isSpectating = true;
                playerLocation = player.getLocation();
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(GOLD + "[*] You're now spectating " + RED + target.getName());
                break;
            case "broadcast":
                if (!(playerAdminLevel(player) > LOW_ADMIN_LEVEL)) {
                    player.sendMessage(PERMS_ERROR);
                    return true;
                }

                StringBuilder sm = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    String arg = (args[i] + " ");
                    sm.append(arg);
                }

                if (args.length == 1) {
                    player.sendMessage(RED + "[*] You need to use /a broadcast <message>");
                    return true;
                }

                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(GRAY + "[" + GOLD + "UhCore98" + GRAY + "] " + RED + sm);
                }
                break;
            default:
                player.sendMessage(RED + "[*] You need to use: /a <gmc/gms/spec> | <heal/feed> (player)");
        }

        if (!(args[0].equalsIgnoreCase("off"))) return true;
        if (isSpectating) {
            isSpectating = false;
            player.teleport(playerLocation);
            player.setGameMode(GameMode.SURVIVAL);
        }
        return true;
    }
}