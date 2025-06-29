package dev.zerosio.warp;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import dev.zerosio.chat.ClickActionType;
import dev.zerosio.chat.MessageUtil;
import dev.zerosio.instance.InstanceID;
import dev.zerosio.user.User;
import dev.zerosio.utility.WorldLoader;

import java.util.Arrays;
import java.util.HashMap;

public class WarpManager {
	private static final HashMap<String, WarpData> warpMap = new HashMap<>();

	public static void registerWarp(String id, WarpType type, Location loc) {
		warpMap.put(id.toLowerCase(), new WarpData(id, type, loc));
	}

	public static boolean warpPlayer(Player player, String id) {
		WarpData warp = warpMap.get(id.toLowerCase());
		User user = User.getUser(player);
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
			player.sendMessage("");
			player.sendMessage("§aYou are playing on profile: §e" + user.getProfileData("name"));
			MessageUtil.sendClickableMessage(player, "§8Profile ID: " + user.getActiveProfileId(), ClickActionType.COPY_TO_CLIPBOARD, user.getActiveProfileId().toString(),
											 Arrays.asList("§8Click to copy to clipboard!"));
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
