package dev.zerosio.warp;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import dev.zerosio.utility.WorldLoader;

public class Warps {
	public static void register() {
		WorldLoader.load("island_of_zerosio");
		
		WarpManager.registerWarp("hub", WarpType.HUB, new Location(Bukkit.getWorld("world"), 0.5, 75, 0.5));
        WarpManager.registerWarp("is", WarpType.ISLAND, new Location(Bukkit.getWorld("island_of_zerosio"), 2.5, 70, -3.5));
	}
}