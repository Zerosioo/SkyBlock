package dev.zerosio.listeners;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import dev.zerosio.chat.ClickActionType;
import dev.zerosio.chat.MessageUtil;
import dev.zerosio.instance.InstanceType;
import dev.zerosio.rank.PlayerRank;
import dev.zerosio.scoreboard.SkyBlockScoreboard;
import dev.zerosio.user.AdminDebug;
import dev.zerosio.user.User;
import dev.zerosio.user.profiledata.UserRank;
import dev.zerosio.utility.NameTag;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        User user = User.getUser(player);
		
		event.setJoinMessage(null);
        
        String init = UserRank.getPlayerRank(player).getPrefix() + player.getName();
        
        NameTag.setDisplayName(player, player.getName(), UserRank.getPlayerRank(player).getPrefix());
        
            
        SkyBlockScoreboard.startTask(player);
        
        String profileName = user.getProfileData("name");
        
        player.sendMessage("§eWelcome to §aZero's SkyBlock§e!");
        player.sendMessage("");
        player.sendMessage("§aYou are playing on profile: §e" + profileName);
        MessageUtil.sendClickableMessage(player, "§8Profile ID: " + user.getActiveProfileId(), ClickActionType.COPY_TO_CLIPBOARD, user.getActiveProfileId().toString(), Arrays.asList("§8Click to copy to clipboard!"));
        
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
                    player.teleport(InstanceType.HUB.getRandomInstanceWorld().getSpawnLocation());
                }
            } catch (IllegalArgumentException e) {
                AdminDebug.debug(player, "Your last instance type is invalid.");
                AdminDebug.debug(player, "Falling back to HUB instance!");
                player.teleport(InstanceType.HUB.getRandomInstanceWorld().getSpawnLocation());
            }
        }
        
        AdminDebug.debug(player, "You are currently in " + InstanceType.getInstance(player.getWorld()).name().replace("_", " ") + " instance.");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        User user = User.getUser(event.getPlayer());
        
        event.setQuitMessage(null);
        
        user.setData("last_instance", InstanceType.getInstance(event.getPlayer().getWorld()).name());
    }
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        User user = User.getUser(player);
        PlayerRank rank = UserRank.getPlayerRank(player);
        String prefix = rank.getPrefix(); 
        
        if (rank.isAboveOrEqual(PlayerRank.VIP)) {
        String format = prefix + player.getName() + "§f: " + event.getMessage();
        event.setFormat(format);
        } else {
        	String format = prefix + player.getName() + ": " + event.getMessage();
        	event.setFormat(format);
        }
    }
    
}
