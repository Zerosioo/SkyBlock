package me.zerosio.inventory.build;

import org.bukkit.entity.Player;
import me.zerosio.utility.ReflectionsUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class GUIRegistry {

    private static final Map<String, Class<?>> registeredGUIs = new LinkedHashMap<>();

    public static void loadAllGUIs() {
        List<String> classes = ReflectionsUtil.getClasses("me.zerosio.inventory");

        for (String className : classes) {
            try {
                Class<?> clazz = Class.forName(className);

                if (!hasValidGUI(clazz)) continue;

                String id = generateGuiId(clazz);
                registeredGUIs.put(id, clazz);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("[GUIRegistry] Loaded " + registeredGUIs.size() + " GUI(s).");
    }

    private static boolean hasValidGUI(Class<?> clazz) {
        try {
            clazz.getConstructor(Player.class);
            try {
                clazz.getMethod("open");
                return true;
            } catch (NoSuchMethodException e) {
                clazz.getMethod("open", Player.class);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static void open(String id, Player player) {
        Class<?> clazz = registeredGUIs.get(id.toUpperCase());
        if (clazz == null) {
            player.sendMessage("§cGUI not found: " + id);
            return;
        }

        try {
            Object gui = clazz.getConstructor(Player.class).newInstance(player);

            try {
                // Try open() first
                Method open = clazz.getMethod("open");
                open.invoke(gui);
            } catch (NoSuchMethodException e) {
                // Then try open(Player)
                Method openWithPlayer = clazz.getMethod("open", Player.class);
                openWithPlayer.invoke(gui, player);
            }

        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage("§cFailed to open GUI: " + id);
        }
    }

    public static String generateGuiId(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        if (simpleName.endsWith("GUI")) {
            simpleName = simpleName.substring(0, simpleName.length() - 3);
        }

        StringBuilder idBuilder = new StringBuilder();
        char[] chars = simpleName.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (i > 0 && Character.isLowerCase(chars[i - 1]) && Character.isUpperCase(chars[i])) {
                idBuilder.append('_');
            }
            idBuilder.append(chars[i]);
        }

        return idBuilder.toString().toUpperCase();
    }

    public static Set<String> getRegisteredIds() {
        return registeredGUIs.keySet();
    }

    public static boolean isValidGUI(Class<?> clazz) {
        if (clazz == null) return false;
        try {
            Constructor<?> constructor = clazz.getConstructor(Player.class);
            Method openMethod;
            try {
                openMethod = clazz.getMethod("open");
            } catch (NoSuchMethodException e) {
                openMethod = clazz.getMethod("open", Player.class);
            }
            return constructor != null && openMethod != null;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public static Set<String> getRegisteredIdss() {
        return Collections.unmodifiableSet(registeredGUIs.keySet());
    }
}
