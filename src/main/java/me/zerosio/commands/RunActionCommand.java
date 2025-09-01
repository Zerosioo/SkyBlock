package me.zerosio.commands;

import org.bukkit.entity.Player;

import me.zerosio.chat.ActionHandler;
import me.zerosio.commands.builder.CommandBase;
import me.zerosio.rank.PlayerRank;

import java.util.Arrays;
import java.util.List;

public class RunActionCommand extends CommandBase {

    @Override
    public String getName() {
        return "blahgydydbd";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("");
    }

    @Override
    public PlayerRank getRequiredRank() {
        return PlayerRank.ADMIN;
    }

    @Override
    public String getDescription() {
        return "Used for selecting options";
    }

    @Override
    public String getUsage() {
        return "/blahgydydbd <id>";
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length != 1) return;
        
        String id = args[0];
        ActionHandler.runAction(id);
        return;
    }
}
