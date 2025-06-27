package dev.zerosio;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import dev.zerosio.commands.builder.CommandManager;
import dev.zerosio.listeners.*;
import dev.zerosio.utility.Logging;
import dev.zerosio.warp.Warps;

public class Loader {
	public void load() {
		Logging.sendMessage("§eRegistering listeners...");
		registerListeners();
		Logging.sendMessage("§aRegistered listeners.");
		
		Logging.sendMessage("§eInitializing GameRules for each world...");
		initializeGameRules();
		Logging.sendMessage("§aInitialized GameRules for each world.");
		
		Logging.sendMessage("§eRegistering commands...");
		CommandManager.registerCommands();
		Logging.sendMessage("§aRegistered commands.");
		
		Logging.sendMessage("§eRegistering warps...");
		Warps.register();
		Logging.sendMessage("§aRegistered warps.");
	}
	
	private void registerListeners() {
		Set<Listener> listeners = new HashSet<>();
	    listeners.add(new PlayerListener());
	    listeners.add(new SkyBlockListener());

		listeners.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, Core.getPlugin(Core.class)));
	}
	
	private void initializeGameRules() {
		Bukkit.getWorlds().forEach(world -> {
			world.setGameRuleValue("doWeatherCycle", "false");
			world.setGameRuleValue("doFireTick", "false");
			world.setGameRuleValue("doMobSpawning", "false");
			world.setGameRuleValue("doMobLoot", "false");
			world.setGameRuleValue("doTileDrops", "false");
			world.setGameRuleValue("naturalRegeneration", "false");
			world.setGameRuleValue("showDeathMessages", "false");
			world.setGameRuleValue("randomTickSpeed", "0");
			world.setGameRuleValue("announceAdvancements", "false");
		});
	}
}