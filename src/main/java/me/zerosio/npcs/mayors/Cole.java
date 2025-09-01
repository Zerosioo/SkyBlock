package me.zerosio.npcs.mayors;

import me.zerosio.mayors.Mayor;
import me.zerosio.mayors.Perk;
import me.zerosio.npcs.build.AbstractNPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class Cole extends AbstractNPC implements Mayor {

    public Cole() {
        super("");
    }

    @Override
    public void onInteract(Player player) {
        if (isFirstInteraction(player)) {
            // First time interaction logic (optional)
        }
    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), 0, 0, 0);
    }

    @Override
    public List<String> getHologramLines() {
        if (isCandidate()) {
        return Arrays.asList(getDisplayName(), "§bCandidate", "§e§lCLICK");
        } else if (isMayor()) {
        	return Arrays.asList("§fMayor Cole", "§e§lCLICK");
        } else if (isMinister()) {
        	return Arrays.asList("§fMinister Cole");
        } else {
        	return Arrays.asList(getDisplayName(), "§e§lCLICK");
        }
    }

    @Override
    public String getTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYyMjc4MTIzODYwNiwKICAicHJvZmlsZUlkIiA6ICJiNWRkZTVmODJlYjM0OTkzYmMwN2Q0MGFiNWY2ODYyMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJsdXhlbWFuIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzE2NDIyZGUwODg0ODk1MmQxY2JlYWQ2NmJiYmFkNmYwNzE5MWJkY2M5NTJmM2QxMDM2YWViMGMyMjkzOGYzOWIiCiAgICB9CiAgfQp9";
    }

    @Override
    public String getSignature() {
        return "WyW8Lhm0VJo5+Gc3DMIuL9OkdOS0+RmXmSqlhBiNPHS+V+L9wHW2e0+QM6guc2cDolir7/TLKsPAIwCVqldqHBJPhQcvFW7Jssrxm4OK9DM7WOxLGTSLEGEEF5xjsexs6LfOWsVhg81QTYHM+2WlGbDUIJkRv32w4jBVAbkxaT1jgkgaz2CCAjsSfiL2fdpBxjWqZBdj3SaUtiw+Fvm5SFwe1zfcPtXimw4jG/MwPvRsgokM9yKvfUQO+XIFqIrcMB7hIA8yK1GISJXaHs39bfTQSgN0FjrVrP6/soru2a/6I/xhxjXJ08lvm0NpBITegs35/kTwIaGdDzHnFjWoIgVzz/9Z7ty6lcyVJLAU28hf6eyoes9NffzEooZa98kIjAg7Yft5pD0fqySqOJ8bMDe4q6rh2aFfyhweftKX4Aokut3BCF4j/K78HLMaaYSwg3axUaRlPtD3H2VcmgZyW/HdKlGKF7eaHEiv+BGijo8XNsy80s57NmCbTB8YOtrD7+KaUwXTvId5ZkvrPgHvgcsySmrfIs2jgcBvGDXj6PTyE3LNSdcFa+pq8jS62plREaltXY8v8/5HTHM2r+iD89+jeeeSGux7RM6bzBedUjnwOPjsZhaZpMujZ7GMM/UVA+NQXsUI6zlf/fPZX7WxY4VO16twO6Nkta9rdX/0RX4=";
    }

    @Override
    public List<Perk> getPerks() {
        return Arrays.asList(
                new Perk("§dProspection", Arrays.asList("§7Mining minions work §a25% §7faster."), mayor -> {}),
                new Perk("§dMining XP Buff", Arrays.asList("§7Get §3+60☯ Mining Wisdom §7on public", "§7islands."), mayor -> {}),
                new Perk("§dMining Fiesta", Arrays.asList("§7Schedules 5 §6Mining Fiestas", "§7throughout the year! During these", "§7events gain §3+75☯ Mining Wisdom§7,", "§7collect §9Refined Mineral §7and §5Glossy", "§5Gemstone§7, and earn §d2x drops §7from", "§7mining!"), mayor -> {}),
                new Perk("§dMolten Forge", Arrays.asList("§7Decrease the time it takes to", "§6forge items by §d25%§7."), mayor -> {})
        );
    }

    @Override
    public String getDisplayName() {
        return "§bCole";
    }

    @Override
    public void onActive() {
        // TODO
    }

    @Override
    public AbstractNPC createNPC() {
        return this;
    }
}
