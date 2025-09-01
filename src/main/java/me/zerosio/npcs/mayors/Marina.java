package me.zerosio.npcs.mayors;

import me.zerosio.mayors.Mayor;
import me.zerosio.mayors.Perk;
import me.zerosio.npcs.build.AbstractNPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class Marina extends AbstractNPC implements Mayor {

    public Marina() {
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
        return Arrays.asList(getDisplayName(), "§9Candidate", "§e§lCLICK");
        } else if (isMayor()) {
        	return Arrays.asList("§fMayor Marina", "§e§lCLICK");
        } else if (isMinister()) {
        	return Arrays.asList("§fMinister Marina");
        } else {
        	return Arrays.asList(getDisplayName(), "§e§lCLICK");
        }
    }

    @Override
    public String getTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTc0OTY5NDM5MTI4NSwKICAicHJvZmlsZUlkIiA6ICIwNDg0N2ZjNWM5YjY0NTQ1YjI1ZWJkYmJiNzdjNjg2NSIsCiAgInByb2ZpbGVOYW1lIiA6ICJOYXFsdWEiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODA3ZmM5YmVlOGQzMzQ0ZTg0MGU0MDMxYTM3MjQ5YTRjM2M4N2ZjODBjZjE2NDMyY2M1YzIxNTNkMWY5YzUzZCIKICAgIH0KICB9Cn0=";
    }

    @Override
    public String getSignature() {
        return "Inc6EBdQcld+Kb8Is+9uYwIwIxSsSYYPcGbSdhOYloqQEXQFR4f9/XnOdGQqAaor76ll85Qo8TOxaQ30MrNfiyrmy4FnQv+01lNZ3NhEnmcA3y7OSDc7L1uMhaPO5IqtpzjJV60JSHssL2nR+r0R/5yIh+zC9wVyoVhkQk7ILc9mjbiClOslVOFmNyCcCMgvbExPdY8Vb3gKVeBo6XLadO5rvY7Hf7gxB/HG2nqeUIs6mfgvf+yadqlmtncBENltdJjkw3iy3ETdor7uzQn7uPHuyuGIqzWVXNJkrx4JLYAAj95iKFinzqKmw35vcWKZO/ZITuVV2XHEBR/+dZ+cQTX58gcx/emoRh1S+4WF2OYLztr9js6zUXYQtfpURzRpw3An0FwoMslddN8/UjZ8amo+vd+PqSTgSjjejn6Gp1okREVtD0TKDTIaVtvMMmz0oTPM0ZTFO5MpOv8JBCTFqsAsnGcTU5ysrdTteoi44FrVuDQjTyD3MoHMPnwgm+REpSeHq6wF28xYbwjVniI4Jb7eWfWQAlKzXn34AqpQ9hEjjM6NqVUoub9xnXWZcend4qVol95ENzID7ejd7MF51RNIDw5uy8HxMzStqZPHn2coW76ZEzMOROeV6F80CT2wsD81qWYxPONRyltCwPzqqlzs0Haqee+CcxwFOIXnWwU=";
    }

    @Override
    public List<Perk> getPerks() {
        return Arrays.asList(
                new Perk("§dFishing XP Buff", Arrays.asList("§7Gain §3+50 ☯ Fishing Wisdom", "§7on public islands."), mayor -> {}),
                new Perk("§dLuck of the Sea 2.0", Arrays.asList("§7Gain §315 α Sea Creature Chance."), mayor -> {}),
                new Perk("§dFishing Festival", Arrays.asList("§7Start a special fishing", "§7event during the first §b3 §7days", "§7of each month!", " ", "§7Fish and fight dangerous", "§bsharks §7and earn unique", "§7Shark loot."), mayor -> {}),
                new Perk("§dDouble Trouble", Arrays.asList("§7For every §31 α Sea Creature Chance§7,", "§7gain §9+0.1 ⚓ Double Hook Chance§7."), mayor -> {})
        );
    }

    @Override
    public String getDisplayName() {
        return "§9Marina";
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
