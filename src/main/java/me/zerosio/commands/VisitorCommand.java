package me.zerosio.commands;

import org.bukkit.entity.Player;
import me.zerosio.commands.builder.CommandBase;
import me.zerosio.garden.visitors.build.VisitorLocalization;
import me.zerosio.garden.visitors.build.VisitorManager;
import me.zerosio.garden.visitors.build.GardenVisitor;
import me.zerosio.rank.PlayerRank;

import java.util.List;

public class VisitorCommand extends CommandBase {

    @Override
    public String getName() {
        return "visitor";
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
        return "Manage garden visitors.";
    }

    @Override
    public String getUsage() {
        return "/visitor spawn | spawnall | despawn <index> | list";
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("§cUsage: " + getUsage());
            return;
        }

        switch (args[0].toLowerCase()) {
            case "spawn":
                VisitorLocalization.registerVisitorLocations(player);
                VisitorManager.spawnRandomVisitor(player, 1000);
                player.sendMessage("§aSpawned a random visitor.");
                break;

            case "spawnall":
                VisitorLocalization.registerVisitorLocations(player);
                VisitorManager.spawnAll(player);
                player.sendMessage("§aSpawned all visitors.");
                break;

            case "despawn":
                if (args.length < 2) {
                    player.sendMessage("§cUsage: /visitor despawn <index>");
                    return;
                }
                try {
                    int index = Integer.parseInt(args[1]);
                    GardenVisitor visitor = VisitorManager.getVisitor(player, index);
                    if (visitor == null) {
                        player.sendMessage("§cNo visitor found at index " + index);
                        return;
                    }
                    VisitorManager.acceptVisitor(player, visitor);
                    player.sendMessage("§aDespawned visitor in slot " + index);
                } catch (NumberFormatException e) {
                    player.sendMessage("§cInvalid index. Please enter a number.");
                }
                break;

            case "list":
                List<GardenVisitor> visitors = VisitorManager.getActiveVisitors(player);
                if (visitors.isEmpty()) {
                    player.sendMessage("§eNo visitors currently active.");
                } else {
                    player.sendMessage("§aCurrent Visitors:");
                    for (int i = 0; i < visitors.size(); i++) {
                        GardenVisitor v = visitors.get(i);
                        player.sendMessage(" §7[" + (i + 1) + "] §f" + v.getName());
                    }
                }
                break;

            default:
                player.sendMessage("§cUnknown subcommand. " + getUsage());
        }
    }
}
