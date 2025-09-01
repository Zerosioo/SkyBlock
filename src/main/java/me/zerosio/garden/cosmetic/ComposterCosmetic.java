package me.zerosio.garden.cosmetic;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import me.zerosio.inventory.build.ItemBuilder;
import me.zerosio.utility.ArmourStandBuilder;

public class ComposterCosmetic {

	public static void spawn(Location loc) {
		World world = loc.getWorld();
		double originX = loc.getBlockX();
		double originY = loc.getBlockY();
		double originZ = loc.getBlockZ();

		Location loc0 = new Location(world, originX + 0.281, originY + -1.219, originZ + -0.344, 0.0f, 0.0f);
		ArmourStandBuilder builder0 = new ArmourStandBuilder(loc0, Material.AIR, (byte)0);
		builder0.setHelmet(new ItemStack(Material.FURNACE, 1, (short)0));;
		builder0.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder0.spawn();

		Location loc1 = new Location(world, originX + 0.656, originY + -1.125, originZ + -0.344, -90.0f, 0.0f);
		ArmourStandBuilder builder1 = new ArmourStandBuilder(loc1, Material.AIR, (byte)0);
		builder1.setHelmet(new ItemStack(Material.STEP, 1, (short)3));;
		builder1.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 0.000));;
		builder1.spawn();

		Location loc2 = new Location(world, originX + 0.250, originY + -0.531, originZ + -0.094, 0.0f, 0.0f);
		ArmourStandBuilder builder2 = new ArmourStandBuilder(loc2, Material.AIR, (byte)0);
		builder2.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));;
		builder2.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder2.spawn();

		Location loc3 = new Location(world, originX + 0.656, originY + -0.500, originZ + -0.344, -90.0f, 0.0f);
		ArmourStandBuilder builder3 = new ArmourStandBuilder(loc3, Material.AIR, (byte)0);
		builder3.setHelmet(new ItemStack(Material.STEP, 1, (short)5));;
		builder3.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 0.000));;
		builder3.spawn();

		Location loc4 = new Location(world, originX + 0.781, originY + -0.375, originZ + 0.188, 0.0f, 0.0f);
		ArmourStandBuilder builder4 = new ArmourStandBuilder(loc4, Material.AIR, (byte)0);
		builder4.setHelmet(new ItemBuilder(Material.SKULL_ITEM).setDurability((short) 3).setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGVkNjc5OTE0OTc4OGI5ZTkwMTY4MTFkM2EzZDBlZDFmNTUyNTMwZDY3Zjk4Njk0NTAzMmQ2ZTQzOWZhODk5ZCJ9fX0=").build());;
		builder4.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder4.spawn();

		Location loc5 = new Location(world, originX + 0.750, originY + -0.563, originZ + 0.219, 90.0f, 0.0f);
		ArmourStandBuilder builder5 = new ArmourStandBuilder(loc5, Material.AIR, (byte)0);
		builder5.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));;
		builder5.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 1.047));;
		builder5.spawn();

		Location loc6 = new Location(world, originX + -0.281, originY + -0.563, originZ + -0.281, -90.0f, 0.0f);
		ArmourStandBuilder builder6 = new ArmourStandBuilder(loc6, Material.AIR, (byte)0);
		builder6.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));;
		builder6.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 1.047));;
		builder6.spawn();

		Location loc7 = new Location(world, originX + 0.781, originY + -0.375, originZ + 0.188, -90.0f, 0.0f);
		ArmourStandBuilder builder7 = new ArmourStandBuilder(loc7, Material.AIR, (byte)0);
		builder7.setHelmet(new ItemBuilder(Material.SKULL_ITEM).setDurability((short)
						   3).setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGVkNjc5OTE0OTc4OGI5ZTkwMTY4MTFkM2EzZDBlZDFmNTUyNTMwZDY3Zjk4Njk0NTAzMmQ2ZTQzOWZhODk5ZCJ9fX0=").build());;
		builder7.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(3.142, 0.000, 0.000));;
		builder7.spawn();

		Location loc8 = new Location(world, originX + -0.313, originY + -0.563, originZ + 0.219, 90.0f, 0.0f);
		ArmourStandBuilder builder8 = new ArmourStandBuilder(loc8, Material.AIR, (byte)0);
		builder8.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));;
		builder8.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 1.047));;
		builder8.spawn();

		Location loc9 = new Location(world, originX + -0.813, originY + -0.469, originZ + -0.125, -90.0f, 0.0f);
		ArmourStandBuilder builder9 = new ArmourStandBuilder(loc9, Material.AIR, (byte)0);
		builder9.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));;
		builder9.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 0.000));;
		builder9.spawn();

		Location loc10 = new Location(world, originX + 0.219, originY + -0.281, originZ + 0.219, 0.0f, 0.0f);
		ArmourStandBuilder builder10 = new ArmourStandBuilder(loc10, Material.AIR, (byte)0);
		builder10.setHelmet(new ItemStack(Material.DIRT, 1, (short)1));;
		builder10.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder10.spawn();

		Location loc11 = new Location(world, originX + 0.188, originY + -1.313, originZ + -0.531, 0.0f, 0.0f);
		ArmourStandBuilder builder11 = new ArmourStandBuilder(loc11, Material.AIR, (byte)0);
		builder11.setHelmet(new ItemStack(Material.WOOD_BUTTON, 1, (short)0));;
		builder11.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(0.000, 0.000, 1.571));;
		builder11.spawn();

		Location loc12 = new Location(world, originX + 0.250, originY + -0.594, originZ + -0.219, -180.0f, 0.0f);
		ArmourStandBuilder builder12 = new ArmourStandBuilder(loc12, Material.AIR, (byte)0);
		builder12.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));;
		builder12.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.047, 0.000, 0.000));;
		builder12.spawn();

		Location loc13 = new Location(world, originX + -0.813, originY + -0.469, originZ + 0.063, -90.0f, 0.0f);
		ArmourStandBuilder builder13 = new ArmourStandBuilder(loc13, Material.AIR, (byte)0);
		builder13.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));;
		builder13.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 0.000));;
		builder13.spawn();

		Location loc14 = new Location(world, originX + -0.813, originY + -0.563, originZ + -0.281, -90.0f, 0.0f);
		ArmourStandBuilder builder14 = new ArmourStandBuilder(loc14, Material.AIR, (byte)0);
		builder14.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));;
		builder14.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 1.047));;
		builder14.spawn();

		Location loc15 = new Location(world, originX + -0.344, originY + -1.219, originZ + -0.344, 0.0f, 0.0f);
		ArmourStandBuilder builder15 = new ArmourStandBuilder(loc15, Material.AIR, (byte)0);
		builder15.setHelmet(new ItemStack(Material.FURNACE, 1, (short)0));;
		builder15.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder15.spawn();

		Location loc16 = new Location(world, originX + 0.250, originY + -0.375, originZ + -0.031, 0.0f, 0.0f);
		ArmourStandBuilder builder16 = new ArmourStandBuilder(loc16, Material.AIR, (byte)0);
		builder16.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));;
		builder16.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(0.524, 0.000, 0.000));;
		builder16.spawn();

		Location loc17 = new Location(world, originX + -0.750, originY + -1.313, originZ + 0.469, 0.0f, 0.0f);
		ArmourStandBuilder builder17 = new ArmourStandBuilder(loc17, Material.AIR, (byte)0);
		builder17.setHelmet(new ItemStack(Material.WOOD_BUTTON, 1, (short)0));;
		builder17.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(0.000, 0.000, 1.571));;
		builder17.spawn();

		Location loc18 = new Location(world, originX + -0.375, originY + 0.438, originZ + -0.094, 45.0f, 0.0f);
		ArmourStandBuilder builder18 = new ArmourStandBuilder(loc18, Material.SKULL, (byte)3);
		builder18.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setLeftArmPose(new EulerAngle(-0.332, 0.000, -0.105));;
		builder18.spawn();

		Location loc19 = new Location(world, originX + -0.625, originY + 0.594, originZ + -1.063, 0.0f, 0.0f);
		ArmourStandBuilder builder19 = new ArmourStandBuilder(loc19, Material.COAL, (byte)1);
		builder19.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setRightArmPose(new EulerAngle(-1.588, 0.000, 2.897));;
		builder19.spawn();

		Location loc20 = new Location(world, originX + -0.375, originY + 0.563, originZ + -0.406, 45.0f, 0.0f);
		ArmourStandBuilder builder20 = new ArmourStandBuilder(loc20, Material.SKULL, (byte)3);
		builder20.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setLeftArmPose(new EulerAngle(-0.332, 0.000, -0.105));;
		builder20.spawn();

		Location loc21 = new Location(world, originX + -0.344, originY + -0.500, originZ + 0.406, 0.0f, 0.0f);
		ArmourStandBuilder builder21 = new ArmourStandBuilder(loc21, Material.AIR, (byte)0);
		builder21.setHelmet(new ItemBuilder(Material.SKULL_ITEM).setDurability((short)
							3).setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I5MjVkM2E1Mjc1OWZkZThjMDI1ODg3MWZlZmQ5MTQxZTVjOTdmZGY0NTNhZjNkZjIxMTA0Y2M4YzQ4OCJ9fX0=").build());;
		builder21.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder21.spawn();

		Location loc22 = new Location(world, originX + -0.281, originY + -0.313, originZ + 0.219, 0.0f, 0.0f);
		ArmourStandBuilder builder22 = new ArmourStandBuilder(loc22, Material.AIR, (byte)0);
		builder22.setHelmet(new ItemStack(Material.DIRT, 1, (short)1));
		builder22.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder22.spawn();

		Location loc23 = new Location(world, originX + -0.281, originY + -0.469, originZ + 0.063, -90.0f, 0.0f);
		ArmourStandBuilder builder23 = new ArmourStandBuilder(loc23, Material.AIR, (byte)0);
		builder23.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));
		builder23.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 0.000));;
		builder23.spawn();

		Location loc24 = new Location(world, originX + -0.313, originY + -0.281, originZ + 0.531, 0.0f, 0.0f);
		ArmourStandBuilder builder24 = new ArmourStandBuilder(loc24, Material.AIR, (byte)0);
		builder24.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));
		builder24.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(-0.873, 0.000, 0.000));;
		builder24.spawn();

		Location loc25 = new Location(world, originX + 0.188, originY + -1.313, originZ + 0.469, 0.0f, 0.0f);
		ArmourStandBuilder builder25 = new ArmourStandBuilder(loc25, Material.AIR, (byte)0);
		builder25.setHelmet(new ItemStack(Material.WOOD_BUTTON, 1, (short)0));
		builder25.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(0.000, 0.000, 1.571));;
		builder25.spawn();

		Location loc26 = new Location(world, originX + -0.344, originY + -1.219, originZ + 0.281, -180.0f, 0.0f);
		ArmourStandBuilder builder26 = new ArmourStandBuilder(loc26, Material.AIR, (byte)0);
		builder26.setHelmet(new ItemStack(Material.FURNACE, 1, (short)0));
		builder26.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder26.spawn();

		Location loc27 = new Location(world, originX + -0.719, originY + -0.500, originZ + -0.344, 90.0f, 0.0f);
		ArmourStandBuilder builder27 = new ArmourStandBuilder(loc27, Material.AIR, (byte)0);
		builder27.setHelmet(new ItemStack(Material.STEP, 1, (short)5));
		builder27.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 0.000));;
		builder27.spawn();

		Location loc28 = new Location(world, originX + 0.281, originY + -1.219, originZ + 0.281, -180.0f, 0.0f);
		ArmourStandBuilder builder28 = new ArmourStandBuilder(loc28, Material.AIR, (byte)0);
		builder28.setHelmet(new ItemStack(Material.FURNACE, 1, (short)0));
		builder28.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder28.spawn();

		Location loc29 = new Location(world, originX + 0.281, originY + -0.500, originZ + 0.406, 0.0f, 0.0f);
		ArmourStandBuilder builder29 = new ArmourStandBuilder(loc29, Material.AIR, (byte)0);
		builder29.setHelmet(new ItemBuilder(Material.SKULL_ITEM).setDurability((short)
							3).setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I5MjVkM2E1Mjc1OWZkZThjMDI1ODg3MWZlZmQ5MTQxZTVjOTdmZGY0NTNhZjNkZjIxMTA0Y2M4YzQ4OCJ9fX0=").build());
		builder29.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder29.spawn();

		Location loc30 = new Location(world, originX + -0.281, originY + -0.531, originZ + -0.094, 0.0f, 0.0f);
		ArmourStandBuilder builder30 = new ArmourStandBuilder(loc30, Material.AIR, (byte)0);
		builder30.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));
		builder30.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder30.spawn();

		Location loc31 = new Location(world, originX + 0.250, originY + -0.188, originZ + -0.250, 0.0f, 0.0f);
		ArmourStandBuilder builder31 = new ArmourStandBuilder(loc31, Material.AIR, (byte)0);
		builder31.setHelmet(new ItemStack(Material.DIRT, 1, (short)1));
		builder31.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder31.spawn();

		Location loc32 = new Location(world, originX + 0.250, originY + -0.469, originZ + 0.063, -90.0f, 0.0f);
		ArmourStandBuilder builder32 = new ArmourStandBuilder(loc32, Material.AIR, (byte)0);
		builder32.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));
		builder32.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 0.000));;
		builder32.spawn();

		Location loc33 = new Location(world, originX + 0.219, originY + -0.563, originZ + 0.219, 90.0f, 0.0f);
		ArmourStandBuilder builder33 = new ArmourStandBuilder(loc33, Material.AIR, (byte)0);
		builder33.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));
		builder33.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 1.047));;
		builder33.spawn();

		Location loc34 = new Location(world, originX + -0.281, originY + -0.469, originZ + -0.125, -90.0f, 0.0f);
		ArmourStandBuilder builder34 = new ArmourStandBuilder(loc34, Material.AIR, (byte)0);
		builder34.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));
		builder34.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 0.000));;
		builder34.spawn();

		Location loc35 = new Location(world, originX + -0.719, originY + -1.313, originZ + -0.531, 0.0f, 0.0f);
		ArmourStandBuilder builder35 = new ArmourStandBuilder(loc35, Material.AIR, (byte)0);
		builder35.setHelmet(new ItemStack(Material.WOOD_BUTTON, 1, (short)0));
		builder35.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(0.000, 0.000, 1.571));;
		builder35.spawn();

		Location loc36 = new Location(world, originX + -0.313, originY + -0.250, originZ + -0.219, 0.0f, 0.0f);
		ArmourStandBuilder builder36 = new ArmourStandBuilder(loc36, Material.AIR, (byte)0);
		builder36.setHelmet(new ItemStack(Material.DIRT, 1, (short)1));
		builder36.setVisible(false)
		.setSmall(true)
		.setMarker(false)
		.setArms(true)
		.setGravity(false);
		builder36.spawn();

		Location loc37 = new Location(world, originX + -0.313, originY + -0.594, originZ + -0.219, -180.0f, 0.0f);
		ArmourStandBuilder builder37 = new ArmourStandBuilder(loc37, Material.AIR, (byte)0);
		builder37.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));
		builder37.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.047, 0.000, 0.000));;
		builder37.spawn();

		Location loc38 = new Location(world, originX + 0.250, originY + -0.469, originZ + -0.125, -90.0f, 0.0f);
		ArmourStandBuilder builder38 = new ArmourStandBuilder(loc38, Material.AIR, (byte)0);
		builder38.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));
		builder38.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 0.000));;
		builder38.spawn();

		Location loc39 = new Location(world, originX + 0.250, originY + -0.563, originZ + -0.281, -90.0f, 0.0f);
		ArmourStandBuilder builder39 = new ArmourStandBuilder(loc39, Material.AIR, (byte)0);
		builder39.setHelmet(new ItemStack(Material.STONE_PLATE, 1, (short)0));
		builder39.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 1.047));;
		builder39.spawn();

		Location loc40 = new Location(world, originX + -0.719, originY + -1.125, originZ + -0.344, 90.0f, 0.0f);
		ArmourStandBuilder builder40 = new ArmourStandBuilder(loc40, Material.AIR, (byte)0);
		builder40.setHelmet(new ItemStack(Material.STEP, 1, (short)3));
		builder40.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(true)
		.setGravity(false)
		.setHeadPose(new EulerAngle(1.571, 0.000, 0.000));;
		builder40.spawn();

		Location loc41 = new Location(world, originX + -3.500, originY + 0.000, originZ + -0.500, 0.0f, 0.0f);
		ArmourStandBuilder builder41 = new ArmourStandBuilder(loc41, Material.AIR, (byte)0);
		builder41.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(false)
		.setGravity(true);
		builder41.spawn();

		Location loc42 = new Location(world, originX + -3.500, originY + -0.250, originZ + -0.500, 0.0f, 0.0f);
		ArmourStandBuilder builder42 = new ArmourStandBuilder(loc42, Material.AIR, (byte)0);
		builder42.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(false)
		.setGravity(true);
		builder42.spawn();

		Location loc43 = new Location(world, originX + 0.000, originY + 0.000, originZ + -0.281, 0.0f, 0.0f);
		ArmourStandBuilder builder43 = new ArmourStandBuilder(loc43, Material.AIR, (byte)0);
		builder43.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(false)
		.setGravity(true);
		builder43.spawn();

		Location loc44 = new Location(world, originX + 0.000, originY + -0.375, originZ + -0.281, 0.0f, 0.0f);
		ArmourStandBuilder builder44 = new ArmourStandBuilder(loc44, Material.AIR, (byte)0);
		builder44.setVisible(false)
		.setSmall(false)
		.setMarker(false)
		.setArms(false)
		.setGravity(true);
		builder44.spawn();
	}

}