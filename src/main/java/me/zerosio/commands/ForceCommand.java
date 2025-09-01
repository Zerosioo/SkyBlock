package me.zerosio.commands;

import org.bukkit.entity.Player;

import me.zerosio.commands.builder.CommandBase;
import me.zerosio.inventory.admin.ForceElectionGUI;
import me.zerosio.rank.PlayerRank;

import java.util.Arrays;
import java.util.List;

public class ForceCommand extends CommandBase {

    @Override
    public String getName() {
        return "force";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("forc");
    }

    @Override
    public PlayerRank getRequiredRank() {
        return PlayerRank.ADMIN;
    }

    @Override
    public String getDescription() {
        return "Force actions";
    }

    @Override
    public String getUsage() {
        return "/force args";
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("§cUsage: /force <election> <action>");
            return;
        }

        String main = args[0].toLowerCase();
        String sub = args[1].toLowerCase();

        switch (main) {
            //case "mayor":
//                switch (sub) {
//                    case "spawn":
//                        MayorManager.spawnElectedMayor(player.getLocation(), MayorManager.getActiveMayor());
//                        player.sendMessage("§aSpawned mayor.");
//                        break;
//                    case "despawn":
//                        MayorManager.despawnMayor();
//                        player.sendMessage("§cDespawned mayor.");
//                        break;
//                    case "relocate":
//                        MayorManager.relocateMayor(player.getLocation());
//                        player.sendMessage("§eRelocated mayor.");
//                        break;
//                    default:
//                        player.sendMessage("§cUnknown mayor action.");
//                        break;
//                }
//                break;
//            case "minister":
//                switch (sub) {
//                    case "spawn":
//                        MayorManager.spawnElectedMinister(player.getLocation(), MayorManager.getActiveMinister());
//                        player.sendMessage("§aSpawned minister.");
//                        break;
//                    case "despawn":
//                        MayorManager.despawnMinister();
//                        player.sendMessage("§cDespawned minister.");
//                        break;
//                    case "relocate":
//                        MayorManager.relocateMinister(player.getLocation());
//                        player.sendMessage("§eRelocated minister.");
//                        break;
//                    default:
//                        player.sendMessage("§cUnknown minister action.");
//                        break;
//                }
//                break;
            case "election":
                new ForceElectionGUI(player).open();
                break;
            default:
                player.sendMessage("§cUnknown type: " + main);
                break;
        }
    }
}
