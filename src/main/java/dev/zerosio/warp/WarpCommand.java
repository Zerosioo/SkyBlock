package dev.zerosio.warp;

import dev.zerosio.warp.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// THIS IS TODO IN NEW COMMAND SYSTEM

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("§cUsage: /warp <location>");
            return true;
        }

        String id = args[0];
        if (!WarpManager.isWarpExist(id)) {
            player.sendMessage("§cUnknown warp location.");
            return true;
        }

        WarpManager.warpPlayer(player, id);
        return true;
    }
}
