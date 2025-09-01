package me.zerosio.mayors.pads;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.zerosio.utility.HologramUtil;

public class TeleportPads {

    public static void register() {
        // Community Centre -> Election Room
        new TeleportPad(
            new Location(Bukkit.getWorld("world"), 4.5, 72, -115.5, 0f, 0f),
            new Location(Bukkit.getWorld("world"), 5.5, 33, -108.5, 180f, 0f),
            "§dWarped to §bElection Room§d!"
        );

        // Election Room -> Community Centre 
        new TeleportPad(
            new Location(Bukkit.getWorld("world"), 5.5, 33, -106.5, 180f, 0f),
            new Location(Bukkit.getWorld("world"), 4.5, 72, -110.5, 0f, 0f),
            "§dWarped to §bCommunity Centre§d!"
        );

        // Holograms
        HologramUtil.createHologram(new Location(Bukkit.getWorld("world"), 4.5, 72, -116),
                "§d✦ Warp To §bElection Room");
        HologramUtil.createHologram(new Location(Bukkit.getWorld("world"), 5.5, 33, -106),
                "§d✦ Warp To §bCommunity Centre");
    }
}
