package io.github.nichxlas98.uhcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

import static io.github.nichxlas98.uhcore.items.ItemManager.*;
import static io.github.nichxlas98.uhcore.models.ModelsClass.*;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.MIN_ADMIN_LEVEL;
import static io.github.nichxlas98.uhcore.utils.AdminUtil.getAdminLevel;

public class PlayerStaffListener implements Listener {

    ArrayList<Player> playerList = new ArrayList<>();
    public static final ArrayList<Player> playerVanished = new ArrayList<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (playerAdminLevel(event.getPlayer()) > MIN_ADMIN_LEVEL) return;
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (!(playerVanished.contains(online))) continue;
            event.getPlayer().hidePlayer(online);
        }
    }

    @EventHandler
    public void onMobTarget(EntityTargetLivingEntityEvent e) {
        if (!(e.getTarget() instanceof Player)) return;
        Player p = (Player) e.getTarget();
        if (staffMode.contains(p)) {
            e.setCancelled(true);
            e.setTarget(null);
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if (staffMode.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        if (staffMode.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void playerDamageByPlayer(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) return;
        Player player = (Player) event.getDamager();

        if (staffMode.contains(player)) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "[*] You cannot damage entities in staff mode.");
        }
    }

    @EventHandler
    public void playerDamageEvent(EntityDamageEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) return;
        Player entity = (Player) event.getEntity();
        if (!(staffMode.contains(entity))) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void playerRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (player.getItemInHand() == null) return;
        if (!(player.getItemInHand().hasItemMeta())) return;
        if (!(player.getItemInHand().getItemMeta().hasDisplayName())) return;
        if (!(playerAdminLevel(player) >= MIN_ADMIN_LEVEL)) return;

        if (getStaffCompass().isSimilar(player.getItemInHand())) {
            System.out.println("DEBUGGER!!! - Staff Compass right clicked!");
        } else if (getStaffRod().isSimilar(player.getItemInHand())) {
            playerList.clear();
            playerList.addAll(Bukkit.getServer().getOnlinePlayers());
            player.teleport(playerList.get(RANDOM.nextInt(playerList.size())).getLocation());
        } else if (getStaffPaper().isSimilar(player.getItemInHand())) {
            Inventory gui = Bukkit.createInventory(player, 18, ChatColor.RED + "Server Admins");
            for (Player admins : Bukkit.getServer().getOnlinePlayers()) {
                if (getAdminLevel(admins.getUniqueId()) < MIN_ADMIN_LEVEL) continue;
                String name = ChatColor.GOLD + admins.getPlayer().getName();
                ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                SkullMeta meta = (SkullMeta) item.getItemMeta();
                meta.setDisplayName(name);
                meta.setOwner(admins.getName());
                ArrayList<String> itemLore = new ArrayList<>();
                itemLore.add(ChatColor.GRAY + "Admin Level: " + ChatColor.AQUA + getAdminLevel(player.getUniqueId()));
                meta.setLore(itemLore);
                item.setItemMeta(meta);
                gui.addItem(item);
            }
            player.openInventory(gui);
        } else if (getStaffWoolon().isSimilar(player.getItemInHand())) {
            playerVanished.add(player);
            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                if (playerAdminLevel(online) > MIN_ADMIN_LEVEL) continue;
                online.hidePlayer(player);
            }

            player.getInventory().setItem(8, getStaffWooloff());
            player.sendMessage(ChatColor.GRAY + "[*] You're now in Vanish.");
        } else if (getStaffWooloff().isSimilar(player.getItemInHand())) {
            playerVanished.remove(player);
            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                if (playerAdminLevel(online) > MIN_ADMIN_LEVEL) continue;
                online.showPlayer(player);
            }
            player.getInventory().setItem(8, getStaffWoolon());
            player.sendMessage(ChatColor.GREEN + "[*] You're no longer in Vanish.");
        }
    }

    @EventHandler
    public void playerLeftClickPlayer(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() != EntityType.PLAYER) return;
        Player playerClicked = (Player) event.getRightClicked();
        Player player = event.getPlayer();

        if (player.getItemInHand() == null || player.getItemInHand().getItemMeta() == null) return;
        ItemStack itemInHand = event.getPlayer().getItemInHand();
        if (!(playerAdminLevel(player) >= MIN_ADMIN_LEVEL)) return;
        if (getStaffIce().isSimilar(itemInHand)) {

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

        if (getStaffBook().isSimilar(itemInHand)) {
            Inventory gui = Bukkit.createInventory(player, 36, ChatColor.RED + "Player Inventory");
            gui.setContents(playerClicked.getInventory().getContents());
            player.openInventory(gui);
            //TODO: Organize ^
        }
    }
}
