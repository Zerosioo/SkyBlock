package me.zerosio.warp;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.zerosio.utility.WorldLoader;

public class Warps {
	public static void register() {
		WorldLoader.load("crystal_hollows");
		WorldLoader.load("dwarven_mines");
		WorldLoader.load("island_of_zerosio");
		WorldLoader.load("kuudra");
		
		WarpManager.registerWarp("hub", WarpType.HUB, new Location(Bukkit.getWorld("world"), 0.5, 75, 0.5));
        WarpManager.registerWarp("is", WarpType.ISLAND, new Location(Bukkit.getWorld("island_of_zerosio"), 2.5, 70, -3.5));
        WarpManager.registerWarp("ch", WarpType.ISLAND, Bukkit.getWorld("crystal_hollows").getSpawnLocation());
        WarpManager.registerWarp("dw", WarpType.ISLAND, Bukkit.getWorld("dwarven_mines").getSpawnLocation());
        WarpManager.registerWarp("kuudra", WarpType.DUNGEON, Bukkit.getWorld("kuudra").getSpawnLocation());
	}
}