package me.zerosio.garden.cosmetic;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import me.zerosio.utility.ArmourStandBuilder;

public class VisitorLogbook {
	public static void spawn(Location loc) {
		Location playerLocation = loc;
		World world = playerLocation.getWorld();

		double originX = playerLocation.getBlockX();
		double originY = playerLocation.getBlockY();
		double originZ = playerLocation.getBlockZ();

		Location loc0 = new Location(world, originX + -0.313, originY + 0.615, originZ + 0.281, 57.7f, 0.0f);
		ArmourStandBuilder builder0 = new ArmourStandBuilder(loc0, Material.SKULL, (byte)3);
		builder0.setVisible(false)
		.setSmall(false)
		.setMarker(true)
		.setArms(true)
		.setGravity(false);
		builder0.spawn();

		Location loc1 = new Location(world, originX + -1.250, originY + 0.250, originZ + -0.031, -180.0f, 0.0f);
		ArmourStandBuilder builder1 = new ArmourStandBuilder(loc1, Material.RECORD_8, (byte)0);
		builder1.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setRightArmPose(new EulerAngle(-1.518, 0.349, 1.588));
		builder1.spawn();

		Location loc2 = new Location(world, originX + -1.531, originY + -0.375, originZ + -0.500, -180.0f, 0.0f);
		ArmourStandBuilder builder2 = new ArmourStandBuilder(loc2, Material.AIR, (byte)0);
		builder2.setHelmet(new ItemStack(Material.CARPET, 1, (short)14));
		builder2.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder2.spawn();

		Location loc3 = new Location(world, originX + -1.469, originY + 0.563, originZ + -0.219, -180.0f, 0.0f);
		ArmourStandBuilder builder3 = new ArmourStandBuilder(loc3, Material.SNOW, (byte)0);
		builder3.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setRightArmPose(new EulerAngle(-0.698, -0.733, 0.332));
		builder3.spawn();

		Location loc4 = new Location(world, originX + -1.500, originY + -0.594, originZ + -0.500, -180.0f, 0.0f);
		ArmourStandBuilder builder4 = new ArmourStandBuilder(loc4, Material.AIR, (byte)0);
		builder4.setHelmet(new ItemStack(Material.IRON_PLATE, 1, (short)0));
		builder4.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder4.spawn();

		Location loc5 = new Location(world, originX + -1.531, originY + -0.219, originZ + -0.531, -180.0f, 0.0f);
		ArmourStandBuilder builder5 = new ArmourStandBuilder(loc5, Material.AIR, (byte)0);
		builder5.setHelmet(new ItemStack(Material.CARPET, 1, (short)14));
		builder5.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder5.spawn();

		Location loc6 = new Location(world, originX + -1.250, originY + 0.250, originZ + -0.500, -180.0f, 0.0f);
		ArmourStandBuilder builder6 = new ArmourStandBuilder(loc6, Material.RECORD_8, (byte)0);
		builder6.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setRightArmPose(new EulerAngle(-1.518, 0.349, 1.588));
		builder6.spawn();

		Location loc7 = new Location(world, originX + -1.250, originY + 0.250, originZ + -0.250, -180.0f, 0.0f);
		ArmourStandBuilder builder7 = new ArmourStandBuilder(loc7, Material.RECORD_8, (byte)0);
		builder7.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setRightArmPose(new EulerAngle(-1.518, 0.349, 1.588));
		builder7.spawn();

		Location loc8 = new Location(world, originX + -1.656, originY + 0.563, originZ + -0.219, -180.0f, 0.0f);
		ArmourStandBuilder builder8 = new ArmourStandBuilder(loc8, Material.SNOW, (byte)0);
		builder8.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setRightArmPose(new EulerAngle(-0.698, -0.733, 0.332));
		builder8.spawn();

		Location loc9 = new Location(world, originX + -1.500, originY + -0.281, originZ + -0.500, 0.0f, 0.0f);
		ArmourStandBuilder builder9 = new ArmourStandBuilder(loc9, Material.AIR, (byte)0);
		builder9.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(false)
		.setGravity(true);
		builder9.spawn();

		Location loc10 = new Location(world, originX + -1.500, originY + -0.656, originZ + -0.500, 0.0f, 0.0f);
		ArmourStandBuilder builder10 = new ArmourStandBuilder(loc10, Material.AIR, (byte)0);
		builder10.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(false)
		.setGravity(true);
		builder10.spawn();

		Location loc11 = new Location(world, originX + -0.844, originY + 1.427, originZ + 0.031, 57.7f, 0.0f);
		ArmourStandBuilder builder11 = new ArmourStandBuilder(loc11, Material.AIR, (byte)0);
		builder11.setVisible(false)
		.setSmall(false)
		.setMarker(true)
		.setArms(false)
		.setGravity(true);
		builder11.spawn();



	}
}