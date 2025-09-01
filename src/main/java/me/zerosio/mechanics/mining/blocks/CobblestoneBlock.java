package me.zerosio.mechanics.mining.blocks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.zerosio.mechanics.mining.MiningBlock;

import java.util.ArrayList;

public class CobblestoneBlock extends MiningBlock {
	public int blockStrength() {
		return 30;
	}
	public int getInstaMineSpeed() {
		return 1800;
	}
	public Material getType() {
		return Material.COBBLESTONE;
	}
	public int getBreakingPower() {
		return 1;
	}
	public int getRequiredBreakingPower() {
		return 2;
	}

	public ArrayList<ItemStack> getDrops(Player player) {
		ArrayList<ItemStack> drops = new ArrayList<>();
		drops.add(new ItemStack(Material.COBBLESTONE, 1));
		return drops;
	}
}
