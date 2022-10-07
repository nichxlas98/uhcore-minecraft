package io.github.nichxlas98.uhcore.models;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.RED;

public class MessageModels {

    public static final String PERMS_ERROR = RED + "[*] You do not have permission to do this!";
    public static final String CONSOLE_ERROR = RED + "[*] You cannot do this from the console.";

    public static boolean senderConsoleError(CommandSender sender) {
        if (!(sender instanceof Player)) {
            System.out.println(CONSOLE_ERROR);
            return true;
        }
        return false;
    }
}
