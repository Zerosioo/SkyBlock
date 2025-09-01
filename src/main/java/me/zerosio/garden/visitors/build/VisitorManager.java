package me.zerosio.garden.visitors.build;

import me.zerosio.Core;
import me.zerosio.inventory.garden.VisitorTradeGUI;
import me.zerosio.npcs.build.AbstractNPC;
import me.zerosio.user.profiledata.VisitorData;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class VisitorManager {

	public static final List<GardenVisitor> ALL_VISITORS = new ArrayList<>();

	private static final Map<UUID, List<GardenVisitor>> ACTIVE_VISITORS = new HashMap<>();
	private static final Map<UUID, Map<GardenVisitor, AbstractNPC>> SPAWNED_VISITORS = new HashMap<>();
	private static final Map<UUID, Map<GardenVisitor, Integer>> VISITOR_SLOTS = new HashMap<>();

	private static final Location[] VISITOR_LOCATIONS = new Location[6];

	private static final long VISITOR_INTERVAL_TICKS = 20L * 60 * 20; // 20 minutes

	public static void registerVisitor(GardenVisitor visitor) {
		for (GardenVisitor v : ALL_VISITORS) {
			if (v.getName().equalsIgnoreCase(visitor.getName())) return;
		}
		ALL_VISITORS.add(visitor);
	}

	public static void setVisitorLocations(Location... locations) {
		for (int i = 0; i < Math.min(locations.length, VISITOR_LOCATIONS.length); i++) {
			VISITOR_LOCATIONS[i] = locations[i].clone();
		}
	}

	public static void startVisitorTimer(Player player, int gardenLevel) {
		new BukkitRunnable() {
			@Override
			public void run() {
				spawnNextVisitor(player, gardenLevel);
			}
		} .runTaskTimer(Core.getInstance(), 0L, VISITOR_INTERVAL_TICKS);
	}

	private static void spawnNextVisitor(Player player, int gardenLevel) {
		UUID uuid = player.getUniqueId();

		ACTIVE_VISITORS.putIfAbsent(uuid, new ArrayList<>());
		SPAWNED_VISITORS.putIfAbsent(uuid, new HashMap<>());
		VISITOR_SLOTS.putIfAbsent(uuid, new HashMap<>());

		List<GardenVisitor> activeList = ACTIVE_VISITORS.get(uuid);
		Map<GardenVisitor, AbstractNPC> spawned = SPAWNED_VISITORS.get(uuid);

		if (activeList.size() >= 6) return;

		List<GardenVisitor> valid = new ArrayList<>();
		for (GardenVisitor visitor : ALL_VISITORS) {
			if (!activeList.contains(visitor) && visitor.getRequiredGardenLevel() <= gardenLevel) {
				valid.add(visitor);
			}
		}

		if (valid.isEmpty()) return;

		Collections.shuffle(valid);
		GardenVisitor next = valid.get(new Random().nextInt(valid.size()));

		AbstractNPC npc = next.createNpc();
		npc.despawn();

		int slot = activeList.size(); // index 0 = front, 5 = back
		Location location = VISITOR_LOCATIONS[slot];
		npc.setPosition(location);

		npc.addInteraction(e -> {
			new VisitorTradeGUI(player, next).open();
		});

		spawned.put(next, npc);
		activeList.add(next); // add at end

		player.sendMessage(next.getRarity().getColor() + next.getName() + " §ehas arrived in your §bGarden§e!");
		VisitorData.addVisit(player, next.getName().toLowerCase());

		updateVisitorNumbers(player);
	}

	public static void declineVisitor(Player player, GardenVisitor visitor) {
		UUID uuid = player.getUniqueId();
		if (!ACTIVE_VISITORS.containsKey(uuid)) return;

		List<GardenVisitor> list = ACTIVE_VISITORS.get(uuid);
		Map<GardenVisitor, AbstractNPC> spawned = SPAWNED_VISITORS.get(uuid);

		if (!list.contains(visitor)) return;

		int index = list.indexOf(visitor); // Save original index
		list.remove(visitor); // Remove visitor
		VISITOR_SLOTS.get(uuid).remove(visitor);

		AbstractNPC npc = spawned.remove(visitor);
		if (npc != null) npc.despawn();

		// Shift remaining NPCs forward to fill the gap
		for (int i = index; i < list.size(); i++) {
			GardenVisitor shiftedVisitor = list.get(i);
			AbstractNPC moveNpc = spawned.get(shiftedVisitor);
			if (moveNpc != null) {
				Location to = VISITOR_LOCATIONS[i];
				if (to.clone().add(0, 1, 0).getBlock().isEmpty()) {
					moveNpc.walkTo(to);
				} else {
					moveNpc.setPosition(to); // fallback if walk blocked
				}
			}
		}

		updateVisitorNumbers(player);
	}


	public static void acceptVisitor(Player player, GardenVisitor visitor) {
		// TODO: Add reward logic here
		VisitorData.addAccepted(player, visitor.getName().toLowerCase());
		declineVisitor(player, visitor);
	}

	private static void updateVisitorNumbers(Player player) {
		UUID uuid = player.getUniqueId();
		List<GardenVisitor> list = ACTIVE_VISITORS.get(uuid);
		Map<GardenVisitor, Integer> slots = VISITOR_SLOTS.get(uuid);

		slots.clear();
		for (int i = 0; i < list.size(); i++) {
			slots.put(list.get(i), i + 1); // Slot 1 → 6
		}
	}

	public static int getVisitorSlot(Player player, GardenVisitor visitor) {
		return VISITOR_SLOTS.getOrDefault(player.getUniqueId(), Collections.emptyMap()).getOrDefault(visitor, -1);
	}

	public static void spawnAllVisitors(Player player, int gardenLevel) {
		for (int i = 0; i < getMaxVisitors(gardenLevel); i++) {
			spawnNextVisitor(player, gardenLevel);
		}
	}

	public static void despawnAllVisitors(Player player) {
		UUID uuid = player.getUniqueId();

		Map<GardenVisitor, AbstractNPC> spawned = SPAWNED_VISITORS.remove(uuid);
		if (spawned != null) {
			for (AbstractNPC npc : spawned.values()) {
				npc.despawn();
			}
		}

		ACTIVE_VISITORS.remove(uuid);
		VISITOR_SLOTS.remove(uuid);
	}

	public static int getMaxVisitors(int gardenLevel) {
		return Math.min(1 + gardenLevel / 5, 6);
	}

	public static List<GardenVisitor> getActiveVisitors(Player player) {
		return ACTIVE_VISITORS.getOrDefault(player.getUniqueId(), Collections.emptyList());
	}

	public static AbstractNPC getVisitorNPC(Player player, int slot) {
		List<GardenVisitor> list = getActiveVisitors(player);
		if (slot < 1 || slot > list.size()) return null;
		GardenVisitor visitor = list.get(slot - 1);
		return SPAWNED_VISITORS.getOrDefault(player.getUniqueId(), Collections.emptyMap()).get(visitor);
	}

	public static GardenVisitor getVisitor(Player player, int slot) {
		List<GardenVisitor> list = getActiveVisitors(player);
		if (slot < 1 || slot > list.size()) return null;
		return list.get(slot - 1);
	}

	public static Collection<AbstractNPC> getSpawnedNPCs(Player player) {
		return SPAWNED_VISITORS.getOrDefault(player.getUniqueId(), Collections.emptyMap()).values();
	}

	public static void spawnRandomVisitor(Player player, int gardenLevel) {
		spawnNextVisitor(player, gardenLevel);
	}

	public static void spawnAll(Player player) {
		for (int i = 0; i < 6; i++) {
			spawnNextVisitor(player, 10000);
		}
	}

}
