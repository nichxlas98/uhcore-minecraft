package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.utils.AdminLevelUtil;
import io.github.nichxlas98.uhcore.utils.SpawnUtil;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.utils.AdminLevelUtil.MIN_ADMIN_LEVEL;

public class spawnGotoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        int playerAdminLevel = AdminLevelUtil.getAdminLevel(player.getUniqueId());
        if (playerAdminLevel >= MIN_ADMIN_LEVEL) {
            SpawnUtil.spawnTeleport(player);
        } else {
            player.sendMessage(ChatColor.RED + "[*] You do not have permission to use this command.");
        }
        return true;
    }
}
