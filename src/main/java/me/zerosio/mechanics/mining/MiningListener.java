package me.zerosio.mechanics.mining;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.zerosio.Core;
import me.zerosio.user.statistics.StatManager;
import me.zerosio.user.statistics.StatType;

public class MiningListener implements Listener {

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (!player.getWorld().getName().equalsIgnoreCase("dwarven_mines"))
            return;

        MiningBlock miningBlock = getMiningBlockByType(block.getType());
        if (miningBlock == null) return;

        if (!miningBlock.canBreak(player)) {
            event.setCancelled(true);
            return;
        }

        event.setInstaBreak(false);

        double miningSpeed = StatManager.getStat(StatType.MINING_SPEED, player);
        int instaMineSpeed = miningBlock.getInstaMineSpeed();

        if (miningSpeed >= instaMineSpeed) {
            // Instamine
            miningBlock.breakBlock(block, player);
            return;
        }

        int miningTicks = miningBlock.getMiningTicks(player);
        new BukkitRunnable() {
            int tick = 0;
            public void run() {
                if (tick >= miningTicks) {
                    miningBlock.breakBlock(block, player);
                    cancel();
                }
                tick++;
            }
        }.runTaskTimer(Core.getInstance(), 1L, 1L);
    }

    private MiningBlock getMiningBlockByType(Material material) {
        for (MiningBlock block : MiningBlock.getBlocks()) {
            if (block.getType() == material) return block;
        }
        return null;
    }
}
