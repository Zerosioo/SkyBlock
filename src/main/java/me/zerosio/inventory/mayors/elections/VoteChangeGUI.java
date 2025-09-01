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

public class VoteChangeGUI {

	private final Player player;
	private final boolean closedElection; 

	public VoteChangeGUI(Player player) {
		this.player = player;
		int year = SkyblockYear.getCurrentYear();
		this.closedElection = (year % 2 == 0);
	}

	public void open() {
		InventoryBuilder gui = new InventoryBuilder("§8Election, Year " + SkyblockYear.getCurrentYear(), 6);
		gui.fill();

		for (int i = 1; i <= 5; i++) {
			final int id = i;
			Mayor mayor = MayorManager.getCandidateMayor(id);
			AbstractNPC npc = MayorManager.getCandidateNPC(id);

			if (mayor == null || npc == null) continue;

			int slot;
			switch (id) {
			case 1:
				slot = 46;
				break;
			case 2:
				slot = 48;
				break;
			case 3:
				slot = 50;
				break;
			case 4:
				slot = 52;
				break;
			case 5:
				slot = 54;
				break;
			default:
				slot = 50;
				break;
			}

			gui.setGuiSlot(slot, new ItemBuilder(Material.SKULL_ITEM)
						   .setDurability((short) 3)
						   .setTexture(npc.getTexture())
						   .setName(mayor.getDisplayName())
						   .addLore(generateLore(mayor, id))
						   .build(), e -> handleVote(id, mayor.getDisplayName()));
		}

		if (!closedElection) {
			buildCandidateBars(gui);
		} else {
			gui.setGuiSlot(13, new ItemBuilder(Material.JUKEBOX)
						   .setName("§cClosed Election")
						   .addLore(
							   "§7All votes are hidden",
							   "§7for this election!"
						   ).build(), e -> {});
		}

		gui.open(player);
	}

	private String[] generateLore(Mayor mayor, int id) {
		boolean voted = ElectionVoteManager.getPlayerVote(player) == id;

		List<String> lore = new ArrayList<>();
		lore.add("§8Candidate");
		lore.add("§8Year " + SkyblockYear.getCurrentYear());
		lore.add("");

		lore.add("§7Last elected: " + mayor.getColorCode()
				 + LastElectedManager.getYearsSinceElected(SkyblockYear.getCurrentYear(), MayorEnum.fromMayor(mayor)) + "y ago");

		if (!closedElection) {
			lore.add("§7Votes: §d" + ElectionVoteManager.getVotes(id)
					 + " §7(§d" + ElectionVoteManager.getVotePercentage(id) + "%§7)");
		} else {
			lore.add("§7Votes: §cHidden");
		}

		lore.add("");
		lore.addAll(mayor.getFormattedPerks());
		lore.add("§7§6✯ §7§bMinister Perks §7are also granted if");
		lore.add("§7this mayor wins second place!");
		lore.add("");

		lore.add(voted
				 ? "§aYou voted for this candidate!"
				 : "§eClick to vote for " + mayor.getDisplayName() + "§e!");

		return lore.toArray(new String[0]);
	}

	private void handleVote(int id, String displayName) {
		if (ElectionVoteManager.getPlayerVote(player) == id) {
			player.sendMessage("§cYou already voted for this candidate!");
			player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
			return;
		}

		ElectionVoteManager.addVote(player.getUniqueId(), id);
		MayorBroadcasts.onVoteCastOrChange(player, false, displayName, "Settler", 1,
										   ElectionVoteManager.getVotePercentageString(id),
										   ElectionVoteManager.getTotalVotesCommafied());

		player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);
		player.closeInventory();
	}

	private void buildCandidateBars(InventoryBuilder gui) {
		int[][] barSlots = {
			{37, 28, 19, 10, 1},   // c1
			{39, 30, 21, 12, 3},   // c2
			{41, 32, 23, 14, 5},   // c3
			{43, 34, 25, 16, 7},   // c4
			{45, 36, 27, 18, 9}    // c5
		};

		short[] durabilityMap = {14, 13, 11, 4, 10};

		for (int i = 1; i <= 5; i++) {
			Mayor mayor = MayorManager.getCandidateMayor(i);
			AbstractNPC npc = MayorManager.getCandidateNPC(i);
			if (mayor == null || npc == null) continue;

			double percent = ElectionVoteManager.getVotePercentage(i);
			String[] lore = generateLore(mayor, i);
			int filledSlots = (int) Math.min(5, Math.floor(percent / 10.0));

			for (int j = 0; j < 5; j++) {
				boolean filled = j < filledSlots;
				gui.setGuiSlot(barSlots[i - 1][j], new ItemBuilder(Material.STAINED_GLASS_PANE)
							   .setDurability(filled ? durabilityMap[i - 1] : 7)
							   .setName(mayor.getDisplayName())
							   .addLore(lore)
							   .build());
			}
		}
	}
}
