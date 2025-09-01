package me.zerosio.inventory.npcmenus.jerry;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.zerosio.inventory.build.InventoryBuilder;
import me.zerosio.inventory.build.ItemBuilder;
import me.zerosio.inventory.build.SkullUtil;

public class PatchNotes {

	private final Player player;

	public PatchNotes(Player player) {
		this.player = player;
	}

	public void open() {
		InventoryBuilder gui = new InventoryBuilder("§8Patch Notes", 4);

		gui.fill();

		gui.setGuiSlot(11, new ItemBuilder(Material.BEACON)
					   .setName("§aSkyBlock v0.02")
					   .addLore("§7zerosio knows...", "", "§eClick to view!")
		.build(), e -> {
			player.sendMessage("§cComing soon...");
		});
		
		gui.setGuiSlot(12, SkullUtil.createSkull("ewogICJ0aW1lc3RhbXAiIDogMTc0OTY5NDE2MDM4MywKICAicHJvZmlsZUlkIiA6ICJmZjQ3NzI5YmQwZDI0YWYwOThiMTFjMGE3ZTFiMGVlZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJtYXRzY2FuIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzgzY2MxY2Y2NzJhNGIyNTQwYmUzNDZlYWQ3OWFjMmQ5ZWQxOWQ5NWI2MDc1YmY5NWJlMGI2ZDBkYTYxMzc3YmUiCiAgICB9CiAgfQp9", "§aSkyBlock v0.1", Arrays.asList("§7zerosio bitch RAHHH")), e -> {
			player.sendMessage("§cComing soon...");
		});
		
		gui.setGuiSlot(32, new ItemBuilder(Material.ARROW)
					   .setName("§aGo Back")
					   .addLore("§7To Jerry The Assistant")
		.build(), e -> {
			new JerryTheAssistantGUI(player).open();
		});

		gui.open(player);
	}
}
