package me.zerosio.rank;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.zerosio.user.profiledata.UserRank;

public class StaffChat {
	public static void sendMessage(Player player, String message) {
		for (Player allPlayers : Bukkit.getOnlinePlayers()) {
			if (UserRank.getPlayerRank(allPlayers).isStaff()) {
				player.sendMessage("§b[STAFF] §f" + UserRank.getPlayerPrefix(player) + "§f: " + message);
			}
		}
	}
	
	public static void sendStaffJoinMessage(Player player) {
		for (Player allPlayers : Bukkit.getOnlinePlayers()) {
			if (UserRank.getPlayerRank(allPlayers).isStaff()) {
				player.sendMessage("§b[STAFF] §f" + UserRank.getPlayerPrefix(player) + " §ejoined.");
			}
		}
	}
	
	public static void sendStaffDisconnectMessage(Player player) {
		for (Player allPlayers : Bukkit.getOnlinePlayers()) {
			if (UserRank.getPlayerRank(allPlayers).isStaff()) {
				player.sendMessage("§b[STAFF] §f" + UserRank.getPlayerPrefix(player) + " §edisconnected.");
			}
		}
	}
}