package me.zerosio.zones;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.zerosio.user.AdminDebug;
import me.zerosio.user.User;

import java.util.HashMap;
import java.util.Map;

public class ZoneManager {

	public static final Map<Player, ZoneTypes> playerLocations = new HashMap<>();

	public void updatePlayerLocation(Player player, User user) {
		int x = player.getLocation().getBlockX();
		int y = player.getLocation().getBlockY();
		int z = player.getLocation().getBlockZ();

		ZoneTypes newLocation = ZoneTypes.getLocation(x, y, z);
		ZoneTypes currentLocation = playerLocations.getOrDefault(player, ZoneTypes.NONE);

		if (currentLocation != newLocation) {
			playerLocations.put(player, newLocation);
			onLocationChange(player, user, currentLocation, newLocation);
		}
	}

	private void onLocationChange(Player player, User user, ZoneTypes oldLocation, ZoneTypes newLocation) {
		String locationId = newLocation.name().toLowerCase();

		//AdminDebug.debug(player, "You moved from " + oldLocation.getName() + " §fto " + newLocation.getName() + "§f.");

		if (user.getProfileBoolean("discovered_zones." + locationId) == false) {
			user.setProfileData("discovered_zones." + locationId, true);

			String title = newLocation.getName();
			String subtitle = "§6§lNew Zone Discovered!";

			player.sendTitle(title, subtitle);
			player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 0.4920635f);
			player.sendMessage("");
			player.sendMessage(" §6§lNEW AREA DISCOVERED!");
			player.sendMessage("§7  ⏣ " + newLocation.getName());
			player.sendMessage("");

			if (getPlayerLocation(player) == ZoneTypes.VILLAGE) {
				locationMessage(player, "Explore the area to learn more about §bSkyBlock§f.");
				locationMessage(player, "Visit the §6Auction House§f.");
				locationMessage(player, "Manage your §6coins §fin the §6Bank§f.");
				locationMessage(player, "Enchant items in the §bLibrary§f.");
				player.sendMessage("");
			}

			if (getPlayerLocation(player) == ZoneTypes.BIRCH_PARK) {
				locationMessage(player, "Talk to Charlie.");
				locationMessage(player, "Chop down Birch logs.");
				player.sendMessage("");
			}
		}
	}

	public static String getCurrentLocationName(Player player) {
		ZoneTypes currentLocation = playerLocations.get(player);
		return currentLocation != null ? currentLocation.getName() : "§7Unknown";
	}


	public ZoneTypes getPlayerLocation(Player player) {
		return playerLocations.getOrDefault(player, ZoneTypes.NONE);
	}


	public void locationMessage(Player player, String message) {
		player.sendMessage("   §7■ §f" + message);
	}
}
