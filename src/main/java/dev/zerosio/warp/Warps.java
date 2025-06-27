package dev.zerosio.warp;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Warps {
	public static void register() {
		WarpManager.registerWarp("hub", WarpType.HUB, new Location(Bukkit.getWorld("world"), 0.5, 75, 0.5));
        WarpManager.registerWarp("is", WarpType.ISLAND, new Location(Bukkit.getWorld("island"), 2.5, 70, -3.5));
	}
}