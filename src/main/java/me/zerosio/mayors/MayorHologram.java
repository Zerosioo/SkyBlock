package me.zerosio.mayors;

import me.zerosio.Core;
import me.zerosio.time.SkyblockYear;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class MayorHologram {

    private static final Location LOCATION = new Location(Bukkit.getWorld("world"), 5.5, 34.1, -119.5);
    private static final List<ArmorStand> STANDS = new ArrayList<>();
    private static BukkitTask updateTask;

    public static void spawnVotingHologram(ElectionTiming met) {
        List<String> lines = getLines(met);
        World world = LOCATION.getWorld();
        double y = LOCATION.getY();

        if (STANDS.isEmpty()) {
            for (String line : lines) {
                Location lineLoc = new Location(world, LOCATION.getX(), y, LOCATION.getZ());
                ArmorStand stand = (ArmorStand) world.spawnEntity(lineLoc, EntityType.ARMOR_STAND);
                stand.setCustomNameVisible(true);
                stand.setCustomName(line);
                stand.setGravity(false);
                stand.setVisible(false);
                stand.setSmall(true);
                STANDS.add(stand);
                y -= 0.25;
            }
        } else {
            for (int i = 0; i < STANDS.size(); i++) {
                if (i < lines.size()) {
                    STANDS.get(i).setCustomName(lines.get(i));
                }
            }
        }

        if (updateTask == null) {
            updateTask = Bukkit.getScheduler().runTaskTimer(Core.getPlugin(Core.class), () -> {
                List<String> updated = getLines(met);
                for (int i = 0; i < STANDS.size(); i++) {
                    if (i < updated.size()) {
                        STANDS.get(i).setCustomName(updated.get(i));
                    }
                }
            }, 20L, 20L);
        }
    }

    public static void remove() {
        for (ArmorStand stand : STANDS) {
            stand.remove();
        }
        STANDS.clear();

        if (updateTask != null) {
            updateTask.cancel();
            updateTask = null;
        }
    }

    private static List<String> getLines(ElectionTiming met) {
        return Arrays.asList(
            "§e§lCLICK TO VOTE",
            "§eTime left: " + met.formatDuration(met.getRemainingCloseTime()),
            "§bYear " + SkyblockYear.getCurrentYear(),
            "§e§lMAYOR ELECTIONS"
        );
    }
}
