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

public class Andrew extends AbstractNPC {

    public Andrew() {
        super("Andrew");
    }

    @Override
    public void onInteract(Player player) {

      new Dialogue()
          .voice(VoiceType.MALE)
          .addMessage("§e[NPC] Andrew§f: This game is still under heavy development, don't forget to check the §aforums §foften for updates!", 0)
          .addMessage("§e[NPC] Andrew§f: If you'd like to discuss SkyBlock with other players then check out the SkyBlock section of the §aforums§f!", 60)
          .start(player);

    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), 38.5, 68, -46.5);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.VILLAGER;
    }
    
    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§fAndrew", "§e§lCLICK");
    }
}


// the ticks are for the before message like after how much time should the next message print.