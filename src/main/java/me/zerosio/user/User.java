package me.zerosio.user;

import me.zerosio.services.bank.BankTier;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.zerosio.chat.ActiveChat;
import me.zerosio.instance.InstanceType;
import me.zerosio.localization.LocalizationLanguages;
import me.zerosio.profile.ProfileTypes;
import me.zerosio.rank.PlayerRank;
import me.zerosio.utility.Logging;
import me.zerosio.utility.NameTag;
import me.zerosio.utility.hook.DiscordWebhook;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
			config.set("vanish_mode", false);
			config.set("nick_mode", false);
			config.set("nick_name", 1);
			config.set("nick_rank", "tes");
			config.set("active_chat", ActiveChat.ALL.name());
			config.set("last_instance", InstanceType.HUB.name());
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
		config.set(path + ".skyblock_level", 0);
		config.set(path + ".skyblock_xp", 0);
		config.set(path + ".economy.coins", 0);
		config.set(path + ".economy.bits", 0);
		config.set(path + ".economy.gems", 0);
		config.set(path + ".economy.bank_coins", 0);
		config.set(path + ".bank.last_interest", System.currentTimeMillis());
		config.set(path + ".bank.tier", BankTier.NONE.name());
		config.set(path + ".skills.combat", 1);
		config.set(path + ".skills.mining", 1);
		config.set(path + ".skills.farming", 1);
		config.set(path + ".skills.foraging", 1);
		config.set(path + ".skills.fishing", 1);
		config.set(path + ".skills.enchanting", 1);
		config.set(path + ".skills.alchemy", 1);
		config.set(path + ".skills.taming", 1);
		config.set(path + ".skills.dungeoneering", 1);
		config.set(path + ".skills.carpentry", 1);
		config.set(path + ".skills.social", 1);
		config.set(path + ".skills.rune_crafting", 1);
		config.set(path + ".rewards.rogue_sword", false);
		config.set(path + ".discovered_zones.none", true);
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
		long start = System.currentTimeMillis();
		UUID active = getActiveProfileId();
		if (active == null) return null;
		T result = (T) config.get("profiles." + active + "." + path);
		long end = System.currentTimeMillis();
		//sendWebhook("getProfileData", "profiles." + active + "." + path, end - start);
		return result;
	}

	public void setData(String path, Object value) {
		long start = System.currentTimeMillis();
		config.set(path, value);
		save();
		long end = System.currentTimeMillis();
		sendWebhook("setData", path, end - start);
	}

	public void setProfileData(String path, Object value) {
		long start = System.currentTimeMillis();
		UUID active = getActiveProfileId();
		if (active == null) return;
		config.set("profiles." + active + "." + path, value);
		save();
		long end = System.currentTimeMillis();
		sendWebhook("setProfileData", "profiles." + active + "." + path, end - start);
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

	public <T> T getData(String key) {
		long start = System.currentTimeMillis();
		T result = (T) config.get(key);
		long end = System.currentTimeMillis();
		//sendWebhook("getData", key, end - start);
		return result;
	}

	private void sendWebhook(String method, String path, long durationMs) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String formattedTime = format.format(new Date());

		DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/1397120261391777842/6uJzBJd3b1YhCpzDXVumtpSd3avCXvsCgTrXA-gpL0b_-8xOPRNjB_uDMcMdcUHzWM-3");
		webhook.setUsername("DB Logger");
		webhook.setAvatarUrl("https://cdn.discordapp.com/attachments/1397111076709208105/1397120634819055688/image0.png?ex=6880917c&is=687f3ffc&hm=2466ed8c8e9f3d52a4196d77a86cb924b486b3ec06a7d129952157959e1982ee&");
		webhook.setContent(null);

		DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject()
		.setTitle("Database Access")
		.setDescription("Method: `" + method + "`")
		.addField("User UUID", uuid.toString(), false)
		.addField("User Name", Bukkit.getPlayer(uuid.toString()).getName(), false)
		.addField("Path", path, false)
		.addField("Time","Fetchtime: " + durationMs + "ms", true)
		.setFooter("Timestamp: " + formattedTime, null);

		webhook.addEmbed(embed);
		webhook.execute();
	}
}
