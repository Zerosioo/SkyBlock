package me.zerosio.items.itemlist.sword;

import me.zerosio.items.ability.Ability;
import me.zerosio.items.ability.abilities.admin.NiggaAnnihilator;
import me.zerosio.items.ability.abilities.dungeon.WitherImpact;
import me.zerosio.items.itemtype.*;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NiggaSlayer2000 extends SItem implements ItemStatistics, Ability.Holder {

    @Override
    public String getName() {
        return "Nigga Slayer 2000";
    }

    @Override
    public List<String> getLore() {
        return new ArrayList(Arrays.asList("§7§oThat thing was","§7§oused to kill","§7§onigga slaves by,","§7§oGeorge Washington.")); 
    }

    @Override
    public String getId() {
        return "NIGGA_SLAYER_2000"; 
    }

    @Override
    public Material getMaterial() {
        return Material.IRON_SWORD;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.SWORD;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.VERY_SPECIAL;
    }

    @Override
    public Gamestage getGamestage() {
        return Gamestage.PROFESSIONAL;
    }
    
    @Override
    public int getGearScore() {
    	return 874;
    }

    @Override
    public int getDamage() {
        return 700;
    }

    @Override
    public int getStrength() {
        return 300;
    }

    @Override
    public int getCritDamage() {
        return 30;
    }

    @Override
    public int getSwingRange() {
        return 4;
    }
    
    @Override 
    public int getIntelligence() {
    	return 2000;
    }
    
    @Override 
    public boolean reforgeable() {
    	return true;
    }
    
    @Override
    public List<Ability> getAbilities() {
        return Arrays.asList(new NiggaAnnihilator());
    }
}
