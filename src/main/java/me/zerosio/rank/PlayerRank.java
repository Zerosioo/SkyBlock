package me.zerosio.rank;

public enum PlayerRank {

    DEFAULT("§7", ""),
    YOUTUBE("§c", "§fYOUTUBE"),
    BETA_TESTER("§d", "BT"),
    STAFF("§9", "STAFF"),
    MOD("§2", "MOD"),
    ADMIN("§c", "ADMIN"),
    DEV("§3", "DEV"),
    OWNER("§c", "OWNER");

    private final String prefix;
    private final String color;

    PlayerRank(String color, String prefix) {
        this.color = color;
        this.prefix = prefix;
    }

    public static PlayerRank getRankOrDefault(int level) {
        for (PlayerRank rank : PlayerRank.values()) {
            if (rank.getLevel() == level) {
                return rank;
            }
        }
        return DEFAULT;
    }

    public int getLevel() {
        return this.ordinal() + 1; 
    }

    public String getScoreRank() {
        return this == DEFAULT ? "§7Default" : getPrefixColoured();
    }

    public String getPrefix() {
        return this == DEFAULT ? color : color + "[" + prefix + color + "] ";
    }

    public String getColour() {
        return color;
    }

    public String getPrefixx() {
        return prefix;
    }

    public String getPrefixColoured() {
        return color + prefix;
    }

    public boolean isBelowOrEqual(PlayerRank rank) {
        return this.getLevel() <= rank.getLevel();
    }

    public boolean isAboveOrEqual(PlayerRank rank) {
        return this.getLevel() >= rank.getLevel();
    }

    public boolean hasRank(PlayerRank requiredRank) {
        return this.getLevel() >= requiredRank.getLevel();
    }

    public boolean isStaff() {
        return this.ordinal() >= STAFF.ordinal();
    }

    public boolean isDefaultPermission() {
        return this == DEFAULT;
    }

    public String getFormattedRank() {
        return prefix;
    }
}
