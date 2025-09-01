package me.zerosio.zones;

public enum ZoneTypes {
    BARN("§bThe Barn", new Point(200, -295), new Point(220, -222)),
    DESERT_SETTLEMENT("§eDesert Settlement", new Point(117, -404), new Point(192, -336)),
    MUSHROOM_DESERT("§eMushroom Desert", new Point(160, -620), new Point(420, -280)),
    OASIS("§bOasis", new Point(70, -610), new Point(210, -425)),
    MUSHROM_GORGE("§bMushroom Gorge", new Point(160, -620, 0), new Point(420, -280, 70)),

    COLLISEUM("§bColliseum", new Point(98, -80), new Point(155, -19)),
    ELECTION_ROOM("§bElection Room", new Point(-8, -106, 50), new Point(19, -133, 30)),
    COMMUNITY_CENTER("§bCommunity Center", new Point(-7, -114), new Point(16, -96)),
    BAZAAR("§eBazaar Allay", new Point(-42, -79), new Point(-25, -73)),
    AUCTION_HOUSE("§6Auction House", new Point(-57, -100), new Point(-18, -80)),
    BANK("§6Bank", new Point(-31, -70), new Point(-19, -48)),
    FARM("§bFarm", new Point(18, -190), new Point(93, -100)),
    COAL_MINE("§bCoal Mine", new Point(-40, -235, 55), new Point(-5, -140, 90)),

    VILLAGE("§bVillage", new Point(-80, -155), new Point(80, 15)),

    GRAVEYARD("§cGraveyard", new Point(-215, -200), new Point(-66, -80)),

    PARK("§aPark", new Point(-225, -80), new Point(-96, 20)),
    BIRCH_PARK("§aBirch Park", new Point(-316, -85, 0), new Point(-269, 50, 104)),
    SPRUCE_WOODS("§aSpruce Woods", new Point(-430, -31, 87), new Point(-317, 87, 123)),
    DARK_THICKET("§aDark Thicket", new Point(-386, -123, 100), new Point(-311, -6, 124)),
    SAVANNA_WOODLANDS("§aSavanna Woodlands", new Point(-462, -53, 106), new Point(-445, -33, 151)),
    JUNGLE_ISLAND("§aJungle Island", new Point(-482, -141, 117), new Point(-360, -30, 162)),

    MOUNTAIN("§bMountain", new Point(-60, 0), new Point(40, 70)),

    SPIDER_DEN("§bSpider's Den", new Point(-400, -372), new Point(-126, -125)),
    NETHER("§cNether Fortress", new Point(-443, -760), new Point(-175, -372)),
    END("§dThe End", new Point(-810, -450), new Point(-430, -100)),
    DRAGON_NEST("§dDragon Nest", new Point(-740, -330, 0), new Point(-611, -222, 100)),

    DEEP_CAVERNS("§bDeep Caverns", new Point(-108, -650), new Point(85, -476)),
    GUNPOWDER("§bGunpowder Mines", new Point(-108, -650, 136), new Point(85, -476, 178)),
    LAPIS("§bLapis Quarry", new Point(-108, -650, 112), new Point(85, -476, 136)),
    PIGMEN("§bPigmen's Den", new Point(-108, -650, 78), new Point(85, -476, 112)),
    SLIMEHILL("§bSlimehill", new Point(-108, -650, 46), new Point(85, -476, 78)),
    DIAMOND("§bDiamond Reserve", new Point(-108, -650, 19), new Point(85, -476, 46)),
    OBSIDIAN("§bObsidian Sanctuary", new Point(-108, -650, 0), new Point(85, -476, 19)),

    CASTLE("§bRuins", new Point(-281, 27, 50), new Point(-190, 131, 255)),

    NONE("§7None", new Point(0, 0), new Point(0, 0));

    public final String name;
    private final Point min;
    private final Point max;

    ZoneTypes(String name, Point min, Point max) {
        this.name = name;
        this.min = min;
        this.max = max;
    }

    public String getName() {
        return name != null ? name : "Unknown Location";
    }

    public boolean isInside(int x, int y, int z) {
        boolean insideXZ = x >= Math.min(min.x, max.x) && x <= Math.max(min.x, max.x)
                        && z >= Math.min(min.z, max.z) && z <= Math.max(min.z, max.z);

        if (!insideXZ) return false;

        if (min.y == null || max.y == null) {
            return true;
        }

        return y >= Math.min(min.y, max.y) && y <= Math.max(min.y, max.y);
    }

    public static ZoneTypes getLocation(int x, int y, int z) {
        for (ZoneTypes zone : values()) {
            if (zone != NONE && zone.isInside(x, y, z)) {
                return zone;
            }
        }
        return NONE;
    }

    public static class Point {
        final int x, z;
        final Integer y; 

        public Point(int x, int z) {
            this(x, z, null);
        }

        public Point(int x, int z, Integer y) {
            this.x = x;
            this.z = z;
            this.y = y;
        }
    }
}
