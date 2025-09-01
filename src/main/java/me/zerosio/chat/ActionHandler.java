package me.zerosio.chat;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ActionHandler {

    private static final Map<String, Runnable> actions = new HashMap<>();
    private static final AtomicInteger idCounter = new AtomicInteger();

    public static String registerAction(Runnable runnable) {
        String id = UUID.randomUUID().toString().substring(0, 8) + "-" + idCounter.incrementAndGet();
        actions.put(id, runnable);
        return id;
    }

    public static boolean runAction(String id) {
        Runnable action = actions.remove(id);
        if (action != null) {
            action.run();
            return true;
        }
        return false;
    }
}
