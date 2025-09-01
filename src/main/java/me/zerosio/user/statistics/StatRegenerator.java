package me.zerosio.user.statistics;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import me.zerosio.Core;
import me.zerosio.user.actionbar.ActionBarManager;
import me.zerosio.user.statistics.calculators.EffectiveHealthCalculator;
import me.zerosio.utility.ActionBar;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatRegenerator {

	private static final Map<UUID, Double> currentEHP = new HashMap<>();
	private static final Map<UUID, Double> currentMana = new HashMap<>();
	private static final Map<UUID, Double> absorption = new HashMap<>();
	private static final Map<UUID, Float> lastSpeed = new HashMap<>();
	

	public static void start(Core plugin) {
		new BukkitRunnable() {
			@Override
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers()) {
					regenerateStats(player);
				}
			}
		} .runTaskTimer(plugin, 0L, 20L);

		new BukkitRunnable() {
			@Override
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers()) {
					StatManager.recalculate(player);
				}
			}
		} .runTaskTimer(plugin, 0L, 10L);
	}

	private static void regenerateStats(Player player) {
		PlayerStats stats = StatManager.getStats(player);
		UUID uuid = player.getUniqueId();

		float speedStat = (float) stats.getStat(StatType.SPEED);
		float walkSpeed = Math.max(0.05f, Math.min(speedStat / 500.0f, 1.0f));

		//UUID uuidd = player.getUniqueId();
//		Float lastApplied = lastSpeed.get(uuid);

//		if (lastApplied == null || Math.abs(lastApplied - walkSpeed) > 0.001f) {
//			player.setWalkSpeed(walkSpeed);
//			lastSpeed.put(uuidd, walkSpeed);
//		}


		double maxHealth = stats.getStat(StatType.HEALTH);
		double maxMana = stats.getStat(StatType.INTELLIGENCE);
		double defense = stats.getStat(StatType.DEFENSE);
		double ehpMax = EffectiveHealthCalculator.getEffectiveHealth(player);
		double absorb = absorption.getOrDefault(uuid, 0.0);

		currentEHP.putIfAbsent(uuid, ehpMax);
		currentMana.putIfAbsent(uuid, maxMana);

		double ehp = currentEHP.get(uuid);
		double mana = currentMana.get(uuid);

		double flatRegen = stats.getStat(StatType.HEALTH_REGEN);
		double percentRegen = stats.getStat(StatType.HEALTH_PERCENT_REGEN);
		double vitality = stats.getStat(StatType.VITALITY);
		double mending = stats.getStat(StatType.MENDING);

		double regenRaw = flatRegen + (percentRegen / 100.0 * maxHealth) + (vitality * 0.05) + (mending * 0.08);
		double regenEHP = regenRaw * (1.0 + defense / 100.0);

		ehp = Math.min(ehpMax, ehp + regenEHP);
		mana = Math.min(maxMana, mana + (maxMana * 0.02));

		if (ehp <= 0 && absorb <= 0) {
			player.setHealth(0);
			currentEHP.put(uuid, 0.0);
			return;
		}

		currentEHP.put(uuid, ehp);
		currentMana.put(uuid, mana);

		double currentHealth = ehp / (1.0 + defense / 100.0);
		currentHealth = Math.min(currentHealth, maxHealth);

		double baseHP = 10.0;
		int extraRows = (int)(maxHealth / 1000);
		double visualMax = Math.min(20.0, baseHP + (extraRows * 10));

		double visualHealth = Math.min(visualMax, currentHealth);
		double visualAbsorb = Math.min(visualMax - visualHealth, absorb);

		player.setMaxHealth(visualMax);
		player.setHealth(visualHealth);

		CraftPlayer cp = (CraftPlayer) player;
		cp.getHandle().setAbsorptionHearts((int) visualAbsorb);

		ActionBarManager.update(player);
	}

	public static void damage(Player player, double rawAmount) {
		UUID uuid = player.getUniqueId();
		PlayerStats stats = StatManager.getStats(player);
		double defense = stats.getStat(StatType.DEFENSE);

		double amountEHP = rawAmount * (1.0 + defense / 100.0);

		double ehp = currentEHP.getOrDefault(uuid, EffectiveHealthCalculator.getEffectiveHealth(player));
		double absorb = absorption.getOrDefault(uuid, 0.0);

		if (absorb > 0) {
			double used = Math.min(absorb, amountEHP);
			absorb -= used;
			amountEHP -= used;
		}

		ehp -= amountEHP;

		if (ehp <= 0 && absorb <= 0) {
			player.setHealth(0);
			currentEHP.put(uuid, 0.0);
		} else {
			currentEHP.put(uuid, Math.max(0, ehp));
		}

		absorption.put(uuid, Math.max(0, absorb));
	}

	public static double getCurrentHealth(Player player) {
		double ehp = currentEHP.getOrDefault(player.getUniqueId(), EffectiveHealthCalculator.getEffectiveHealth(player));
		double defense = StatManager.getStat(StatType.DEFENSE, player);
		return ehp / (1.0 + defense / 100.0);
	}

	public static double getCurrentMana(Player player) {
		return currentMana.getOrDefault(player.getUniqueId(), StatManager.getStat(StatType.INTELLIGENCE, player));
	}

	public static double getCurrentAbsorption(Player player) {
		return absorption.getOrDefault(player.getUniqueId(), 0.0);
	}

	public static void setAbsorption(Player player, double amount) {
		absorption.put(player.getUniqueId(), amount);
	}

	public static void addAbsorption(Player player, double amount) {
		UUID uuid = player.getUniqueId();
		absorption.put(uuid, getCurrentAbsorption(player) + amount);
	}

	public static void clear(Player player) {
		UUID uuid = player.getUniqueId();
		currentEHP.remove(uuid);
		currentMana.remove(uuid);
		absorption.remove(uuid);
	}

	public static boolean subtractMana(Player player, double amount) {
		UUID uuid = player.getUniqueId();
		double current = getCurrentMana(player);
		if (current < amount) return false;

		currentMana.put(uuid, current - amount);
		return true;
	}

}
