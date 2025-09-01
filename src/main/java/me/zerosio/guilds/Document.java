package me.zerosio.guilds;

import java.util.*;

public class Document {
    private final Map<String, Object> data = new HashMap<>();

    public Document() {}

    public Document(String key, Object value) {
        data.put(key, value);
    }

    public Document append(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public Object get(String key) {
        return data.get(key);
    }

    public String getString(String key) {
        Object value = data.get(key);
        return value != null ? value.toString() : null;
    }

    public int getInt(String key) {
        Object value = data.get(key);
        if (value instanceof Number) return ((Number) value).intValue();
        try {
            return Integer.parseInt(value.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public long getLong(String key) {
        Object value = data.get(key);
        if (value instanceof Number) return ((Number) value).longValue();
        try {
            return Long.parseLong(value.toString());
        } catch (Exception e) {
            return 0L;
        }
    }

    public boolean getBoolean(String key) {
        Object value = data.get(key);
        if (value instanceof Boolean) return (Boolean) value;
        return Boolean.parseBoolean(value.toString());
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(String key, Class<T> type) {
        Object value = data.get(key);
        if (value instanceof List<?>) {
            List<?> raw = (List<?>) value;
            List<T> typed = new ArrayList<>();
            for (Object o : raw) {
                if (type.isInstance(o)) {
                    typed.add(type.cast(o));
                }
            }
            return typed;
        }
        return new ArrayList<>();
    }

    public boolean containsKey(String key) {
        return data.containsKey(key);
    }

    public Set<String> keySet() {
        return data.keySet();
    }

    public Map<String, Object> toMap() {
        return new HashMap<>(data);
    }

    public static Document fromMap(Map<String, Object> map) {
        Document doc = new Document();
        doc.data.putAll(map);
        return doc;
    }

    public String toString() {
        return data.toString();
    }
}
