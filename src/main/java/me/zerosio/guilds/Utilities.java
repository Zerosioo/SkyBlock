package me.zerosio.guilds;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.zerosio.user.profiledata.UserRank;

import java.text.NumberFormat;
import java.util.*;

public class Utilities {
    private static final NumberFormat COMMA_FORMAT = NumberFormat.getInstance();

    static {
        COMMA_FORMAT.setGroupingUsed(true);
    }

    public static void runAsync(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static void delay(Runnable runnable, long delayTicks) {
        new Thread(() -> {
            try {
                Thread.sleep(delayTicks * 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runnable.run();
        }).start();
    }

    public static HashMap<?, ?> sortByValue(Map<?, ?> map) {
        List<Map.Entry<?, ?>> list = new LinkedList<>(map.entrySet());
        list.sort((o1, o2) -> ((Comparable) o2.getValue()).compareTo(o1.getValue()));

        HashMap<Object, Object> result = new LinkedHashMap<>();
        for (Map.Entry<?, ?> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static String commaify(long value) {
        return COMMA_FORMAT.format(value);
    }

    public static String trans(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    
    public static String getRankFromPlayer(Player player) {
    	return UserRank.getPlayerRank(player).getPrefix();
    }
}
