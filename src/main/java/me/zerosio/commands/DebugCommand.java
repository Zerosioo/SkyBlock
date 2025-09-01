package me.zerosio.commands;

import org.bukkit.entity.Player;

import me.zerosio.commands.builder.CommandBase;
import me.zerosio.rank.PlayerRank;
import me.zerosio.user.User;

import java.util.List;

public class DebugCommand extends CommandBase {

    @Override
    public String getName() {
        return "debug";
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
        return "toggle admin debug";
    }

    @Override
    public String getUsage() {
        return "/debug";
    }

    @Override
    public void execute(Player player, String[] args) {
        User user = User.getUser(player);
        
        boolean debugMode = user.getData("debug_mode");
        
        if (debugMode) {
        	user.setData("debug_mode", false);
        	player.sendMessage("§c[ADMIN DEBUG] §fturned §coff§f.");
        } else if (!debugMode) {
        	user.setData("debug_mode", true);
        	player.sendMessage("§c[ADMIN DEBUG] §fturned §aon§f.");
        }
    }
}
