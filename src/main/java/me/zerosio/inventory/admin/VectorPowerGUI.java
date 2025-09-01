package me.zerosio.inventory.admin;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.zerosio.inventory.build.InventoryBuilder;
import me.zerosio.inventory.build.ItemBuilder;
import me.zerosio.mayors.MayorLocalization;
import me.zerosio.user.AdminDebug;

public class VectorPowerGUI {

	private final Player player;

	public VectorPowerGUI(Player player) {
		this.player = player;
	}

	public void open() {
		InventoryBuilder gui = new InventoryBuilder("§8Force Election Control", 3);

		gui.fill();

		gui.setGuiSlot(11, new ItemBuilder(Material.SKULL_ITEM)
					   .setName("§aStart")
					   .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjU0OTQwMDU3Y2NjYTMzMjE4MjRiMjAwNzJiODRhNzBjYmM1MDI5ZDU5YjI2YThmZTViMzUzNWNhN2U0YjAyYiJ9fX0=")
					   .addLore("§7Click to §astart §7the", "§bElection §7process.", "", "§eClick to start!")
		.build(), e -> {
			MayorLocalization.startElections();
			AdminDebug.debug(player, "§aStarted election process.");
		});

		gui.setGuiSlot(17, new ItemBuilder(Material.ARROW)
					   .setName("§cStop")
					   .addLore("§7Click to §cstop §7the", "§bElection §7process.", "", "§eClick to start!")
					   .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjhhNjU0NjZhNjk3NTIwMjNmYTQ4ZmNkM2ZkMWQzZTRmNDYwNTBkYTU0MjBjNjJlMDg5ZDJhMzBiYThiOTBiNCJ9fX0=")
		.build(), e -> {

			MayorLocalization.stopElections();
			AdminDebug.debug(player, "§cStopped elections.");

		});

		gui.open(player);
	}
}
