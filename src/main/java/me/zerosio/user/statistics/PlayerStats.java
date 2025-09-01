package me.zerosio.user.statistics;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.zerosio.items.itemtype.ItemStatistics;
import me.zerosio.items.itemtype.SItem;

import java.util.EnumMap;
import java.util.Map;

public class PlayerStats {

	private final Player player;
	private final Map<StatType, Double> stats = new EnumMap<>(StatType.class);

	public PlayerStats(Player player) {
		this.player = player;
		loadBaseStats();
		loadItemStats();
		applyMultipliers();
	}

	private void loadBaseStats() {
		for (StatType type : StatType.values()) {
			stats.put(type, 0.0);
		}
		stats.put(StatType.HEALTH, 100.0);
		stats.put(StatType.INTELLIGENCE, 100.0);
		stats.put(StatType.CRIT_CHANCE, 30.0);
		stats.put(StatType.CRIT_DAMAGE, 50.0);
	}

	private void loadItemStats() {
		PlayerInventory inv = player.getInventory();

		if (inv.getItemInHand() != null && inv.getItemInHand().getType() != Material.AIR) {
			applyItemStats(inv.getItemInHand());
		}

		for (ItemStack armor : inv.getArmorContents()) {
			if (armor != null && armor.getType() != Material.AIR) {
				applyItemStats(armor);
			}
		}
	}

	private void applyItemStats(ItemStack item) {
		SItem sItem = SItem.getItemClass(item);

		// Apply NBT/declared stats from SItem (preferred)
		if (sItem instanceof ItemStatistics) {
			ItemStatistics is = (ItemStatistics) sItem;
			for (StatType type : StatType.values()) {
				double value = is.get(type);
				if (value != 0) {
					stats.put(type, stats.getOrDefault(type, 0.0) + value);
				}
			}
		}

		// Fallback: parse stats from lore if not using NBT-based system
		if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
			for (String line : item.getItemMeta().getLore()) {
				for (StatType type : StatType.values()) {
					if (line.contains(type.getStatSymbol())) {
						String numberStr = line.replaceAll("[^0-9+\\-.]", "");
						try {
							double value = Double.parseDouble(numberStr);
							stats.put(type, stats.getOrDefault(type, 0.0) + value);
						} catch (NumberFormatException ignored) {}
					}
				}
			}
		}
	}


	private void applyMultipliers() {
		double strength = stats.getOrDefault(StatType.STRENGTH, 0.0);
		strength *= 1.10; // example
		stats.put(StatType.STRENGTH, strength);

		double critChance = Math.min(100.0, stats.getOrDefault(StatType.CRIT_CHANCE, 0.0));
		stats.put(StatType.CRIT_CHANCE, critChance);
	}

	public double getStat(StatType type) {
		return stats.getOrDefault(type, 0.0);
	}

	public Map<StatType, Double> getAllStats() {
		return stats;
	}
}
