package me.zerosio.commands.builder;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zerosio.rank.PlayerRank;
import me.zerosio.user.profiledata.UserRank;

import java.util.List;

public abstract class CommandBase {

    public abstract String getName();

    public abstract List<String> getAliases();

    public abstract PlayerRank getRequiredRank();

    public abstract String getDescription();

    public abstract String getUsage();

    public abstract void execute(Player sender, String[] args);

    public void executeCommand(CommandSender sender, String[] args) {
        if (!consoleCommand() && !(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can use this command.");
            return;
        }

        Player player = sender instanceof Player ? (Player) sender : null;

        if (!consoleCommand() && player != null) {
            PlayerRank rank = getPlayerRank(player);
            if (!rank.isAboveOrEqual(getRequiredRank())) {
                player.sendMessage("§cYou need " + getRequiredRank().getPrefixColoured() + "§c or higher to use this command.");
                return;
            }
        }

        execute(player, args);
    }

    private PlayerRank getPlayerRank(Player player) {
        return UserRank.getPlayerRank(player);
    }

    public boolean consoleCommand() {
        return false;
    }
}
