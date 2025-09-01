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

public class Ryu extends AbstractNPC {

    public Ryu() {
        super("Ryu");
    }

    @Override
    public void onInteract(Player player) {
     User user = User.getUser(player);
     if (isFirstInteraction(player)) {
      new Dialogue()
          .voice(VoiceType.MALE)
          .addMessage("§e[NPC] Ryu§f: There are §a12 Skills §fin SkyBlock!", 0)
          .addMessage("§e[NPC] Ryu§f: Some include Farming, Mining, Foraging, Fishing, and Combat. There are plenty more Skills to discover and level up!", 20)
          .addMessage("§e[NPC] Ryu§f: You can learn all about them in the §aSkill Menu§f, located in your §aSkyBlock Menu§f.", 55)
          .start(player);
     } else {
     	new Dialogue()
     	.voice(VoiceType.MALE)
     	.addMessage("§e[NPC] Ryu§f: Most actions in SkyBlock will reward you Skill EXP.", 0)
     	.addMessage("§e[NPC] Ryu§f: You get rewarded every time you level up a Skill!", 25)
     	.start(player);
     }

    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), 27.5, 70, -116.5);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.VILLAGER;
    }
    
    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§fRyu", "§e§lCLICK");
    }
}
