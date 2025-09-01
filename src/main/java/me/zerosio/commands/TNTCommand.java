package me.zerosio.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.util.Vector;
import org.bukkit.scheduler.BukkitRunnable;

import me.zerosio.Core;
import me.zerosio.commands.builder.CommandBase;
import me.zerosio.rank.PlayerRank;

import java.util.List;
import java.util.Random;

public class TNTCommand extends CommandBase {

    @Override
    public String getName() {
        return "tnt";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public PlayerRank getRequiredRank() {
        return PlayerRank.ADMIN;
    }

    @Override
    public String getDescription() {
        return "Shoots TNT in random directions for 60 seconds";
    }

    @Override
    public String getUsage() {
        return "/tnt";
    }

    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage("§aTNT barrage started for 60 seconds!");

        new BukkitRunnable() {
            int ticksPassed = 0;
            final Random random = new Random();

            @Override
            public void run() {
                if (ticksPassed >= 1200) { // 60 seconds
                    cancel();
                    player.sendMessage("§cTNT barrage ended.");
                    return;
                }

                Location loc = player.getLocation().add(0, 1.5, 0);
                for (int i = 0; i < 10; i++) {
                    TNTPrimed tnt = player.getWorld().spawn(loc, TNTPrimed.class);
                    Vector velocity = new Vector(
                        (random.nextDouble() - 0.5) * 2,
                        random.nextDouble() * 1.5,
                        (random.nextDouble() - 0.5) * 2
                    ).normalize().multiply(2);
                    tnt.setVelocity(velocity);
                    tnt.setFuseTicks(40); // 2 seconds
                }

                ticksPassed += 20;
            }
        }.runTaskTimer(Core.getInstance(), 0L, 20L); // every 1 second
    }
}
