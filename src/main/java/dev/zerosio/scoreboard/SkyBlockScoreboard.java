package dev.zerosio.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import dev.zerosio.Core;
import dev.zerosio.instance.InstanceID;
import dev.zerosio.user.profiledata.UserEconomy;
import dev.zerosio.utility.NumberFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SkyBlockScoreboard {

    public static void startTask(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) {
                    cancel();
                    return;
                }

                BoardManager.setScoreboard(player,
                    "§e§lSKYBLOCK",
                    "§7" + getDateString() + " §8" + InstanceID.getName(player.getWorld()),
                    "§e",
                    "§fSpring 18th",
                    "§71:50am §b🌙",
                    "§7R Unknown",
                    "",
                    "§fPurse: §6" + NumberFormatter.commafy(UserEconomy.getCoins(player)),
                    "§f",
                    "§ewww.zerosio.net"
                );
            }
        }.runTaskTimer(Core.getPlugin(Core.class), 20L, 20L); 
    }

    public static String getDateString() {
        return new SimpleDateFormat("MM/dd/yy").format(new Date());
    }
}
