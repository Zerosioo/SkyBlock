package me.zerosio.items.itemtype;

public enum Rarity {
	COMMON("§f"), UNCOMMON("§a"), RARE("§9"), EPIC("§5"), LEGENDARY("§6"), MYTHIC("§d"), DIVINE("§b"),SPECIAL("§c"), VERY_SPECIAL("§c"), UNOBTAINABLE("§b"), NONE("§9");
private String color;
private static Rarity[] values = values();
public Rarity next()
{
    return values[(this.ordinal()+1) % values.length];
}
public Rarity previous() {
	int c = this.ordinal()-1;
	if(c == -1) {
		return values()[values().length - 1];
	}
	return values()[c];
}
Rarity(String string) {
	color = string;
}
public String getColor() {
	return color;
}
public static Rarity getByInt(int rarity) {
	return values[rarity];
}
}
