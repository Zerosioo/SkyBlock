package me.zerosio.commands;

import me.zerosio.commands.builder.CommandBase;
import me.zerosio.user.statistics.StatManager;
import me.zerosio.user.statistics.StatType;
import me.zerosio.user.statistics.PlayerStats;
import me.zerosio.rank.PlayerRank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddStatCommand extends CommandBase {

    @Override
    public String getName() {
        return "addstat";
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
        return "Temporarily adds a stat to a player.";
    }

    @Override
    public String getUsage() {
        return "/addstat <player> <stat> <amount>";
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length < 3) {
            player.sendMessage("§cUsage: /addstat <player> <stat> <amount>");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("§cPlayer not found: §e" + args[0]);
            return;
        }

        StatType stat = null;
        for (StatType type : StatType.values()) {
            if (type.name().equalsIgnoreCase(args[1])) {
                stat = type;
                break;
            }
        }

        if (stat == null) {
            player.sendMessage("§cInvalid stat type. Valid types: §e" +
                Arrays.stream(StatType.values()).map(Enum::name).collect(Collectors.joining(", ")));
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            player.sendMessage("§cInvalid amount: §e" + args[2]);
            return;
        }

        PlayerStats stats = StatManager.getStats(target);
        double current = stats.getStat(stat);
        stats.getAllStats().put(stat, current + amount);

        player.sendMessage("§aAdded §b" + amount + " " + stat.getFormattedName() +
            "§a to §e" + target.getName() + "§a (New total: §b" + (current + amount) + "§a)");
        target.sendMessage("§aYou gained §b" + amount + " " + stat.getFormattedName() + "§a!");
    }
}
