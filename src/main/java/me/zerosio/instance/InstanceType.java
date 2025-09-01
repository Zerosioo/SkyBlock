package me.zerosio.instance;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum InstanceType {
	ISLAND("island"),
	DUNGEON("dungeon"),
	HUB("hub"),
	LIMBO("limbo"),
	GARDEN("garden"),
	LOBBY("lobby"),
	WORLD("world"),
	UNKNOWN("");

	private final String prefix;

	InstanceType(String prefix) {
		this.prefix = prefix.toLowerCase();
	}

	public String getPrefix() {
		return prefix;
	}

	public static InstanceType getInstance(World world) {
		return getInstance(world.getName());
	}

	public static InstanceType getInstance(String worldName) {
		String lower = worldName.toLowerCase();
		for (InstanceType type : values()) {
			if (!type.prefix.isEmpty() && lower.startsWith(type.prefix)) {
				return type;
			}
		}
		return UNKNOWN;
	}

	public static boolean isInsideInstance(World world, InstanceType type) {
		return getInstance(world) == type;
	}

	public static List<World> getMatchingWorlds(InstanceType type) {
		List<World> matches = new ArrayList<>();
		for (World world : Bukkit.getWorlds()) {
			if (getInstance(world) == type) {
				matches.add(world);
			}
		}
		return matches;
	}

	public boolean worldNameStartsWith(World world) {
		return world.getName().toLowerCase().startsWith(this.prefix);
	}

	public World getFirstWorld() {
		for (World world : Bukkit.getWorlds()) {
			if (getInstance(world) == this) {
				return world;
			}
		}
		return null;
	}

	public World getRandomInstanceWorld() {
		List<World> matches = new ArrayList<>();
		for (World world : Bukkit.getWorlds()) {
			if (world.getName().toLowerCase().startsWith(this.prefix)) {
				matches.add(world);
			}
		}
		if (matches.isEmpty()) return null;
		return matches.get(new Random().nextInt(matches.size()));
	}

}
