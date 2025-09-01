package me.zerosio.utility;

import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.plugin.Plugin;

import me.zerosio.Core;

import java.util.function.Consumer;

public class EventUtil {

    private static final Plugin plugin = Core.getInstance();

    public static <T extends Event> void onEvent(Class<T> eventClass, Consumer<T> handler) {
        onEvent(eventClass, handler, EventPriority.NORMAL, true);
    }

    public static <T extends Event> void onEvent(Class<T> eventClass, Consumer<T> handler, EventPriority priority, boolean ignoreCancelled) {
        Bukkit.getPluginManager().registerEvent(
            eventClass,
            new Listener() {},
            priority,
            (listener, event) -> {
                if (eventClass.isInstance(event)) {
                    handler.accept(eventClass.cast(event));
                }
            },
            plugin,
            ignoreCancelled
        );
    }
}

//EventUtil.onEvent(PlayerJoinEvent.class, e -> {
//    e.getPlayer().sendMessage("§aWelcome to the server!");
//});
