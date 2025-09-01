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

public class Stella extends AbstractNPC {

    public Stella() {
        super("Stella");
    }

    @Override
    public void onInteract(Player player) {

      new Dialogue()
          .voice(VoiceType.FEMALE)
          .addMessage("§e[NPC] Stella§f: At any time you can create a Co-op with your friends!", 0)
          .addMessage("§e[NPC] Stella§f: Simply go in your §aSkyBlock Menu §fwhere you can find the §aProfile Menu§f.", 35)
          .addMessage("§e[NPC] Stella§f: This is where you can create, delete or switch SkyBlock Profiles.", 50)
          .addMessage("§e[NPC] Stella§f: Enter §b/coop §ffollowed by the name of all the friends you want to invite!", 50)
          .addMessage("§e[NPC] Stella§f: All your friends have to be online to accept!", 50)
          .start(player);

    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), 17.5, 70, -99.5);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.VILLAGER;
    }
    
    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§fStella", "§e§lCLICK");
    }
}
