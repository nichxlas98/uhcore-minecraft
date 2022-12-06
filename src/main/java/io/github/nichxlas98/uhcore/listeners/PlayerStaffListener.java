package io.github.nichxlas98.uhcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

import static io.github.nichxlas98.uhcore.items.ItemManager.STAFF_WOOLOFF;
import static io.github.nichxlas98.uhcore.items.ItemManager.STAFF_WOOLON;
import static io.github.nichxlas98.uhcore.models.ModelsClass.*;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.MIN_ADMIN_LEVEL;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.getAdminLevel;

public class PlayerStaffListener implements Listener {

    ArrayList<Player> playerList = new ArrayList<>();
    ArrayList<Player> playerVanished = new ArrayList<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (playerAdminLevel(event.getPlayer()) > MIN_ADMIN_LEVEL) return;
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (!(playerVanished.contains(online))) continue;
            event.getPlayer().hidePlayer(online);
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if (!(event instanceof Player)) return;

        if (staffMode.contains(event.getPlayer())) event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockBreakEvent event) {
        if (!(event instanceof Player)) return;

        if (staffMode.contains(event.getPlayer())) event.setCancelled(true);
    }

    @EventHandler
    public void playerDamageByPlayer(EntityDamageByEntityEvent event) {
        if (!(event instanceof Player)) return;

        Player player = ((Player) event).getPlayer();
        if (staffMode.contains(player)) event.setCancelled(true);
        player.sendMessage(ChatColor.RED + "[*] You cannot damage players in staff mode.");
    }

    @EventHandler
    public void playerRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if ((e.getAction() != Action.RIGHT_CLICK_AIR) || (e.getItem() == null) || (e.getAction() != Action.RIGHT_CLICK_BLOCK))
            return;
        if (e.getItem().getItemMeta() == null) return;
        if (!(playerAdminLevel(player) >= MIN_ADMIN_LEVEL)) return;
        switch (e.getItem().getItemMeta().getDisplayName()) {
            case "Staff Compass":

                break;
            case "Teleport Rod":
                playerList.clear();
                playerList.addAll(Bukkit.getServer().getOnlinePlayers());
                player.teleport(playerList.get(RANDOM.nextInt()).getLocation());
                break;
            case "Online Staff":
                Inventory gui = Bukkit.createInventory(player, 18, ChatColor.RED + "Server Admins");
                for (Player admins : Bukkit.getServer().getOnlinePlayers()) {
                    if (getAdminLevel(admins.getUniqueId()) < MIN_ADMIN_LEVEL) continue;
                    String name = admins.getPlayer().getName();
                    ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                    SkullMeta meta = (SkullMeta) item.getItemMeta();
                    meta.setDisplayName(name);
                    meta.setOwner(admins.getName());
                    item.setItemMeta(meta);
                    gui.addItem(item);
                }
                player.openInventory(gui);
                break;
            case "Enable Vanish":
                playerVanished.add(player);
                for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                    if (playerAdminLevel(online) > MIN_ADMIN_LEVEL) continue;
                    online.hidePlayer(player);
                }

                player.getInventory().setItem(8, STAFF_WOOLOFF);
                break;
            case "Disable Vanish":
                playerVanished.remove(player);
                for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                    if (playerAdminLevel(online) > MIN_ADMIN_LEVEL) continue;
                    online.showPlayer(player);
                }
                player.getInventory().setItem(8, STAFF_WOOLON);
                break;
            default:
                //
        }

    }

    @EventHandler
    public void playerLeftClickPlayer(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() != EntityType.PLAYER) return;
        Player playerClicked = (Player) event.getRightClicked();
        Player player = event.getPlayer();

        if (event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getItemMeta() != null)
            return;
        ItemMeta itemMeta = event.getPlayer().getItemInHand().getItemMeta();
        if (!(playerAdminLevel(player) >= MIN_ADMIN_LEVEL)) return;
        if (itemMeta.getDisplayName().contains("Freeze Block")) {

            if (playerFrozen.contains(playerClicked)) {
                playerFrozen.remove(playerClicked);
                playerClicked.sendMessage(ChatColor.GREEN + "[*] You're no longer frozen.");
                player.sendMessage(ChatColor.AQUA + "[*] You've unfrozen " + playerClicked.getDisplayName());
                return;
            }

            playerFrozen.add(playerClicked);
            playerClicked.sendMessage(ChatColor.RED + "[*] You've been frozen.");
            player.sendMessage(ChatColor.AQUA + "[*] You've frozen " + playerClicked.getDisplayName());
            return;
        }

        if (itemMeta.getDisplayName().contains("Inventory Book")) {
            Inventory gui = Bukkit.createInventory(player, 36, ChatColor.RED + "Player Inventory");
            gui.setContents(playerClicked.getInventory().getContents());
            player.openInventory(gui);
            //TODO: Organize ^
        }
    }
}
