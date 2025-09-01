package me.zerosio.guilds.database;

import java.util.function.BiPredicate;

public class Filters {

    public static Filter eq(String key, Object value) {
        return (id, db) -> {
            Object found = db.get(key, null);
            return found != null && found.toString().equalsIgnoreCase(value.toString());
        };
    }

    public static Filter notEq(String key, Object value) {
        return (id, db) -> {
            Object found = db.get(key, null);
            return found == null || !found.toString().equalsIgnoreCase(value.toString());
        };
    }

    public static Filter exists(String key) {
        return (id, db) -> db.get(key, null) != null;
    }

    public static Filter gt(String key, Number value) {
        return (id, db) -> {
            Object found = db.get(key, null);
            if (found instanceof Number) {
                return ((Number) found).doubleValue() > value.doubleValue();
            }
            return false;
        };
    }

    public static Filter lt(String key, Number value) {
        return (id, db) -> {
            Object found = db.get(key, null);
            if (found instanceof Number) {
                return ((Number) found).doubleValue() < value.doubleValue();
            }
            return false;
        };
    }

    public static Filter and(Filter... filters) {
        return (id, db) -> {
            for (Filter filter : filters) {
                if (!filter.apply(id, db)) return false;
            }
            return true;
        };
    }

    public static Filter or(Filter... filters) {
        return (id, db) -> {
            for (Filter filter : filters) {
                if (filter.apply(id, db)) return true;
            }
            return false;
        };
    }

    public interface Filter {
        boolean apply(String id, GuildDatabase db);
    }
}
