package me.zerosio.kuudra.tentacles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.zerosio.Core;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import java.util.ArrayList;
import java.util.List;

public class Tentacle {

	private final List<MagmaCube> segments = new ArrayList<>();
	private final List<Location> segmentPositions = new ArrayList<>();
	private final World world;
	private final int length;
	private final int maxSize;
	private final int moveDelay;
	private final Location baseLocation;

	private final double centerX = 0;
	private final double centerY = 100;
	private final double centerZ = 0;

	public Tentacle(World world, Location base, int length, int maxSize, int moveDelay) {
		this.world = world;
		this.length = length;
		this.maxSize = maxSize;
		this.moveDelay = moveDelay;
		this.baseLocation = base.clone();
		spawnTentacle();
		startMovementTask();
	}

	private void spawnTentacle() {
		Location prevLoc = baseLocation.clone();
		for (int i = maxSize; i > maxSize - length; i--) {
			int size = Math.max(1, i);
			double spacing = size * 0.25;
			Location loc = prevLoc.clone().add(0, spacing, 0);
			segmentPositions.add(loc);
			MagmaCube segment = world.spawn(loc, MagmaCube.class);
			segment.setSize(size);
			disableAI(segment);
			segment.setRemoveWhenFarAway(false);
			segment.setCustomNameVisible(false);
			setTag(segment, "TentacleSegment", "true");
			segment.setVelocity(new Vector(0, 0, 0));
			segments.add(segment);
			prevLoc = loc;
		}
	}

	private void startMovementTask() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (segments.isEmpty()) {
					cancel();
					return;
				}
				updateSegmentPositions();
				applyPositionsToEntities();
			}
		}.runTaskTimer(Core.getPlugin(Core.class), 0L, moveDelay);
	}

	private void updateSegmentPositions() {
		Location previous = baseLocation.clone();
		long time = System.currentTimeMillis();
		double swayAmplitude = 0.35;
		double swaySpeed = 0.002;

		for (int i = 0; i < length; i++) {
			MagmaCube segment = segments.get(i);
			int size = segment.getSize();
			double spacing = size * 0.5;
			double scale = Math.sin(Math.PI * i / (length - 1));
			double sway = Math.sin(time * swaySpeed + i * 0.5) * swayAmplitude * scale;
			Location loc = previous.clone().add(sway, spacing, 0);
			segmentPositions.set(i, loc);
			previous = loc;
		}
	}

	private void applyPositionsToEntities() {
		Location centerLocation = new Location(world, centerX, centerY, centerZ);
		for (int i = 0; i < segments.size(); i++) {
			MagmaCube segment = segments.get(i);
			Location loc = segmentPositions.get(i).clone();
			Vector toCenter = centerLocation.toVector().subtract(loc.toVector());
			if (toCenter.lengthSquared() > 0.01) {
				Vector away = toCenter.multiply(-1);
				double yaw = Math.toDegrees(Math.atan2(-away.getX(), away.getZ()));
				double pitch = Math.toDegrees(Math.asin(-away.getY() / away.length()));
				loc.setYaw((float) yaw);
				loc.setPitch((float) pitch);
			}
			segment.teleport(loc);
		}
	}

	public void remove() {
		for (Entity e : segments) {
			e.remove();
		}
		segments.clear();
		segmentPositions.clear();
	}

	public static void disableAI(Entity entity) {
		net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) entity).getHandle();
		if (nmsEntity instanceof EntityInsentient) {
			EntityInsentient insentient = (EntityInsentient) nmsEntity;
			NBTTagCompound tag = new NBTTagCompound();
			insentient.c(tag);
			tag.setByte("NoAI", (byte) 1);
			tag.setByte("Silent", (byte) 1);
			tag.setByte("NoGravity", (byte) 1);
			insentient.f(tag);
		}
	}

	public static void setTag(Entity entity, String key, String value) {
		net.minecraft.server.v1_8_R3.Entity nms = ((CraftEntity) entity).getHandle();
		NBTTagCompound tag = new NBTTagCompound();
		nms.c(tag);
		tag.setString(key, value);
		nms.f(tag);
	}

	public static boolean hasTag(Entity entity, String key, String expectedValue) {
		net.minecraft.server.v1_8_R3.Entity nms = ((CraftEntity) entity).getHandle();
		NBTTagCompound tag = new NBTTagCompound();
		nms.c(tag);
		return tag.hasKey(key) && tag.getString(key).equals(expectedValue);
	}
}
