package me.zerosio.mechanics.mining.blocks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.zerosio.mechanics.mining.MiningBlock;

import java.util.ArrayList;

public class IronBlock extends MiningBlock {
	public int blockStrength() {
		return 30;
	}
	public int getInstaMineSpeed() {
		return 1800;
	}
	public Material getType() {
		return Material.IRON_ORE;
	}
	public int getBreakingPower() {
		return 1;
	}
	public int getRequiredBreakingPower() {
		return 1;
	}

	public ArrayList<ItemStack> getDrops(Player player) {
		ArrayList<ItemStack> drops = new ArrayList<>();
		drops.add(new ItemStack(Material.IRON_ORE, 1));
		return drops;
	}
}
