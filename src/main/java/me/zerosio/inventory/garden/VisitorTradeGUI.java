package me.zerosio.inventory.garden;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.zerosio.garden.visitors.build.GardenVisitor;
import me.zerosio.garden.visitors.build.VisitorManager;
import me.zerosio.inventory.build.InventoryBuilder;
import me.zerosio.inventory.build.ItemBuilder;
import me.zerosio.npcs.build.AbstractNPC;
import me.zerosio.user.profiledata.VisitorData;

public class VisitorTradeGUI {

	private final Player player;
	private final GardenVisitor visitor;

	public VisitorTradeGUI(Player player, GardenVisitor visitor) {
		this.player = player;
		this.visitor = visitor;
	}

	public void open() {
		InventoryBuilder gui = new InventoryBuilder("§8" + visitor.getName(), 6);

		AbstractNPC npc = visitor.createNpc();

		gui.fill();

		if (npc.getTexture() != null && npc.getSignature() != null && npc.getTexture() != "" && npc.getSignature() != "") {
			gui.setGuiSlot(14, new ItemBuilder(Material.SKULL_ITEM)
						   .setName(visitor.getRarity().getColor() + visitor.getName())
						   .addLore(visitor.getRarity().getColor() + "§l" + visitor.getRarity().name().replace("_", " "), "", "§7Times Visited: §a" + VisitorData.getVisits(player, visitor.getName().toLowerCase()),
									"§7Offers Accepted: §a" + VisitorData.getAccepted(player, visitor.getName().toLowerCase()))
						   .setTexture(npc.getTexture())
			.build(), e -> {
			});
		} else if (npc.getEntityType() == EntityType.VILLAGER) {
			gui.setGuiSlot(14, new ItemBuilder(Material.MONSTER_EGG)
			.setDurability((short) 120)
						   .setName(visitor.getRarity().getColor() + visitor.getName())
						   .addLore(visitor.getRarity().getColor() + "§l" + visitor.getRarity().name().replace("_", " "), "", "§7Times Visited: §a" + VisitorData.getVisits(player, visitor.getName().toLowerCase()),
									"§7Offers Accepted: §a" + VisitorData.getAccepted(player, visitor.getName().toLowerCase()))
			.build(), e -> {
			});
		} else if (npc.getEntityType() == EntityType.WITCH) {
			gui.setGuiSlot(14, new ItemBuilder(Material.MONSTER_EGG)
			.setDurability((short) 66)
						   .setName(visitor.getRarity().getColor() + visitor.getName())
						   .addLore(visitor.getRarity().getColor() + "§l" + visitor.getRarity().name().replace("_", " "), "", "§7Times Visited: §a" + VisitorData.getVisits(player, visitor.getName().toLowerCase()),
									"§7Offers Accepted: §a" + VisitorData.getAccepted(player, visitor.getName().toLowerCase()))
			.build(), e -> {
			});
		} else {
			gui.setGuiSlot(14, new ItemBuilder(Material.SKULL_ITEM)
						   .setName(visitor.getRarity().getColor() + visitor.getName())
						   .addLore(visitor.getRarity().getColor() + "§l" + visitor.getRarity().name().replace("_", " "), "", "§7Times Visited: §a" + VisitorData.getVisits(player, visitor.getName().toLowerCase()),
									"§7Offers Accepted: §a" + VisitorData.getAccepted(player, visitor.getName().toLowerCase()))
			.build(), e -> {
			});
		}
		
		gui.setGuiSlot(30, new ItemBuilder(Material.STAINED_CLAY)
		.setDurability((short) 13)
					   .setName("§aAccept Offer")
					   .addLore("§7Item Required:", "", "§7Rewards:", "", "§cMissing items to accept!")		   
		.build(), e -> {
			visitor.sendAcceptMessage(player);
			VisitorManager.acceptVisitor(player, visitor);
			player.closeInventory();
		});
		
		gui.setGuiSlot(34, new ItemBuilder(Material.STAINED_CLAY)
		.setDurability((short) 14)
					   .setName("§cRefuse Offer")
					   .addLore(visitor.getRarity().getColor() + visitor.getName() + " §7will leave your §bGarden §7and", "§7maybe come back later.", "", "§eClick to refuse!")		   
		.build(), e -> {
			VisitorManager.declineVisitor(player, visitor);
			visitor.sendDeclineMessage(player);
			player.closeInventory();
		});
		
		gui.open(player);
	}
}
