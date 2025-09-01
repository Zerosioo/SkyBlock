package me.zerosio.inventory.mayors.elections;

import me.zerosio.inventory.build.InventoryBuilder;
import me.zerosio.inventory.build.ItemBuilder;
import me.zerosio.mayors.Mayor;
import me.zerosio.mayors.MayorBroadcasts;
import me.zerosio.mayors.MayorEnum;
import me.zerosio.mayors.MayorManager;
import me.zerosio.mayors.ElectionVoteManager;
import me.zerosio.mayors.LastElectedManager;
import me.zerosio.npcs.build.AbstractNPC;
import me.zerosio.time.SkyblockYear;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class FirstElectionGUI {

	private final Player player;

	public FirstElectionGUI(Player player) {
		this.player = player;
	}

	public void open() {
		InventoryBuilder gui = new InventoryBuilder("§8Election", 3);
		gui.fill();

		for (int i = 1; i <= 5; i++) {
			final int id = i;
			Mayor mayor = MayorManager.getCandidateMayor(i);
			AbstractNPC npc = MayorManager.getCandidateNPC(i);

			if (mayor == null || npc == null) continue;

			int slot = 8 + (i * 2); // 9, 11, 13, 15, 17 style positioning

			gui.setGuiSlot(slot, new ItemBuilder(Material.SKULL_ITEM)
						   .setDurability((short) 3)
						   .setTexture(npc.getTexture())
						   .setName(mayor.getDisplayName())
						   .addLore(generateLore(mayor))
			.build(), e -> {
				ElectionVoteManager.addVote(player.getUniqueId(), id);
				MayorBroadcasts.onVoteCastOrChange(player, false, mayor.getDisplayName(), "Settler", 1,
												   ElectionVoteManager.getVotePercentageString(id),
												   ElectionVoteManager.getTotalVotesCommafied());
				player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				player.closeInventory();
			});
		}

		gui.open(player);
	}

	private String[] generateLore(Mayor mayor) {
		List<String> lore = new ArrayList<>();

		lore.add("§8Candidate");
		lore.add("§8Year " + SkyblockYear.getCurrentYear());
		lore.add(""); 
		
		lore.add("§7Last elected: " + mayor.getColorCode() + LastElectedManager.getYearsSinceElected(SkyblockYear.getCurrentYear(), MayorEnum.fromMayor(mayor)) + "y ago"); 
		
		lore.add(""); 

		lore.addAll(mayor.getFormattedPerks());
		
		lore.add("§7§6✯ §7§bMinister Perks §7are also granted if");
		lore.add("§7this mayor wins second place!");
		lore.add("");
		lore.add("§8You may change your vote at");
		lore.add("§8anytime until the election");
		lore.add("§8ends");
		lore.add(""); 
		lore.add("§eClick to vote for " + mayor.getDisplayName() + "§e!");

		return lore.toArray(new String[0]);
	}

}
