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

public class Leo extends AbstractNPC {

    public Leo() {
        super("Leo");
    }

    @Override
    public void onInteract(Player player) {

      new Dialogue()
          .voice(VoiceType.MALE)
          .addMessage("§e[NPC] Leo§f: You can unlock §aLeaflet Armor §fby progressing through your §aOak Log Collection§f.", 0)
          .addMessage("§e[NPC] Leo§f: There is a §bForest §fwest of the §bVillage §fwhere you can gather Oak Logs.", 30)
          .addMessage("§e[NPC] Leo§f: To check your Collection progress and rewards, open the §aCollection Menu §fin your §aSkyBlock Menu§f.", 50)
          .start(player);

    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), -7.5, 70, -75.5);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.VILLAGER;
    }
    
    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§fLeo", "§e§lCLICK");
    }
}
