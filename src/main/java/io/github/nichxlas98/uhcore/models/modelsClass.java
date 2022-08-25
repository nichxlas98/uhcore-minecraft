package io.github.nichxlas98.uhcore.models;


import java.util.ArrayList;
import java.util.Random;

public class modelsClass {


    public static boolean gameEnabled = false;
    public static boolean gracePeriod = false;
    public static boolean pearlUHC = false;
    public static boolean doubleHP = false;
    public static boolean doubleSpeed = false;
    public static boolean fastsEnabled = false;
    public static boolean doubleHeads = false;
    public static boolean goldRush = false;
    public static boolean uhcKits = false;
    public static ArrayList<String> workerKit = new ArrayList<>();
    public static ArrayList<String> bowKit = new ArrayList<>();
    public static ArrayList<String> goldMinerKit = new ArrayList<>();
    public static ArrayList<String> fisherManKit = new ArrayList<>();
    public static ArrayList<String> enchanterKit = new ArrayList<>();

    public static boolean getChance(int minimalChance) {
        Random random = new Random();
        return random.nextInt(99) + 1 >= minimalChance;
    }


}
