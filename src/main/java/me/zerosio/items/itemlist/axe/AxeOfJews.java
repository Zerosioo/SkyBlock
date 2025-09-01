package me.zerosio.items.itemlist.axe;

import me.zerosio.items.ability.Ability;
import me.zerosio.items.ability.abilities.axe.ProfessionalThief;
import me.zerosio.items.itemtype.*;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AxeOfJews extends SItem implements ItemStatistics, Ability.Holder {

    @Override
    public String getName() {
        return "Axe of Jews";
    }

    @Override
    public List<String> getLore() {
        return Arrays.asList("§7§oThis was used by jews to", "§7§osteal gold from Europe."); 
    }

    @Override
    public String getId() {
        return "AXE_OF_JEWS"; 
    }

    @Override
    public Material getMaterial() {
        return Material.IRON_AXE;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.AXE;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public Gamestage getGamestage() {
        return Gamestage.AMATEUR;
    }
    
    @Override
    public int getSweep() {
    	return 3;
    }
    
    @Override 
    public boolean reforgeable() {
    	return true;
    }
    
    @Override
    public List<Ability> getAbilities() {
        return Collections.singletonList(new ProfessionalThief());
    }
}
