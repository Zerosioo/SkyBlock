package dev.zerosio.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import dev.zerosio.Core;

public class SkyBlockScoreboard {
	public static void startTask(Player player) {

		Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), () -> {

			BoardManager.setScoreboard(player,
									   "§e§lSKYBLOCK",
									   ""
									   );

		}, 20L);
	}

	public static String getDateString() {
		return new java.text.SimpleDateFormat("MM/dd/yy").format(new java.util.Date());
	}
}