package me.zerosio.mayors;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.yaml.snakeyaml.Yaml;

import me.zerosio.utility.Logging;

import java.io.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ElectionTiming {

	private final JavaPlugin plugin;
	private final long cycleDurationMillis;
	private final long electionOpenDurationMillis;
	private final long electionCloseDurationMillis;
	private long startTimestamp;
	private final File dataFile;

	public ElectionTiming(JavaPlugin plugin) {
		this.plugin = plugin;
		this.cycleDurationMillis = Duration.ofDays(5).plusHours(4).toMillis();
		this.electionOpenDurationMillis = Duration.ofDays(3).plusHours(21).toMillis();
		this.electionCloseDurationMillis = Duration.ofDays(1).plusHours(7).toMillis();
		this.dataFile = new File(plugin.getDataFolder(), "election.yml");
		loadOrCreateStartTimestamp();
	}

	private void loadOrCreateStartTimestamp() {
		if (!dataFile.exists()) {
			startTimestamp = System.currentTimeMillis();
			save();
			return;
		}
		try (InputStream input = new FileInputStream(dataFile)) {
			Yaml yaml = new Yaml();
			Map<String, Object> data = (Map<String, Object>) yaml.load(input);
			if (data != null && data.containsKey("startTimestamp")) {
				Object value = data.get("startTimestamp");
				if (value instanceof Number) {
					startTimestamp = ((Number) value).longValue();
				} else if (value instanceof String) {
					startTimestamp = Long.parseLong((String) value);
				} else {
					startTimestamp = System.currentTimeMillis();
					save();
				}
			} else {
				startTimestamp = System.currentTimeMillis();
				save();
			}
		} catch (Exception e) {
			startTimestamp = System.currentTimeMillis();
			save();
		}
	}

	private void save() {
		try {
			dataFile.getParentFile().mkdirs();
			Yaml yaml = new Yaml();
			Map<String, Object> data = new HashMap<>();
			data.put("startTimestamp", startTimestamp);
			try (Writer writer = new FileWriter(dataFile)) {
				yaml.dump(data, writer);
			}
		} catch (IOException ignored) { }
	}

	public void start() {
		new BukkitRunnable() {
			private boolean electionActive = false;

			@Override
			public void run() {
				boolean openNow = isElectionOpen();
				if (openNow && !electionActive) {
					electionActive = true;
					onElectionActivate();
				} else if (!openNow && electionActive) {
					electionActive = false;
					onElectionClose();
				}
			}
		} .runTaskTimer(plugin, 0L, 20L);
	}

	private void onElectionActivate() {
		MayorLocalization.startElections();
		Logging.sendMessage("&bMayor Elections &eare now &aopen&e!");
	}

	private void onElectionClose() {
		MayorLocalization.stopElections();
		Logging.sendMessage("&bMayor Elections &eare now &cclosed&e!");
	}

	public boolean isElectionOpen() {
		long now = System.currentTimeMillis();
		long elapsed = (now - startTimestamp) % cycleDurationMillis;
		return elapsed < electionOpenDurationMillis;
	}

	public Duration getRemainingOpenTime() {
		if (!isElectionOpen()) return Duration.ZERO;
		long now = System.currentTimeMillis();
		long elapsed = (now - startTimestamp) % cycleDurationMillis;
		return Duration.ofMillis(electionOpenDurationMillis - elapsed);
	}

	public Duration getRemainingCloseTime() {
		if (isElectionOpen()) return Duration.ZERO;
		long now = System.currentTimeMillis();
		long elapsed = (now - startTimestamp) % cycleDurationMillis;
		return Duration.ofMillis(cycleDurationMillis - elapsed);
	}

	public static String formatDuration(Duration duration) {
		long totalSeconds = duration.getSeconds();
		long days = totalSeconds / (60 * 60 * 24);
		long hours = (totalSeconds % (60 * 60 * 24)) / 3600;
		long minutes = (totalSeconds % 3600) / 60;
		long seconds = totalSeconds % 60;
		return String.format("%dd %dh %dm %ds", days, hours, minutes, seconds);
	}

	public void forceNextPhase() {
		long now = System.currentTimeMillis();
		long elapsed = (now - startTimestamp) % cycleDurationMillis;
		if (elapsed < electionOpenDurationMillis) {
			startTimestamp -= (electionOpenDurationMillis - elapsed) + 1000;
		} else {
			startTimestamp -= (cycleDurationMillis - elapsed) + 1000;
		}
		save();
		Logging.sendMessage("&c[Admin] &fElection phase force-skipped.");
	}
}
