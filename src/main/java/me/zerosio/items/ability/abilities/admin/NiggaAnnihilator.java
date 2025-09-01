package me.zerosio.items.ability.abilities.admin;

import me.zerosio.items.ability.Ability;
import me.zerosio.items.ability.AbilityType;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

public class NiggaAnnihilator implements Ability {

    @Override
    public String getAbilityName() {
        return "Nigga Annihilator";
    }

    @Override
    public AbilityType getType() {
        return AbilityType.RIGHT_CLICK;
    }

    @Override
    public int getManaCost() {
        return 10;
    }

    @Override
    public int getCooldownSeconds() {
        return 1;
    }

    @Override
    public List<String> getAbilityDescription() {
        return Arrays.asList(
            "§7Unleash a soul shattering explosion,",
            "§7dealing §ctrue damage §7to",
            "§7niggers in a §e5 block radius§7."
        );
    }

    @Override
    public void activate(Player player, PlayerInteractEvent event) {
        for (Entity nearby : player.getNearbyEntities(5, 5, 5)) {
            if (nearby instanceof LivingEntity && nearby != player) {
                ((LivingEntity) nearby).damage(10.0, player);
                player.sendMessage("§8KILLING ALL NIGERS FUCK ASS!");
            }
        }
    }
}
