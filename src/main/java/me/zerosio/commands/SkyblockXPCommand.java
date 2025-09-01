package me.zerosio.commands;

import me.zerosio.commands.builder.CommandBase;
import me.zerosio.rank.PlayerRank;
import me.zerosio.user.profiledata.UserLevel;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class SkyblockXPCommand extends CommandBase {

    @Override
    public String getName() {
        return "skyblockxp";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("sbxp", "sxp");
    }

    @Override
    public PlayerRank getRequiredRank() {
        return PlayerRank.ADMIN;
    }

    @Override
    public String getDescription() {
        return "Manage a player's SkyBlock level or XP.";
    }

    @Override
    public String getUsage() {
        return "/skyblockxp <level|lvl|xp> <add|set> <amount>";
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length < 3) {
            player.sendMessage("§cUsage: /skyblockxp <level|lvl|xp> <add|set> <amount>");
            return;
        }

        String type = args[0].toLowerCase();
        String action = args[1].toLowerCase();
        int amount;

        try {
            amount = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            player.sendMessage("§cInvalid amount: must be a number.");
            return;
        }

        boolean isLevel = type.equals("level") || type.equals("lvl");
        boolean isXp = type.equals("xp");

        if (!isLevel && !isXp) {
            player.sendMessage("§cInvalid type. Use 'level', 'lvl', or 'xp'.");
            return;
        }

        if (!(action.equals("add") || action.equals("set"))) {
            player.sendMessage("§cInvalid action. Use 'add' or 'set'.");
            return;
        }

        if (isLevel) {
            if (action.equals("add")) {
                UserLevel.addLevel(player, amount);
                player.sendMessage("§aAdded §e" + amount + " §alevel(s). New level: §b" + UserLevel.getLevel(player));
            } else {
                UserLevel.setLevel(player, amount);
                player.sendMessage("§aSet level to: §b" + amount);
            }
        } else {
            if (action.equals("add")) {
                UserLevel.addXp(player, amount);
                player.sendMessage("§aAdded §e" + amount + " §aXP. Current XP: §b" + UserLevel.getXp(player));
            } else {
                UserLevel.setXp(player, amount);
                player.sendMessage("§aSet XP to: §b" + amount);
            }
        }
    }
}
