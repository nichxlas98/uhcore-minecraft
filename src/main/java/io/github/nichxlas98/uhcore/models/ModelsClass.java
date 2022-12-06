package io.github.nichxlas98.uhcore.models;


import io.github.nichxlas98.uhcore.utils.AdminUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class ModelsClass {

    public static final Random RANDOM = new Random();

    public static final HashMap<UUID, Integer> kitSelected = new HashMap<>();
    public static final int NONE_SELECTED = 0;
    public static final int WORKER_KIT = 1;
    public static final int BOW_KIT = 2;
    public static final int GOLD_MINER_KIT = 3;
    public static final int FISHER_MAN_KIT = 4;
    public static final int ENCHANTER_KIT = 5;
    public static final int JEWELER_KIT = 6;
    public static final ArrayList<Player> playerFrozen = new ArrayList<>();
    public static final ArrayList<Player> adminChat = new ArrayList<>();
    public static final ArrayList<Player> pmsBlocked = new ArrayList<>();
    public static final ArrayList<Player> staffMode = new ArrayList<>();

    public static boolean getChance(int minimalChance) {
        return RANDOM.nextInt(99) + 1 >= minimalChance;
    }

    public static int playerAdminLevel(Player player) {
        return AdminUtil.getAdminLevel(player.getUniqueId());
    }
}
