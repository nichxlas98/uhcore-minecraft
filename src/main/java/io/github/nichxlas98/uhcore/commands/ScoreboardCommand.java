package io.github.nichxlas98.uhcore.commands;

import io.github.nichxlas98.uhcore.models.ScoreHelper;
import io.github.nichxlas98.uhcore.utils.ServerManagerUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.nichxlas98.uhcore.models.MessageModels.PERMS_ERROR;
import static io.github.nichxlas98.uhcore.models.MessageModels.senderConsoleError;
import static io.github.nichxlas98.uhcore.models.ModelsClass.playerAdminLevel;
import static io.github.nichxlas98.uhcore.utils.AdminlevelUtil.MAX_ADMIN_LEVEL;

public class ScoreboardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        senderConsoleError(sender);
        Player player = (Player) sender;

        if (!(playerAdminLevel(player) == MAX_ADMIN_LEVEL)) {
            player.sendMessage(PERMS_ERROR);
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "[*] Please use: /scoreboard <toggle>.");
            return true;
        }

        if (args[0].equalsIgnoreCase("toggle")) {
            if (ServerManagerUtil.checkScoreboard()) {
                player.sendMessage(ChatColor.RED + "[*] UhCoreMC Scoreboard has been disabled.");
                ServerManagerUtil.scoreboardDisable();
                ScoreHelper.removeScore(player);
                for (Player players : Bukkit.getOnlinePlayers()) {
                    ScoreHelper.removeScore(players);
                }
            } else {
                player.sendMessage(ChatColor.AQUA + "[*] UhCoreMC Scoreboard has been enabled.");
                ServerManagerUtil.scoreboardEnable();
                for (Player players : Bukkit.getOnlinePlayers()) {
                    ScoreHelper helper = ScoreHelper.createScore(players);
                    helper.setTitle("    &6UhCore&cMC    ");
                    helper.setSlot(5, "&7&m--------------");
                    helper.setSlot(4, "&c» &fState: &6Waiting");
                    helper.setSlot(3, "&c» &fGrace: &bNone");
                    helper.setSlot(2, "");
                    helper.setSlot(1, "&7&m--------------");
                }
            }
        }
        return true;
    }
}
