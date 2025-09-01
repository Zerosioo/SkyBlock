package me.zerosio.mayors;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ElectionHistoryManager {

	private static File file;
	private static FileConfiguration config;

	public static void initialize(File dataFolder) {
		File dataDir = new File(dataFolder, "data");
		if (!dataDir.exists()) dataDir.mkdirs();

		file = new File(dataDir, "election_history.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		config = YamlConfiguration.loadConfiguration(file);
	}

	public static void saveElection(
		int year,
		List<Mayor> candidates,
		Map<Mayor, Integer> voteCounts,
		Mayor winner,
		Mayor minister
	) {
		String path = "elections.year_" + year;

		for (int i = 0; i < candidates.size(); i++) {
			MayorEnum enumVal = MayorEnum.fromMayor(candidates.get(i));
			if (enumVal != null) {
				config.set(path + ".candidate_" + (i + 1), enumVal.name());
			}
		}

		config.set(path + ".votes", null); // clear previous votes
		int totalVotes = 0;
		for (Mayor mayor : candidates) {
			MayorEnum enumVal = MayorEnum.fromMayor(mayor);
			if (enumVal != null) {
				int votes = voteCounts.getOrDefault(mayor, 0); // ensure 0 if missing
				config.set(path + ".votes." + enumVal.name(), votes);
				totalVotes += votes;
			}
		}


		config.set(path + ".winner", MayorEnum.fromMayor(winner).name());
		config.set(path + ".minister", MayorEnum.fromMayor(minister).name());
		config.set(path + ".total_votes", totalVotes);

		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<MayorEnum> getCandidates(int year) {
		List<MayorEnum> result = new ArrayList<>();
		String base = "elections.year_" + year;
		for (int i = 1; i <= 5; i++) {
			String raw = config.getString(base + ".candidate_" + i);
			result.add(MayorEnum.fromString(raw));
		}
		return result;
	}

	public static Map<MayorEnum, Integer> getVotes(int year) {
		Map<MayorEnum, Integer> votes = new HashMap<>();
		String path = "elections.year_" + year + ".votes";
		if (!config.contains(path)) return votes;

		for (String key : config.getConfigurationSection(path).getKeys(false)) {
			MayorEnum mayor = MayorEnum.fromString(key);
			votes.put(mayor, config.getInt(path + "." + key));
		}
		return votes;
	}

	public static MayorEnum getWinner(int year) {
		return MayorEnum.fromString(config.getString("elections.year_" + year + ".winner"));
	}

	public static MayorEnum getMinister(int year) {
		return MayorEnum.fromString(config.getString("elections.year_" + year + ".minister"));
	}

	public static int getTotalVotes(int year) {
		return config.getInt("elections.year_" + year + ".total_votes", 0);
	}

	public static Set<Integer> getRecordedYears() {
		if (!config.contains("elections")) return Collections.emptySet();
		Set<Integer> years = new HashSet<>();
		for (String yearKey : config.getConfigurationSection("elections").getKeys(false)) {
			if (yearKey.startsWith("year_")) {
				try {
					years.add(Integer.parseInt(yearKey.substring(5)));
				} catch (NumberFormatException ignored) {}
			}
		}
		return years;
	}
}
