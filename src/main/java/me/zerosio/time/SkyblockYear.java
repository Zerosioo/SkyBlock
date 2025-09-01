package me.zerosio.time;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SkyblockYear {

    private static final File file = new File("plugins/SkyBlock/data/year.yml");
    private static FileConfiguration config;

    static {
        load();
    }

    private static void load() {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
                config = YamlConfiguration.loadConfiguration(file);
                config.set("year", 1);
                config.save(file);
            } catch (IOException e) {
                Bukkit.getLogger().warning("[SkyblockYear] Failed to create year.yml: " + e.getMessage());
            }
        } else {
            config = YamlConfiguration.loadConfiguration(file);
            if (!config.contains("year")) {
                config.set("year", 1);
                save();
            }
        }
    }

    public static int getCurrentYear() {
        return config.getInt("year", 1);
    }

    public static void setCurrentYear(int year) {
        config.set("year", year);
        save();
    }

    public static void setNextYear() {
        setCurrentYear(getCurrentYear() + 1);
    }

    private static void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            Bukkit.getLogger().warning("[SkyblockYear] Failed to save year.yml: " + e.getMessage());
        }
    }
}
