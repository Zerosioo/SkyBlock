package me.zerosio.utility;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class EntityUtil {
	public static void killAllEntities() {
		for (World world : Bukkit.getWorlds()) {
			for (Entity entity : world.getEntities()) {
				entity.remove(); 
			}
		}
	  Logging.sendMessage("&dKilling all entities");
	}
}