package me.zerosio.warp;

import org.bukkit.Location;

public class WarpData {
    private final String id;
    private final WarpType type;
    private final Location location;

    public WarpData(String id, WarpType type, Location location) {
        this.id = id;
        this.type = type;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public WarpType getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }
}
