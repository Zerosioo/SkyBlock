package me.zerosio.garden;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;

import me.zerosio.garden.visitors.build.VisitorManager;
import me.zerosio.schematic.SchematicManager;
import me.zerosio.user.AdminDebug;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GardenAlgorithm {

	private static final Map<UUID, String> loadedGardens = new HashMap<>();

	public static void sendToGarden(Player player) {
		UUID uuid = player.getUniqueId();
		String worldName = "garden_" + uuid;

		File worldFolder = new File(worldName);
		if (!worldFolder.exists()) {
			try {
			createGarden(player.getUniqueId());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		World world = Bukkit.getWorld(worldName);
		if (world == null) {
			WorldCreator creator = new WorldCreator(worldName);
			creator.environment(World.Environment.NORMAL);
			creator.type(WorldType.FLAT);
			creator.generator(new EmptyChunkGenerator());
			creator.generateStructures(false);

			world = creator.createWorld(); // LOADS the world from disk
		}

		if (world == null) {
			player.sendMessage("§cFailed to load your garden.");
			return;
		}

		world.setGameRuleValue("doWeatherCycle", "false");
		world.setGameRuleValue("doFireTick", "false");
		world.setGameRuleValue("doMobSpawning", "false");
		world.setGameRuleValue("doMobLoot", "false");
		world.setGameRuleValue("doTileDrops", "false");
		world.setGameRuleValue("naturalRegeneration", "false");
		world.setGameRuleValue("showDeathMessages", "false");
		world.setGameRuleValue("randomTickSpeed", "0");
		world.setGameRuleValue("announceAdvancements", "false");

		loadedGardens.put(uuid, worldName);
		player.teleport(new Location(world, 50, 77, 50, -180f, 0f));
		VisitorManager.spawnAllVisitors(player, 100);
	}

	public static void unloadGardenIfEmpty(UUID uuid) {
		String worldName = loadedGardens.get(uuid);
		if (worldName == null) return;
		
		VisitorManager.despawnAllVisitors(Bukkit.getPlayer(uuid));
		World world = Bukkit.getWorld(worldName);
		if (world == null || !world.getPlayers().isEmpty()) return;

		Bukkit.unloadWorld(world, true);
		loadedGardens.remove(uuid);
	}

	public static boolean isInOwnGarden(Player player) {
		String worldName = loadedGardens.get(player.getUniqueId());
		return player.getWorld().getName().equals(worldName);
	}

	public static void createGarden(UUID uuid) throws IOException {
		String worldName = "garden_" + uuid;
		File worldFolder = new File(worldName);

		if (worldFolder.exists()) return;

		WorldCreator creator = new WorldCreator(worldName);
		creator.environment(World.Environment.NORMAL);
		creator.type(WorldType.FLAT);
		creator.generator(new EmptyChunkGenerator());
		creator.generateStructures(false);

		World world = creator.createWorld();
		if (world == null) return;

		Player player = Bukkit.getPlayer(uuid);

		// paste
		long totalStart = System.currentTimeMillis();

		AdminDebug.debug(player, "Starting paste service for §egarden_base");

      // garden_base
		long start = System.currentTimeMillis();
		SchematicManager.pasteSchematic("garden_base", new Location(world, 0, 70, 0), 0);
		long end = System.currentTimeMillis();
		AdminDebug.debug(player, "Pasted service §egarden_base §fin §b" + (end - start) + " ms");

      // Plot 1
		String plot1 = Plots.getRandomPlot();
		int rot1 = Plots.getRandomRotation();
		start = System.currentTimeMillis();
		SchematicManager.pasteSchematic(plot1, new Location(world, 0, 70, 96), rot1);
		end = System.currentTimeMillis();
		AdminDebug.debug(player, "Pasted service §e" + plot1 + " §fin §b" + (end - start) + " ms");

      // Plot 2
		String plot2 = Plots.getRandomPlot();
		int rot2 = Plots.getRandomRotation();
		start = System.currentTimeMillis();
		SchematicManager.pasteSchematic(plot2, new Location(world, 0, 70, -96), rot2);
		end = System.currentTimeMillis();
		AdminDebug.debug(player, "Pasted service §e" + plot2 + " §fin §b" + (end - start) + " ms");

      // Plot 3
		String plot3 = Plots.getRandomPlot();
		int rot3 = Plots.getRandomRotation();
		start = System.currentTimeMillis();
		SchematicManager.pasteSchematic(plot3, new Location(world, -96, 70, 0), rot3);
		end = System.currentTimeMillis();
		AdminDebug.debug(player, "Pasted service §e" + plot3 + " §fin §b" + (end - start) + " ms");

      // Plot 4
		String plot4 = Plots.getRandomPlot();
		int rot4 = Plots.getRandomRotation();
		start = System.currentTimeMillis();
		SchematicManager.pasteSchematic(plot4, new Location(world, 96, 70, 0), rot4);
		end = System.currentTimeMillis();
		AdminDebug.debug(player, "Pasted service §e" + plot4 + " §fin §b" + (end - start) + " ms");

		long totalEnd = System.currentTimeMillis();
		long totalTime = totalEnd - totalStart;

		AdminDebug.debug(player, "§aCompleted §fpaste service for §c§lGARDEN §r§fin §b" + totalTime + " ms");



		world.save();
		Bukkit.unloadWorld(world, true);
	}

	public static void deleteGarden(UUID uuid) {
		File liveFolder = new File("garden_" + uuid);
		if (liveFolder.exists()) {
			deleteFolder(liveFolder);
		}

		loadedGardens.remove(uuid);
	}

	private static void deleteFolder(File folder) {
		if (!folder.exists()) return;
		File[] files = folder.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) deleteFolder(f);
				else f.delete();
			}
		}
		folder.delete();
	}

	public static class EmptyChunkGenerator extends ChunkGenerator {
		@Override
		public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
			return createChunkData(world);
		}
	}
}
