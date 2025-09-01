package me.zerosio.user.profiledata;

import me.zerosio.utility.NameTag;
import org.bukkit.entity.Player;
import me.zerosio.user.User;
import me.zerosio.user.levels.SkyBlockLevel;

public class UserLevel {

    public static int getLevel(Player player) {
        User user = User.getUser(player);
        return getNumber(user, "skyblock_level");
    }

    public static int getXp(Player player) {
        User user = User.getUser(player);
        return getNumber(user, "skyblock_xp");
    }

    public static void setLevel(Player p, int amount) {
        User.getUser(p).setProfileData("skyblock_level", amount);
        NameTag.update(p);
    }

    public static void setXp(Player p, int amount) {
        User.getUser(p).setProfileData("skyblock_xp", amount);
    }

    public static void resetXp(Player p) {
        User.getUser(p).setProfileData("skyblock_xp", 0);
    }

    public static void addLevel(Player p, int amount) {
        User user = User.getUser(p);
        int current = getNumber(user, "skyblock_level");
        user.setProfileData("skyblock_level", current + amount);
        NameTag.update(p);
    }

    public static void addXp(Player p, int amount) {
        User user = User.getUser(p);
        int currentXp = getNumber(user, "skyblock_xp");
        int totalXp = currentXp + amount;

        while (totalXp >= 100) {
            SkyBlockLevel.levelUp(p);
            totalXp -= 100;
        }

        setXp(p, totalXp);
    }

    private static int getNumber(User user, String path) {
        Object raw = user.getProfileData(path);
        return (raw instanceof Number) ? ((Number) raw).intValue() : 0;
    }
}
