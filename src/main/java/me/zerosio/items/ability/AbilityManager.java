package me.zerosio.items.ability;

import me.zerosio.user.statistics.StatRegenerator;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AbilityManager {

    private static final Map<UUID, Map<String, Long>> cooldowns = new HashMap<>();

    public static boolean isOnCooldown(Player player, String abilityId) {
        Map<String, Long> playerCooldowns = cooldowns.getOrDefault(player.getUniqueId(), new HashMap<>());
        long time = playerCooldowns.getOrDefault(abilityId, 0L);
        return System.currentTimeMillis() < time;
    }

    public static long getRemainingCooldown(Player player, String abilityId) {
        Map<String, Long> playerCooldowns = cooldowns.getOrDefault(player.getUniqueId(), new HashMap<>());
        long endTime = playerCooldowns.getOrDefault(abilityId, 0L);
        return Math.max(0, endTime - System.currentTimeMillis());
    }

    public static void setCooldown(Player player, String abilityId, int seconds) {
        cooldowns.computeIfAbsent(player.getUniqueId(), id -> new HashMap<>())
                 .put(abilityId, System.currentTimeMillis() + (seconds * 1000L));
    }

    public static boolean takeMana(Player player, int amount) {
        return StatRegenerator.subtractMana(player, amount);
    }

    public static void reset(Player player) {
        cooldowns.remove(player.getUniqueId());
    }
}
