package me.zerosio.user.profiledata;

import me.zerosio.user.User;
import org.bukkit.entity.Player;

public class VisitorData {

    public static int getVisits(Player player, String visitorName) {
        User user = User.getUser(player);
        return getStat(user, visitorName, "times_visited");
    }

    public static int getAccepted(Player player, String visitorName) {
        User user = User.getUser(player);
        return getStat(user, visitorName, "offers_accepted");
    }

    public static void addVisit(Player player, String visitorName) {
        User user = User.getUser(player);
        String key = getPath(visitorName, "times_visited");
        int current = getStat(user, visitorName, "times_visited");
        user.setProfileData(key, current + 1);
    }

    public static void addAccepted(Player player, String visitorName) {
        User user = User.getUser(player);
        String key = getPath(visitorName, "offers_accepted");
        int current = getStat(user, visitorName, "offers_accepted");
        user.setProfileData(key, current + 1);
    }

    private static int getStat(User user, String visitorName, String stat) {
        Object raw = user.getProfileData(getPath(visitorName, stat));
        return (raw instanceof Number) ? ((Number) raw).intValue() : 0;
    }

    private static String getPath(String visitorName, String stat) {
        return "visitor_stats." + visitorName.toLowerCase() + "." + stat;
    }
}
