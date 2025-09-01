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

public class Vex extends AbstractNPC {

    public Vex() {
        super("Vex");
    }

    @Override
    public void onInteract(Player player) {
     User user = User.getUser(player);
     if (isFirstInteraction(player)) {
      new Dialogue()
          .voice(VoiceType.MALE)
          .addMessage("§e[NPC] Vex§f: You can shift click any player to trade with them!", 0)
          .addMessage("§e[NPC] Vex§f: Once both players are ready to trade, click on §aAccept trade§f!", 40)
          .addMessage("§e[NPC] Vex§f: Make sure you don't give away all of your belongings!", 50)
          .start(player);
     } else {
     	new Dialogue()
     	.voice(VoiceType.MALE)
     	.addMessage("§e[NPC] Vex§f: You can disable Player Trading in your §bSkyBlock Settings§f!", 0)
     	.addMessage("§e[NPC] Vex§f: Your settings can found in the §aSkyBlock Menu§f.", 40)
     	.start(player);
     }

    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), -16.5, 70, -81.5);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.VILLAGER;
    }
    
    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§fVex", "§e§lCLICK");
    }
}
