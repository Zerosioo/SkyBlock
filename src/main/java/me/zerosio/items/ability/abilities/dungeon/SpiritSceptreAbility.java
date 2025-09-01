package me.zerosio.items.ability.abilities.dungeon;

import me.zerosio.items.ability.Ability;
import me.zerosio.items.ability.AbilityType;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import me.zerosio.Core;

import java.util.Arrays;
import java.util.List;

public class SpiritSceptreAbility implements Ability {

    @Override
    public String getAbilityName() {
        return "Guided Bat";
    }

    @Override
    public AbilityType getType() {
        return AbilityType.RIGHT_CLICK;
    }

    @Override
    public int getManaCost() {
        return 200;
    }

    @Override
    public int getCooldownSeconds() {
        return 0;
    }

    @Override
    public List<String> getAbilityDescription() {
        return Arrays.asList(
                "§7Shoots an guided spirit bat, following your aim",
                "§7and exploding for §c2,000 §7damage."
        );
    }

    @Override
    public void activate(Player player, PlayerInteractEvent event) {
        Location launchLoc = player.getEyeLocation().add(0, 0.5, 0);
        Vector direction = launchLoc.getDirection().normalize().multiply(1.2);

        Bat bat = (Bat) player.getWorld().spawn(launchLoc, Bat.class);
        bat.setMetadata("spirit_sceptre", new FixedMetadataValue(Core.getInstance(), true));
        bat.setCustomNameVisible(false);
        bat.setCustomName("");
        bat.setPassenger(null);

        new BukkitRunnable() {
            int ticks = 0;

            public void run() {
                if (ticks++ > 100 || bat.isDead()) {
                    explode();
                    return;
                }

                Location loc = bat.getLocation();

                if (loc.getBlock().getType().isSolid()) {
                    explode();
                    return;
                }

                bat.setVelocity(direction);
            }

            void explode() {
                Location loc = bat.getLocation();

                loc.getWorld().playEffect(loc, Effect.EXPLOSION_HUGE, 0);
                loc.getWorld().playSound(loc, Sound.EXPLODE, 2f, 1f);

                for (Entity entity : loc.getWorld().getNearbyEntities(loc, 5, 5, 5)) {
                    if (entity instanceof LivingEntity && entity != player) {
                        ((LivingEntity) entity).damage(2000, player);
                    }
                }

                bat.remove();
                cancel();
            }
        }.runTaskTimer(Core.getInstance(), 1L, 2L);
    }
}
