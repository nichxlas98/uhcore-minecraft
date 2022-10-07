package io.github.nichxlas98.uhcore.models;


import io.github.nichxlas98.uhcore.utils.AdminlevelUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class ModelsClass {

    public static boolean MAINTENANCE_MODE = false;
    public static boolean GAME_ENABLED = false;
    public static boolean GAME_MEETUP = false;
    public static boolean GRACE_PERIOD = false;
    public static boolean SPECTATOR_MODE = false;
    public static boolean NO_SWORDS = false;
    public static boolean PEARL_UHC = false;
    public static boolean DOUBLE_HP = false;
    public static boolean DOUBLE_SPEED = false;
    public static boolean CUT_CLEAN = false;
    public static boolean DOUBLE_HEADS = false;
    public static boolean GOLD_RUSH = false;
    public static boolean UHC_KITS = false;
    public static boolean LIFE_STEAL = false;
    public static boolean NO_CLEAN = false;

    public static final HashMap<UUID, Integer> kitSelected = new HashMap<>();
    public static final int NONE_SELECTED = 0;
    public static final int WORKER_KIT = 1;
    public static final int BOW_KIT = 2;
    public static final int GOLD_MINER_KIT = 3;
    public static final int FISHER_MAN_KIT = 4;
    public static final int ENCHANTER_KIT = 5;
    public static final int JEWELER_KIT = 6;

    public static final ArrayList<Player> adminChat = new ArrayList<>();
    public static final ArrayList<Player> pmsBlocked = new ArrayList<>();


    public static boolean getChance(int minimalChance) {
        Random random = new Random();
        return random.nextInt(99) + 1 >= minimalChance;
    }

    public static int playerAdminLevel(Player player) {
        return AdminlevelUtil.getAdminLevel(player.getUniqueId());
    }


}
