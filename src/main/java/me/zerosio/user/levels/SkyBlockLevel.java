package me.zerosio.user.levels;

import java.io.IOException;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.zerosio.garden.GardenAlgorithm;
import me.zerosio.user.profiledata.UserLevel;
import me.zerosio.utility.NameTag;

public class SkyBlockLevel {
	public static void levelUp(Player player) {
		int oldLevel = UserLevel.getLevel(player);
		UserLevel.resetXp(player);
		UserLevel.addLevel(player, 1);
		int newLevel = oldLevel + 1;

		if (player != null) {
			player.sendMessage("§b§m-----------------------------------------------------");
			player.sendMessage(" §3§lSKYBLOCK LEVEL UP §bLevel §8" + oldLevel + "➜§3" + newLevel);
			player.sendMessage("§b§m-----------------------------------------------------");
			player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
		}
		
		if (newLevel == 5 || newLevel > 5) {
			try {
			GardenAlgorithm.createGarden(player.getUniqueId());
			} catch (IOException e) {
				System.out.println("Something got fucked up while creating garden for player: " + player.getName());
			}
		}
	}

	public static String getNameTagPrefix(Player p) {
		return "§8[" + getColour(UserLevel.getLevel(p)) + UserLevel.getLevel(p) + "§8]";
	}

	public static String getPrefix(Player p) {
		return getColour(UserLevel.getLevel(p) + UserLevel.getLevel(p));
	}

	public static String getColour(int level) {
		if (level < 40) return "§7";
		if (level < 80) return "§f";
		if (level < 120) return "§e";
		if (level < 160) return "§a";
		if (level < 200) return "§2";
		if (level < 240) return "§b";
		if (level < 280) return "§3";
		if (level < 320) return "§9";
		if (level < 360) return "§d";
		if (level < 400) return "§5";
		if (level < 440) return "§6";
		if (level < 480) return "§c";
		if (level < 500) return "§4";
		return "§4";
	}
}