package me.zerosio.garden.visitors.npcs.uncommon;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.zerosio.garden.visitors.build.GardenVisitor;
import me.zerosio.items.itemtype.Rarity;
import me.zerosio.npcs.build.AbstractNPC;

public class JotraelineGreatforgeG extends AbstractNPC implements GardenVisitor {

    public JotraelineGreatforgeG() {
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
        return new Location(Bukkit.getWorld("world"), 0, 0, 0);
    }

    @Override
    public List<String> getHologramLines() {
        return Arrays.asList(getRarity().getColor() + getName(), "§e§lCLICK");
    }

    @Override
    public String getTexture() {
        return "";
    }

    @Override
    public String getSignature() {
        return "";
    }

    @Override
    public int getRequiredGardenLevel() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.UNCOMMON;
    }

    @Override
    public String getName() {
        return "Jotraeline Greatforge";
    }

    @Override
    public AbstractNPC createNpc() {
        return this;
    }
}
