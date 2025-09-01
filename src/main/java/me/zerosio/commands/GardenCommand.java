package me.zerosio.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import me.zerosio.commands.builder.CommandBase;
import me.zerosio.garden.GardenAlgorithm;
import me.zerosio.rank.PlayerRank;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GardenCommand extends CommandBase {

    @Override
    public String getName() {
        return "garden";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("grdn", "gd");
    }

    @Override
    public PlayerRank getRequiredRank() {
        return PlayerRank.DEFAULT;
    }

    @Override
    public String getDescription() {
        return "Teleport to your garden.";
    }

    @Override
    public String getUsage() {
        return "/garden";
    }

    @Override
    public void execute(Player player, String[] args) {
        String worldName = "garden_" + player.getUniqueId();

        if (args.length > 0 && args[0].equalsIgnoreCase("create")) {
            try {
                player.sendMessage("§aCreating your garden...");
                GardenAlgorithm.createGarden(player.getUniqueId());
            } catch (IOException e) {
                player.sendMessage("§cFailed to create garden.");
                e.printStackTrace();
            }
            return;
        }

        if (args.length > 0 && args[0].equalsIgnoreCase("recreate")) {
            try {
                player.sendMessage("§aRecreating your garden...");
                if (player.getWorld().getName().equalsIgnoreCase(worldName)) {
                    World defaultWorld = Bukkit.getWorlds().get(0);
                    if (defaultWorld != null) {
                        player.teleport(defaultWorld.getSpawnLocation());
                    }
                }

                GardenAlgorithm.deleteGarden(player.getUniqueId());
                GardenAlgorithm.createGarden(player.getUniqueId());
                GardenAlgorithm.sendToGarden(player);
            } catch (IOException e) {
                player.sendMessage("§cFailed to recreate garden.");
                e.printStackTrace();
            }
            return;
        }

        GardenAlgorithm.sendToGarden(player);
    }
}
