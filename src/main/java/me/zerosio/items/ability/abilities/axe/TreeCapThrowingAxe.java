package me.zerosio.items.ability.abilities.axe;

import me.zerosio.items.ability.Ability;
import me.zerosio.items.ability.AbilityType;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

public class TreeCapThrowingAxe implements Ability {

    @Override
    public String getAbilityName() {
        return "Throwing Axe";
    }

    @Override
    public AbilityType getType() {
        return AbilityType.RIGHT_CLICK;
    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public int getCooldownSeconds() {
        return 1;
    }

    @Override
    public List<String> getAbilityDescription() {
        return Arrays.asList(
            "§7While on §2Forging Islands§7, throw your Axe at a", "§7tree! It will sweep §c50% §7of the logs it normally", "§7would!"
        );
    }

    @Override
    public void activate(Player player, PlayerInteractEvent event) {
        player.sendMessage("§cAbility is currently on maintenance, we are working on it so please have patience.");
    }
}
