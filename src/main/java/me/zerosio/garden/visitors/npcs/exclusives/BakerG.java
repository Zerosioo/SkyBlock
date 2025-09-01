package me.zerosio.garden.visitors.npcs.exclusives;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.zerosio.garden.visitors.build.GardenVisitor;
import me.zerosio.items.itemtype.Rarity;
import me.zerosio.npcs.build.AbstractNPC;

public class BakerG extends AbstractNPC implements GardenVisitor {

    public BakerG() {
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
        return "ewogICJ0aW1lc3RhbXAiIDogMTYxNDcxNTQzODExMCwKICAicHJvZmlsZUlkIiA6ICI4NDMwMDNlM2JlNTY0M2Q5OTQxMTBkMzJhMzU2MTk2MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJHYWJvTWNHYW1lciIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hZDIzNmRlMDA0NmFhNGUzNmRhYzIxMjUxMzg3MmRlNjM4MWI3Yjg1ODMwYzU2MzEwNTMyZjEwNDYzZDNiYTEzIgogICAgfQogIH0KfQ==";
    }

    @Override
    public String getSignature() {
        return "XtKtd5De6Po+SsEgi/ReeAbKDV2a7772cSMwxkhXrB/Q+f3ZUfLyit9R6HCkypmikrDsUTF5VOVbdhGBi69ICVKbd68Qjad8JkoOGtTY0AtmnulSXjlc5lLRJbKRfCRRGZyTzJaUbkjrqyLYwLs9iENA7GRVUXhShz008R3yopizBaFmUm+xx5FIgzm/OrJg2KhQsJPIwb01troPhQkeuCqiJ3jvdSYHhftKVvJ9QFzAAJFJbqdYHN62OTwY7NHqyc+LPQMA+BXWh8nT9AewE+r81nO+jNEmIBGUQRxLA+MJBbuXdi0w2G2/fifebny/9tDhk1lNtSroqzlsMbn/ghvNoCfSmUX910RHD5LFdxQ+jrVp6SPsYiT0Xk+OQYuX2snMl0k/x+441G8gxJ1sNxssy8KBV/6rl6UEoMpoSrAdeW53+ov1VohusAKqRQUxVITSP+iEg1uZrQxsyuziECbYc86I5/EeBoFaWoAQ/5Ua1klFD6sKjSUpXC3heEkSSBXvejEugXBxl1tdx0W0U+vicOgbZ4/fHfqMGwXipS7VTo4SAYygI4UPzBdIubOi28Ham4PUXkn7wUpPcUtp45VgEH3+8a2+1QAf/h0S6T983d3AZ2Ypx9HTMwfZg3UPvdTzpbH7Y4AJEdkm55wLYvrTXrbNnOdLNpspfLCmFb8=";
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
        return "Baker";
    }

    @Override
    public AbstractNPC createNpc() {
        return this;
    }
}
