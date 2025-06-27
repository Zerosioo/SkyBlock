package dev.zerosio.user.profiledata;

import org.bukkit.entity.Player;
import dev.zerosio.user.User;

public class UserEconomy {

	private static double getNumber(User user, String path) {
		Object raw = user.getProfileData(path);
		return (raw instanceof Number) ? ((Number) raw).doubleValue() : 0.0;
	}

	public static double getCoins(Player p) {
		return getNumber(User.getUser(p), "economy.coins");
	}

	public static double getBits(Player p) {
		return getNumber(User.getUser(p), "economy.bits");
	}

	public static double getGems(Player p) {
		return getNumber(User.getUser(p), "economy.gems");
	}

	public static void setCoins(Player p, double amount) {
		User.getUser(p).setProfileData("economy.coins", amount);
	}

	public static void setBits(Player p, double amount) {
		User.getUser(p).setProfileData("economy.bits", amount);
	}

	public static void setGems(Player p, double amount) {
		User.getUser(p).setProfileData("economy.gems", amount);
	}

	public static void addCoins(Player p, double amount) {
		User user = User.getUser(p);
		double current = getNumber(user, "economy.coins");
		user.setProfileData("economy.coins", current + amount);
	}

	public static void addBits(Player p, double amount) {
		User user = User.getUser(p);
		double current = getNumber(user, "economy.bits");
		user.setProfileData("economy.bits", current + amount);
	}

	public static void addGems(Player p, double amount) {
		User user = User.getUser(p);
		double current = getNumber(user, "economy.gems");
		user.setProfileData("economy.gems", current + amount);
	}

	public static void deductCoins(Player p, double amount) {
		User user = User.getUser(p);
		double current = getNumber(user, "economy.coins");
		user.setProfileData("economy.coins", current - amount);
	}

	public static void deductBits(Player p, double amount) {
		User user = User.getUser(p);
		double current = getNumber(user, "economy.bits");
		user.setProfileData("economy.bits", current - amount);
	}

	public static void deductGems(Player p, double amount) {
		User user = User.getUser(p);
		double current = getNumber(user, "economy.gems");
		user.setProfileData("economy.gems", current - amount);
	}

	public static boolean hasEnoughCoins(Player p, double amount) {
		return getCoins(p) >= amount;
	}

	public static boolean hasEnoughBits(Player p, double amount) {
		return getBits(p) >= amount;
	}

	public static boolean hasEnoughGems(Player p, double amount) {
		return getGems(p) >= amount;
	}
}
