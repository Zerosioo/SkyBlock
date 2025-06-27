package dev.zerosio.rank;

public enum PlayerRank {

    DEFAULT("§7", "", 1),
    VIP("§a", "VIP", 2),
    VIPPLUS("§a", "VIP§c+", 3),
    MVP("§b", "MVP", 4),
    MVPPLUS("§b", "MVP§c+", 5),
    MVPPLUSPLUS("§6", "MVP§c++", 6),
    YOUTUBE("§c", "§fYOUTUBE", 7),
    HELPER("§9", "HELPER", 8),
    MOD("§2", "MOD", 9),
    GAMEMASTER("§2", "GM", 10),
    BETATESTER("§d", "BETATEST", 11),
    ADMIN("§c", "ADMIN", 12),
    COOWNER("§c", "COOWNER", 13),
    JERRY("§d", "JERRY§c++", 14),
    OWNER("§c", "OWNER", 15);


    private final String prefix;
    private final String color;
    private final int level;

    PlayerRank(String color,String prefix, int level) {
        this.color = color;
        this.prefix = prefix;
        this.level = level;
    }

    public static PlayerRank getRankOrDefault(int level) {
        for (PlayerRank rank : PlayerRank.values()) {
            if (rank.level == level) {
                return rank;
            }
        }
        return DEFAULT;
    }
    
    public String getScoreRank() {
    	if (this == DEFAULT) {
    		return "§7Default";
    	} else {
    		return getPrefixColoured();
    	}
    }

    public String getPrefix() {
        if (this == DEFAULT) {
            return color; 
        } else {
            return color + "[" + prefix + color + "] ";
        }
    }

    public int getLevel() {
        return level;
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
        return this.level <= rank.level;
    }

    public boolean isAboveOrEqual(PlayerRank rank) {
        return this.level >= rank.level;
    }

    public boolean hasRank(PlayerRank requiredRank) {
        return this.level >= requiredRank.level;
    }

    public boolean isStaff() {
        return this.level >= HELPER.level;
    }

    public boolean isDefaultPermission() {
        return this == DEFAULT;
    }

    public String getFormattedRank() {
        return prefix;
    }


}