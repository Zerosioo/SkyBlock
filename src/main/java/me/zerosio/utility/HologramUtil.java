package me.zerosio.utility;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class HologramUtil {

    private static final List<ArmorStand> staticHolograms = new ArrayList<>();
    private static final Map<Location, ArmorStand> dynamicHolograms = new HashMap<>();
    private static final Map<Location, List<ArmorStand>> multiLineDynamicHolograms = new HashMap<>();

    private static final Map<ArmorStand, BukkitRunnable> dynamicTasks = new HashMap<>();
    private static final Map<List<ArmorStand>, BukkitRunnable> multiLineDynamicTasks = new HashMap<>();

    // STATIC HOLOGRAM
    public static void createHologram(Location location, String... lines) {
        double offset = 0;
        for (int i = lines.length - 1; i >= 0; i--) {
            ArmorStand stand = spawnHologramLine(location.clone().add(0, offset, 0), lines[i]);
            staticHolograms.add(stand);
            offset += 0.25;
        }
    }

    // SINGLE LINE CHANGING HOLOGRAM
    public static void createChangingHologram(Location location, JavaPlugin plugin, java.util.function.Supplier<String> textSupplier, long intervalTicks) {
        removeDynamicHologramAt(location);
        ArmorStand stand = spawnHologramLine(location, textSupplier.get());
        staticHolograms.add(stand);
        dynamicHolograms.put(location, stand);

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                if (stand.isDead()) {
                    this.cancel();
                    dynamicTasks.remove(stand);
                    dynamicHolograms.remove(location);
                    return;
                }
                stand.setCustomName(textSupplier.get());
            }
        };
        task.runTaskTimer(plugin, intervalTicks, intervalTicks);
        dynamicTasks.put(stand, task);
    }

    // MULTI LINE CHANGING HOLOGRAM
    public static void createMultiLineChangingHologram(Location location, JavaPlugin plugin, ArrayList<String> initialLines, long intervalTicks, LineUpdater updater) {
        removeMultiLineDynamicHologramAt(location);

        double offset = 0;
        List<ArmorStand> stands = new ArrayList<>();
        for (int i = initialLines.size() - 1; i >= 0; i--) {
            ArmorStand stand = spawnHologramLine(location.clone().add(0, offset, 0), initialLines.get(i));
            staticHolograms.add(stand);
            stands.add(stand);
            offset += 0.25;
        }

        multiLineDynamicHolograms.put(location, stands);

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                ArrayList<String> updatedLines = updater.update();
                if (updatedLines.size() != stands.size()) {
                    this.cancel();
                    multiLineDynamicTasks.remove(stands);
                    multiLineDynamicHolograms.remove(location);
                    return;
                }
                for (int i = 0; i < stands.size(); i++) {
                    ArmorStand stand = stands.get(i);
                    if (stand.isDead()) continue;
                    stand.setCustomName(updatedLines.get(i));
                }
            }
        };
        task.runTaskTimer(plugin, intervalTicks, intervalTicks);
        multiLineDynamicTasks.put(stands, task);
    }

    // Only remove static holograms
    public static void removeStaticHolograms() {
        for (ArmorStand stand : staticHolograms) {
            if (stand != null && !stand.isDead()) {
                stand.remove();
            }
        }
        staticHolograms.clear();
    }

    // Remove single-line dynamic hologram at location
    private static void removeDynamicHologramAt(Location location) {
        ArmorStand stand = dynamicHolograms.remove(location);
        if (stand != null) {
            BukkitRunnable task = dynamicTasks.remove(stand);
            if (task != null) task.cancel();
            if (!stand.isDead()) stand.remove();
        }
    }

    // Remove multi-line dynamic hologram at location
    private static void removeMultiLineDynamicHologramAt(Location location) {
        List<ArmorStand> stands = multiLineDynamicHolograms.remove(location);
        if (stands != null) {
            BukkitRunnable task = multiLineDynamicTasks.remove(stands);
            if (task != null) task.cancel();
            for (ArmorStand stand : stands) {
                if (stand != null && !stand.isDead()) {
                    stand.remove();
                }
            }
        }
    }

    // FULL WIPE (if ever needed)
    public static void removeHolograms() {
        removeStaticHolograms();
        for (Location loc : dynamicHolograms.keySet()) {
            removeDynamicHologramAt(loc);
        }
        for (Location loc : multiLineDynamicHolograms.keySet()) {
            removeMultiLineDynamicHologramAt(loc);
        }
    }

    private static ArmorStand spawnHologramLine(Location loc, String text) {
        ArmorStand stand = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        stand.setGravity(false);
        stand.setVisible(false);
        stand.setCustomName(text);
        stand.setCustomNameVisible(true);
        stand.setSmall(true);
        return stand;
    }

    public interface LineUpdater {
        ArrayList<String> update();
    }
}
