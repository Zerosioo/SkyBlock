package me.zerosio.npcs.hub.villagers;

import me.zerosio.npcs.build.AbstractNPC;
import me.zerosio.npcs.build.dialogue.Dialogue;
import me.zerosio.npcs.build.dialogue.VoiceType;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Tom extends AbstractNPC {

    public Tom() {
        super("Tom");
    }

    @Override
    public void onInteract(Player player) {

      new Dialogue()
          .voice(VoiceType.MALE)
          .addMessage("§e[NPC] Tom§f: Did you know that you have a §eCrafting Table §fin your §aSkyBlock Menu§f?", 0)
          .addMessage("§e[NPC] Tom§f: You can craft from anywhere in the world!", 40)
          .start(player);

    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), 28.5, 69, -57.5);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.VILLAGER;
    }
    
    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§fTom", "§e§lCLICK");
    }
}
