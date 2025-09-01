package me.zerosio.garden.visitors.npcs.exclusives;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.zerosio.garden.visitors.build.GardenVisitor;
import me.zerosio.items.itemtype.Rarity;
import me.zerosio.npcs.build.AbstractNPC;

public class MaeveG extends AbstractNPC implements GardenVisitor {

    public MaeveG() {
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
        return "ewogICJ0aW1lc3RhbXAiIDogMTY5NzU2OTY4ODUxNywKICAicHJvZmlsZUlkIiA6ICJmZWYyZDZjYzY5ZGI0ZWM5OWQzYzI5MzBmYzRmNTBhYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsb3Zlbm90d2FyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZhM2NkY2NjOTIyYjY3MDY3MDdmYWM3ZGNlMzQzNzczYmVjNzdkYTE4NTA5MGJjODQyZWM1NDE1ZjcyM2M1ZDMiCiAgICB9CiAgfQp9";
    }

    @Override
    public String getSignature() {
        return "YBH7xUoW+ZppK4c3hqqQ0KAznFAccAn1a57ckYsNAT9ozqAqYhkqE8LeVpcoP4xPejXnzZFk+StIE/JligyIrt1kgdc7ubIzdOF4hw+jyC0TayQUsVSeRD4aJr0QYE07uomgLUpLgtME00ZBBpU15dGbE6hX5GhWo2p8xTysYGiSXgE15sKaI6uWWQGvRPZa39PP7USixduZajkimefypDU2wvAAYpJnLh32kgL6qfKQKFltcFZ8xnEqSmtfXtt4znNgvnEw0VptKt/V/51eU+siwRMQFUwIqwLeTBtdSZNDMSCHjeB031uwBFKy6MpZzI/BSjB1/1HR8BmrjAkYpWGboAjx+uKgJqnQpZS9WmYid0heMpD8xS7VcKhI2xfrcsXn/Vb7SO9g+QBdGlT1R1wT0kFRzKxFcOhJRjjfQ9TRruyqh2yaV3r+IeYPPxmH+e2BlF/o2rbhhSEOAMhmLAGOWW6xtkjJ/8U/ogfSs9K1ZZo3uaPzbRuy2NTyXWC6MS0HyazDf+VWlGhY4igRsGbINMLv4fa18Mo+WaXcahikKBM2xuA6A81N3cjEi6UjSKo/DIqYKIdwirPsXVRHrIQfyiqMMbVNTeWneE0K0BsHoa7O8X15sV7ybimhEi3W0y4c1n6/iQf9auMUP9tTivBUyjn+E/XnKRYkueN2fPs=";
    }

    @Override
    public int getRequiredGardenLevel() {
        return 12;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.MYTHIC;
    }

    @Override
    public String getName() {
        return "Maeve";
    }

    @Override
    public AbstractNPC createNpc() {
        return this;
    }
}
