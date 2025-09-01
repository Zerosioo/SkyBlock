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

public class Jamie extends AbstractNPC {

    public Jamie() {
        super("Jamie");
    }

    @Override
    public void onInteract(Player player) {
     User user = User.getUser(player);
     if (isFirstInteraction(player) || !user.getProfileBoolean("reward.rogue_sword")) {
      new Dialogue()
          .voice(VoiceType.MALE)
          .addMessage("§e[NPC] Jamie§f: You might have noticed that you have a Mana bar!", 0)
          .addMessage("§e[NPC] Jamie§f: Some items have mysterious properties, called Abilities.", 30)
          .addMessage("§e[NPC] Jamie§f: Abilities use your Mana as a resource. Here, take this Rogue Sword. I don't need it!", 50, () -> {
          	// open rouge sword gift menu
          	})
          .start(player);
     } else {
     	new Dialogue()
     	.voice(VoiceType.MALE)
     	.addMessage("§e[NPC] Jamie§f: Your total Mana can be increased by improving your §bIntelligence§f!", 0)
     	.addMessage("§e[NPC] Jamie§f: Right click with the Rogue Sword to use its Ability and become temporarily faster!", 30)
     	.addMessage("§e[NPC] Jamie§f: You can use Mana Potions to increase your §bIntelligence §fover time!", 40)
     	.start(player);
     }

    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), -36.5, 68, -38.5);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.VILLAGER;
    }
    
    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§fJamie", "§e§lCLICK");
    }
}
