package me.zerosio.npcs;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.zerosio.npcs.build.AbstractNPC;

public class Template extends AbstractNPC {

    public Template() {
        super("");
    }

    @Override
    public void onInteract(Player player) {
        if (isFirstInteraction(player)) {
            // TODO: Handle first-time interaction
        }
    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), -41, 70, -64);
    }

    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§f", "§e§lCLICK");
    }

    @Override
    public String getTexture() {
        return "";
    }

    @Override
    public String getSignature() {
        return "";
    }

}
