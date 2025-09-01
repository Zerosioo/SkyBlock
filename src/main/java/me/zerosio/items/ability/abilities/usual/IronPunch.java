package me.zerosio.items.ability.abilities.usual;

import me.zerosio.items.ability.Ability;
import me.zerosio.items.ability.AbilityType;
import me.zerosio.utility.managers.ParticleManager;
import net.minecraft.server.v1_8_R3.EnumParticle;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class IronPunch implements Ability {

    @Override
    public String getAbilityName() {
        return "Iron Punch";
    }

    @Override
    public AbilityType getType() {
        return AbilityType.RIGHT_CLICK;
    }

    @Override
    public int getManaCost() {
        return 70;
    }

    @Override
    public int getCooldownSeconds() {
        return 3;
    }

    @Override
    public List<String> getAbilityDescription() {
        return Arrays.asList(
            "§7Punch the ground, damaging enemies",
            "§7in a hexagon around you for §c460 damage"
        );
    }

    @Override
    public void activate(Player player, PlayerInteractEvent event) {
        Location centre = player.getLocation();
        int angle = 0;
        int radius = 5;
        
        
    }
    
    private void spawnParticleLine(Location d1, Location d2) {

            d1 = d1.clone();

            d2 = d2.clone();

            double length = 0;

            Vector v = d2.toVector().subtract(d1.toVector()).normalize().multiply(0.25);

            double add = v.length();

            double distance = d2.distance(d1);

            for (; length < distance; d1.add(v)) {

                ParticleManager.spawnParticle(EnumParticle.FLAME, d1);

                length += add;

            }

        }
}
