package me.zerosio.mayors;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.zerosio.Core;
import me.zerosio.mayors.pads.TeleportPad;
import me.zerosio.mayors.pads.TeleportPads;
import me.zerosio.npcs.mayors.*;
import me.zerosio.time.SkyblockYear;
import me.zerosio.utility.RunnableUtil;

public class MayorLocalization {

	private static ClerkSeraphine clerk;

	public static void initialise() {
		ElectionVoteManager.initialize(Core.getPlugin(Core.class).getDataFolder());
		ElectionHistoryManager.initialize(Core.getPlugin(Core.class).getDataFolder());
		LastElectedManager.initialize(Core.getPlugin(Core.class).getDataFolder());

		//ElectionTiming et = new ElectionTiming(Core.getPlugin(Core.class));
//		et.start();

		startElections();
		MayorManager.despawnAllCandidates();
		MayorHologram.remove();
		MayorManager.SELECTED_CANDIDATES.clear();
		try {
			clerk.despawn();
		} catch (Exception blyat) {
		}

		TeleportPad.startMonitoring();
		TeleportPads.register();
	}

	public static void registerCandidates() {
		MayorManager.registerMayor(new Aatrox());
		MayorManager.registerMayor(new Diana());
		MayorManager.registerMayor(new Paul());
		MayorManager.registerMayor(new Cole());
		MayorManager.registerMayor(new Marina());
		//MayorManager.registerMayor(new SmartyXD());
	}

	public static void setCandidatePositions() {
		Location l1 = new Location(Bukkit.getWorld("world"), -6.5, 33, -119.5);
		Location l2 = new Location(Bukkit.getWorld("world"), -3.5, 33, -128.5);
		Location l3 = new Location(Bukkit.getWorld("world"), 5.5, 33, -131.5);
		Location l4 = new Location(Bukkit.getWorld("world"), 14.5, 33, -128.5);
		Location l5 = new Location(Bukkit.getWorld("world"), 17.5, 33, -119.5);

		MayorManager.setCandidateLocations(l1, l2, l3, l4, l5);
	}

	public static void startElections() {
		registerCandidates();
		setCandidatePositions();

		MayorManager.spawnRandomisedCandidates();

		ElectionTiming et = new ElectionTiming(Core.getPlugin(Core.class));
		MayorHologram.spawnVotingHologram(et);

		clerk = new ClerkSeraphine();
	}

	public static void stopElections() {
		MayorManager.despawnAllCandidates();

		Mayor mayor = MayorManager.getCandidateMayor(ElectionVoteManager.calculateWinner());
		Mayor minister = MayorManager.getCandidateMayor(ElectionVoteManager.calculateSecondPlace());
		
		LastElectedManager.updateLastElected(SkyblockYear.getCurrentYear(), mayor, minister);
		ElectionHistoryManager.saveElection(
			SkyblockYear.getCurrentYear(),
			MayorManager.SELECTED_CANDIDATES,
			convertVotesToMayorMap(),
			MayorManager.getCandidateMayor(ElectionVoteManager.calculateWinner()),
			MayorManager.getCandidateMayor(ElectionVoteManager.calculateSecondPlace())
		);

		MayorHologram.remove();

		ElectionVoteManager evm = new ElectionVoteManager();

		String winnerMeta = MayorManager.getCandidateMayor(evm.calculateWinner()).getDisplayName();
		String ministerMeta = MayorManager.getCandidateMayor(evm.calculateSecondPlace()).getDisplayName();

		MayorBroadcasts.onElectionEnd(winnerMeta, ministerMeta, evm.getTotalVotesCommafied());

		RunnableUtil.runLater(() -> {

			MayorManager.spawnElectedMayor(new Location(Bukkit.getWorld("world"), -0.5, 72, -111.5), mayor);
			RunnableUtil.runLater(() -> {
				MayorManager.spawnElectedMinister(new Location(Bukkit.getWorld("world"), -2.5, 72, 109.5), minister);
			}, 10L);

			ElectionVoteManager.removeAllVotesAndCandidates();

			if (clerk != null) {
				clerk.despawn();
			}

			SkyblockYear.setNextYear(); // just to test, might just keep it lul

			MayorManager.SELECTED_CANDIDATES.clear();
		}, 200L);
	}

	private static Map<Mayor, Integer> convertVotesToMayorMap() {
		Map<Mayor, Integer> result = new HashMap<>();
		Map<Integer, Integer> rawVotes = ElectionVoteManager.getAllCandidateVotes();

		for (Map.Entry<Integer, Integer> entry : rawVotes.entrySet()) {
			int candidateIndex = entry.getKey();
			Mayor mayor = MayorManager.getCandidateMayor(candidateIndex);
			if (mayor != null) {
				result.put(mayor, entry.getValue());
			}
		}

		return result;
	}


}