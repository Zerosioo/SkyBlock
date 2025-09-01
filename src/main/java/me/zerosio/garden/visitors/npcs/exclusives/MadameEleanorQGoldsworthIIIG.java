package me.zerosio.garden.visitors.npcs.exclusives;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.zerosio.garden.visitors.build.GardenVisitor;
import me.zerosio.items.itemtype.Rarity;
import me.zerosio.npcs.build.AbstractNPC;

public class MadameEleanorQGoldsworthIIIG extends AbstractNPC implements GardenVisitor {

    public MadameEleanorQGoldsworthIIIG() {
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
        return "ewogICJ0aW1lc3RhbXAiIDogMTYzNTE2OTIwNTU3NywKICAicHJvZmlsZUlkIiA6ICI5MThhMDI5NTU5ZGQ0Y2U2YjE2ZjdhNWQ1M2VmYjQxMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJCZWV2ZWxvcGVyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2I3ZTBmYzhkZjkwNjdkMzkzYzk3N2YxZjM0MDI1NjM0MjIzZGIxNDVlOTQwM2RjMzJjMzExODg5ZTMzNjU5NjUiCiAgICB9CiAgfQp9";
    }

    @Override
    public String getSignature() {
        return "VnhMZ2a55oTztZzmxmmkoTQ8vmKUFyh1QUFBLHeVGB6AsaFeWhef+NLUtftoWAf64m78I+OkmIQcexSHtCLZm6xSGitEcx8i/TRx5X25ZsV+jHKd2jJQMMA/BFrnnfawvV6KZ7zqbc7m/5Uv4aOHZxcYt/EBJyNiDBpqhEGDX/Ulo34Ti87JTHp5lT4qG1pmND9FQw4T/1JNdJTI+Wlxw2Ux9k2tRloDAUtyb9YDwYjMn27Ua49eYtVv3tFawuibXFtIj0u/Ni88PF25zAU2kE+1i3dtmj6htQ/Nzgc8gaFB/cETHD997D4/DkpPPnCPL1sd8iO63ncma5aFxvaBwAh97bGIwNUrsJJy2AtYlluD3PHrIIukKCuN+v37+Tn8KM9AbYVVfpJ6Z1Xot+s3BClWuzo4+sAvfBER6QiOvCYuSBjlGxCagSEIaBkxc6YIFhVs5Wa1ijpPcebB+HROePr2lQNkRFjiA4QYDprZJLm6HeGpXAhHtKAEb/D965sYe1EY3zDSPB33ZdO/Yq0u5oq+jyzAIurmS/oHWbMJ8VPWF39jUzc7ykSosEUDwOt1N6bO67pOB7Axjkt/45zJiYgAiU9XOEAsfkeSQcHKeJve3yKKizs0j6nPXGsF9z+mYwVokYLFbisanum6GtXHmhCNNJEkHbX6cgOid5V0I4U=";
    }

    @Override
    public int getRequiredGardenLevel() {
        return 2;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public String getName() {
        return "Madame Eleanor Q. Goldsworth III";
    }

    @Override
    public AbstractNPC createNpc() {
        return this;
    }
}
