package me.zerosio.guilds.database;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;
import java.util.stream.Collectors;

public class GuildCollection {

    private FileConfiguration getConfig() {
        return GuildDatabase.config;
    }

    public List<String> getIds() {
        if (getConfig().getConfigurationSection("guilds") == null) return new ArrayList<>();
        return new ArrayList<>(getConfig().getConfigurationSection("guilds").getKeys(false));
    }

    public List<GuildDatabase> findAll() {
        return getIds().stream().map(GuildDatabase::new).collect(Collectors.toList());
    }

    public GuildDatabase findByField(String field, String value) {
        for (String id : getIds()) {
            String path = "guilds." + id + "." + field;
            if (value.equalsIgnoreCase(getConfig().getString(path))) {
                return new GuildDatabase(id);
            }
        }
        return null;
    }

    public boolean exists(String id) {
        return getConfig().contains("guilds." + id);
    }

    public void delete(String id) {
        getConfig().set("guilds." + id, null);
    }
}
