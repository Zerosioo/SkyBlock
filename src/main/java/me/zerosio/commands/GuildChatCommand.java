package me.zerosio.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.zerosio.commands.builder.CommandBase;
import me.zerosio.guilds.Utilities;
import me.zerosio.guilds.main.Guild;
import me.zerosio.rank.PlayerRank;
import me.zerosio.warp.WarpManager;
import static me.zerosio.commands.GuildCommand.cooldown;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Joiner;

public class GuildChatCommand extends CommandBase {

	@Override
	public String getName() {
		return "guildchat";
	}

	@Override
	public List<String> getAliases() {
		return Arrays.asList("gc");
	}

	@Override
	public PlayerRank getRequiredRank() {
		return PlayerRank.DEFAULT;
	}

	@Override
	public String getDescription() {
		return "Guild chat";
	}

	@Override
	public String getUsage() {
		return "/gc <message>";
	}

	@Override
	public void execute(Player player, String[] args) {
		handle(player, args);
	}

	public static void handle(Player player, String[] args) {
		if (cooldown.containsKey(player.getUniqueId())) {
			if (System.currentTimeMillis() - cooldown.get(player.getUniqueId()) < 1000) {
				player.sendMessage("§cYou are sending commands too fast!");
				return;
			}
		}
		cooldown.put(player.getUniqueId(), System.currentTimeMillis());
		if (Guild.inGuild(player)) {
			Guild guild = Guild.getGuildFromPlayer(player);

			String toAdd = "";
			if (guild.getLeader().equals(player.getUniqueId().toString())) {
				toAdd = "§" + guild.getTagColor() + "[GM]";
			}
			if (guild.getMembers().contains(player.getUniqueId().toString())) {
				toAdd = "§" + guild.getTagColor() + "[M]";
			}
			if (guild.getOfficer().contains(player.getUniqueId().toString())) {
				toAdd = "§" + guild.getTagColor() + "[OF]";
			}

			String finalToAdd = toAdd;
			guild.getOnlinePlayers().forEach(onlinePlayer -> {
				onlinePlayer.sendMessage("§2Guild > " + Utilities.getRankFromPlayer(player) + player.getName() + " " + finalToAdd + "§f: " + ChatColor.translateAlternateColorCodes('&', Joiner.on(" ").join(args)));
			});

		} else {
			player.sendMessage("§b§m-----------------------------------------------------");
			player.sendMessage("§cYou must be in a guild to use this command!");
			player.sendMessage("§b§m-----------------------------------------------------");
		}
	}
}
