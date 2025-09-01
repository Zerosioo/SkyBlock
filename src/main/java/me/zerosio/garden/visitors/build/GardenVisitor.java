package me.zerosio.garden.visitors.build;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.zerosio.items.itemtype.Rarity;
import me.zerosio.npcs.build.AbstractNPC;

import java.util.List;

public interface GardenVisitor {

    int getRequiredGardenLevel();

    Rarity getRarity();

    default List<ItemStack> getWantedItems() {
    	return null;
    }

    default void getUniqueRewards() {
    	
    }

    String getName();
    
    AbstractNPC createNpc();
    
    default void sendAcceptMessage(Player player) {
    	player.sendMessage("§e[NPC] " + getRarity().getColor() + getName() + "§f: " + acceptMessage());
    	player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1f, 1f);
    }
    
    default String acceptMessage() {
    	return "Tanks for accepting my offer UwU. §8(those messages haven't been added for this visitor lol.)";
    }
    
    default void sendDeclineMessage(Player player) {
    	player.sendMessage("§e[NPC] " + getRarity().getColor() + getName() + "§f: " + declineMessage());
    	player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
    }
    
    default String declineMessage() {
    	return "Bruhh why :skull: §8(those messages haven't been added for this visitor lol.)";
    }
}
