package me.zerosio.commands.schem;

import me.zerosio.commands.builder.CommandBase;
import me.zerosio.rank.PlayerRank;
import me.zerosio.schematic.SchematicManager;
import me.zerosio.user.AdminDebug;
import me.zerosio.utility.Timer;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PasteCommand extends CommandBase {

    @Override
    public String getName() {
        return "pasteschem";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("pastebro", "paste", "ps");
    }

    @Override
    public PlayerRank getRequiredRank() {
        return PlayerRank.ADMIN;
    }

    @Override
    public String getDescription() {
        return "Paste a .56tb schematic";
    }

    @Override
    public String getUsage() {
        return "/pasteschem <name>";
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length != 1) {
            player.sendMessage("§cUsage: /pasteschem <name>");
            return;
        }

        String name = args[0];
        Location loc = player.getLocation().getBlock().getLocation();
        Timer timer = new Timer();
        
        AdminDebug.debug(player, "Starting paste service §e" + name + ".zerosio§f.");
        try {
        	timer.start();
        SchematicManager.pasteSchematic(name, loc, 0);
            timer.stop();
            AdminDebug.debug(player, "Paste service §acompleted §ffor §e" + name + " §fin §b" + timer.endTime() + " ms§f.");
        } catch (IOException e) {
        	player.sendMessage("§cFailed to complete paste service, check logs ");
        }
    }
}
