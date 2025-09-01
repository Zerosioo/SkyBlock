package me.zerosio.commands;

import org.bukkit.entity.Player;

import me.zerosio.commands.builder.CommandBase;
import me.zerosio.rank.PlayerRank;
import me.zerosio.rank.StaffChat;

import java.util.Arrays;
import java.util.List;

public class StaffChatCommand extends CommandBase {

	@Override
	public String getName() {
		return "staffchat";
	}

	@Override
	public List<String> getAliases() {
		return Arrays.asList("sc");
	}

	@Override
	public PlayerRank getRequiredRank() {
		return PlayerRank.STAFF;
	}

	@Override
	public String getDescription() {
		return "Used chatting with staff";
	}

	@Override
	public String getUsage() {
		return "/sc <message>";
	}

	@Override
	public void execute(Player player, String[] args) {

		if (args.length == 0) {
			player.sendMessage("§cUsage: " + getUsage());
			return;
		}

		String message = String.join(" ", args);
		StaffChat.sendMessage(player, message);
	}
}
