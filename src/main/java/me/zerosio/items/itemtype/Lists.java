package me.zerosio.items.itemtype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.zerosio.utility.Logging;
import me.zerosio.utility.ReflectionsUtil;

public class Lists {
    public static final Map<String, Class<? extends SItem>> items = new HashMap<>();

    public static void registerItems() {
        List<String> classes = ReflectionsUtil.getClasses("me.zerosio.items.itemlist");

        for (String className : classes) {
            try {
                Class<?> clazz = Class.forName(className);
                if (!SItem.class.isAssignableFrom(clazz)) continue;

                Class<? extends SItem> itemClass = (Class<? extends SItem>) clazz;
                SItem instance = ReflectionsUtil.getInstance(itemClass);
                if (instance == null) continue;

                String id = instance.getId().toUpperCase();
                items.put(id, itemClass);

                Logging.print("&b[iTEM] &eLoaded &d" + id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Logging.print("&b[iTEM] &dLoaded &e" + items.size() + " &dItems!");
    }

    public static SItem getItemClass(String id) {
        if (id == null || id.equalsIgnoreCase("null")) return null;
        id = id.replace("-", "_").toUpperCase();
        Class<? extends SItem> clazz = items.get(id);
        return clazz != null ? ReflectionsUtil.getInstance(clazz) : null;
    }
}
