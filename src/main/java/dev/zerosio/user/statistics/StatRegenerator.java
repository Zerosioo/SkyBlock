package dev.zerosio.user.statistics;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import dev.zerosio.Core;
import dev.zerosio.utility.ActionBar;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatRegenerator {

    private static final Map<UUID, Double> currentHealth = new HashMap<>();
    private static final Map<UUID, Double> currentMana = new HashMap<>();

    public static void start(Core plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    regenerateStats(player);
                }
            }
        }.runTaskTimer(plugin, 0L, 20L); 
    }

    private static void regenerateStats(Player player) {
        PlayerStats stats = StatManager.getStats(player);
        UUID uuid = player.getUniqueId();

        double maxHealth = stats.getStat(StatType.HEALTH);
        double maxMana = stats.getStat(StatType.INTELLIGENCE);

        currentHealth.putIfAbsent(uuid, maxHealth);
        currentMana.putIfAbsent(uuid, maxMana);

        double health = currentHealth.get(uuid);
        double mana = currentMana.get(uuid);

        double flatRegen = stats.getStat(StatType.HEALTH_REGEN);
        double percentRegen = stats.getStat(StatType.HEALTH_PERCENT_REGEN);
        double vitality = stats.getStat(StatType.VITALITY);
        double mending = stats.getStat(StatType.MENDING);

        double totalHealthRegen = flatRegen + (percentRegen / 100 * maxHealth)
                + (vitality * 0.05) + (mending * 0.08);

        health = Math.min(maxHealth, health + totalHealthRegen);
        mana = Math.min(maxMana, mana + (maxMana * 0.02));

        currentHealth.put(uuid, health);
        currentMana.put(uuid, mana);

        ActionBar.send(player, "§c❤ " + (int) health + "/" + (int) maxHealth + "   §b✎ " + (int) mana + "/" + (int) maxMana);
    }

    public static void damage(Player player, double amount) {
        UUID uuid = player.getUniqueId();
        double health = currentHealth.getOrDefault(uuid, StatManager.getStat(StatType.HEALTH, player));
        health = Math.max(0, health - amount);
        currentHealth.put(uuid, health);
    }

    public static double getCurrentHealth(Player player) {
        return currentHealth.getOrDefault(player.getUniqueId(), StatManager.getStat(StatType.HEALTH, player));
    }

    public static double getCurrentMana(Player player) {
        return currentMana.getOrDefault(player.getUniqueId(), StatManager.getStat(StatType.INTELLIGENCE, player));
    }
}
