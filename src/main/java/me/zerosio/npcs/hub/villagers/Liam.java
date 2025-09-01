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

public class Liam extends AbstractNPC {

    public Liam() {
        super("Liam");
    }

    @Override
    public void onInteract(Player player) {
     User user = User.getUser(player);
     if (isFirstInteraction(player)) {
      new Dialogue()
          .voice(VoiceType.MALE)
          .addMessage("§e[NPC] Liam§f: Did you know you have a SkyBlock Level?", 0)
          .addMessage("§e[NPC] Liam§f: In fact, everyone does! You can see them in the tab list by holding [TAB]!", 35)
          .addMessage("§e[NPC] Liam§f: You can level up by playing every aspect of the game!", 50)
          .addMessage("§e[NPC] Liam§f: If you're curious, you can view your level, and more information in your SkyBlock Menu!", 50)
          .start(player);
     } else {
     	new Dialogue()
     	.voice(VoiceType.MALE)
     	.addMessage("§e[NPC] Liam§f: Did you know the Levels Menu will try to help you throughout your journey?", 0)
     	.addMessage("§e[NPC] Liam§f: You can view the Level Guide, a make-shift to-do list that will help you out!", 50)
     	.addMessage("§e[NPC] Liam§f: If you ever feel lost or stuck, view this list and maybe it will help you out!", 50)
     	.start(player);
     }

    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), 10.5, 70, -41.5);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.VILLAGER;
    }
    
    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§fLiam", "§e§lCLICK");
    }
}
