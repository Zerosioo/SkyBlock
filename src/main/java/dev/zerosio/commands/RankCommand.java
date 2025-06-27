package dev.zerosio.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import dev.zerosio.commands.builder.CommandBase;
import dev.zerosio.rank.PlayerRank;
import dev.zerosio.user.profiledata.UserRank;

import java.util.Arrays;
import java.util.List;

public class RankCommand extends CommandBase {

    @Override
    public String getName() {
        return "rank";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("setrank");
    }

    @Override
    public PlayerRank getRequiredRank() {
        return PlayerRank.ADMIN; // Require ADMIN or higher to use
    }

    @Override
    public String getDescription() {
        return "Sets the rank of a player";
    }

    @Override
    public String getUsage() {
        return "§cUsage: /rank <rank> [player]";
    }

    @Override
    public void execute(Player executor, String[] args) {
        if (args.length < 1) {
            executor.sendMessage(getUsage());
            return;
        }

        String rankInput = args[0].toUpperCase();
        PlayerRank rank;

        try {
            rank = PlayerRank.valueOf(rankInput);
        } catch (IllegalArgumentException e) {
            executor.sendMessage("§cInvalid rank: §e" + rankInput);
            executor.sendMessage("§cValid ranks: §7" + Arrays.toString(PlayerRank.values()));
            return;
        }

        Player target;
        if (args.length >= 2) {
            target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                executor.sendMessage("§cPlayer not found: §e" + args[1]);
                return;
            }
        } else {
            target = executor;
        }

        UserRank.setPlayerRank(rank, target);

        if (target.equals(executor)) {
            executor.sendMessage("§aYour rank has been set to §e" + rank.name());
        } else {
            executor.sendMessage("§aSet rank of §b" + target.getName() + " §ato §e" + rank.name());
            target.sendMessage("§aYour rank has been set to §e" + rank.name() + " §aby §b" + executor.getName());
        }
    }
}
