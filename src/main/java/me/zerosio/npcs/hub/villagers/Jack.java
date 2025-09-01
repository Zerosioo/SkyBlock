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

public class Jack extends AbstractNPC {

    public Jack() {
        super("Jack");
    }

    @Override
    public void onInteract(Player player) {

      new Dialogue()
          .voice(VoiceType.MALE)
          .addMessage("§e[NPC] Jack§f: Your §aSkyBlock Profile §fin your §aSkyBlock Menu §fshows details about your current stats!", 0)
          .addMessage("§e[NPC] Jack§f: There are 7 stats in total, including §c❤ Health§f, §c❁ Strength§f, and §a❈ Defense§f.", 40)
          .addMessage("§e[NPC] Jack§f: Equipped armor, weapons, and accessories in your inventory all improve your stats.", 40)
          .addMessage("§e[NPC] Jack§f: Additionally, leveling your Skills can permanently boost some of your stats!", 40)
          .addMessage("§e[NPC] Jack§f: The higher your §c❤ Health §fstat, the faster your health will regenerate!", 40)
          .start(player);

    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), 0.5, 70, -54.5);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.VILLAGER;
    }
    
    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§fJack", "§e§lCLICK");
    }
}
