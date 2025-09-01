package me.zerosio.mayors;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class LastElectedManager {

    private static File file;
    private static FileConfiguration config;

    public static void initialize(File dataFolder) {
        File dataDir = new File(dataFolder, "data");
        if (!dataDir.exists()) dataDir.mkdirs();

        file = new File(dataDir, "last_elected.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);

        for (MayorEnum mayor : MayorEnum.values()) {
            if (!config.contains(mayor.name())) {
                config.set(mayor.name(), 0);
            }
        }
        save();
    }

    public static void updateLastElected(int year, Mayor... elected) {
        for (Mayor mayor : elected) {
            MayorEnum enumVal = MayorEnum.fromMayor(mayor);
            if (enumVal != null) {
                config.set(enumVal.name(), year);
            }
        }
        save();
    }

    public static int getLastElectedYear(MayorEnum mayor) {
        return config.getInt(mayor.name(), 0);
    }

    public static int getYearsSinceElected(int currentYear, MayorEnum mayor) {
        int lastYear = getLastElectedYear(mayor);
        return (lastYear == 0) ? -1 : currentYear - lastYear;
    }

    public static Map<MayorEnum, Integer> getAllLastElected() {
        Map<MayorEnum, Integer> map = new EnumMap<>(MayorEnum.class);
        for (MayorEnum mayor : MayorEnum.values()) {
            map.put(mayor, getLastElectedYear(mayor));
        }
        return map;
    }

    private static void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
