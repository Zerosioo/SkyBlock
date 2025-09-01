package me.zerosio.commands.schem;

import me.zerosio.commands.builder.CommandBase;
import me.zerosio.rank.PlayerRank;
import me.zerosio.Core;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class RemoveStoneCommand extends CommandBase {

    private static final Map<UUID, BukkitRunnable> activeTasks = new HashMap<>();

    @Override
    public String getName() {
        return "removestone";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("clearsmooth", "chrs");
    }

    @Override
    public PlayerRank getRequiredRank() {
        return PlayerRank.ADMIN;
    }

    @Override
    public String getDescription() {
        return "Removes all smooth stone (data 0) starting near the player.";
    }

    @Override
    public String getUsage() {
        return "/removestone or /chrs cancel";
    }

    @Override
    public void execute(Player player, String[] args) {
        UUID uuid = player.getUniqueId();

        // Cancel task if requested
        if (args.length > 0 && (args[0].equalsIgnoreCase("cancel") || args[0].equalsIgnoreCase("c"))) {
            if (activeTasks.containsKey(uuid)) {
                activeTasks.get(uuid).cancel();
                activeTasks.remove(uuid);
                player.sendMessage("§cStone removal cancelled.");
            } else {
                player.sendMessage("§7You have no active removal task.");
            }
            return;
        }

        if (activeTasks.containsKey(uuid)) {
            player.sendMessage("§cYou already have an active stone removal task. Use §e/chrs cancel §cto stop it.");
            return;
        }

        World world = player.getWorld();
        int centerX = player.getLocation().getChunk().getX();
        int centerZ = player.getLocation().getChunk().getZ();
        player.sendMessage("§eRemoving smooth stone (data 0) near you in §a" + world.getName() + "§e...");

        List<Chunk> chunks = generateSpiralChunks(world, centerX, centerZ, 100);

        BukkitRunnable task = new BukkitRunnable() {
            int index = 0;

            @Override
            public void run() {
                if (index >= chunks.size()) {
                    player.sendMessage("§aFinished removing all smooth stone.");
                    activeTasks.remove(uuid);
                    cancel();
                    return;
                }

                Chunk chunk = chunks.get(index);
                if (!chunk.isLoaded()) chunk.load();

                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        for (int y = 0; y < world.getMaxHeight(); y++) {
                            Block block = chunk.getBlock(x, y, z);
                            if (block.getType() == Material.STONE && block.getData() == (byte) 0) {
                                block.setType(Material.AIR, false);
                            }
                        }
                    }
                }

                index++;
            }
        };

        activeTasks.put(uuid, task);
        task.runTaskTimer(Core.getPlugin(Core.class), 1L, 1L);
    }

    private List<Chunk> generateSpiralChunks(World world, int centerX, int centerZ, int radius) {
        List<Chunk> chunks = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        int x = 0, z = 0, dx = 0, dz = -1;
        int max = radius * radius;

        for (int i = 0; i < max; i++) {
            int chunkX = centerX + x;
            int chunkZ = centerZ + z;
            String key = chunkX + "," + chunkZ;

            if (!visited.contains(key)) {
                visited.add(key);
                chunks.add(world.getChunkAt(chunkX, chunkZ));
            }

            if (x == z || (x < 0 && x == -z) || (x > 0 && x == 1 - z)) {
                int tmp = dx;
                dx = -dz;
                dz = tmp;
            }

            x += dx;
            z += dz;
        }

        return chunks;
    }
}
