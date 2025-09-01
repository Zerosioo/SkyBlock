package me.zerosio.utility;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import me.zerosio.Core;

public class RunnableUtil {

    public static void runLater(Runnable runnable, long delayTicks) {
        Bukkit.getScheduler().runTaskLater(Core.getInstance(), runnable, delayTicks);
    }

    public static void runLaterAsync(Runnable runnable, long delayTicks) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(Core.getInstance(), runnable, delayTicks);
    }

    public static void runNow(Runnable runnable) {
        Bukkit.getScheduler().runTask(Core.getInstance(), runnable);
    }

    public static void runNowAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(Core.getInstance(), runnable);
    }

    public static BukkitTask runRepeating(Runnable runnable, long delayTicks, long intervalTicks) {
        return Bukkit.getScheduler().runTaskTimer(Core.getInstance(), runnable, delayTicks, intervalTicks);
    }

    public static BukkitTask runRepeatingAsync(Runnable runnable, long delayTicks, long intervalTicks) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(Core.getInstance(), runnable, delayTicks, intervalTicks);
    }

    public static void cancelTask(int taskId) {
        Bukkit.getScheduler().cancelTask(taskId);
    }

    public static void cancelAll() {
        Bukkit.getScheduler().cancelTasks(Core.getInstance());
    }

    public static void customRunnable(long delay, long interval, Runnable task) {
        new BukkitRunnable() {
            @Override
            public void run() {
                task.run();
            }
        }.runTaskTimer(Core.getInstance(), delay, interval);
    }
}



//RunnableUtil.runLater(() -> {
//    player.sendMessage("yo wait 1 sec");
//}, 20L);

//RunnableUtil.runRepeating(() -> {
//    player.sendMessage("tick tock");
//}, 0L, 20L);

//RunnableUtil.customRunnable(0L, 40L, () -> {
//    // logic here
//});
