package dev.zerosio.user;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import dev.zerosio.profile.ProfileTypes;
import dev.zerosio.rank.PlayerRank;
import dev.zerosio.utility.Logging;
import dev.zerosio.utility.NameTag;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class User {

	private static final Map<UUID, User> userCache = new HashMap<>();
	private static File dataFolder;

	private final UUID uuid;
	private final File file;
	public final FileConfiguration config;

	private User(UUID uuid) {
		this.uuid = uuid;
		this.file = new File(dataFolder, uuid.toString() + ".yml");

		boolean created = false;

		if (!file.exists()) {
			try {
				file.createNewFile();
				created = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		this.config = YamlConfiguration.loadConfiguration(file);

		if (created) {
			config.set("uuid", uuid.toString());
			config.set("name", Bukkit.getOfflinePlayer(uuid).getName());
			config.set("rank", PlayerRank.DEFAULT.name().toUpperCase());
			config.set("debug_mode", false);
			save();
		}
	}

	public static void init(File pluginDataFolder) {
		dataFolder = new File(pluginDataFolder, "playerdata");
		if (!dataFolder.exists()) {
			dataFolder.mkdirs();
		}
	}

	public static User getUser(Player player) {
		return getUser(player.getUniqueId());
	}

	public static User getUser(UUID uuid) {
		return userCache.computeIfAbsent(uuid, User::new);
	}

	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			System.out.println("[ERROR] Could not save file for user: " + uuid);
			e.printStackTrace();
		}
	}

	public static void saveAll() {
		for (User user : userCache.values()) {
			user.save();
		}
	}

	public UUID createProfile(String name) {
		UUID profileId = UUID.randomUUID();
		String path = "profiles." + profileId;
		config.set(path + ".name", name);
		config.set(path + ".created_at", System.currentTimeMillis());
		config.set(path + ".economy.coins", 0);
		config.set(path + ".economy.bits", 0);
		config.set(path + ".economy.gems", 0);
		config.set(path + ".settings.players_hidden", false);

		save();
		return profileId;
	}

	public UUID getActiveProfileId() {
		String idStr = config.getString("active_profile");
		if (idStr != null) {
			try {
				return UUID.fromString(idStr);
			} catch (IllegalArgumentException ignored) {}
		}

		UUID newProfile = createProfile(ProfileTypes.getRandomProfileName());
		config.set("active_profile", newProfile.toString());
		save();
		return newProfile;
	}



	public void setActiveProfile(UUID profileId) {
		if (config.contains("profiles." + profileId)) {
			config.set("active_profile", profileId.toString());
			save();
		}
	}

	public boolean getProfileBoolean(String path) {
		UUID active = getActiveProfileId();
		if (active == null) return false;
		return config.getBoolean("profiles." + active + "." + path, false);
	}

	public <T> T getProfileData(String path) {
		UUID active = getActiveProfileId();
		if (active == null) return null;
		return (T) config.get("profiles." + active + "." + path);
	}
	
	public void setData(String path, Object value) {
		config.set(path, value);
		save();
	}

	public void setProfileData(String path, Object value) {
		UUID active = getActiveProfileId();
		if (active == null) return;
		config.set("profiles." + active + "." + path, value);
		save();
	}

	public void wipeProfile(UUID profileId) {
		String profilePath = "profiles." + profileId;
		if (!config.contains(profilePath)) return;

		config.set(profilePath, null);
		if (profileId.equals(getActiveProfileId())) {
			config.set("active_profile", null);
		}
		save();
	}

	public boolean hasProfile(UUID profileId) {
		return config.contains("profiles." + profileId);
	}

	public Map<UUID, Map<String, Object>> getProfiles() {
		Map<UUID, Map<String, Object>> profileMap = new HashMap<>();
		if (!config.contains("profiles")) return profileMap;

		for (String id : config.getConfigurationSection("profiles").getKeys(false)) {
			try {
				UUID uuid = UUID.fromString(id);
				Map<String, Object> values = config.getConfigurationSection("profiles." + id).getValues(true);
				profileMap.put(uuid, values);
			} catch (IllegalArgumentException ignored) {}
		}
		return profileMap;
	}

	public String getString(String key) {
		return config.getString(key);
	}
}
