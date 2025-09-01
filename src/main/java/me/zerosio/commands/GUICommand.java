package me.zerosio.commands;

import org.bukkit.entity.Player;

import me.zerosio.commands.builder.CommandBase;
import me.zerosio.inventory.build.GUIRegistry;
import me.zerosio.rank.PlayerRank;

import java.util.List;

public class GUICommand extends CommandBase {

	@Override
	public String getName() {
		return "gui";
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
		return "open gui";
	}

	@Override
	public String getUsage() {
		return "/gui <gui_name>";
	}

	@Override
	public void execute(Player player, String[] args) {
		if (args.length < 1) {
			player.sendMessage("§cUsage: /warp <location>");
			return;
		}

		String id = args[0];

		if (id.equalsIgnoreCase("list")) {
			player.sendMessage("        §aAVAILABLE GUI ID(s)");

			for (String idd : GUIRegistry.getRegisteredIds()) {
				player.sendMessage("§e§l" + idd);
			}
			
			
			return;
		}

		GUIRegistry.open(id.toUpperCase(), player);
	}
}
