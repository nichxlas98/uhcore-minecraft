package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.models.scoreHelper;
import io.github.nichxlas98.uhcore.utils.adminLevelUtil;
import io.github.nichxlas98.uhcore.utils.serverManagerUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class scoreboardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (adminLevelUtil.getAdminLevel(player.getUniqueId()) == 4) {

                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "[*] Please use: /scoreboard <toggle>.");
                    return true;
                }

                if (args[0].equalsIgnoreCase("toggle")) {
                    if (serverManagerUtil.checkScoreboard()) {
                        player.sendMessage(ChatColor.RED + "[*] UhCoreMC Scoreboard has been disabled.");
                        serverManagerUtil.scoreboardDisable();
                        scoreHelper.removeScore(player);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            scoreHelper.removeScore(players);
                        }
                    } else {
                        player.sendMessage(ChatColor.AQUA + "[*] UhCoreMC Scoreboard has been enabled.");
                        serverManagerUtil.scoreboardEnable();
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            scoreHelper helper = scoreHelper.createScore(players);
                            helper.setTitle("    &6UhCore&cMC    ");
                            helper.setSlot(5, "&7&m--------------");
                            helper.setSlot(4, "&c» &fState: &6Waiting");
                            helper.setSlot(3, "&c» &fGrace: &bNone");
                            helper.setSlot(2, "");
                            helper.setSlot(1, "&7&m--------------");
                        }
                    }

                }
            } else {
                player.sendMessage(ChatColor.RED + "[*] You do not have permission to do this..");
            }
        }
        return true;
    }
}
