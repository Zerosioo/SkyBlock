package me.zerosio.schematic.devworld;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.*;

public class DevWorld {

    private static final Map<UUID, String> activeDevWorlds = new HashMap<>();

    public static void create(Player player) {
        UUID uuid = player.getUniqueId();
        if (activeDevWorlds.containsKey(uuid)) {
            player.sendMessage("§cYou're already in a dev world.");
            return;
        }

        String worldName = "dev_" + uuid.toString().substring(0, 8);
        WorldCreator creator = new WorldCreator(worldName);
        creator.generator(new EmptyChunkGenerator());
        creator.environment(World.Environment.NORMAL);
        creator.type(WorldType.FLAT);
        World world = creator.createWorld();

        Location spawn = new Location(world, 0.5, 0, 0.5);
        player.teleport(spawn);
        player.setAllowFlight(true);
        player.setFlying(true);

        activeDevWorlds.put(uuid, worldName);
        player.sendMessage("§aTeleported to your blank dev world.");
    }

    public static void handleLeave(Player player) {
        UUID uuid = player.getUniqueId();
        String worldName = activeDevWorlds.remove(uuid);
        if (worldName == null) return;

        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            deleteWorldAsync(world);
        }
    }

    public static boolean isInDevWorld(Player player) {
        return activeDevWorlds.containsKey(player.getUniqueId());
    }

    private static void deleteWorldAsync(World world) {
        String name = world.getName();
        Bukkit.unloadWorld(world, false);

        new BukkitRunnable() {
            @Override
            public void run() {
                deleteDirectory(new File(Bukkit.getWorldContainer(), name));
            }
        }.runTaskAsynchronously(Bukkit.getPluginManager().getPlugin("SkyBlock"));
    }

    private static void deleteDirectory(File path) {
        if (!path.exists()) return;
        File[] files = path.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) deleteDirectory(file);
                else file.delete();
            }
        }
        path.delete();
    }

    private static class EmptyChunkGenerator extends ChunkGenerator {
        @Override
        public byte[] generate(World world, Random random, int x, int z) {
            return new byte[32768];
        }
    }
}
