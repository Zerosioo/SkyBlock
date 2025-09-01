package me.zerosio.user.statistics.calculators;

import org.bukkit.entity.Player;

import me.zerosio.user.statistics.StatManager;
import me.zerosio.user.statistics.StatRegenerator;
import me.zerosio.user.statistics.StatType;

public class EffectiveHealthCalculator {
    public static double getEffectiveHealth(Player player) {
        double healthStat = StatManager.getStat(StatType.HEALTH, player);
        double currentAbsorb = StatRegenerator.getCurrentAbsorption(player);
        double defenseStat = StatManager.getStat(StatType.DEFENSE, player);
        double totalHealth = healthStat + currentAbsorb;
        return totalHealth * (1.0 + defenseStat / 100.0);
    }
}
