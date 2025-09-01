package me.zerosio;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import me.zerosio.commands.builder.CommandManager;
import me.zerosio.garden.visitors.build.VisitorLocalization;
import me.zerosio.guilds.main.Guild;
import me.zerosio.inventory.build.GUIRegistry;
import me.zerosio.items.itemtype.Lists;
import me.zerosio.listeners.*;
import me.zerosio.mayors.MayorLocalization;
import me.zerosio.mechanics.mining.MiningListener;
import me.zerosio.mechanics.mining.RegisterBlocks;
import me.zerosio.npcs.build.NPCInteractListener;
import me.zerosio.npcs.hub.merchants.*;
import me.zerosio.npcs.hub.special.Zerosio;
import me.zerosio.npcs.hub.villagers.*;
import me.zerosio.npcs.mayors.*;
import me.zerosio.utility.Logging;
import me.zerosio.warp.Warps;
import me.zerosio.zones.ZoneListener;

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
		
		Logging.sendMessage("§eRegistering NPCs...");
		
		registerNPCs();
		
		Logging.sendMessage("§aRegistered NPCs.");
		
		Logging.sendMessage("§eLoading guilds...");
		
		Guild.loadAllGuilds();
		
		Logging.sendMessage("§aLoaded guilds.");
		
		Logging.sendMessage("§eLoading items...");
		
		Lists.registerItems();
		
		Logging.sendMessage("§eInitialising Mayors...");
		
		MayorLocalization.initialise();
		
		Logging.sendMessage("§aInitialised Mayors.");
		
		Logging.sendMessage("§eRegistering Mining Blocks...");
		
		RegisterBlocks.reg();
		
		Logging.sendMessage("§aRegistered Mining Blocks");
		
		Logging.sendMessage("§eRegistering Garden Visitors...");
		
		VisitorLocalization.registerVisitors();
		
		Logging.sendMessage("§aRegistered Garden Visitors.");
		
		GUIRegistry.loadAllGUIs();
	}
	
	private void registerListeners() {
		Set<Listener> listeners = new HashSet<>();
	    listeners.add(new PlayerListener());
	    listeners.add(new SkyBlockListener());
	    listeners.add(new NPCInteractListener());
	    listeners.add(new AbilityListener());
	    listeners.add(new ElectionJukeBox());
	    new ZoneListener();
	    listeners.add(new MiningListener());
	    
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
	
	public static void registerNPCs() {
		// Hub Villagers!
		new Andrew();
		new Jack();
		new Jamie();
		new Felix();
		new Leo();
		new Ryu();
		new Tom();
		new Duke();
		new Lynn();
		new Stella();
		new Vex();
		new Liam();
		
		// Hub merchants
	    new Adventurer();
	    new Amelia();
	    new Anita();
	    new Arthur();
	    
	    // Hub Special
	    new Zerosio();
	}
}