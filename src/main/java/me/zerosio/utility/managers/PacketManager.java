package me.zerosio.utility.managers;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockChange;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftMagicNumbers;
import org.bukkit.entity.Player;

public class PacketManager {
	public static void send(Location location, Packet packet) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.getWorld().equals(location.getWorld()) && p.getLocation().distance(location) < 50) {
				((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
			}
		}
	}

	//public static void send(Location location, String permission,Packet packet) {
//		for(Player p : Bukkit.getOnlinePlayers()) {
//			if(p.getWorld().equals(location.getWorld()) && p.getLocation().distance(location) < 50 && User.getUser(p).isLogged() && User.getUser(p).getActiveProfile().hasPermission(permission)) {
//				((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
//			}
//		}
//	}

	//public static void send(Location location, String permission, boolean permissionValue,Packet packet) {
//		for(Player p : Bukkit.getOnlinePlayers()) {
//			if(p.getWorld().equals(location.getWorld()) && p.getLocation().distance(location) < 50 && User.getUser(p).isLogged() && ((User.getUser(p).getActiveProfile().hasPermission(permission) && permissionValue) || (!User.getUser(p).getActiveProfile().hasPermission(permission) && !permissionValue))) {
//				((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
//			}
//		}
//	}

	public static void sendBlockChange(Location location){
		PacketPlayOutBlockChange packet = new PacketPlayOutBlockChange(((CraftWorld)location.getWorld()).getHandle(), new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
		send(location,packet);
	}


	public static void sendBlockChange(Location location, Material material){
		sendBlockChange(location, material, (byte) 0);
	}

	public static void sendBlockChange(Location location, Material material, byte data){
		sendBlockChange(location, material.getId(), data);
	}

	public static void sendBlockChange(Location location, int material, byte data){
		PacketPlayOutBlockChange packet = new PacketPlayOutBlockChange(((CraftWorld)location.getWorld()).getHandle(), new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
		packet.block = CraftMagicNumbers.getBlock(material).fromLegacyData(data);
		send(location,packet);
	}
}
