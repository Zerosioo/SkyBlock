package me.zerosio.npcs.mayors;

import me.zerosio.mayors.Mayor;
import me.zerosio.mayors.Perk;
import me.zerosio.npcs.build.AbstractNPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class Paul extends AbstractNPC implements Mayor {

    public Paul() {
        super("");
    }

    @Override
    public void onInteract(Player player) {
        player.sendMessage("§aWassup my §8Nigga");
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
        	return Arrays.asList("§fMayor Paul", "§e§lCLICK");
        } else if (isMinister()) {
        	return Arrays.asList("§fMinister Paul");
        } else {
        	return Arrays.asList(getDisplayName(), "§e§lCLICK");
        }
    }

    @Override
    public String getTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYyMjgxMDcyNzc5NiwKICAicHJvZmlsZUlkIiA6ICI0ODBkMmFiMmY1ZTk0OWQ0YWM2Mzk0NDdmNjIzYTYwYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJIb3RhbXBhIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFiNTljNDNkOGRiY2NmZDdlYzZlNjM5NGI2MzA0YjcwZDRlZDMxNWFkZDA0OTRlZTc3YzczM2Y0MTgxOGM3M2EiCiAgICB9CiAgfQp9";
    }

    @Override
    public String getSignature() {
        return "HUQ6kEWFl4QcdU56wEv4nj8HIopxp44ivtgTeeRhtjioZ7/yvMfwHYBnPNfNC6jkbHh43UiSCXX0nNfEcdLet0auccdkdYb+ESzBqnHKCw55PcikdjJ9D387e5Bpaqwj8RDtqvykNFZoKZjJ+pMGh+/Vh3FumGqC1O7K3AXYPOrZ7Fh9s1VWR3D2MP+lBzfhrC+EXTGKwS3e5PD+eHxY3JnrCd5nwjpVl3+nZaqxtSE7vHusZXkx6hxcxoOucXyZPh+oVwRQdSrdetBi/imdXDHaJ2RAwz2EGeC62q3GB2AN35DdIDrfZvVtH/VlyRzucWKxZnMUsUjQnjdYfG9LKXV2vy4cikO7HvSWfJFc5d/UZrvIJO/hES+l3WINbBQIBJY9Pop4+0jbCGHuS0vO8NI5mKIbLQYuHWqqG2B+Ub8nS+or7i4Ixo6/ylG37zFSZJNhbrUTR3vxpUq+B59FRsJDmN+pekAERUEbZUNnpD+XpyGBDT/YVp/4Raa6ZFDlds6r0oNnj+SiwpqjA3wPw73AAUa7C6Stdsyd60/JbRmgqjzcEBJ5gDSfFg/XqYzFpD3OQ9gtTlwhTpRU6ty0yXXAZwQDmx/cFgdL9nPI0ZjU2jpE+M1Jah9rGlUncVVuMVZHchwYz/dzBiOL6Cj+nxQoTPrf4jEztPf5gagJJMA=";
    }

    @Override
    public List<Perk> getPerks() {
        return Arrays.asList(
                new Perk("§dMarauder", Arrays.asList("§7Dungeon reward chests are", "§e20% §7cheaper."), mayor -> {}),
                new Perk("§dEZPZ", Arrays.asList("§7Gain §e10 §7bonus score on", "§7dungeon runs."), mayor -> {}),
                new Perk("§dBenediction", Arrays.asList("§7Blessings are §d25% §7stronger."), mayor -> {})
        );
    }

    @Override
    public String getDisplayName() {
        return "§bPaul";
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
