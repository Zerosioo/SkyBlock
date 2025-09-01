package me.zerosio.npcs.hub.villagers;

import me.zerosio.npcs.build.AbstractNPC;
import me.zerosio.npcs.build.dialogue.Dialogue;
import me.zerosio.npcs.build.dialogue.VoiceType;
import me.zerosio.user.User;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Duke extends AbstractNPC {

    public Duke() {
        super("Duke");
    }

    @Override
    public void onInteract(Player player) {
     User user = User.getUser(player);
     if (isFirstInteraction(player)) {
      new Dialogue()
          .voice(VoiceType.MALE)
          .addMessage("§e[NPC] Duke§f: Are you new here? As you can see there is a lot to explore!", 0)
          .addMessage("§e[NPC] Duke§f: My advice is to start by visiting the §bFarm§f, or the §bCoal Mine §fboth North of here.", 40)
          .addMessage("§e[NPC] Duke§f: If you do need some wood, the best place to get some is West of the §bVillage§f!", 40)
          .start(player);
     } else {
     	new Dialogue()
     	.voice(VoiceType.MALE)
     	.addMessage("§e[NPC] Duke§f: I would not venture South of the §bVillage§f, it seems like this place was abandoned.", 0)
     	.addMessage("§e[NPC] Duke§f: I found a few Fairy Souls during my travels, they are usually pretty hard to find!", 40)
     	.start(player);
     }

    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), -11.5, 70, -96.5);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.VILLAGER;
    }
    
    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§fDuke", "§e§lCLICK");
    }
}
