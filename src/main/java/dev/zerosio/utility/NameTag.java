package dev.zerosio.utility;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import dev.zerosio.scoreboard.BoardManager;

public class NameTag {
    public static void setDisplayName(Player player, String initialName, String prefix) {
        player.setDisplayName(initialName);
        player.setPlayerListName(initialName);

        Scoreboard scoreboard = BoardManager.getOrCreateBoard(player);

        String teamName = "prefix_" + player.getName();
        Team team = scoreboard.getTeam(teamName);
        if (team == null) {
            team = scoreboard.registerNewTeam(teamName);
        }

        team.setPrefix(prefix);
        team.addEntry(player.getName());
    }
}
