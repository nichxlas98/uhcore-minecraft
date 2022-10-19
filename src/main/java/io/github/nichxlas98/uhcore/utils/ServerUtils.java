package io.github.nichxlas98.uhcore.utils;

public class ServerUtils {
    private static boolean MAINTENANCE_MODE = false;
    private static boolean GAME_ENABLED = false;
    private static boolean GAME_MEETUP = false;
    private static boolean GRACE_PERIOD = false;
    private static boolean SPECTATOR_MODE = false;
    private static boolean NO_SWORDS = false;
    private static boolean PEARL_UHC = false;
    private static boolean DOUBLE_HP = false;
    private static boolean DOUBLE_SPEED = false;
    private static boolean CUT_CLEAN = false;
    private static boolean DOUBLE_HEADS = false;
    private static boolean GOLD_RUSH = false;
    private static boolean UHC_KITS = false;
    private static boolean LIFE_STEAL = false;
    private static boolean NO_CLEAN = false;

    private static int graceTime = 10;

    public static void resetGraceTime() {
        graceTime = 10;
    }

    public static void updateGraceTime() {
        graceTime--;
    }

    public static int getGraceTime() {
        return graceTime;
    }


    public static boolean isMaintenanceMode() {
        return MAINTENANCE_MODE;
    }

    public static void setMaintenanceMode(boolean maintenanceMode) {
        MAINTENANCE_MODE = maintenanceMode;
    }

    public static boolean isGameEnabled() {
        return GAME_ENABLED;
    }

    public static void setGameEnabled(boolean gameEnabled) {
        GAME_ENABLED = gameEnabled;
    }

    public static boolean isGameMeetup() {
        return GAME_MEETUP;
    }

    public static void setGameMeetup(boolean gameMeetup) {
        GAME_MEETUP = gameMeetup;
    }

    public static boolean isGracePeriod() {
        return GRACE_PERIOD;
    }

    public static void setGracePeriod(boolean gracePeriod) {
        GRACE_PERIOD = gracePeriod;
    }

    public static boolean isSpectatorMode() {
        return SPECTATOR_MODE;
    }

    public static void setSpectatorMode(boolean spectatorMode) {
        SPECTATOR_MODE = spectatorMode;
    }

    public static boolean isNoSwords() {
        return NO_SWORDS;
    }

    public static void setNoSwords(boolean noSwords) {
        NO_SWORDS = noSwords;
    }

    public static boolean isPearlUhc() {
        return PEARL_UHC;
    }

    public static void setPearlUhc(boolean pearlUhc) {
        PEARL_UHC = pearlUhc;
    }

    public static boolean isDoubleHp() {
        return DOUBLE_HP;
    }

    public static void setDoubleHp(boolean doubleHp) {
        DOUBLE_HP = doubleHp;
    }

    public static boolean isDoubleSpeed() {
        return DOUBLE_SPEED;
    }

    public static void setDoubleSpeed(boolean doubleSpeed) {
        DOUBLE_SPEED = doubleSpeed;
    }

    public static boolean isCutClean() {
        return CUT_CLEAN;
    }

    public static void setCutClean(boolean cutClean) {
        CUT_CLEAN = cutClean;
    }

    public static boolean isDoubleHeads() {
        return DOUBLE_HEADS;
    }

    public static void setDoubleHeads(boolean doubleHeads) {
        DOUBLE_HEADS = doubleHeads;
    }

    public static boolean isGoldRush() {
        return GOLD_RUSH;
    }

    public static void setGoldRush(boolean goldRush) {
        GOLD_RUSH = goldRush;
    }

    public static boolean isUhcKits() {
        return UHC_KITS;
    }

    public static void setUhcKits(boolean uhcKits) {
        UHC_KITS = uhcKits;
    }

    public static boolean isLifeSteal() {
        return LIFE_STEAL;
    }

    public static void setLifeSteal(boolean lifeSteal) {
        LIFE_STEAL = lifeSteal;
    }

    public static boolean isNoClean() {
        return NO_CLEAN;
    }

    public static void setNoClean(boolean noClean) {
        NO_CLEAN = noClean;
    }
}
