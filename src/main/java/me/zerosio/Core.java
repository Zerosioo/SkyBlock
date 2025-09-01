package me.zerosio;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import me.zerosio.mayors.MayorHologram;
import me.zerosio.npcs.build.AbstractNPC;
import me.zerosio.user.User;
import me.zerosio.user.statistics.StatRegenerator;
import me.zerosio.utility.EntityUtil;
import me.zerosio.utility.HologramUtil;

public class Core extends JavaPlugin {

	private static Core instance;

	@Override
	public void onEnable() {
		EntityUtil.killAllEntities();
		
		instance = this;

		User.init(getDataFolder());

		new Loader().load();

		for (World world : getServer().getWorlds()) {
			me.zerosio.instance.InstanceID.register(world);
			System.out.println("[SkyBlock] Registered world instance (pre-loaded): " + world.getName());
		}
		
		StatRegenerator.start(this);
	}
	
	@Override
	public void onDisable() {
		AbstractNPC.clearAllNPCs();
		MayorHologram.remove();
		HologramUtil.removeHolograms();
		EntityUtil.killAllEntities();
		User.saveAll();
    }

	public static Core getInstance() {
		return instance;
	}
}
