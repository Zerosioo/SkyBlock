package me.zerosio.mechanics.farming.drops;

public enum DropRarity {
    UNCOMMON("UNCOMMON DROP", "§a§l"),
    RARE("RARE DROP", "§9§l"),
    CRAZY_RARE("CRAZY RARE DROP", "§d"),
    LEGENDARY("LEGENDARY DROP", "§6§l"),
    PRAY_TO_RNGESUS("PRAY TO RNGESUS", "§5§l");

    private final String title;
    private final String color;

    DropRarity(String title, String color) {
        this.title = title;
        this.color = color;
    }

    public String getTitle() { return title; }
    public String getColor() { return color; }
}
