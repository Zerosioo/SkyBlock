package me.zerosio.commands;

import me.zerosio.user.User;
import me.zerosio.user.profiledata.UserEconomy;
import me.zerosio.utility.NumberFormatter;
import org.bukkit.entity.Player;

import me.zerosio.Core;
import me.zerosio.chat.ChatOptionUtil;
import me.zerosio.commands.builder.CommandBase;
import me.zerosio.garden.cosmetic.ComposterCosmetic;
import me.zerosio.garden.cosmetic.DeskBook;
import me.zerosio.garden.cosmetic.VisitorLogbook;
import me.zerosio.inventory.mayors.elections.FirstElectionGUI;
import me.zerosio.inventory.mayors.elections.VoteChangeGUI;
import me.zerosio.inventory.npcmenus.jerry.JerryTheAssistantGUI;
import me.zerosio.kuudra.tentacles.Tentacle;
import me.zerosio.mayors.ElectionTiming;
import me.zerosio.mayors.ElectionVoteManager;
import me.zerosio.mayors.MayorLocalization;
import me.zerosio.mechanics.farming.drops.DropRarity;
import me.zerosio.mechanics.farming.drops.DropTitleAnimation;
import me.zerosio.mechanics.farming.drops.DropTitleAnimationManager;
import me.zerosio.rank.PlayerRank;
import me.zerosio.slayer.vampire.BlockPhaseEffect;

import java.util.List;

public class TestCommand extends CommandBase {
	
	Tentacle tentacle = null;

	@Override
	public String getName() {
		return "test";
	}

	@Override
	public List<String> getAliases() {
		return null;
	}

	@Override
	public PlayerRank getRequiredRank() {
		return PlayerRank.ADMIN;
	}

	@Override
	public String getDescription() {
		return "Used for testing purposes";
	}

	@Override
	public String getUsage() {
		return "/test";
	}

	@Override
	public void execute(Player player, String[] args) {

		if (args.length > 0 && args[0].equalsIgnoreCase("diddy")) {
			ChatOptionUtil.sendOptions(player, "§eDo you want to continue?",
									   () -> player.sendMessage("§aYou selected YES!"),
									   () -> player.sendMessage("§cYou selected NO!")
									  );
			return;
		}

		if (args.length > 0 && args[0].equalsIgnoreCase("dta")) {
			DropTitleAnimationManager.play(player, DropRarity.RARE, "§aEnchanted Melon", 16);
			return;
		}

		if (args.length > 0 && args[0].equalsIgnoreCase("e_start")) {
			MayorLocalization.startElections();
			return;
		}

		if (args.length > 0 && args[0].equalsIgnoreCase("e_stop")) {
			MayorLocalization.stopElections();
			return;
		}

		if (args.length > 0 && args[0].equalsIgnoreCase("e_gui")) {
			ElectionTiming met = new ElectionTiming(Core.getPlugin(Core.class));

			if (met.isElectionOpen()) {
				if (!ElectionVoteManager.hasVoted(player)) {
					new FirstElectionGUI(player).open();
				} else {
					new VoteChangeGUI(player).open();
				}
			} else {
				player.sendMessage("§cThe election booth is closed!");
			}
			return;
		}

		if (args.length > 0 && args[0].equalsIgnoreCase("e_check")) {
			ElectionVoteManager.inspect(player);
			return;
		}

		if (args.length > 0 && args[0].equalsIgnoreCase("interest")) {
			UserEconomy.setBankCoins(player, 100000000);
			User.getUser(player).setProfileData("bank.last_interest", System.currentTimeMillis() + 111700000);
			return;
		}

		if (args.length > 0 && args[0].equalsIgnoreCase("bbal")) {
			player.sendMessage("§aYour current balance is §6" + NumberFormatter.commafy(UserEconomy.getBankCoins(player)) + " coins§a.");
		}

		if (args.length > 0 && args[0].equalsIgnoreCase("spawn_visitors_logbook")) {
			VisitorLogbook.spawn(player.getLocation());
			return;
		}
		
		if (args.length > 0 && args[0].equalsIgnoreCase("spawn_desk_book")) {
			DeskBook.spawn(player.getLocation());
			return;
		}
		
		if (args.length > 0 && args[0].equalsIgnoreCase("spawn_composter")) {
			ComposterCosmetic.spawn(player.getLocation());
			return;
		}

		if (args.length > 0 && args[0].equalsIgnoreCase("tentacle")) {
			tentacle = new Tentacle(player.getWorld(), player.getLocation(), 14, 14, 1);
			return;
		}

		if (args.length > 0 && args[0].equalsIgnoreCase("remove_tentacle")) {
			if (tentacle != null) {
				player.sendMessage("§cRemoving previous kuudra tentacle...");
				tentacle.remove();
				tentacle = null;
			} else {
				player.sendMessage("§cNo previous kuudra tentacle.");
			}
			return;
		}
		
		if (args.length > 0 && args[0].equalsIgnoreCase("vamp_block_phase")) {
			new BlockPhaseEffect(player).startPhase();
			return;
		}

		new JerryTheAssistantGUI(player).open();
	}
}
