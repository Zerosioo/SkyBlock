package me.zerosio.mechanics.mining.blocks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.zerosio.mechanics.mining.MiningBlock;

import java.util.ArrayList;

public class StoneBlock extends MiningBlock {
	public int blockStrength() {
		return 15;
	}
	public int getInstaMineSpeed() {
		return 450;
	}
	public Material getType() {
		return Material.STONE;
	}
	public int getBreakingPower() {
		return 0;
	}
	public int getRequiredBreakingPower() {
		return 0;
	}

	public ArrayList<ItemStack> getDrops(Player player) {
		ArrayList<ItemStack> drops = new ArrayList<>();
		drops.add(new ItemStack(Material.COBBLESTONE, 1));
		return drops;
	}
	
	@Override
	public Material blockIfBroken() {
		return Material.COBBLESTONE;
	}
}
