package me.zerosio.npcs.build;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.zerosio.Core;
import me.zerosio.user.User;
import me.zerosio.utility.Logging;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;

public abstract class AbstractNPC {
	protected final UUID id = UUID.randomUUID();
	protected final NPC npc;
	protected Location location;
	protected final List<ArmorStand> holograms = new ArrayList<>();
	private static final List<AbstractNPC> spawnedNPCs = new ArrayList<>();
	private final List<Consumer<Player>> interactionHandlers = new ArrayList<>();
	private int hologramTaskId = -1;

	protected AbstractNPC(String name) {
		this.npc = CitizensAPI.getNPCRegistry().createNPC(getEntityType(), name);
		spawnedNPCs.add(this);

		this.location = getInitialLocation();
		if (location != null) {
			npc.spawn(location);
			updatePosition();
		}

		if (canLook()) {
			LookClose look = npc.getOrAddTrait(LookClose.class);
			look.setRealisticLooking(true);
			look.setRange(10);
			look.lookClose(true);
		}

		npc.setName("");
		npc.data().setPersistent("removefromfile", true);
		npc.data().setPersistent(NPC.NAMEPLATE_VISIBLE_METADATA, false);

		addSkin();
		applyHeldItem();
		applyHolograms();
		NPCInteractListener.register(this);
		Logging.sendMessage("&aSpawning: &d" + getClass().getSimpleName() + " &7(ID: " + id + ")");
	}

	public void setX(double x) {
		location.setX(x);
		updatePosition();
	}

	public void setY(double y) {
		location.setY(y);
		updatePosition();
	}

	public void setZ(double z) {
		location.setZ(z);
		updatePosition();
	}

	public void setYaw(float yaw) {
		location.setYaw(yaw);
		updatePosition();
	}

	public void setPitch(float pitch) {
		location.setPitch(pitch);
		updatePosition();
	}

	public void setPosition(Location loc) {
		this.location = loc;

		if (!loc.getChunk().isLoaded()) {
			loc.getChunk().load();
		}

		if (npc.isSpawned()) {
			npc.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
		} else {
			npc.spawn(loc);
		}

		updateHologramLocation();
	}

	private void updatePosition() {
		if (location != null && npc.isSpawned())
			npc.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
	}

	public void walkTo(Location locc) {
		this.location = locc;
		npc.getNavigator().getLocalParameters().speedModifier(0.4f);
		npc.getNavigator().setTarget(locc);
		npc.getNavigator().setTarget(locc);
		updateHologramLocation();
	}

	public void setItemHeld(ItemStack item) {
		Equipment trait = npc.getOrAddTrait(Equipment.class);
		trait.set(Equipment.EquipmentSlot.HAND, item);
	}

	public void addSkin() {
		if (getTexture() != null && getSignature() != null && getTexture().isEmpty() == false && getSignature().isEmpty() == false) {
			npc.getOrAddTrait(SkinTrait.class).setSkinPersistent("zerosio", getSignature(), getTexture());
		}
	}

	public void setHolograms(List<String> lines) {
		clearHolograms();

		double baseY = location.getY() + getHologramStartOffset();
		double spacing = getHologramLineSpacing();

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			double y = baseY + ((lines.size() - 1 - i) * spacing);
			Location holoLoc = new Location(location.getWorld(), location.getX(), y, location.getZ());

			ArmorStand stand = spawnHologram(holoLoc, line);
			holograms.add(stand);
		}
	}

	private ArmorStand spawnHologram(Location loc, String text) {
		ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class);
		stand.setVisible(false);
		stand.setGravity(false);
		stand.setCustomName(ChatColor.translateAlternateColorCodes('&', text));
		stand.setCustomNameVisible(true);
		stand.setMarker(true);
		stand.setSmall(true);
		return stand;
	}

	protected void clearHolograms() {
		for (ArmorStand stand : holograms) {
			stand.remove();
		}
		holograms.clear();
	}

	private void applyHolograms() {
		List<String> lines = getHologramLines();

		if (lines != null && !lines.isEmpty()) {
			setHolograms(lines);
		} else {
			setHolograms(Arrays.asList("&f" + npc.getName(), "&e&lCLICK"));
		}
	}

	private void applyHeldItem() {
		ItemStack item = getHeldItem();
		if (item != null) setItemHeld(item);
	}

	public void startHologramUpdater(long ticks) {
		if (hologramTaskId != -1) {
			Bukkit.getScheduler().cancelTask(hologramTaskId);
		}

		hologramTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(
		JavaPlugin.getProvidingPlugin(getClass()), () -> {
			List<String> updatedLines = getUpdatedHologramLines();
			if (updatedLines != null && !updatedLines.isEmpty()) {
				setHolograms(updatedLines);
			}
		}, 0L, ticks
						 );
	}

	public void stopHologramUpdater() {
		if (hologramTaskId != -1) {
			Bukkit.getScheduler().cancelTask(hologramTaskId);
			hologramTaskId = -1;
		}
	}

	public void updateHologramLocation() {
		if (location == null) return;

		List<String> updatedLines = getUpdatedHologramLines();
		if (updatedLines == null || updatedLines.isEmpty()) return;

		clearHolograms();
		applyHolograms();
	}

	public static void deleteCitizensSave() {
		new File("plugins/Citizens/saves.yml").delete();
	}

	public static void clearAllNPCs() {
		for (AbstractNPC npc : spawnedNPCs) {
			if (npc.npc.isSpawned()) npc.npc.despawn();
			CitizensAPI.getNPCRegistry().deregister(npc.npc);
			npc.clearHolograms();
			npc.stopHologramUpdater();
		}
		spawnedNPCs.clear();
		deleteCitizensSave();
	}

	public void despawn() {
		if (npc != null && npc.isSpawned()) {
			npc.despawn();
			Logging.sendMessage("&aDespawning: &d" + npc.getClass().getSimpleName());
		}
		clearHolograms();
		stopHologramUpdater();
	}

	public NPC getNPC() {
		return npc;
	}

	public UUID getId() {
		return id;
	}

	public boolean isFirstInteraction(Player player) {
		User user = User.getUser(player);
		UUID profileId = user.getActiveProfileId();

		String key = getClass().getSimpleName().toLowerCase();
		String path = "profiles." + profileId + ".interactions." + key;

		boolean first = !user.config.getBoolean(path, false);
		if (first) {
			user.config.set(path, true);
			user.save();
		}
		return first;
	}

	public final void handleInteract(Player player) {
		
		onInteract(player);
		
		for (Consumer<Player> handler : interactionHandlers) {
			handler.accept(player);
		}
	}

	public void addInteraction(Consumer<Player> handler) {
		interactionHandlers.add(handler);
	}

	public EntityType getEntityType() {
		return EntityType.PLAYER;
	}

	public abstract void onInteract(Player player);

	public abstract Location getInitialLocation();

	public List<String> getHologramLines() {
		return null;
	}

	public List<String> getUpdatedHologramLines() {
		return getHologramLines();
	}

	public ItemStack getHeldItem() {
		return null;
	}

	public String getTexture() {
		return null;
	}

	public String getSignature() {
		return null;
	}

	public double getHologramLineSpacing() {
		return 0.25;
	}

	public double getHologramStartOffset() {
		return 1.9;
	}

	public boolean canLook() {
		return true;
	}
}
