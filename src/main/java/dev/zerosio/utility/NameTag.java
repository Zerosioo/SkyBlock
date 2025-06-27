package dev.zerosio.utility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class NameTag {
	public static void setDisplayName(Player player, String initialName, String prefix) {
		player.setDisplayName(initialName);
		player.setPlayerListName(initialName);
		
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam(player.getName());
        if (team == null) {
            team = scoreboard.registerNewTeam(player.getName());
        }
        team.setPrefix(prefix);
        team.addEntry(player.getName());
        player.setScoreboard(scoreboard);
	}
}
