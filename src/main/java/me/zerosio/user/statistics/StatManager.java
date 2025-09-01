package me.zerosio.user.statistics;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class StatManager {

    private static final Map<Player, PlayerStats> cache = new HashMap<>();

    public static double getStat(StatType type, Player player) {
        PlayerStats stats = cache.computeIfAbsent(player, PlayerStats::new);
        return stats.getStat(type);
    }

    public static PlayerStats getStats(Player player) {
        return cache.computeIfAbsent(player, PlayerStats::new);
    }

    public static void recalculate(Player player) {
        cache.put(player, new PlayerStats(player));
    }

    public static void clear(Player player) {
        cache.remove(player);
    }
}
