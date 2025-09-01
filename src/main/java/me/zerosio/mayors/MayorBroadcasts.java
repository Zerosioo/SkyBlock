package me.zerosio.mayors;

import me.zerosio.Core;
import me.zerosio.chat.ClickActionType;
import me.zerosio.chat.MessageUtil;
import me.zerosio.time.SkyblockYear;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MayorBroadcasts {

    public static void onVoteCastOrChange(Player player, boolean artifactOfControl, String mayorname, String famerank, int vote, String votePercentage, String totalVotes) {
        List<String> hoverText = MessageUtil.hover(
                "&eIncrease your &bFame Rank &eby",
                "&espending &bBits &e& &aGems &ein the Community Shop!"
        );

        player.sendMessage("§d-----------------------------------------------------");
        player.sendMessage("§eYou voted for/changed your vote to " + mayorname + " §ein the §bYear " + SkyblockYear.getCurrentYear() + " Elections§e!");

        if (!artifactOfControl) {
            MessageUtil.sendClickableMessage(player, "&eAs a &b" + famerank + "&e, your vote counts for &a" + vote + "&e.", ClickActionType.NONE, "", hoverText);
        } else {
            MessageUtil.sendClickableMessage(player, "&eAs a &b" + famerank + "&e, your vote counts for &a" + (vote * 2) + "&e. &8(doubled by Artifact of Control)&f.", ClickActionType.NONE, "", hoverText);
        }

        player.sendMessage("§d" + mayorname + " §enow has §d" + votePercentage + "% §eof votes with §d" + totalVotes + " votes§e!");
        player.sendMessage("§d-----------------------------------------------------");
    }

    public static void onElectionEnd(String mayor, String minister, String totalVotes) {
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§d§k§lk §e§lEvent: §bMayor Elections " + SkyblockYear.getCurrentYear() + "! §d§k§lk");
        Bukkit.broadcastMessage("§eThe election room is now closed. Clerk Seraphine is doing a final count of the votes...");
        Bukkit.broadcastMessage("");

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), () -> {
            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage("§d§k§lk §e§lEvent: §bMayor Elections " + SkyblockYear.getCurrentYear() + "! §d§k§lk");
            Bukkit.broadcastMessage("§e" + mayor + " §eis elected Mayor for the year, §6gg§e!");
            Bukkit.broadcastMessage("§a" + minister + " §ecame in 2nd and is the Minister, §6lesser gg§e!");
            Bukkit.broadcastMessage("§eSeraphine counted votes from §b" + totalVotes + " §edifferent players.");
            Bukkit.broadcastMessage("§eEverybody unlocks §6exclusive §eperks!");
            Bukkit.broadcastMessage("");
        }, 200L);
    }
}
