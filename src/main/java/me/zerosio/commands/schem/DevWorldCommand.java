package me.zerosio.commands.schem;

import me.zerosio.commands.builder.CommandBase;
import me.zerosio.rank.PlayerRank;
import me.zerosio.schematic.devworld.DevWorld;

import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class DevWorldCommand extends CommandBase {

    @Override
    public String getName() {
        return "devworld";
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("dw");
    }

    @Override
    public PlayerRank getRequiredRank() {
        return PlayerRank.DEV;
    }

    @Override
    public String getDescription() {
        return "Create and enter a temporary dev world.";
    }

    @Override
    public String getUsage() {
        return "/devworld";
    }

    @Override
    public void execute(Player player, String[] args) {
        DevWorld.create(player);
    }
}
