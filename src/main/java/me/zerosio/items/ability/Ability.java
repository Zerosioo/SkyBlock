package me.zerosio.items.ability;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

public interface Ability {
	String getAbilityName();
	AbilityType getType();
	void activate(Player player, PlayerInteractEvent event);

	default int getManaCost() {
		return 0;
	}

	default int getCooldownSeconds() {
		return 0;
	}

	default List<String> getAbilityDescription() {
		return Arrays.asList("§7No description.");
	}

	interface Holder {
		List<Ability> getAbilities();
	}
}
