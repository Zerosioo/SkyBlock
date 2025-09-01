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

public class Felix extends AbstractNPC {

    public Felix() {
        super("Felix");
    }

    @Override
    public void onInteract(Player player) {

      new Dialogue()
          .voice(VoiceType.MALE)
          .addMessage("§e[NPC] Felix§f: You can access your §aEnder Chest §fin your §aSkyBlock Menu§f.", 0)
          .addMessage("§e[NPC] Felix§f: Store items in this chest and access them at any time!", 30)
          .start(player);

    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), -25.5, 70, -103.5);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.VILLAGER;
    }
    
    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§fFelix", "§e§lCLICK");
    }
}
