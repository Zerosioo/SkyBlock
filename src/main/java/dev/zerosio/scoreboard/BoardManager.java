package dev.zerosio.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;

public class BoardManager {

    private static final Map<Player, Scoreboard> boards = new HashMap<>();

    public static void setScoreboard(Player player, String title, String... lines) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = board.registerNewObjective("sidebar", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(title);

        int score = lines.length;
        for (String line : lines) {
            if (line == null || line.isEmpty()) line = " ";
            if (score == 0) break;
            objective.getScore(line.length() > 40 ? line.substring(0, 40) : line).setScore(score--);
        }

        player.setScoreboard(board);
        boards.put(player, board);
    }

    public static void clear(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        boards.remove(player);
    }

    public static void removeAll() {
        for (Player player : boards.keySet()) {
            clear(player);
        }
        boards.clear();
    }
}
