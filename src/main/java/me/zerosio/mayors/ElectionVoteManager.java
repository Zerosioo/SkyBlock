package me.zerosio.mayors;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.zerosio.user.AdminDebug;
import me.zerosio.utility.Logging;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class ElectionVoteManager {

	private static File file;
	private static FileConfiguration config;
	private static Integer cachedMinisterSlot = null;

	private static final Map<UUID, Integer> playerVotes = new HashMap<>();
	private static final Map<Integer, Integer> candidateVotes = new HashMap<>();

	public static void initialize(File dataFolder) {
		File dataDir = new File(dataFolder, "data");
		if (!dataDir.exists()) {
			dataDir.mkdirs();
		}

		file = new File(dataDir, "election_votes.yml");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		config = YamlConfiguration.loadConfiguration(file);
		loadVotes();
	}

	private static void loadVotes() {
		playerVotes.clear();
		candidateVotes.clear();

		if (config.contains("votes")) {
			for (String uuidStr : config.getConfigurationSection("votes").getKeys(false)) {
				try {
					UUID uuid = UUID.fromString(uuidStr);
					int candidate = config.getInt("votes." + uuidStr);
					playerVotes.put(uuid, candidate);
					candidateVotes.put(candidate, candidateVotes.getOrDefault(candidate, 0) + 1);
				} catch (IllegalArgumentException ignored) {
					System.out.println("Something went wrong while loading election votes");
				}

				System.out.println("[DEBUG] Loading votes...");
				System.out.println("[DEBUG] Found: " + config.getConfigurationSection("votes").getKeys(false).size() + " entries.");

			}
		}
	}

	private static void saveVotes() {
		config.set("votes", null);
		for (Map.Entry<UUID, Integer> entry : playerVotes.entrySet()) {
			config.set("votes." + entry.getKey().toString(), entry.getValue());
			System.out.println("[DEBUG] Saving " + playerVotes.size() + " votes to file.");
		}
		try {
			config.save(file);
		} catch (IOException e) {
			Logging.severe("&cSomething went wrong while saving election votes!");
		}
	}

	public static void addVote(UUID playerUUID, int candidateNumber) {
		Player player = Bukkit.getPlayer(playerUUID);

		if (candidateNumber < 1 || candidateNumber > 5) {
			AdminDebug.debug(player, "Invalid candidate number.");
			throw new IllegalArgumentException("Invalid candidate number.");
		}

		System.out.println("[DEBUG] " + playerUUID + " voted for candidate " + candidateNumber);
		System.out.println("[DEBUG] Total votes (players): " + playerVotes.size());

		AdminDebug.debug(player, "Removing old vote if available...");
		removeOldVoteByPlayer(playerUUID);

		AdminDebug.debug(player, "Removed old vote if available.");

		AdminDebug.debug(player, "Putting vote in escrow...");
		playerVotes.put(playerUUID, candidateNumber);
		candidateVotes.put(candidateNumber, candidateVotes.getOrDefault(candidateNumber, 0) + 1);
		AdminDebug.debug(player, "You voted for candidate: §a" + candidateNumber);
		saveVotes();

		AdminDebug.debug(player, "Process §asuccess§f!");
	}

	public static void removeOldVoteByPlayer(UUID playerUUID) {
		if (playerVotes.containsKey(playerUUID)) {
			int previousCandidate = playerVotes.get(playerUUID);
			candidateVotes.put(previousCandidate, candidateVotes.get(previousCandidate) - 1);
			if (candidateVotes.get(previousCandidate) <= 0) {
				candidateVotes.remove(previousCandidate);
			}
			playerVotes.remove(playerUUID);
			saveVotes();
		}
	}

	public static void removeAllVotesAndCandidates() {
		cachedMinisterSlot = null;
		playerVotes.clear();
		candidateVotes.clear();
		config.set("votes", null);
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean hasVoted(Player player) {
		return playerVotes.containsKey(player.getUniqueId());
	}

	public static int getTotalVotes() {
		return playerVotes.size();
	}

	public static int getVotes(int candidateNumber) {
		return candidateVotes.getOrDefault(candidateNumber, 0);
	}

	public static double getVotePercentage(int candidateNumber) {
		int total = getTotalVotes();
		if (total == 0) return 0.0;
		int votes = getVotes(candidateNumber);
		return Math.round((votes * 100.0 / total) * 10.0) / 10.0;
	}

	public static String getVotePercentageString(int candidateNumber) {
		return new DecimalFormat("0.0").format(getVotePercentage(candidateNumber));
	}

	public static int calculateWinner() {
		int winner = -1;
		int maxVotes = -1;

		for (int i = 1; i <= 5; i++) {
			int votes = getVotes(i);
			if (votes > maxVotes) {
				maxVotes = votes;
				winner = i;
			}
		}
		return winner;
	}

	public static int calculateSecondPlace() {
		if (cachedMinisterSlot != null) return cachedMinisterSlot;

		int winner = calculateWinner();
		List<Integer> eligible = new ArrayList<>();
		int maxVotes = Integer.MIN_VALUE;

		for (int i = 1; i <= 5; i++) {
			if (i == winner) continue;
			int votes = getVotes(i);
			if (votes > maxVotes) {
				maxVotes = votes;
			}
		}
		for (int i = 1; i <= 5; i++) {
			if (i == winner) continue;
			if (getVotes(i) == maxVotes) {
				eligible.add(i);
			}
		}

		if (eligible.isEmpty()) {
			cachedMinisterSlot = null; 
			return -1;
		}

		int chosen = eligible.get(new Random().nextInt(eligible.size()));
		cachedMinisterSlot = chosen;
		return chosen;
	}

	public static String getTotalVotesCommafied() {
		return NumberFormat.getNumberInstance(Locale.US).format(getTotalVotes());
	}

	public static int getPlayerVote(UUID playerUUID) {
		return playerVotes.getOrDefault(playerUUID, -1);
	}

	public static int getPlayerVote(Player player) {
		return getPlayerVote(player.getUniqueId());
	}

	public static Map<Integer, Integer> getAllCandidateVotes() {
		return new HashMap<>(candidateVotes);
	}

	public static void inspect(Player player) {
		AdminDebug.debug(player, "        §e§lELECTIONS DEBUG");

		for (Map.Entry<UUID, Integer> entry : playerVotes.entrySet()) {
			System.out.println("UUID: " + entry.getKey() + " -> Candidate: " + entry.getValue());
			AdminDebug.debug(player, "UUID: " + entry.getKey() + " voted for Candidate: " + entry.getValue());
		}

		AdminDebug.debug(player, "Total Votes: §a" + getTotalVotes());
	}
}
