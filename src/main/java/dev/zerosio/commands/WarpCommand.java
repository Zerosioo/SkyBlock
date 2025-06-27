package dev.zerosio.commands;

import org.bukkit.entity.Player;

import dev.zerosio.commands.builder.CommandBase;
import dev.zerosio.rank.PlayerRank;
import dev.zerosio.warp.WarpManager;

import java.util.Arrays;
import java.util.List;

public class WarpCommand extends CommandBase {

    @Override
    public String getName() {
        return "warp";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("w");
    }

    @Override
    public PlayerRank getRequiredRank() {
        return PlayerRank.DEFAULT;
    }

    @Override
    public String getDescription() {
        return "Warp to places";
    }

    @Override
    public String getUsage() {
        return "/warp <location>";
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§cUsage: /warp <location>");
            return;
        }

        String id = args[0];
        if (!WarpManager.isWarpExist(id)) {
            player.sendMessage("§cUnknown warp location.");
            return;
        }

        WarpManager.warpPlayer(player, id);
        return;
    }
}
