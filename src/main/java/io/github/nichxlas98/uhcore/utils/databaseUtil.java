package io.github.nichxlas98.uhcore.utils;
import io.github.nichxlas98.uhcore.UhCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class databaseUtil {

    public static File yml = new File(UhCore.getPlugin().getDataFolder()+"/playerData.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(yml);

    public static void saveCustomData(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
