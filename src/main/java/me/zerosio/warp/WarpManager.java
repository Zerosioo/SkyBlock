package me.zerosio.warp;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.zerosio.instance.InstanceID;
import me.zerosio.user.User;

import java.util.HashMap;

public class WarpManager {
	private static final HashMap<String, WarpData> warpMap = new HashMap<>();

	public static void registerWarp(String id, WarpType type, Location loc) {
		warpMap.put(id.toLowerCase(), new WarpData(id, type, loc));
	}

	public static boolean warpPlayer(Player player, String id) {
		WarpData warp = warpMap.get(id.toLowerCase());
		if (warp == null) return false;
		try {

			if (player.getLocation().getWorld() == warp.getLocation().getWorld()) {
				player.sendMessage("§7Warping...");
				player.teleport(warp.getLocation());
				player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 0f, 1f);
			} else {
				player.sendMessage("§7Warping...");
				player.sendMessage("§7Sending to server " + InstanceID.getName(warp.getLocation().getWorld()) + "...");
				player.teleport(warp.getLocation());
				player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 0f, 1f);
			}
			return true;
		} catch (Exception e) {
			player.sendMessage("§cCouldn't warp you! Try again later. §7(DYNAMIC_POOL_ERROR)");
			return true;
		}
	}

	public static boolean isWarpExist(String id) {
		return warpMap.containsKey(id.toLowerCase());
	}

	public static void unregisterWarp(String id) {
		warpMap.remove(id.toLowerCase());
	}
}
