package me.zerosio.items.ability.abilities.axe;

import me.zerosio.items.ability.Ability;
import me.zerosio.items.ability.AbilityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

public class ProfessionalThief implements Ability {

    @Override
    public String getAbilityName() {
        return "Jet Fuel can't melt steel";
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
        return 0;
    }

    @Override
    public List<String> getAbilityDescription() {
        return Arrays.asList(
            "§7Uses the §2power §7of the §6talmud §7to fool the world",
            "§7and steal §65,000,000 §7from everyone."
        );
    }

    @Override
    public void activate(Player player, PlayerInteractEvent event) {
        player.sendMessage("§cAbility is currently on maintenance, we are working on it so please have patience.");
    }
}
