package me.zerosio.guilds.main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.zerosio.Core;
import me.zerosio.guilds.database.GuildDatabase;

import java.io.File;
import java.util.*;

public class Guild {
	public static final Map<UUID, Guild> GUILD_CACHE = new HashMap<>();

	private String name, displayName, motd, tag, tagColor, leader, discord, description;
	private long mutedUntil, experience;
	private boolean open, publicDiscord;
	private UUID uuid;
	private ArrayList<String> members, officer;
	private Map<String, Long> expHistory, muted;
	private GuildDatabase database;

	public Guild(UUID uuid, String leader, String name) {
		this.uuid = uuid;
		this.leader = leader;
		this.name = name;
		this.displayName = name;
		this.experience = 0;
		this.expHistory = new HashMap<>();
		this.expHistory.put(leader, 0L);
		this.discord = "none";
		this.open = false;
		this.publicDiscord = false;
		this.description = "none";
		this.motd = "";
		this.tag = "";
		this.tagColor = "7";
		this.mutedUntil = 0;
		this.muted = new HashMap<>();
		this.members = new ArrayList<>();
		this.officer = new ArrayList<>();
		GUILD_CACHE.put(uuid, this);
		this.database = new GuildDatabase(uuid.toString());
		saveAll();
	}

	public Guild(UUID uuid, String leader, String name, String displayName, String motd, String tag, String tagColor,
				 ArrayList<String> members, ArrayList<String> officer, long mutedUntil, long experience,
				 Map<String, Long> expHistory, String discord, boolean open, boolean publicDiscord, String description) {
		this.uuid = uuid;
		this.leader = leader;
		this.name = name;
		this.displayName = displayName;
		this.experience = experience;
		this.expHistory = expHistory;
		this.discord = discord;
		this.open = open;
		this.publicDiscord = publicDiscord;
		this.description = description;
		this.motd = motd;
		this.tag = tag;
		this.tagColor = tagColor;
		this.mutedUntil = mutedUntil;
		this.members = members;
		this.officer = officer;
		this.muted = new HashMap<>();
		GUILD_CACHE.put(uuid, this);
		this.database = new GuildDatabase(uuid.toString());
		saveAll();
	}

	private void saveAll() {
		database.set("name", name);
		database.set("displayName", displayName);
		database.set("leader", leader);
		database.set("motd", motd);
		database.set("tag", tag);
		database.set("tagColor", tagColor);
		database.set("mutedUntil", mutedUntil);
		database.set("members", members);
		database.set("officer", officer);
		database.set("experience", experience);
		database.set("expHistory", expHistory);
		database.set("discord", discord);
		database.set("open", open);
		database.set("publicDiscord", publicDiscord);
		database.set("description", description);
	}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getMotd() {
		return motd;
	}

	public String getTag() {
		return tag;
	}

	public String getTagColor() {
		return tagColor;
	}

	public String getLeader() {
		return leader;
	}

	public String getDiscord() {
		return discord;
	}

	public String getDescription() {
		return description;
	}

	public long getMutedUntil() {
		return mutedUntil;
	}

	public long getExperience() {
		return experience;
	}

	public boolean isOpen() {
		return open;
	}

	public boolean isPublicDiscord() {
		return publicDiscord;
	}

	public UUID getUuid() {
		return uuid;
	}

	public ArrayList<String> getMembers() {
		return members;
	}

	public ArrayList<String> getOfficer() {
		return officer;
	}

	public Map<String, Long> getExpHistory() {
		return expHistory;
	}

	public Map<String, Long> getMuted() {
		return muted;
	}

	public GuildDatabase getDatabase() {
		return database;
	}

	public static Guild getGuild(UUID uuid) {
		return GUILD_CACHE.getOrDefault(uuid, null);
	}

	public static Guild getGuildFromName(String name) {
		return GUILD_CACHE.values().stream()
			   .filter(g -> g.getName().equalsIgnoreCase(name))
			   .findFirst().orElse(null);
	}

	public static Guild getGuildFromTag(String tag) {
		return GUILD_CACHE.values().stream()
			   .filter(g -> g.getTag().equalsIgnoreCase(tag))
			   .findFirst().orElse(null);
	}

	public static boolean inGuild(Player player) {
		return GUILD_CACHE.values().stream()
			   .anyMatch(g -> g.getAllPlayers().contains(player.getUniqueId().toString()));
	}

	public static Guild getGuildFromPlayer(Player player) {
		return GUILD_CACHE.values().stream()
			   .filter(g -> g.getAllPlayers().contains(player.getUniqueId().toString()))
			   .findFirst().orElse(null);
	}

	public List<Player> getOnlinePlayers() {
		List<Player> players = new ArrayList<>();
		for (String id : getAllPlayers()) {
			Player p = Bukkit.getPlayer(UUID.fromString(id));
			if (p != null && p.isOnline()) players.add(p);
		}
		return players;
	}

	public List<Player> getOnlinePlayersExceptLeader() {
		List<Player> players = new ArrayList<>();
		for (String id : members) {
			Player p = Bukkit.getPlayer(UUID.fromString(id));
			if (p != null && p.isOnline()) players.add(p);
		}
		for (String id : officer) {
			Player p = Bukkit.getPlayer(UUID.fromString(id));
			if (p != null && p.isOnline()) players.add(p);
		}
		return players;
	}

	public void mute(String player, Long until) {
		muted.put(player, until);
	}

	public Long isMuted(String player) {
		return muted.containsKey(player) && System.currentTimeMillis() - muted.get(player) > 0
			   ? System.currentTimeMillis() - muted.get(player)
			   : 0L;
	}

	public void left(String player) {
		if (members.remove(player)) {
			setMembers(members);
		} else if (officer.remove(player)) {
			setOfficer(officer);
		}
	}

	public List<String> getAllPlayers() {
		List<String> all = new ArrayList<>();
		all.add(leader);
		all.addAll(members);
		all.addAll(officer);
		return all;
	}

	public boolean isLeader(Player player) {
		return player.getUniqueId().toString().equals(leader);
	}

	public boolean isOfficer(Player player) {
		return officer.contains(player.getUniqueId().toString());
	}

	public boolean isMember(Player player) {
		return members.contains(player.getUniqueId().toString());
	}

	public void setName(String toSet) {
		this.name = toSet.toUpperCase();
		this.displayName = toSet;
		database.set("name", name);
		database.set("displayName", displayName);
	}

	public void setMotd(String toSet) {
		this.motd = toSet;
		database.set("motd", motd);
	}

	public void setTag(String toSet) {
		this.tag = toSet.toLowerCase();
		database.set("tag", tag);
	}

	public void setTagColor(String toSet) {
		this.tagColor = toSet.toLowerCase();
		database.set("tagColor", tagColor);
	}

	public void setMutedUntil(Long toSet) {
		this.mutedUntil = toSet;
		database.set("mutedUntil", mutedUntil);
	}

	public void setMembers(ArrayList<String> toSet) {
		this.members = toSet;
		database.set("members", members);
	}

	public void setOfficer(ArrayList<String> toSet) {
		this.officer = toSet;
		database.set("officer", officer);
	}

	public void setLeader(String toSet) {
		this.leader = toSet;
		database.set("leader", leader);
	}

	public void setExperience(long toSet) {
		this.experience = toSet;
		database.set("experience", experience);
	}

	public void addExperience(Player player, long toAdd) {
		long currentXP = experience;
		long newXP = currentXP + toAdd;

		if (Level.getLevelFromXP(newXP) > Level.getLevelFromXP(currentXP)) {
			for (String id : getAllPlayers()) {
				Player p = Bukkit.getPlayer(UUID.fromString(id));
				if (p != null) {
					p.sendMessage("§b§m-----------------------------------------------------");
					p.sendMessage("§eYour guild has just reached §6Level " + Level.getLevelFromXP(newXP) + "§e!");
					p.sendMessage("§b§m-----------------------------------------------------");
				}
			}
		}

		experience = newXP;
		expHistory.put(player.getUniqueId().toString(), expHistory.getOrDefault(player.getUniqueId().toString(), 0L) + toAdd);
		setExperience(experience);
		setExpHistory(expHistory);
	}

	public void setExpHistory(Map<String, Long> toSet) {
		this.expHistory = toSet;
		database.set("expHistory", expHistory);
	}

	public void setDiscord(String toSet) {
		this.discord = toSet;
		database.set("discord", discord);
	}

	public void setOpen(boolean toSet) {
		this.open = toSet;
		database.set("open", open);
	}

	public void setPublicDiscord(boolean toSet) {
		this.publicDiscord = toSet;
		database.set("publicDiscord", publicDiscord);
	}

	public void setDescription(String toSet) {
		this.description = toSet;
		database.set("description", description);
	}

	public static void loadAllGuilds() {
		File file = new File(Core.getPlugin(Core.class).getDataFolder(), "guilds.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		if (!config.contains("guilds")) return;

		ConfigurationSection section = config.getConfigurationSection("guilds");
		for (String id : section.getKeys(false)) {
			ConfigurationSection g = section.getConfigurationSection(id);
			if (g == null) continue;

			Map<String, Long> expHistory = new HashMap<>();
			if (g.isConfigurationSection("expHistory")) {
				for (String key : g.getConfigurationSection("expHistory").getKeys(false)) {
					expHistory.put(key, g.getLong("expHistory." + key));
				}
			}

			Guild guild = new Guild(
				UUID.fromString(id),
				g.getString("leader"),
				g.getString("name"),
				g.getString("displayName"),
				g.getString("motd"),
				g.getString("tag"),
				g.getString("tagColor"),
				new ArrayList<>(g.getStringList("members")),
				new ArrayList<>(g.getStringList("officer")),
				g.getLong("mutedUntil"),
				g.getLong("experience"),
				expHistory,
				g.getString("discord"),
				g.getBoolean("open"),
				g.getBoolean("publicDiscord"),
				g.getString("description")
			);

			GUILD_CACHE.put(guild.getUuid(), guild);
		}
	}

}
