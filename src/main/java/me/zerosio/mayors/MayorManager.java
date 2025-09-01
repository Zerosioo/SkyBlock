package me.zerosio.mayors;

import me.zerosio.npcs.build.AbstractNPC;
import me.zerosio.time.SkyblockYear;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.*;

public class MayorManager {

	public static final List<Mayor> ALL_MAYORS = new ArrayList<>();
	public static final List<Mayor> SELECTED_CANDIDATES = new ArrayList<>();
	public static final Map<Mayor, AbstractNPC> SPAWNED_NPCS = new HashMap<>();

	private static Location loc1, loc2, loc3, loc4, loc5;
	private static Mayor activeMayor;
	private static Mayor activeMinister;

	public static void registerMayor(Mayor mayor) {
		for (Mayor m : ALL_MAYORS) {
			if (m.getIdOfMayor().equalsIgnoreCase(mayor.getIdOfMayor())) return;
		}
		ALL_MAYORS.add(mayor);
	}

	public static void setCandidateLocations(Location l1, Location l2, Location l3, Location l4, Location l5) {
		loc1 = l1;
		loc2 = l2;
		loc3 = l3;
		loc4 = l4;
		loc5 = l5;
	}

	public static void spawnRandomisedCandidates() {
		SELECTED_CANDIDATES.clear();
		despawnAllCandidates();
		SPAWNED_NPCS.clear();

		int currentYear = SkyblockYear.getCurrentYear();

		List<Mayor> available = new ArrayList<>();
		List<Mayor> special = new ArrayList<>();

		for (Mayor mayor : ALL_MAYORS) {
			if (mayor.isSpecialMayor() && mayor.getSpecialInterval() > 0 && currentYear % mayor.getSpecialInterval() == 0) {
				special.add(mayor);
			} else {
				available.add(mayor);
			}

			mayor.setRole(MayorRole.NONE);
		}

		Collections.shuffle(available);

		List<Mayor> selected = new ArrayList<>(special);
		for (Mayor m : available) {
			if (selected.size() >= 5) break;
			selected.add(m);
		}

		SELECTED_CANDIDATES.addAll(selected);

		if (SELECTED_CANDIDATES.size() < 5) {
			Bukkit.getLogger().warning("Not enough mayors registered to fill all 5 candidate slots.");
		}

		Location[] locs = {loc1, loc2, loc3, loc4, loc5};
		for (int i = 0; i < SELECTED_CANDIDATES.size(); i++) {
			spawnCandidate(SELECTED_CANDIDATES.get(i), locs[i]);
		}
	}

	public static void despawnAllCandidates() {
		for (AbstractNPC npc : SPAWNED_NPCS.values()) {
			npc.despawn();
		}
		SPAWNED_NPCS.clear();
	}

	private static void spawnCandidate(Mayor mayor, Location location) {
		AbstractNPC npc = mayor.createNPC();
		npc.setPosition(location);
		SPAWNED_NPCS.put(mayor, npc);
	}

	public static void spawnElectedMayor(Location location, Mayor mayor) {
		if (mayor == null) {
			Bukkit.getLogger().warning("Tried to spawn a null mayor.");
			return;
		}

		if (activeMayor != null) despawnMayor();

		activeMayor = mayor;
		activeMayor.setRole(MayorRole.MAYOR);
		AbstractNPC npc = activeMayor.createNPC();
		npc.setPosition(location);
		SPAWNED_NPCS.put(activeMayor, npc);
		activeMayor.onActive();
	}
	
	public static void spawnElectedMinister(Location location, Mayor minister) {
		if (minister == null) {
			Bukkit.getLogger().warning("Tried to spawn a null mayor.");
			return;
		}

		if (activeMinister != null) despawnMinister();

		activeMinister = minister;
		activeMinister.setRole(MayorRole.MINISTER);
		AbstractNPC npc = activeMinister.createNPC();
		npc.despawn();
		npc.setPosition(location);
		SPAWNED_NPCS.put(activeMinister, npc);
		activeMinister.onActive();
	}


	public static void despawnMayor() {
		if (activeMayor != null) {
			AbstractNPC npc = SPAWNED_NPCS.remove(activeMayor);
			if (npc != null) npc.despawn();
			activeMayor.setRole(MayorRole.NONE);
			activeMayor = null;
		}
	}

	public static void despawnMinister() {
		if (activeMinister != null) {
			AbstractNPC npc = SPAWNED_NPCS.remove(activeMinister);
			if (npc != null) npc.despawn();
			activeMinister.setRole(MayorRole.NONE);
			activeMinister = null;
		}
	}

	public static void relocateMayor(Location newLocation) {
		if (activeMayor != null) {
			AbstractNPC npc = SPAWNED_NPCS.get(activeMayor);
			if (npc != null) npc.setPosition(newLocation);
		}
	}

	public static void relocateMinister(Location newLocation) {
		if (activeMinister != null) {
			AbstractNPC npc = SPAWNED_NPCS.get(activeMinister);
			if (npc != null) npc.setPosition(newLocation);
		}
	}

	public static Mayor getCandidateMayor(int slot) {
		if (slot < 1 || slot > SELECTED_CANDIDATES.size()) return null;
		return SELECTED_CANDIDATES.get(slot - 1);
	}

	public static AbstractNPC getCandidateNPC(int slot) {
		Mayor mayor = getCandidateMayor(slot);
		if (mayor == null) return null;
		return SPAWNED_NPCS.get(mayor);
	}

	public static Collection<AbstractNPC> getSpawnedNPCs() {
		return SPAWNED_NPCS.values();
	}

	public static Mayor getActiveMayor() {
		return activeMayor;
	}

	public static Mayor getActiveMinister() {
		return activeMinister;
	}
}
