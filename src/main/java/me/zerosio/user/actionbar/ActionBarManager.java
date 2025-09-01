package me.zerosio.user.actionbar;

import me.zerosio.utility.ActionBar;
import me.zerosio.user.statistics.*;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ActionBarManager {

	private static final Map<UUID, String> overrideText = new HashMap<>();
	private static final Map<UUID, Long> overrideEnd = new HashMap<>();
	private static final Map<UUID, ActionBarOverrideMode> overrideMode = new HashMap<>();

	public static void update(Player player) {
		UUID uuid = player.getUniqueId();

		double mana = StatRegenerator.getCurrentMana(player);
		double maxMana = StatManager.getStat(StatType.INTELLIGENCE, player);
		String manaText = "§b✎ " + (int) mana + "/" + (int) maxMana;

		if (overrideText.containsKey(uuid)) {
			long end = overrideEnd.getOrDefault(uuid, 0L);
			if (System.currentTimeMillis() < end) {
				String text = overrideText.get(uuid);
				ActionBarOverrideMode mode = overrideMode.getOrDefault(uuid, ActionBarOverrideMode.FULL);

				if (mode == ActionBarOverrideMode.FULL) {
					ActionBar.send(player, text + "     " + manaText);
					return;
				}
				if (mode == ActionBarOverrideMode.CENTER_ONLY) {
					ActionBar.send(player, text + "     " + manaText);
					return;
				}
				if (mode == ActionBarOverrideMode.MANA_ONLY) {
					ActionBar.send(player, manaText);
					return;
				}
				if (mode == ActionBarOverrideMode.HEALTH_AND_CENTRE) {
					double health = StatRegenerator.getCurrentHealth(player);
					double maxHealth = StatManager.getStat(StatType.HEALTH, player);

					StringBuilder bar = new StringBuilder();
					bar.append("§c❤ ").append((int) health).append("/").append((int) maxHealth);
					bar.append("   ").append(text);
					bar.append("     ").append(manaText);
					ActionBar.send(player, bar.toString());
					return;
				}
			} else {
				overrideText.remove(uuid);
				overrideEnd.remove(uuid);
				overrideMode.remove(uuid);
			}
		}

		double health = StatRegenerator.getCurrentHealth(player);
		double maxHealth = StatManager.getStat(StatType.HEALTH, player);
		double defense = StatManager.getStat(StatType.DEFENSE, player);

		StringBuilder bar = new StringBuilder();
		bar.append("§c❤ ").append((int) health).append("/").append((int) maxHealth);
		if (defense > 0) {
			bar.append("   §a❈ ").append((int) defense);
		}
		bar.append("     ").append(manaText);

		ActionBar.send(player, bar.toString());
	}

	public static void setTemporary(Player player, String message, int durationTicks, ActionBarOverrideMode mode) {
		UUID uuid = player.getUniqueId();

		// Replace existing override instantly
		overrideText.put(uuid, message);
		overrideEnd.put(uuid, System.currentTimeMillis() + (durationTicks * 50L));
		overrideMode.put(uuid, mode);
	}
}
