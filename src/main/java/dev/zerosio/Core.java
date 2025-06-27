package dev.zerosio;

import org.bukkit.plugin.java.JavaPlugin;

import dev.zerosio.user.User;

public class Core extends JavaPlugin {

    private static Core instance;

    @Override
    public void onEnable() {
        instance = this;
        
        User.init(getDataFolder());
        
        new Loader().load();
    }

    public static Core getInstance() {
        return instance;
    }
}
