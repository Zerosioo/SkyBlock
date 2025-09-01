package me.zerosio.items.itemtype;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
public static ItemStack buildItemStack(Material material,byte data,String name, List<String> lore) {
	ItemStack item = new ItemStack(material, 1, data);
	ItemMeta meta = item.getItemMeta();
	meta.setLore(lore);
	meta.setDisplayName(name);
	item.setItemMeta(meta);
	return item;
}
}
