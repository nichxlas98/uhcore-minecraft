package io.github.nichxlas98.uhcore.listeners.modifiers;

import org.apache.commons.lang.math.RandomUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import static io.github.nichxlas98.uhcore.utils.ServerUtils.isPotUhc;

public class PotModifier implements Listener {

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event) {
        if (!isPotUhc()) return;
        int randomPotDrop = RandomUtils.nextInt(100);
        int randomSpeed = RandomUtils.nextInt(100);

        Block blockBroken = event.getBlock();
        Material blockType = blockBroken.getType();
        World blockWorld = blockBroken.getWorld();

        ItemStack healthPot = new ItemStack(Material.POTION, 1, (short) 16421);
        ItemStack speedPot = new ItemStack(Material.POTION, 1, (short) 8226);

        if (blockType != Material.LEAVES && blockType != Material.LEAVES_2) return;
        if (!(randomPotDrop < 50)) return;
        blockBroken.getDrops().clear();
        blockWorld.dropItemNaturally(blockBroken.getLocation(), healthPot);
        if (!(randomSpeed < 10)) return;
        blockWorld.dropItemNaturally(blockBroken.getLocation(), speedPot);
    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event) {
        if (!isPotUhc()) return;
        Player player = event.getEntity().getPlayer();
        Player killer = player.getKiller();
        killer.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 3));
        killer.sendMessage(ChatColor.RED + "[*] You've been given three EnderPearls for your kill on " + player.getName());
    }
}
