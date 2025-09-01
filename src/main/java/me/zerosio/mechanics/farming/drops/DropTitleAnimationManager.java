package me.zerosio.mechanics.farming.drops;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class DropTitleAnimationManager {

    private static final HashMap<UUID, BukkitRunnable> activeAnimations = new HashMap<>();

    public static void stop(Player player) {
        BukkitRunnable task = activeAnimations.remove(player.getUniqueId());
        if (task != null) task.cancel();
    }

    public static void play(Player player, DropRarity rarity, String itemName, int amount) {
        stop(player); // Cancel previous animation
        
        String handItemName = player.getItemInHand().getItemMeta().getDisplayName();
        
        if (handItemName == "AIR" || handItemName == null) {
        	handItemName = "Unknown";
        }
        
        player.sendMessage(rarity.getColor() + rarity.getTitle() + "! §e" + handItemName + " §edropped §a" + amount + "x " + itemName + "§e!");

        DropTitleAnimation animation = new DropTitleAnimation(player, rarity, itemName, amount);
        animation.runTaskTimer(me.zerosio.Core.getInstance(), 0, 10); // 10 tick = 0.5s
        activeAnimations.put(player.getUniqueId(), animation);
    }
}
