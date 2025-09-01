package me.zerosio.commands.schem;

import me.zerosio.commands.builder.CommandBase;
import me.zerosio.rank.PlayerRank;
import me.zerosio.schematic.SchematicManager;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.*;

public class SaveCommand extends CommandBase {

    private final Map<UUID, Location> pos1 = new HashMap<>();
    private final Map<UUID, Location> pos2 = new HashMap<>();

    @Override
    public String getName() {
        return "saveschem";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("sschem", "save56tb", "ss", "saveservice");
    }

    @Override
    public PlayerRank getRequiredRank() {
        return PlayerRank.ADMIN;
    }

    @Override
    public String getDescription() {
        return "Save a schematic to .56tb format";
    }

    @Override
    public String getUsage() {
        return "/saveschem <name> or /saveschem pos1|pos2";
    }

    @Override
    public void execute(Player player, String[] args) {
        UUID uuid = player.getUniqueId();

        if (args.length == 1) {
            String arg = args[0].toLowerCase();

            if (arg.equals("pos1") || arg.equals("p1")) {
                Location loc = player.getLocation().getBlock().getLocation();
                pos1.put(uuid, loc);
                player.sendMessage("§dPosition 1 set to §e" + format(loc));
                showParticles(player, loc, pos2.get(uuid));
                return;
            }

            if (arg.equals("pos2") || arg.equals("p2")) {
                Location loc = player.getLocation().getBlock().getLocation();
                pos2.put(uuid, loc);
                player.sendMessage("§dPosition 2 set to §e" + format(loc));
                showParticles(player, pos1.get(uuid), loc);
                return;
            }

            Location p1 = pos1.get(uuid);
            Location p2 = pos2.get(uuid);
            if (p1 == null || p2 == null) {
                player.sendMessage("§cSet both positions using /saveschem pos1 and pos2.");
                return;
            }

            try {
                SchematicManager.saveSchematic(args[0], p1, p2);
               // SchematicSaver.save(args[0], p1, p2);
                player.sendMessage("§aSchematic saved as §e" + args[0] + ".zerosio§a.");

                // Clear selection
                pos1.remove(uuid);
                pos2.remove(uuid);
                player.sendMessage("§7Selection cleared.");
            } catch (IOException e) {
                player.sendMessage("§cFailed to save schematic.");
                e.printStackTrace();
            }
        } else {
            player.sendMessage("§cUsage: /saveschem <name>");
        }
    }

    private String format(Location loc) {
        return "(" + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + ")";
    }

    private void showParticles(Player player, Location l1, Location l2) {
        if (l1 == null || l2 == null) return;

        int minX = Math.min(l1.getBlockX(), l2.getBlockX());
        int minY = Math.min(l1.getBlockY(), l2.getBlockY());
        int minZ = Math.min(l1.getBlockZ(), l2.getBlockZ());
        int maxX = Math.max(l1.getBlockX(), l2.getBlockX());
        int maxY = Math.max(l1.getBlockY(), l2.getBlockY());
        int maxZ = Math.max(l1.getBlockZ(), l2.getBlockZ());

        World world = player.getWorld();

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    if ((x == minX || x == maxX || y == minY || y == maxY || z == minZ || z == maxZ)
                        && (x + y + z) % 3 == 0) {
                        Location particleLoc = new Location(world, x + 0.5, y + 0.5, z + 0.5);
                        world.playEffect(particleLoc, Effect.CLOUD, 0);
                    }
                }
            }
        }
    }
}
