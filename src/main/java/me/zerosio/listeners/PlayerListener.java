package me.zerosio.listeners;

import java.util.Arrays;

import me.zerosio.services.bank.BankInterest;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.zerosio.chat.ActiveChat;
import me.zerosio.chat.ClickActionType;
import me.zerosio.chat.MessageUtil;
import me.zerosio.instance.InstanceType;
import me.zerosio.rank.PlayerRank;
import me.zerosio.rank.StaffChat;
import me.zerosio.schematic.devworld.DevWorld;
import me.zerosio.scoreboard.SkyBlockScoreboard;
import me.zerosio.user.AdminDebug;
import me.zerosio.user.User;
import me.zerosio.user.levels.SkyBlockLevel;
import me.zerosio.user.profiledata.UserRank;
import me.zerosio.utility.NameTag;

public class PlayerListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		User user = User.getUser(player);

		event.setJoinMessage(null);

		//String init = UserRank.getPlayerRank(player).getPrefix() + player.getName();

		StaffChat.sendStaffJoinMessage(player);

		//NameTag.setDisplayName(player, player.getName(), UserRank.getPlayerRank(player).getPrefix());
		NameTag.update(player);

		SkyBlockScoreboard.startTask(player);

		String profileName = user.getProfileData("name");

		player.sendMessage("§eWelcome to §aProject Saturn's SkyBlock§e!");
		player.sendMessage("");
		player.sendMessage("§aYou are playing on profile: §e" + profileName);
		MessageUtil.sendClickableMessage(player, "§8Profile ID: " + user.getActiveProfileId(), ClickActionType.COPY_TO_CLIPBOARD, user.getActiveProfileId().toString(),
										 Arrays.asList("§8Click to copy to clipboard!"));

		Object stored = user.getData("last_instance");
		if (stored != null) {
			try {
				InstanceType playerLastInstance = InstanceType.valueOf(stored.toString());
				World target = playerLastInstance.getRandomInstanceWorld();
				if (target != null) {
					player.teleport(target.getSpawnLocation());
				} else {
					AdminDebug.debug(player, "No available worlds for your last instance: §c" + playerLastInstance.name());
					AdminDebug.debug(player, "Falling back to HUB instance!");
					//player.teleport(InstanceType.HUB.getRandomInstanceWorld().getSpawnLocation());
				}
			} catch (IllegalArgumentException e) {
				AdminDebug.debug(player, "Your last instance type is invalid.");
				AdminDebug.debug(player, "Falling back to HUB instance!");
				//player.teleport(InstanceType.HUB.getRandomInstanceWorld().getSpawnLocation());
			}
		}

		AdminDebug.debug(player, "You are currently in " + InstanceType.getInstance(player.getWorld()).name().replace("_", " ") + " instance.");
		
		player.sendMessage("");
		
		if (user.getData("active_chat") == ActiveChat.GUILD.name()) {
		player.sendMessage("§eYou are currently chatting in §2GUILD §echannel.");
		player.sendMessage("§eUse §b/chat a §eor §b/chat all §eto switch to all chat.");
		}
		
		if (user.getData("active_chat") == ActiveChat.STAFF.name()) {
		player.sendMessage("§eYou are currently chatting in §bSTAFF §echannel.");
		player.sendMessage("§eUse §b/chat a §eor §b/chat all §eto switch to all chat.");
		}

		BankInterest.startMonitoring(player);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		User user = User.getUser(event.getPlayer());

		event.setQuitMessage(null);

		StaffChat.sendStaffDisconnectMessage(event.getPlayer());

		user.setData("last_instance", InstanceType.getInstance(event.getPlayer().getWorld()).name());
		DevWorld.handleLeave(event.getPlayer());
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		User user = User.getUser(player);
		PlayerRank rank = UserRank.getPlayerRank(player);
		String prefix = rank.getPrefix();
		String level = SkyBlockLevel.getNameTagPrefix(player) + " ";

		if (user.getData("active_chat") == ActiveChat.ALL.name()) {
			if (rank.isAboveOrEqual(PlayerRank.YOUTUBE)) {
				String format = level + prefix + player.getName() + "§f: " + event.getMessage();
				event.setFormat(format);
			} else {
				String format = level + prefix + player.getName() + ": " + event.getMessage();
				event.setFormat(format);
			}
		} else if (user.getData("active_chat") == ActiveChat.GUILD.name()) {
			event.setCancelled(true);
			player.performCommand("gc " + event.getMessage());
		} else {
			if (rank.isAboveOrEqual(PlayerRank.YOUTUBE)) {
				String format = level + prefix + player.getName() + "§f: " + event.getMessage();
				event.setFormat(format);
			} else {
				String format = level + prefix + player.getName() + ": " + event.getMessage();
				event.setFormat(format);
			}
		}
	}

}
