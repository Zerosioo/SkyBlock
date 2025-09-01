package me.zerosio.mechanics.mining;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.zerosio.Core;
import me.zerosio.items.itemtype.ItemStatistics;
import me.zerosio.items.itemtype.SItem;
import me.zerosio.user.profiledata.UserLevel;
import me.zerosio.user.statistics.StatManager;
import me.zerosio.user.statistics.StatType;

import java.util.ArrayList;

public abstract class MiningBlock {

	// @Getter
	private static final ArrayList<MiningBlock> blocks = new ArrayList<>();
	protected Block block = null;

	public abstract int blockStrength();
	public abstract int getInstaMineSpeed();
	public abstract Material getType();
	public abstract ArrayList<ItemStack> getDrops(Player player);
	public int getRequiredBreakingPower() {
		return 0;
	}

	public boolean canBreak(Player player) {
		 int playerPower = 0;
		 
		 
		 ItemStack itemStack = player.getItemInHand();
        SItem sItem = SItem.getItem(itemStack);
        if ((sItem instanceof ItemStatistics)) {
        	playerPower = ((ItemStatistics) sItem).getBreakingPower();
        }
		
		return playerPower >= getRequiredBreakingPower();
	}

	public static ArrayList<MiningBlock> getBlocks() {
		return blocks;
	}

	public long regenTime() {
		return 5 * 20;
	}
	public Material blockIfBroken() {
		return Material.BEDROCK;
	}
	public Material resetType() {
		return getType();
	}
	public double getSoftCap() {
		return Math.round((6d + 2d / 3d) * blockStrength());
	}

	public int getMiningTicks(Player player) {
		double miningSpeed = StatManager.getStat(StatType.MINING_SPEED, player);
		double softCap = getSoftCap();
		if (miningSpeed > softCap) miningSpeed = softCap;
		double miningTime = (blockStrength() * 30) / miningSpeed;
		return Math.max(1, (int) miningTime);
	}

	public void breakBlock(Block b, Player player) {
		this.block = b;

		if (player.getWorld().getName() == "crystal_hollows") {
			b.setType(Material.AIR);
			b.getState().update();
			dropItems(player);
			return;
		}

		b.setType(blockIfBroken());

		new BukkitRunnable() {
			@Override
			public void run() {
				b.setType(resetType());
				b.getState().update();
			}
		} .runTaskLater(Core.getInstance(), regenTime());

		dropItems(player);
	}

	protected void dropItems(Player player) {
		for (ItemStack item : getDrops(player)) {
			if (item == null || item.getType() == Material.AIR || item.getAmount() <= 0) continue;
			if (UserLevel.getLevel(player) == 6 || UserLevel.getLevel(player) > 6) player.getInventory().addItem(item);
			else block.getWorld().dropItemNaturally(block.getLocation(), item);
		}
	}

	public void reset() {
		if (block != null) block.setType(getType());
	}
}
