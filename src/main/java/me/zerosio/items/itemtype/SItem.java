package me.zerosio.items.itemtype;

import me.zerosio.user.statistics.StatType;
import me.zerosio.utility.ItemUtil;
import me.zerosio.utility.ReflectionsUtil;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.ArrayList;
import java.util.List;

public abstract class SItem {

	public String origin;
	public boolean recomb;
	public int hpb;
	public String uuid;
	public List<SItemFlag> flags = getFlags();
	public boolean blacklisted;
	public String skin;
	public String dye;
	public boolean fpb;
	public int kills;
	public boolean trackKills;

	public static SItem getItemClass(ItemStack item) {
		if (item == null || item.getType() == Material.AIR) return null;
		String id = ItemUtil.getId(item);
		if (id == null) return null;
		return getItemClass(id);
	}

	public static SItem getItemClass(String id) {
		if (id == null || id.equals("null")) return null;
		id = id.replace("-", "__").toUpperCase();
		Class<? extends SItem> clazz = Lists.items.get(id);
		return clazz != null ? ReflectionsUtil.getInstance(clazz) : null;
	}

	public static SItem getItem(ItemStack item) {
		SItem i = getItemClass(item);
		if (i == null) return null;
		net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(item);
		if (!nms.hasTag()) return i;
		i.load(nms.getTag());
		return i;
	}

	public ItemStack buildItemStack(Player player) {
		Rarity rarity = getRarity();
		if (recomb) rarity = rarity.next();

		String name = getName();
		List<String> lore = new ArrayList<>();

		ItemStatistics stats = this instanceof ItemStatistics ? (ItemStatistics) this : null;
		
		if (stats != null && stats.getBreakingPower() != 0) {
			lore.add("§8Breaking Power " + stats.getBreakingPower());
			lore.add("");
		}

		if (stats != null && stats.getGearScore() != 0) {
			lore.add("§7Gear Score: §d" + stats.getGearScore());
		}

		if (stats != null) {
			boolean split = false;
			for (StatType type : StatType.values()) {
				int base = stats.get(type);
				String s = editStat(type, base, hpb, type.isPassive());
				if (s != null) {
					if (!type.isPassive()) {
						split = true;
					} else if (split) {
						split = false;
					}
					lore.add(s);
				}
			}
		}


		List<String> desc = getLore();

		if (this instanceof me.zerosio.items.ability.Ability.Holder) {
			List<me.zerosio.items.ability.Ability> abilities = ((me.zerosio.items.ability.Ability.Holder) this).getAbilities();
			if (!abilities.isEmpty()) {
				if (!lore.isEmpty() && !lore.get(lore.size() - 1).isEmpty()) {
					lore.add("");
				}
				for (me.zerosio.items.ability.Ability ability : abilities) {
					lore.add("§6Ability: " + ability.getAbilityName() + " §e§l" + ability.getType().name().replace('_', ' '));

					lore.addAll(ability.getAbilityDescription());

					if (ability.getManaCost() > 0) {
						lore.add("§8Mana Cost: §3" + ability.getManaCost());
					}
					if (ability.getCooldownSeconds() > 0) {
						lore.add("§8Cooldown: §a" + ability.getCooldownSeconds() + "s");
					}
				}
			}
		}
		
		if (desc != null) {
			lore.add("");
			lore.addAll(desc);
			lore.add("");
		}

		if (!lore.isEmpty() && !lore.get(lore.size() - 1).isEmpty()) lore.add("");

		String suffix = "";

		if (isDungeonItem()) {
			suffix = " DUNGEON";
		}

		String rarityLine = (rarity.getColor() + (recomb ? "§l§kk§r " + rarity.getColor() : "") + "§l"
							 + rarity.toString().replace("_", " ") + suffix)
							+ (getItemType() != null && getItemType() != ItemType.NULL
							   ? " " + getItemType().toString().replace("_", " ")
							   : "") + (recomb ? " §kk" : "");

		if (reforgeable()) {
			lore.add("§8This item can be reforged!");
		}

		if (getDungeonType() == DungeonType.MASTER_CATACOMBS) {
			lore.add("§4❣ §cRequires §aMaster Mode");
		}

		lore.add(rarityLine);
		name = rarity.getColor() + name;

		ItemStack item;
		if (this instanceof SkullItem && ((SkullItem) this).getTexture() != null) {
			SkullItem skull = (SkullItem) this;
			item = skull.isURL()
				   ? ItemUtil.getSkullFromUrl(skull.getTexture())
				   : ItemUtil.getSkull(skull.getTexture(), name, lore);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(name);
			meta.setLore(lore);
			item.setItemMeta(meta);
		} else {
			item = ItemBuilder.buildItemStack(getMaterial(), getDurability(), name, lore);
		}

		ItemMeta meta = item.getItemMeta();
		meta.spigot().setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);

		if (this instanceof ColoredArmor && isLeatherArmor(getMaterial())) {
			Color color = ((LeatherArmorMeta) this).getColor();
			if (color != null) {
				LeatherArmorMeta armorMeta = (LeatherArmorMeta) item.getItemMeta();
				armorMeta.setColor(color);
				item.setItemMeta(armorMeta);
			}
		}

		return addAttributes(item);
	}

	public boolean isLeatherArmor(Material m) {
		return m == Material.LEATHER_BOOTS || m == Material.LEATHER_LEGGINGS
			   || m == Material.LEATHER_CHESTPLATE || m == Material.LEATHER_HELMET;
	}

	@OverridingMethodsMustInvokeSuper
	public ItemStack addAttributes(ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(item);
		NBTTagCompound attributes = ItemUtil.getCompound(nms);
		addAttributes(attributes);
		return CraftItemStack.asBukkitCopy(nms);
	}

	@OverridingMethodsMustInvokeSuper
	public void addAttributes(NBTTagCompound attributes) {
		if (hpb != 0) attributes.setInt("hpb", hpb);
		if (recomb) attributes.setInt("rarity_upgrades", 1);
		if (uuid != null) attributes.setString("uuid", uuid);
		if (skin != null) attributes.setString("active_skin", skin);
		if (dye != null) attributes.setString("armor_dye", dye);
		if (fpb) attributes.setInt("fpb", 1);
		if ((getItemType() == ItemType.SWORD || getItemType() == ItemType.BOW) && trackKills) {
			attributes.setInt("trackKills", 1);
			attributes.setInt("kills", kills);
		}
		attributes.setString("id", getId());
	}

	public void load(NBTTagCompound tag) {
		NBTTagCompound ea = tag.getCompound("ExtraAttributes");
		loadAttributes(ea);
	}

	@OverridingMethodsMustInvokeSuper
	public void loadAttributes(NBTTagCompound ea) {
		if (ea.hasKey("origin")) origin = ea.getString("origin");
	}

	public String editStat(StatType type, int base, int hpb, boolean passive) {
		int bonus = getHpbBonus(type, hpb);
		base += bonus;
		if (base == 0) return null;

		String statName = capitalize(type.name().replace("_", " "));
		String stat = "§7" + statName + ": " + (passive ? "§a" : "§c") + "+" + base;
		if (type.isPercent()) stat += "%";
		if (bonus != 0) stat += " §e(" + (bonus > 0 ? "+" : "") + bonus + ")";
		return stat;
	}

	public int getHpbBonus(StatType type, int hpb) {
		ItemType itemType = getItemType();

		if (itemType == ItemType.SWORD || itemType == ItemType.AXE ||
				itemType == ItemType.BOW || itemType == ItemType.WEAPON || itemType == ItemType.FISHING_ROD) {
			if (type == StatType.DAMAGE || type == StatType.STRENGTH) return hpb * 2;
		}

		if (itemType == ItemType.HELMET || itemType == ItemType.CHESTPLATE ||
				itemType == ItemType.LEGGINGS || itemType == ItemType.BOOTS) {
			if (type == StatType.HEALTH) return hpb * 4;
			if (type == StatType.DEFENSE) return hpb * 2;
		}

		return 0;
	}

	public String capitalize(String str) {
		String[] words = str.toLowerCase().split(" ");
		StringBuilder builder = new StringBuilder();
		for (String word : words) {
			if (!word.isEmpty()) builder.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
		}
		return builder.toString().trim();
	}

	public abstract String getName();
	public abstract Rarity getRarity();
	public abstract Material getMaterial();
	public abstract List<String> getLore();
	public abstract ItemType getItemType();

	public List<SItemFlag> getFlags() {
		return new ArrayList<>();
	}
	public List<String> getAfterLore() {
		return null;
	}
	public byte getDurability() {
		return 0;
	}
	public Gamestage getGamestage() {
		return Gamestage.STARTER;
	}
	public DungeonType getDungeonType() {
		return DungeonType.NONE;
	}
	public Soulbound getSoulbound() {
		return Soulbound.NONE;
	}
	public String getId() {
		return getClass().getSimpleName();
	}
	public boolean isEnchanted() {
		return false;
	}
	public boolean isStackable() {
		return true;
	}
	public boolean isDungeonItem() {
		return false;
	}
	public boolean reforgeable() {
		return false;
	}

	public enum SItemFlag {
		HIDE_RARITY, ENCHANTED, DUNGEON
	}
}
