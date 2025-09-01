package me.zerosio.mayors.pads;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.zerosio.Core;

import java.util.HashSet;
import java.util.Set;

public class TeleportPad {

    private static final Set<TeleportPad> pads = new HashSet<>();

    private final Location from;
    private final Location to;
    private final String message;

    public TeleportPad(Location from, Location to, String message) {
        this.from = from.clone();
        this.to = to.clone();
        this.message = message;
        pads.add(this);
    }

    public static void startMonitoring() {
        Bukkit.getScheduler().runTaskTimer(Core.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Location loc = player.getLocation();
                for (TeleportPad pad : pads) {
                    if (pad.isInsidePad(loc)) {
                        pad.teleport(player);
                        break;
                    }
                }
            }
        }, 1L, 1L);
    }

    private boolean isInsidePad(Location playerLocation) {
        if (!from.getWorld().equals(playerLocation.getWorld())) {
            return false;
        }
        return from.distance(playerLocation) <= 0.5;
    }

    private void teleport(Player player) {
        player.teleport(to);
        player.playSound(to, Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
        if (message != null && !message.isEmpty()) {
            player.sendMessage(message);
        }
    }
}
