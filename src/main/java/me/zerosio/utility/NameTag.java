package me.zerosio.utility;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import me.zerosio.rank.PlayerRank;
import me.zerosio.scoreboard.BoardManager;
import me.zerosio.user.levels.SkyBlockLevel;
import me.zerosio.user.profiledata.UserRank;

public class NameTag {

    public static void setDisplayName(Player player, String initialName, String prefix) {
        player.setDisplayName(initialName);
        player.setPlayerListName(initialName);

        Scoreboard scoreboard = BoardManager.getOrCreateBoard(player);

        String teamName = "prefix_" + player.getName();
        if (teamName.length() > 16) {
            teamName = "p_" + Integer.toHexString(player.getName().hashCode()).substring(0, 12);
        }

        Team team = scoreboard.getTeam(teamName);
        if (team == null) {
            team = scoreboard.registerNewTeam(teamName);
        }

        team.setPrefix(prefix);
        team.addEntry(player.getName());
    }

    public static void update(Player p) {
        String levelPrefix = SkyBlockLevel.getNameTagPrefix(p);
        if (levelPrefix == null) levelPrefix = "§70";

        PlayerRank rank = UserRank.getPlayerRank(p);
        if (rank == null) rank = PlayerRank.DEFAULT;

        String rankColor = rank.getColour();
        setDisplayName(p, rankColor + p.getName(), levelPrefix + " " + rankColor);
    }
}
