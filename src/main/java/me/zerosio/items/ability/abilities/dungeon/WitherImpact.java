package me.zerosio.items.ability.abilities.dungeon;

import me.zerosio.items.ability.Ability;
import me.zerosio.items.ability.AbilityType;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class WitherImpact implements Ability {

    @Override
    public String getAbilityName() {
        return "Wither Impact";
    }

    @Override
    public AbilityType getType() {
        return AbilityType.RIGHT_CLICK;
    }

    @Override
    public int getManaCost() {
        return 240;
    }

    @Override
    public int getCooldownSeconds() {
        return 0;
    }

    @Override
    public List<String> getAbilityDescription() {
        return Arrays.asList(
            "§7Teleport §a10 blocks §7ahead of",
            "§7you and implode dealing",
            "§c10,000 §7damange to nearby",
            "§7enemies. Also applies the wither",
            "§7shield scroll ability reducing",
            "§7damage taken and granting an",
            "§7absorbtion shield for §e5",
            "§7seconds."
        );
    }

    @Override
    public void activate(Player player, PlayerInteractEvent event) {
        Location teleportLocation = getSafeTeleportLocation(player, 10);
        teleport(player, teleportLocation);

        spawnExplosionParticles(player);
        damageNearbyEntities(player, 10.0, 10000);

        player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 2f, 1f);
    }

    private Location getSafeTeleportLocation(Player player, int maxDistance) {
        Location origin = player.getEyeLocation();
        Vector direction = origin.getDirection().normalize();
        for (int i = maxDistance; i > 0; i--) {
            Location check = origin.clone().add(direction.clone().multiply(i));
            if (!check.getBlock().getType().isSolid()) {
                return check;
            }
        }
        return player.getLocation();
    }

    private void teleport(Player player, Location loc) {
        loc.setYaw(player.getLocation().getYaw());
        loc.setPitch(player.getLocation().getPitch());
        player.teleport(loc);
    }

    private void damageNearbyEntities(Player player, double radius, double baseDamage) {
        for (Entity e : player.getNearbyEntities(radius, radius, radius)) {
            if (e instanceof LivingEntity && e != player) {
                ((LivingEntity) e).damage(baseDamage, player);
            }
        }
    }

    private void spawnExplosionParticles(Player player) {
        Location loc = player.getLocation();
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
            EnumParticle.EXPLOSION_LARGE, true,
            (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(),
            0f, 0f, 0f, 1, 8
        );

        for (Player nearby : loc.getWorld().getPlayers()) {
            ((CraftPlayer) nearby).getHandle().playerConnection.sendPacket(packet);
        }

        // Additional explosion visuals
        for (int i = 0; i < 6; i++) {
            Location effectLoc = loc.clone().add(
                Math.random() * 4 - 2,
                Math.random(),
                Math.random() * 4 - 2
            );
            loc.getWorld().spigot().playEffect(effectLoc, Effect.EXPLOSION_LARGE, 0, 0, 0, 0, 0, 0, 1, 64);
        }
    }
}
