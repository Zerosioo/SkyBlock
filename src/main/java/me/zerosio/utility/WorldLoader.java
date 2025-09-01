package me.zerosio.utility;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class WorldLoader {

    private static final Set<String> loadedWorlds = new HashSet<>();

    public static World load(String name) {
        if (loadedWorlds.contains(name)) return Bukkit.getWorld(name);

        File worldFolder = new File(Bukkit.getWorldContainer(), name);
        if (!worldFolder.exists() || !worldFolder.isDirectory()) {
            Logging.print("§7[§eWorldLoader§7] §cWorld not found: §e" + name);
            return null;
        }

        World world = WorldCreator.name(name).createWorld();
        if (world != null) {
            loadedWorlds.add(name);
            Logging.print("§7[§eWorldLoader§7] §aLoaded: §d" + name);
        }

        return world;
    }
}
