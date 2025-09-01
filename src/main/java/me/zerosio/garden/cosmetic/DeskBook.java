package me.zerosio.garden.cosmetic;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import me.zerosio.utility.ArmourStandBuilder;

public class DeskBook {
	public static void spawn(Location loc) {
		World world = loc.getWorld();
		
		double originX = loc.getBlockX();
		double originY = loc.getBlockY();
		double originZ = loc.getBlockY();

		Location loc0 = new Location(world, originX + 0.219, originY + -0.844, originZ + 0.406, 90.0f, 0.0f);
		ArmourStandBuilder builder0 = new ArmourStandBuilder(loc0, Material.AIR, (byte)0);
		builder0.setHelmet(new ItemStack(Material.WOOD_PLATE, 1, (short)0));
		builder0.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(-0.227, 0.000, 0.000));
		builder0.spawn();

		Location loc1 = new Location(world, originX + 0.656, originY + -0.438, originZ + 0.188, 0.0f, 0.0f);
		ArmourStandBuilder builder1 = new ArmourStandBuilder(loc1, Material.STICK, (byte)0);
		builder1.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setRightArmPose(new EulerAngle(-0.524, 0.000, 0.035));
		builder1.spawn();

		Location loc2 = new Location(world, originX + 0.281, originY + -0.563, originZ + 0.406, 90.0f, 0.0f);
		ArmourStandBuilder builder2 = new ArmourStandBuilder(loc2, Material.AIR, (byte)0);
		builder2.setHelmet(new ItemStack(Material.CARPET, 1, (short)0));
		builder2.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(-0.227, 0.000, 0.000));
		builder2.spawn();

		Location loc3 = new Location(world, originX + 0.719, originY + -0.844, originZ + 0.375, -90.0f, 0.0f);
		ArmourStandBuilder builder3 = new ArmourStandBuilder(loc3, Material.AIR, (byte)0);
		builder3.setHelmet(new ItemStack(Material.WOOD_PLATE, 1, (short)0));
		builder3.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(-0.227, 0.000, 0.000));
		builder3.spawn();

		Location loc4 = new Location(world, originX + 0.688, originY + -0.563, originZ + 0.406, -90.0f, 0.0f);
		ArmourStandBuilder builder4 = new ArmourStandBuilder(loc4, Material.AIR, (byte)0);
		builder4.setHelmet(new ItemStack(Material.CARPET, 1, (short)0));
		builder4.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(-0.227, 0.000, 0.000));
		builder4.spawn();

		Location loc5 = new Location(world, originX + 0.500, originY + -1.281, originZ + 0.500, 0.0f, 0.0f);
		ArmourStandBuilder builder5 = new ArmourStandBuilder(loc5, Material.AIR, (byte)0);
		builder5.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(false)
		.setGravity(true);
		builder5.spawn();

		Location loc6 = new Location(world, originX + 0.500, originY + -1.656, originZ + 0.500, 0.0f, 0.0f);
		ArmourStandBuilder builder6 = new ArmourStandBuilder(loc6, Material.AIR, (byte)0);
		builder6.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(false)
		.setGravity(true);
		builder6.spawn();


	}
}