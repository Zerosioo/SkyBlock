package me.zerosio.utility;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MapView.Scale;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ItemUtil {
	static HashMap<String, UUID> skulls = new HashMap();
	public static void setName(ItemStack item, String name) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
	}

	public static String getId(ItemStack item) {
		String id = null;

		if (item != null && item.getType() != Material.AIR) {
			net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(item);
			if (stack.getTag() != null) {
				id = stack.getTag().getCompound("ExtraAttributes").getString("id");
			}
			if (id == null || id.isEmpty()) {
				if (item.getType() == Material.STAINED_GLASS_PANE && item.getDurability() == 15) {
					return null;
				}
				id = item.getType().toString();
				if (item.getDurability() != 0) {
					id += "-" + item.getDurability();
				}
			}
			return id;
		}
		return null;
	}

	public static NBTTagCompound getCompound(net.minecraft.server.v1_8_R3.ItemStack nmsStack) {
		if (nmsStack == null) return null;
		NBTTagCompound compound;
		if (nmsStack.getTag() == null || nmsStack.getTag().isEmpty()) {
			compound = new NBTTagCompound();
			nmsStack.setTag(compound);
		} else {
			compound = nmsStack.getTag();
		}
		NBTTagCompound attributes;
		if (compound.getCompound("ExtraAttributes").isEmpty()) {
			attributes = new NBTTagCompound();
			compound.set("ExtraAttributes", attributes);
		} else {
			attributes = compound.getCompound("ExtraAttributes");
		}
		return attributes;
	}

	public static ItemStack setId(ItemStack item, String id) {
		if (item == null || id == null || id.isEmpty()) return null;
		net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		if (nmsStack == null) return null;
		NBTTagCompound compound;
		if (nmsStack.getTag().isEmpty()) {
			compound = new NBTTagCompound();
			nmsStack.setTag(compound);
		}
		compound = nmsStack.getTag();
		NBTTagCompound stackCompound;
		if (compound.getCompound("ExtraAttributes").isEmpty()) {
			stackCompound = new NBTTagCompound();
			compound.set("ExtraAttributes", stackCompound);
		}
		stackCompound = compound.getCompound("ExtraAttributes");
		stackCompound.setString("id", id.replace("__", "-"));
		item = CraftItemStack.asBukkitCopy(nmsStack);
		return item;
	}

	public static boolean isBG(ItemStack item) {
		if (item != null && item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(" ")) {
			return true;
		}
		return false;
	}

	public static boolean isSimilar(ItemStack item1, ItemStack item2) {
		if (item1 == null || item2 == null) return false;
		if (item1.getType() != item2.getType()) return false;

		if (item1.hasItemMeta()) {
			if (!item2.hasItemMeta()) {
				return false;
			}
		} else {
			if (!item1.hasItemMeta()) {
				return false;
			}
		}
		ItemMeta meta1 = item1.getItemMeta();
		ItemMeta meta2 = item2.getItemMeta();
		if (meta1.hasDisplayName()) {
			if (!meta2.hasDisplayName()) {
				return false;
			}
		} else {
			if (!meta1.hasDisplayName()) {
				return false;
			}
		}
		if (meta1.getDisplayName().equals(meta2.getDisplayName())) return true;
		return false;
	}

	public static ItemStack addUUID(ItemStack item) {
		return addUUID(item, UUID.randomUUID().toString());
	}
	public static ItemStack addUUID(ItemStack item, String uuid) {
		if (uuid == null || uuid.isEmpty()) uuid = UUID.randomUUID().toString();
		net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		NBTTagCompound compound;
		if (nmsStack.getTag().isEmpty()) {
			compound = new NBTTagCompound();
			nmsStack.setTag(compound);
		}
		compound = nmsStack.getTag();
		NBTTagCompound stackCompound;
		if (compound.getCompound("ExtraAttributes").isEmpty()) {
			stackCompound = new NBTTagCompound();
			compound.set("ExtraAttributes", stackCompound);
		}
		stackCompound = compound.getCompound("ExtraAttributes");
		stackCompound.setString("uuid", uuid);
		item = CraftItemStack.asBukkitCopy(nmsStack);
		return item;
	}
	private static UUID getUUID(String skull) {
		if (skulls.containsKey(skull)) {
			return skulls.get(skull);
		}
		UUID id = UUID.randomUUID();
		skulls.put(skull, id);
		return id;
	}

	public static ItemStack getSkull(String url, String displayname, int amount, List<String> lore) {
		ItemStack item = new ItemStack(Material.SKULL_ITEM, amount, (short)3);
		if (url.isEmpty())
			return item;
		SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
		GameProfile profile = new GameProfile(getUUID(url), null);
		byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { url }).getBytes());
		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		Field profileField = null;
		try {
			profileField = itemMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(itemMeta, profile);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		itemMeta.setDisplayName(displayname);
		itemMeta.setLore(lore);
		item.setItemMeta((ItemMeta)itemMeta);
		return item;
	}
	public static ItemStack getSkullFromUrl(String url) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		if (url == null || url.isEmpty())
			return skull;
		ItemMeta skullMeta = skull.getItemMeta();
		GameProfile profile = new GameProfile(getUUID(url), null);
		byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		Field profileField = null;
		try {
			profileField = skullMeta.getClass().getDeclaredField("profile");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		profileField.setAccessible(true);
		try {
			profileField.set(skullMeta, profile);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		skull.setItemMeta(skullMeta);
		return skull;
	}
	public static ItemStack getMap(MapRenderer r, int id) {
		MapView mapView = Bukkit.getMap((short) id);

		for (MapRenderer renderer : mapView.getRenderers())
			mapView.removeRenderer(renderer);
		mapView.addRenderer(r);

		mapView.setScale(Scale.CLOSEST);
		ItemStack map = new ItemStack(Material.MAP);
		map.setDurability(mapView.getId());
		return map;
	}
	public static ItemStack getSkullFromTexture(String texture) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
		SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
		GameProfile profile = new GameProfile(getUUID(texture), null);
		profile.getProperties().put("textures", new Property("textures", texture));
		skull.setItemMeta((ItemMeta)skullMeta);
		try {
			Field profileField = skullMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(skullMeta, profile);
		} catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException error) {
			error.printStackTrace();
		}

		skull.setItemMeta((ItemMeta)skullMeta);
		return skull;
	}
	public static ItemStack getSkull(String texture, String displayname, List<String> lore) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
		SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
		GameProfile profile = new GameProfile(getUUID(texture), null);
		profile.getProperties().put("textures", new Property("textures", texture));
		skull.setItemMeta((ItemMeta)skullMeta);
		try {
			Field profileField = skullMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(skullMeta, profile);
		} catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException error) {
			error.printStackTrace();
		}

		skullMeta.setDisplayName(displayname);
		skullMeta.setLore(lore);
		skull.setItemMeta((ItemMeta)skullMeta);
		return skull;
	}

	public static String colorToName(int color) {
		switch (color) {
		case 0:
			return "White";
		case 1:
			return "Orange";
		case 2:
			return "Magenta";
		case 3:
			return "Light Blue";
		case 4:
			return "Yellow";
		case 5:
			return "Lime";
		case 6:
			return "Pink";
		case 7:
			return "Gray";
		case 8:
			return "Light Gray";
		case 9:
			return "Cyan";
		case 10:
			return "Purple";
		case 11:
			return "Blue";
		case 12:
			return "Brown";
		case 13:
			return "Green";
		case 14:
			return "Red";
		case 15:
			return "Black";
		}
		return "Wrong Color";
	}
	public static String itemColorToChatColor(int color) {
		switch (color) {
		case 0:
			return "§f";
		case 1:
			return "§6";
		case 2:
			return "§5";
		case 3:
			return "§b";
		case 4:
			return "§e";
		case 5:
			return "§a";
		case 6:
			return "§d";
		case 7:
			return "§8";
		case 8:
			return "§7";
		case 9:
			return "§3";
		case 10:
			return "§5";
		case 11:
			return "§9";
		case 12:
			return "§6";
		case 13:
			return "§2";
		case 14:
			return "§c";
		case 15:
			return "§0";
		}
		return "Wrong Color";
	}

	public static int chatColorToItemColor(String color) {
		switch (color) {
		case "§f":
			return 0;
		case "§6":
			return 1;
		case "§5":
			return 2;
		case "§b":
			return 3;
		case "§e":
			return 4;
		case "§a":
			return 5;
		case "§d":
			return 6;
		case "§8":
			return 7;
		case "§7":
			return 8;
		case "§3":
			return 9;
		case "§9":
			return 11;
		case "§2":
			return 13;
		case "§c":
			return 14;
		case "§0":
			return 15;
		}
		return 0;
	}

	public static String dyeColorToChatColor(int color) {
		switch (color) {
		case 0:
			return "§0";
		case 1:
			return "§c";
		case 2:
			return "§2";
		case 3:
			return "§6";
		case 4:
			return "§9";
		case 5:
			return "§5";
		case 6:
			return "§3";
		case 7:
			return "§7";
		case 8:
			return "§8";
		case 9:
			return "§d";
		case 10:
			return "§a";
		case 11:
			return "§e";
		case 12:
			return "§b";
		case 13:
			return "§5";
		case 14:
			return "§6";
		case 15:
			return "§f";
		}
		return "Wrong Color";
	}
	public static int dyeColorToItemColor(int color) {
		switch (color) {
		case 0:
			return 15;
		case 1:
			return 14;
		case 2:
			return 13;
		case 3:
			return 12;
		case 4:
			return 11;
		case 5:
			return 10;
		case 6:
			return 9;
		case 7:
			return 8;
		case 8:
			return 7;
		case 9:
			return 6;
		case 10:
			return 5;
		case 11:
			return 4;
		case 12:
			return 3;
		case 13:
			return 2;
		case 14:
			return 1;
		case 15:
			return 0;
		}
		return 0;
	}

}
