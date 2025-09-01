package me.zerosio.garden.visitors.npcs.uncommon;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.zerosio.garden.visitors.build.GardenVisitor;
import me.zerosio.items.itemtype.Rarity;
import me.zerosio.npcs.build.AbstractNPC;

public class AdventurerG extends AbstractNPC implements GardenVisitor {

    public AdventurerG() {
        super("Adventurer");
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
        return Arrays.asList(getName(), "§e§lCLICK");
    }

    @Override
    public String getTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYxOTAwNTE5NDkzNCwKICAicHJvZmlsZUlkIiA6ICJiYjdjY2E3MTA0MzQ0NDEyOGQzMDg5ZTEzYmRmYWI1OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsYXVyZW5jaW8zMDMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjMwN2IwMjJhOGQ0M2NiZTA1ODIxMDM0MGRjMmQ1YWE1ZGQ0YTRkMjk5YTNhOWI1MjM4ZmFiY2QxYmMzMGY3NiIKICAgIH0KICB9Cn0=";
    }

    @Override
    public String getSignature() {
        return "ptaQtcV3bDRoRSN7Ix50CQOvSOcWBFydHmDasDAKQA+DQYrFm3CcKvaY/gdwkzr3MfwkdLerXUdmISIIj3iaDc0lOHTgoMo5txe+c9CtGxgYNsPhtWlNNgdTzwoET9ZNkORKEmM7qL8wc7LrLXHQqtFvEUlnjE+rvV7KhQzI1Ogeo4LmZY6DWGWEGjfA36FYZ25W0BuN67mKuJOfmeXJmrElNPq8TwZ3gkCwz/jxFxM2R3+J3mNHQlLNqmS4v66ET9Oij1YJ4sqJ8ZzHQpgWlR2iTEaWm4PbkS87lto9SOQo/Z7L20lutMiu6ROGRW2yMq/TE/yHX8pqfplnyMOHwK8ags4o4tIXePQY3BgqtKghLjGesUUsGOi4PZJRPzEQBhsh1VT1wXlJROwfXN+jk3q2XXKXxyPIUAgY+MsKE0c+hs6NoEuP3phNofn3no1kLkDgTEuHKFBajJFy+sXI5lyEe4HpT8nnfohIrokVF7iIFEGLRqCemw686a/X6kBZkA3AYLXvBpCODSXBHuwXulu8eaZWHC5psxxkGh5AHIepAZ8hXrFkHECPfjJtN/4dFcsKTprckUWrlAAUoYy8IEAOBXUTt+SJk/VkHOhogac21kVXjxzSSMPzyeNfi95m60GvONYUIW8xj0TEsjCtiXADQ/zqLGzHpI67l31Zkmg=";
    }

	@Override
	public int getRequiredGardenLevel() {
		return 3;
	}

	@Override
	public Rarity getRarity() {
		return Rarity.UNCOMMON;
	}

	@Override
	public String getName() {
		return "Adventurer";
	}

	@Override
	public AbstractNPC createNpc() {
		return this;
	}
}