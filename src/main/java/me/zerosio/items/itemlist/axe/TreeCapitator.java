package me.zerosio.items.itemlist.axe;

import me.zerosio.items.ability.Ability;
import me.zerosio.items.ability.abilities.admin.SoulExplosion;
import me.zerosio.items.ability.abilities.axe.TreeCapThrowingAxe;
import me.zerosio.items.itemtype.*;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TreeCapitator extends SItem implements ItemStatistics, Ability.Holder {

    @Override
    public String getName() {
        return "Tree Capitator";
    }

    @Override
    public List<String> getLore() {
        return null; 
    }

    @Override
    public String getId() {
        return "TREE_CAPITATOR"; 
    }

    @Override
    public Material getMaterial() {
        return Material.GOLD_AXE;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.AXE;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
    }

    @Override
    public Gamestage getGamestage() {
        return Gamestage.AMATEUR;
    }
    
    @Override
    public int getSweep() {
    	return 25;
    }
    
    @Override 
    public boolean reforgeable() {
    	return true;
    }
    
    @Override
    public List<Ability> getAbilities() {
        return Collections.singletonList(new TreeCapThrowingAxe());
    }
}
