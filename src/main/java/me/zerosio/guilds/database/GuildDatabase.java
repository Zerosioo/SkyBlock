package me.zerosio.guilds.database;

import me.zerosio.Core;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GuildDatabase {

    protected static File file;
    protected static FileConfiguration config;

    public static final GuildCollection collection = new GuildCollection();

    public final String id;

    public GuildDatabase(String id) {
        this.id = id;
        loadConfig();
        createIfNotExists();
    }

    public GuildDatabase() {
        this.id = UUID.randomUUID().toString();
        loadConfig();
        createIfNotExists();
    }

    private static void loadConfig() {
        if (file != null && config != null) return;

        file = new File(Core.getPlugin(Core.class).getDataFolder(), "guilds.yml");
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    private void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createIfNotExists() {
        if (!config.contains("guilds." + id)) {
            config.createSection("guilds." + id);
            saveConfig();
        }
    }

    public void set(String key, Object value) {
        config.set("guilds." + id + "." + key, value);
        saveConfig();
    }

    public Object get(String key, Object def) {
        String path = "guilds." + id + "." + key;
        return config.contains(path) ? config.get(path) : def;
    }

    public String getString(String key, String def) {
        return String.valueOf(get(key, def));
    }

    public int getInt(String key, int def) {
        return config.getInt("guilds." + id + "." + key, def);
    }

    public long getLong(String key, long def) {
        return config.getLong("guilds." + id + "." + key, def);
    }

    public boolean getBoolean(String key, boolean def) {
        return config.getBoolean("guilds." + id + "." + key, def);
    }

    public <T> List<T> getList(String key, Class<T> t) {
        List<?> rawList = config.getList("guilds." + id + "." + key);
        if (rawList == null) return new ArrayList<>();

        List<T> list = new ArrayList<>();
        for (Object o : rawList) {
            if (t.isInstance(o)) list.add(t.cast(o));
        }
        return list;
    }

    public boolean remove(String id) {
        if (!config.contains("guilds." + id)) {
            return false;
        }
        config.set("guilds." + id, null);
        saveConfig();
        return true;
    }

    public boolean exists() {
        return config.contains("guilds." + id);
    }

    public static GuildDatabase findByGuildName(String name) {
        if (config.getConfigurationSection("guilds") == null) return null;

        for (String id : config.getConfigurationSection("guilds").getKeys(false)) {
            String guildName = config.getString("guilds." + id + ".name");
            if (guildName != null && guildName.equalsIgnoreCase(name)) {
                return new GuildDatabase(id);
            }
        }
        return null;
    }

    public static GuildDatabase findByGuildTag(String tag) {
        if (config.getConfigurationSection("guilds") == null) return null;

        for (String id : config.getConfigurationSection("guilds").getKeys(false)) {
            String guildTag = config.getString("guilds." + id + ".tag");
            if (guildTag != null && guildTag.equalsIgnoreCase(tag)) {
                return new GuildDatabase(id);
            }
        }
        return null;
    }
}
