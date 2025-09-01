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

public class ZoomAxe extends SItem implements ItemStatistics, Ability.Holder {

    @Override
    public String getName() {
        return "Zoom Axe";
    }

    @Override
    public List<String> getLore() {
        return null; 
    }

    @Override
    public String getId() {
        return "ZOOM_AXE"; 
    }

    @Override
    public Material getMaterial() {
        return Material.DIAMOND_AXE;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.AXE;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.DIVINE;
    }

    @Override
    public Gamestage getGamestage() {
        return Gamestage.MASTER;
    }
    
    @Override
    public int getSweep() {
    	return 100000;
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
