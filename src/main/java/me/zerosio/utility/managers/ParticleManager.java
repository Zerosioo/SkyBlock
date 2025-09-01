package me.zerosio.utility.managers;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.zerosio.utility.XYZ;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class ParticleManager {
	public static void spawnParticle(EnumParticle effect, Location location) {
		PacketManager.send(location, new PacketPlayOutWorldParticles(effect, true, (float)location.getX(), (float)location.getY(), (float)location.getZ(), 0, 0, 0, 0, 1));
	}
	//public static void spawnParticle(EnumParticle effect, Location location, String permission) {
//		PacketManager.send(location, permission, new PacketPlayOutWorldParticles(effect, false, (float)location.getX(), (float)location.getY(), (float)location.getZ(), 0, 0, 0, 0, 1));
//	}
	public static void spawnParticle(EnumParticle effect, Location location, boolean color, float offsetX, float offsetY, float offsetZ, int amount) {
		PacketManager.send(location, new PacketPlayOutWorldParticles(effect, color, (float)location.getX(), (float)location.getY(), (float)location.getZ(), offsetX, offsetY, offsetZ, amount, 0));
	}

	//public static void spawnColor(Location location, Color color) {
//		PacketManager.send(location, new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float)location.getX(), (float)location.getY(), (float)location.getZ(),
//						   ((float) color.getRed()) / 255 + 0.01f, ((float) color.getGreen()) / 255 + 0.01f, ((float) color.getBlue()) / 255 + 0.01f, (float) 1, 0));
//	}
	//public static void spawnColor(Location location, String permission, boolean permissionValue, Color color) {
//		PacketManager.send(location, permission, permissionValue, new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float)location.getX(), (float)location.getY(), (float)location.getZ(),
//						   ((float) color.getRed()) / 255 + 0.01f, ((float) color.getGreen()) / 255 + 0.01f, ((float) color.getBlue()) / 255 + 0.01f, (float) 1, 0));
//	}
	public static void spawnColor(Location location, Color color) {
		PacketManager.send(location, new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float) location.getX(), (float) location.getY(), (float) location.getZ(),
						   ((float) color.getRed()) / 255 + 0.01f, ((float) color.getGreen()) / 255 + 0.01f, ((float) color.getBlue()) / 255 + 0.01f, (float) 1, 0));
	}


	public static void spawnParticle(Player player, EnumParticle effect, XYZ location) {
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutWorldParticles(effect, false, (float) location.getX(), (float) location.getY(), (float) location.getZ(), 0, 0, 0, 0, 1));
	}
}
