package me.zerosio.garden.visitors.npcs.exclusives;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.zerosio.garden.visitors.build.GardenVisitor;
import me.zerosio.items.itemtype.Rarity;
import me.zerosio.npcs.build.AbstractNPC;

public class SpacemanG extends AbstractNPC implements GardenVisitor {

    public SpacemanG() {
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
        return Arrays.asList(getName(), "§e§lCLICK");
    }

    @Override
    public String getTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYxOTI5ODU1OTg0NSwKICAicHJvZmlsZUlkIiA6ICJiNzVjZDRmMThkZjg0MmNlYjJhY2MxNTU5MTNiMjA0YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJLcmlzdGlqb25hczEzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2UyMGJkMWVkOGQ5Y2UwODY0ODcyNzM3YmVkNzE4ODZhMzgzMTAxZDA5ZjE2ZTAxNjAwNTljMWY4YjA1MDg1ZDIiCiAgICB9CiAgfQp9";
    }

    @Override
    public String getSignature() {
        return "M9Qnxg5gOSNvJwq23h2/M+Wgy0mitEghPeDl9WC2IvkKCHTrl/c83Wdj10HNgUs0D/wgJzdBaEmWnDcPyYqBfAR1d1cuSlo/XNvdNTudjjbiqpOQYX/qVE+c9YUREoCmUmgLi5odsQ0UsPINUHhG1jeU3qWGXGs9XROVFLgeGVO7lq37Wojd5PjwDHxKRY6VF0goOKS2EVqbNp/hk93vl7Pxrhx2SoHfca474q3SS9EfJU/alDv10mFESQBkHURMvjMeEMZMyuvZK4JlRmy1ilOa1wnnDrffiXHwiUzHI5yB3FCzygXw8yAHm6nx9NN6W0vaMRMpRPJ/qM+v3HKxMeOy8MbRNaQBCAIDvihaQFl/1y3lUxv+Ma8DlRJIo1GB6miLOib7jGSdwBai21HZOonA2NqZ98T21aCleb3Qf6ENAMxs9/QM85C0NpA9IDHAlrr04LtNq89EN2HXNwuimgB1y7a0g0Oyr5qfRoLPEh1TrF3DE5nZHR/X8BuiLBTrFts9+If9OTCpJcQkvCywXAY0eo1BteUjpeYMwUPXu1vX1KTctRSlKUe20cOrEEV0m8TBtxCeYqHVdvwHQz5sY6YW3dKa/R00GBQ6tnq7LGvKaT4GjnTRWdKSjs5zrBalJ7CTjx2n0Z/P7xby4xLMHbxfPwKFAuOF9r8UsD0pyXg=";
    }

	@Override
	public int getRequiredGardenLevel() {
		return 0;
	}

	@Override
	public Rarity getRarity() {
		return Rarity.SPECIAL;
	}

	@Override
	public String getName() {
		return "Spaceman";
	}

	@Override
	public AbstractNPC createNpc() {
		return this;
	}
}