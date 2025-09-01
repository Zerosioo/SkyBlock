package me.zerosio.slayer.vampire;

import me.zerosio.Core;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class BlockPhaseEffect {

    private final LivingEntity boss;
    private final Map<Block, MaterialData> originalBlocks = new HashMap<>();
    private final Set<Block> safeZoneBlocks = new HashSet<>();

    private final List<MaterialData> phaseCycle = Arrays.asList(
            new MaterialData(Material.STAINED_CLAY, (byte) 4),
            new MaterialData(Material.STAINED_CLAY, (byte) 5),
            new MaterialData(Material.STAINED_CLAY, (byte) 14),
            new MaterialData(Material.REDSTONE_BLOCK),
            new MaterialData(Material.COAL_BLOCK)
    );

    private boolean active = false;
    private int stage = 0;
    private BukkitTask task;

    public BlockPhaseEffect(LivingEntity boss) {
        this.boss = boss;
    }

    public void startPhase() {
        if (active) return;
        active = true;

        World world = boss.getWorld();
        Location center = boss.getLocation().getBlock().getLocation();

        for (int x = -13; x <= 13; x++) {
            for (int z = -13; z <= 13; z++) {
                if (Math.sqrt(x * x + z * z) > 13) continue;

                for (int y = -1; y <= 1; y++) {
                    Location loc = center.clone().add(x, y, z);
                    Block block = world.getBlockAt(loc);
                    if (block.getType() == Material.AIR) continue;

                    Block above = block.getLocation().add(0, 1, 0).getBlock();
                    if (above.getType() == Material.AIR) {
                        originalBlocks.put(block, new MaterialData(block.getType(), block.getData()));
                        break;
                    }
                }
            }
        }

        selectSafeZone();

        task = new BukkitRunnable() {
            @Override
            public void run() {
                if (stage >= phaseCycle.size()) {
                    resetBlocks();
                    cancel();
                    return;
                }

                MaterialData data = phaseCycle.get(stage);

                for (Block block : originalBlocks.keySet()) {
                    if (safeZoneBlocks.contains(block)) {
                        if (stage == 0 || stage == 1) {
                            block.setType(data.getItemType());
                            block.setData(data.getData());
                        }
                        continue;
                    }

                    if (stage >= 2) {
                        block.setType(data.getItemType());
                        block.setData(data.getData());
                    }
                }

                stage++;
            }
        }.runTaskTimer(Core.getPlugin(Core.class), 0L, 20L);
    }

    private void selectSafeZone() {
        safeZoneBlocks.clear();
        Location center = boss.getLocation().getBlock().getLocation();

        Random rand = new Random();
        int sx, sz;
        do {
            sx = rand.nextInt(27) - 13;
            sz = rand.nextInt(27) - 13;
        } while (Math.sqrt(sx * sx + sz * sz) > 11);

        Location safeCenter = center.clone().add(sx, 0, sz);

        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (Math.sqrt(x * x + z * z) > 2) continue;

                for (int y = -1; y <= 1; y++) {
                    Block block = safeCenter.clone().add(x, y, z).getBlock();
                    if (originalBlocks.containsKey(block)) {
                        safeZoneBlocks.add(block);
                    }
                }
            }
        }
    }

    public void resetBlocks() {
        for (Map.Entry<Block, MaterialData> entry : originalBlocks.entrySet()) {
            Block block = entry.getKey();
            MaterialData original = entry.getValue();
            block.setType(original.getItemType());
            block.setData(original.getData());
        }
        originalBlocks.clear();
        safeZoneBlocks.clear();
        stage = 0;
        active = false;
    }

    public void endPhase() {
        if (task != null) task.cancel();
        resetBlocks();
    }

    public boolean isActive() {
        return active;
    }
}
