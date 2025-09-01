package me.zerosio.inventory.npcmenus.jerry;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.zerosio.inventory.build.InventoryBuilder;
import me.zerosio.inventory.build.ItemBuilder;

public class JerryTheAssistantGUI {

	private final Player player;

	public JerryTheAssistantGUI(Player player) {
		this.player = player;
	}

	public void open() {
		InventoryBuilder gui = new InventoryBuilder("§8Jerry The Assistant", 4);

		gui.fill();

		gui.setGuiSlot(12, new ItemBuilder(Material.BOOK)
					   .setName("§aPatch Notes")
					   .addLore("§7View the latest features and", "§7changes to the game.")
		.build(), e -> {
			new PatchNotes(player).open();
		});

		gui.setGuiSlot(14, new ItemBuilder(Material.ENDER_CHEST)
					   .setName("§aDeliveries")
					   .addLore("§7Any items that may be delivered", "§7to yourself or your island will", "§7appear here for collection!", "", "§eClick to open!")
		.build(), e -> {
			
		});
		
		gui.setGuiSlot(16, new ItemBuilder(Material.EMERALD)
					   .setName("§aVisiting & Guesting")
					   .addLore("§7Learn all about how to §a/visit", "§7players across the SkyBlock", "§7universe!", "", "§eClick to learn!")
		.build(), e -> {
			
		});
		
		gui.setGuiSlot(28, new ItemBuilder(Material.NETHERRACK)
					   .setName("§aConvert Island Biome")
					   .addLore("§7Convert the biometric of the entire", "§7island by using a biometric stick!", "", "§cRequires one biome stick!")
		.build(), e -> {
			
		});
		
		gui.setGuiSlot(29, new ItemBuilder(Material.NETHER_BRICK)
					   .setName("§aRemove Hub Portal")
					   .addLore("§7Removes all the portal blocks", "§7from the Hub Portal on your", "§7island!", "", "§cRequires level V in any skill!")
		.build(), e -> {
			
		});
		
		gui.setGuiSlot(32, ItemBuilder.CLOSE_ITEM, e -> {
			player.closeInventory();
		});
		
		gui.setGuiSlot(33, new ItemBuilder(Material.DIAMOND)
					   .setName("§aSkyBlock Achivements")
					   .addLore("§7Unlocked: §b0§7/§b238 §8(0%)", "§7Points: §e0§7/§e2,180 §8(0%)", "", "§eClick to view achivements!")
		.build(), e -> {
			
		});
		
		gui.setGuiSlot(36, new ItemBuilder(Material.BEDROCK)
					   .setName("§aMove Jerry")
		.build(), e -> {
			
		});

		gui.open(player);
	}
}
