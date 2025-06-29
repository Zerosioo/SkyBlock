package dev.zerosio.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.world.WorldLoadEvent;

import dev.zerosio.instance.InstanceID;
import dev.zerosio.instance.InstanceType;
import dev.zerosio.instance.hub.HubInstance;
import dev.zerosio.user.AdminDebug;

import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SkyBlockListener implements Listener {

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        World world = event.getWorld();
        InstanceID.register(world);
        System.out.println("[SkyBlock] Registered world instance: " + world.getName());
    }

    @EventHandler
    public void onWorldInit(WorldInitEvent event) {
        World world = event.getWorld();
        InstanceID.register(world);
        System.out.println("[SkyBlock] Registered world instance: " + world.getName());
    }
    
    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
    	Player player = event.getPlayer();
    	World world = player.getWorld();
    	
    	if (InstanceType.isInsideInstance(world, InstanceType.HUB)) {
    		HubInstance.start();
    	}
    	
    	AdminDebug.debug(player, "You are currently in " + InstanceType.getInstance(world).name().replace("_", " ") + " instance.");
    }
}
