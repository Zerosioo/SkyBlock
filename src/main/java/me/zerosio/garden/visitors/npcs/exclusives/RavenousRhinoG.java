package me.zerosio.garden.visitors.npcs.exclusives;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.zerosio.garden.visitors.build.GardenVisitor;
import me.zerosio.items.itemtype.Rarity;
import me.zerosio.npcs.build.AbstractNPC;

public class RavenousRhinoG extends AbstractNPC implements GardenVisitor {

    public RavenousRhinoG() {
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
        return "ewogICJ0aW1lc3RhbXAiIDogMTc1MjkzNzY3ODk5NywKICAicHJvZmlsZUlkIiA6ICIxZjk0OTQzN2RlYmQ0ODgyYTlhYzZhZmZmN2RhNDcxMSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNaWlra2FLYSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82YzRkZmZhOWRjMWU3NGFiZjQ5NmY3NTgwODMwNDU5MDE1NTQwODFhYTUxNDc2M2U1NjNkNjdmOTBiZDE0MzgyIgogICAgfQogIH0KfQ==";
    }

    @Override
    public String getSignature() {
        return "muHEe+LAc2SRh1q6raQ4mSTodI2RYSP3oaR4FQvxNvNglc/172swdOBF3Foj7HnCzqFP3zfXGyCf/sieNewNJWvUd8CVp6ChIBDTPv0qQzwDfr83MoF/ePMTiMURWH2l2zvb6Vq/+/lucyb3g2WjMT9ZnhtpHGhWsKRaftyQkeJW2hVb3YYa4UAbr/LDO6MYq7eKh1pwVwxyBf7L/X4u3juczpFfvY72Vo0PRyTb98mn73RvbwpTvbrdZx9p03fUPfOQ6jVBGC3Cj91AZkKxxJyvTiFaHzBI2CpD6LlLmM+cNfjc+LcdY4zXP+0fsuVwqN1OB+zvenvjDd/aeiReNd4/kxcrWzWUMzv1A4w53YWg+fwaxmVGDShAfpvKTfg/k1VWxvs0AHHg2dDGqVUBD5JBXl2Va7qL47orpGT8hAgUrCY7ohxY/WcMTkZM2G/3kyq7EbKVl6QOZyN6pql8cTsVy3rBx7W9xojRmCGRaDTdi5CFVEOgSF4vum3hofnFRH0LR7J5HHBOFz+5gWp01jd4U8hKXFxZ4OM57YTHEhgy3MZx7xNM3k3aOWBKUZxWqIpMCRMrn6cE/mojYZpVmsrvFoxIxp9YKSyoybtcMlazGWPkLsKJFq3znbhZqUMvN+790X3Ezt2vq5DjIMvmLPzvV9QCkaar1xFxAiDWbGk=";
    }

    @Override
    public int getRequiredGardenLevel() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.MYTHIC;
    }

    @Override
    public String getName() {
        return "Ravenous Rhino";
    }

    @Override
    public AbstractNPC createNpc() {
        return this;
    }
}
