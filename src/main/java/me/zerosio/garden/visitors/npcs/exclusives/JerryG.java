package me.zerosio.garden.visitors.npcs.exclusives;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.zerosio.garden.visitors.build.GardenVisitor;
import me.zerosio.items.itemtype.Rarity;
import me.zerosio.npcs.build.AbstractNPC;

public class JerryG extends AbstractNPC implements GardenVisitor {

    public JerryG() {
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
    public EntityType getEntityType() {
        return EntityType.VILLAGER;
    }

    @Override
    public int getRequiredGardenLevel() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public String getName() {
        return "Jerry";
    }

    @Override
    public AbstractNPC createNpc() {
        return this;
    }
}
