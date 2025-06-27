package dev.zerosio.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

import dev.zerosio.instance.InstanceID;

import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.World;

public class SkyBlockListener implements Listener {

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        World world = event.getWorld();
        InstanceID.register(world);
    }

    @EventHandler
    public void onWorldInit(WorldInitEvent event) {
        World world = event.getWorld();
        InstanceID.register(world);
    }
}
