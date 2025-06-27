package dev.zerosio.instance;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.HashMap;
import java.util.Random;

public class InstanceID {

    private static final HashMap<World, String> worldNames = new HashMap<>();
    private static final char[] LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final Random random = new Random();

    public enum ServerType {
        MINI("mini"),
        MEGA("mega"),
        M1("m"),
        M2("M"),
        UNKNOWN("server");

        private final String prefix;

        ServerType(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }

        public static ServerType fromWorldName(String name) {
            name = name.toLowerCase();

            if (name.startsWith("island")) return MINI;
            if (name.startsWith("hub")) return MEGA;
            if (name.startsWith("world")) return MEGA;
            if (name.startsWith("mini")) return MINI;
            if (name.startsWith("mega")) return MEGA;
            if (name.startsWith("garden")) return MINI;
            if (name.startsWith("dungeon")) return MINI;
            if (name.startsWith("limbo")) return MINI;

            return UNKNOWN;
        }
    }

    public static void register(World world) {
        if (world == null || worldNames.containsKey(world)) return;

        ServerType type = ServerType.fromWorldName(world.getName());
        int num = 10 + random.nextInt(90); // 10–99
        char letter = LETTERS[random.nextInt(LETTERS.length)];

        String finalName = type.getPrefix() + num + letter;
        worldNames.put(world, finalName);
    }

    public static void register(World world, ServerType forcedType) {
        if (world == null || worldNames.containsKey(world)) return;

        int num = 10 + random.nextInt(90);
        char letter = LETTERS[random.nextInt(LETTERS.length)];

        String finalName = forcedType.getPrefix() + num + letter;
        worldNames.put(world, finalName);
    }

    public static void registerAll() {
        for (World world : Bukkit.getWorlds()) {
            register(world);
        }
    }

    public static String getName(World world) {
        return worldNames.getOrDefault(world, "unknown");
    }

    public static String getName(String worldName) {
        World world = Bukkit.getWorld(worldName);
        return world != null ? getName(world) : "unknown";
    }

    public static boolean isRegistered(World world) {
        return worldNames.containsKey(world);
    }

    public static void unregister(World world) {
        worldNames.remove(world);
    }
}
