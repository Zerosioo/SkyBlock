package me.zerosio.mayors.barrier;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.material.Lever;
import org.bukkit.material.MaterialData;

import me.zerosio.zones.ZoneTypes;

public class ElectionBlockade {
    public static Location middle = new Location(Bukkit.getWorld("world"), 0, 0, 0);
    
    public static void placeBarricade() {
        World world = middle.getWorld();
        int x = middle.getBlockX();
        int y = middle.getBlockY();
        int z = middle.getBlockZ();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = 0; dy < 4; dy++) {
                Block b = new Location(world, x + dx, y + dy, z).getBlock();
                b.setType(Material.LOG_2);
                b.setData((byte) 1);
            }
        }

        setLever(new Location(world, x, y + 1, z), BlockFace.SOUTH, true);

        setLever(new Location(world, x, y + 2, z), BlockFace.SOUTH, false);
    }

    public static void removeBarricade() {
        World world = middle.getWorld();
        int x = middle.getBlockX();
        int y = middle.getBlockY();
        int z = middle.getBlockZ();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = 0; dy < 4; dy++) {
                new Location(world, x + dx, y + dy, z).getBlock().setType(Material.AIR);
            }
        }

        new Location(world, x, y + 1, z).getBlock().setType(Material.AIR);
        new Location(world, x, y + 2, z).getBlock().setType(Material.AIR);
        
        for (Player player : Bukkit.getOnlinePlayers()) {
        	if (ZoneTypes.getLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ()) == ZoneTypes.ELECTION_ROOM) {
        		player.teleport(new Location(Bukkit.getWorld("world"), 4.5, 72, -110.5, 0f, 0f));
        		player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 1f);
        	}
        }
    }

    private static void setLever(Location loc, BlockFace face, boolean powered) {
        Block block = loc.getBlock();
        block.setType(Material.LEVER);

        Lever lever = new Lever();
        lever.setFacingDirection(face);
        lever.setPowered(powered);

        block.setData(lever.getData());
    }
}
