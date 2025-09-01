package me.zerosio.items.itemlist.drill;

import me.zerosio.items.itemtype.*;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class DivanDrill extends SItem implements ItemStatistics, Museum {

	@Override
	public String getName() {
		return "Divan's Drill";
	}

	@Override
	public List<String> getLore() {
		return Arrays.asList("§7Fuel Tank: §cNot Installed",
			   "§7§7Increases fuel capacity with",
			   "§7part installed.",
			   "",
			   "§7Drill Engine: §cNot Installed",
			   "§7§7Increases §6⸕ Mining Speed",
			   "§6§7with part installed.",
			   "",
			   "§7Upgrade Module: §cNot Installed",
			   "§7§7Applies a passive upgrade with",
			   "§7part installed.",
			   "",
			   "§7Apply Drill Parts to this Drill",
			   "§7by talking to a §2Drill",
			   "§2Mechanic§7!",
			   "",
			   "§7Fuel: §23,000§8/3k");
	}

	@Override
	public String getId() {
		return "DIVAN_DRILL";
	}

	@Override
	public Material getMaterial() {
		return Material.PRISMARINE_SHARD;
	}

	@Override
	public ItemType getItemType() {
		return ItemType.DRILL;
	}

	@Override
	public Rarity getRarity() {
		return Rarity.MYTHIC;
	}

	@Override
	public boolean isEnchanted() {
		return true;
	}

	@Override
	public boolean isMuseum() {
		return true;
	}

	@Override
	public Gamestage getGamestage() {
		return Gamestage.MASTER;
	}

	@Override
	public int getDamage() {
		return 75;
	}

	@Override
	public int getBreakingPower() {
		return 10;
	}

	@Override
	public int getMiningFortune() {
		return 150;
	}

	@Override
	public int getMiningSpeed() {
		return 1800;
	}

	@Override
	public boolean reforgeable() {
		return true;
	}

}
