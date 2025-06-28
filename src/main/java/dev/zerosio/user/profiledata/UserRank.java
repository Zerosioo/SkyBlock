package dev.zerosio.user.profiledata;

import org.bukkit.entity.Player;

import dev.zerosio.rank.PlayerRank;
import dev.zerosio.user.User;
import dev.zerosio.utility.Logging;
import dev.zerosio.utility.NameTag;

public class UserRank {

    public static PlayerRank getPlayerRank(Player player) {
        User user = User.getUser(player);

        String rankString = user.getString("rank");
        if (rankString == null) {
            Logging.sendMessage("§c[Rank] §fRank is §enull §ffor §b" + player.getName());
            return PlayerRank.DEFAULT;
        }

        try {
            return PlayerRank.valueOf(rankString.toUpperCase());
        } catch (IllegalArgumentException e) {
            Logging.print("§c[Rank] §fInvalid rank value '§e" + rankString + "§f' for §b" + player.getName());
            return PlayerRank.DEFAULT;
        }
    }
    
    public static void setPlayerRank(PlayerRank rank, Player player) {
    	User user = User.getUser(player);
		user.setData("rank", rank.name().toUpperCase());
		user.save();
		String displayName = rank.getPrefix() + player.getName();
		NameTag.setDisplayName(player, player.getName(), rank.getPrefix());
	}
}
