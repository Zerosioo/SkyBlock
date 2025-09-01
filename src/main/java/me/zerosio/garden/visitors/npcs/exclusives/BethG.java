package me.zerosio.garden.visitors.npcs.exclusives;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.zerosio.garden.visitors.build.GardenVisitor;
import me.zerosio.items.itemtype.Rarity;
import me.zerosio.npcs.build.AbstractNPC;

public class BethG extends AbstractNPC implements GardenVisitor {

    public BethG() {
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
        return "ewogICJ0aW1lc3RhbXAiIDogMTc1Mjg1ODc2MDU1MCwKICAicHJvZmlsZUlkIiA6ICJmOWQ0YzBkNDY2OWY0YTVkODg1MGZjYTNkMzQ0YjY2NSIsCiAgInByb2ZpbGVOYW1lIiA6ICJQYWRhbmdHYXMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2FiYjJjMDQ2MzVhMjliMDhlMTYxZDg1OWIxMWJkYWRiNzVhMjlkM2M2NGRmN2U4NGExMDhjMzdmN2Y4M2MwIgogICAgfQogIH0KfQ==";
    }

    @Override
    public String getSignature() {
        return "Uaz6TnCCqycjMR+7JO8ZetpqEaq4L3+HaholxqAyyYTIq3VsUmODnwzuNaBIFUa3MMsuASO3wkqlu/0OcSs52os6digYIBVrYFfpFnhfaRE/dPwb4byi97Bd/83sOgbfsQMyjpZacbCq2+90a3xXCZBO+uOz+IOeHlv7gXg8rDgfJUc9YlBi0cP4QZrVKFrAPBjY0LmLgDqKj1NGARXM6L4cQUeO1NonHQzMlOuX7uTG4deh1RIJYaIqffa1gQ+4yQMsOSnY/g8TK37P5O8J04nhlvZjh8+UjjrJ1mgcWX9JcfPa8mYH3p2Bm72aqrDijxleXukX6SWA193h59P/zmcsVy5xfpKTd+yhcs/2ElACQcyaQnTo2hZJw+9R4y3hce0qDzYVZXgnNwNoMbvnlyNPcWkRxCwzkgRNpfouIT0UxPYRX5DuyIslx4s9M/5vDRd5ADL/t4GKxzhF1/R9/q3DAQLNu1otS/SjkJ1yl1OAgmi2P6c+R4hqMXqgEU6JWiXGNwYkodw4WEbwKJmdzc7JVfdARKa1meOL3aNSMnhoKLfRW6jhfHA0esV91EsVNArVrq6tV1hbg3+rcBiIP6PNxlMHo0/6lWlCsBW2/1JD7kfC2mN27iejyncShatyFVwubMqT+T2lPCGZfy9nEGWOzdSNildE4AX+/0A0y0k=";
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
        return "Beth";
    }

    @Override
    public AbstractNPC createNpc() {
        return this;
    }
}
