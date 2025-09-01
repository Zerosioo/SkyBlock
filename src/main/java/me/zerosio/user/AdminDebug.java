package me.zerosio.user;

import org.bukkit.entity.Player;

public class AdminDebug {
	public static void debug(Player player, String message) {
		User user = User.getUser(player);
		Boolean debugMode = user.getData("debug_mode");
		if (debugMode) {
			player.sendMessage("§c[ADMIN DEBUG] §f" + message);
		}
	}
}