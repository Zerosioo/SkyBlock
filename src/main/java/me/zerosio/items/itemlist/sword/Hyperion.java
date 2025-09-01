package me.zerosio.items.itemlist.sword;

import org.bukkit.Color;
import org.bukkit.Material;

import me.zerosio.items.ability.Ability;
import me.zerosio.items.ability.abilities.dungeon.WitherImpact;
import me.zerosio.items.itemtype.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hyperion extends SItem implements ItemStatistics, Ability.Holder {

    @Override
    public String getName() {
        return "Hyperion";
    }

    @Override
    public List<String> getLore() {
        return null; 
    }

    @Override
    public String getId() {
        return "HYPERION"; 
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
        return Rarity.LEGENDARY;
    }

    @Override
    public boolean isDungeonItem() {
      	return true;
    }

    @Override
    public Gamestage getGamestage() {
        return Gamestage.MASTER;
    }

    @Override
    public DungeonType getDungeonType() {
        return DungeonType.CATACOMBS;
    }

    @Override
    public int getDamage() {
        return 260;
    }

    @Override
    public int getStrength() {
        return 150;
    }

    @Override
    public int getIntelligence() {
        return 350;
    }
    
    @Override
    public List<Ability> getAbilities() {
        return Arrays.asList(new WitherImpact());
    }
}
