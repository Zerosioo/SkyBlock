package dev.zerosio;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import dev.zerosio.user.User;

public class Core extends JavaPlugin {

	private static Core instance;

	@Override
	public void onEnable() {
		instance = this;

		User.init(getDataFolder());

		new Loader().load();

		for (World world : getServer().getWorlds()) {
			dev.zerosio.instance.InstanceID.register(world);
			System.out.println("[SkyBlock] Registered world instance (pre-loaded): " + world.getName());
		}
	}

	public static Core getInstance() {
		return instance;
	}
}
