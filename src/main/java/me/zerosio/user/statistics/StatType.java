package me.zerosio.user.statistics;

public enum StatType {

    DAMAGE(false, "§c❁ Damage", "§c❁"),
    STRENGTH(false, "§c❁ Strength", "§c❁"),
    CRIT_CHANCE(false, "§9☣ Crit Chance", "§9☣", true),
    CRIT_DAMAGE(false, "§9☠ Crit Damage", "§9☠", true),
    BONUS_ATTACK_SPEED(false, "§e⚔ Bonus Attack Speed", "§e⚔", true),
    FEROCITY(false, "§c⫽ Ferocity", "§c⫽"),

    HEALTH(true, "§c❤ Health", "§c❤"),
    DEFENSE(true, "§a❈ Defense", "§a❈"),
    EFFECTIVE_HEALTH(true, "§c❤ Effective Health", "§c❤"),
    TRUE_DEFENSE(true, "§f❂ True Defense", "§f❂"),
    HEALTH_REGEN(true, "§c❣ Health Regen", "§c❣"),
    VITALITY(true, "§c❣ Vitality", "§c❣"),
    MENDING(true, "§c❣ Mending", "§c❣"),
    UNDEAD_DEFENSE(true, "§a❈ Undead Defense", "§a❈"),

    INTELLIGENCE(true, "§b✎ Intelligence", "§b✎"),
    ABILITY_DAMAGE(false, "§c๑ Ability Damage", "§c๑", true),

    MINING_SPEED(true, "§6⸕ Mining Speed", "§6⸕"),
    MINING_FORTUNE(true, "§6☘ Mining Fortune", "§6☘"),
    FARMING_FORTUNE(true, "§6☘ Farming Fortune", "§6☘"),
    FORAGING_FORTUNE(true, "§6☘ Foraging Fortune", "§6☘"),
    FISHING_SPEED(true, "§b☂ Fishing Speed", "§b☂"),
    PRISTINE(true, "§5✧ Pristine", "§5✧"),
    SWEEP(true, "§a∮ Sweep", "§a∮"),

    ALCHEMY_WISDOM(true, "§3☯ Alchemy Wisdom", "§3☯"),
    COMBAT_WISDOM(true, "§3☯ Combat Wisdom", "§3☯"),
    ENCHANTING_WISDOM(true, "§3☯ Enchanting Wisdom", "§3☯"),
    FARMING_WISDOM(true, "§3☯ Farming Wisdom", "§3☯"),
    FISHING_WISDOM(true, "§3☯ Fishing Wisdom", "§3☯"),
    FORAGING_WISDOM(true, "§3☯ Foraging Wisdom", "§3☯"),
    MINING_WISDOM(true, "§3☯ Mining Wisdom", "§3☯"),
    SLAYER_WISDOM(true, "§3☯ Slayer Wisdom", "§3☯"),

    SPEED(true, "§f✦ Speed", "§f✦"),
    MAGIC_FIND(true, "§b✯ Magic Find", "§b✯"),
    PET_LUCK(true, "§d♣ Pet Luck", "§d♣"),
    SEA_CREATURE_CHANCE(false, "§3α Sea Creature Chance", "§3α", true),
    ATTUNEMENT(true, "§5♨ Attunement", "§5♨"),
    SWING_RANGE(true, "§e↔ Swing Range", "§e↔"),

    RIFT_TIME(true, "§dф Rift Time", "§dф"),

    HEALTH_PERCENT_REGEN(true, "§c❣ Health Percent Regeneration", "§c❣", true);

    final boolean passive;
    final String formattedName;
    final String symbol;
    final boolean percent;

    StatType(boolean passive, String formattedName, String symbol) {
        this.passive = passive;
        this.formattedName = formattedName;
        this.symbol = symbol;
        this.percent = false;
    }

    StatType(boolean passive, String formattedName, String symbol, boolean percent) {
        this.passive = passive;
        this.formattedName = formattedName;
        this.symbol = symbol;
        this.percent = percent;
    }

    public boolean isPassive() {
        return passive;
    }

    public String getFormattedName() {
        return this.formattedName;
    }

    public String getStatSymbol() {
        return symbol;
    }

    public boolean isPercent() {
        return percent;
    }
}