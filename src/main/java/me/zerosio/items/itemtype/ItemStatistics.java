package me.zerosio.items.itemtype;

import me.zerosio.user.statistics.StatType;

public interface ItemStatistics {

    default int getGearScore() {
        return 0;
    }
    
    default int getBreakingPower() {
    	return 0;
    }

    // Core Combat Stats
    default int getDamage() { return 0; }
    default int getStrength() { return 0; }
    default int getCritChance() { return 0; }
    default int getCritDamage() { return 0; }
    default int getFerocity() { return 0; }
    default int getAttackSpeed() { return 0; }

    // Survivability Stats
    default int getHealth() { return 0; }
    default int getDefense() { return 0; }
    default int getTrueDefense() { return 0; }
    default int getHealthRegeneration() { return 0; }
    default int getVitality() { return 0; }

    // Magic & Utility
    default int getIntelligence() { return 0; }
    default double getAbilityDamage() { return 0; }
    default int getSwingRange() { return 0; }

    // Movement & Luck
    default int getSpeed() { return 0; }
    default int getMagicFind() { return 0; }
    default int getPetLuck() { return 0; }

    // Gathering Stats
    default int getFarmingFortune() { return 0; }
    default int getMiningFortune() { return 0; }
    default int getForagingFortune() { return 0; }
    default int getFishingFortune() { return 0; }
    default int getMiningSpeed() { return 0; }
    default int getFishingSpeed() { return 0; }
    default int getPristine() { return 0; }
    default int getSweep() { return 0; }

    // Fishing & Sea
    default int getSeaCreatureChance() { return 0; }

    // Wisdoms
    default int getCombatWisdom() { return 0; }
    default int getMiningWisdom() { return 0; }
    default int getFarmingWisdom() { return 0; }
    default int getForagingWisdom() { return 0; }
    default int getFishingWisdom() { return 0; }
    default int getEnchantingWisdom() { return 0; }
    default int getAlchemyWisdom() { return 0; }
    default int getCarpentryWisdom() { return 0; }
    default int getRunecraftingWisdom() { return 0; }
    default int getSocialWisdom() { return 0; }
    default int getTamingWisdom() { return 0; }

    // Resistance Stats
    default int getFireResistance() { return 0; }
    default int getFallResistance() { return 0; }
    default int getExplosionResistance() { return 0; }
    default int getProjectileResistance() { return 0; }

    // Rift Stats
    default int getRiftMana() { return 0; }
    default int getRiftManaRegeneration() { return 0; }
    default int getRiftTime() { return 0; }

    // Extra
    default int getEffectiveHealth() { return 0; }
    default int getMending() { return 0; }
    default int getUndeadDefense() { return 0; }
    default int getSlayerWisdom() { return 0; }
    default int getAttunement() { return 0; }
    default int getHealthPercentRegeneration() { return 0; }

    default int get(StatType type) {
        switch (type) {
            case DAMAGE: return getDamage();
            case STRENGTH: return getStrength();
            case CRIT_CHANCE: return getCritChance();
            case CRIT_DAMAGE: return getCritDamage();
            case BONUS_ATTACK_SPEED: return getAttackSpeed();
            case FEROCITY: return getFerocity();

            case HEALTH: return getHealth();
            case DEFENSE: return getDefense();
            case TRUE_DEFENSE: return getTrueDefense();
            case HEALTH_REGEN: return getHealthRegeneration();
            case VITALITY: return getVitality();
            case EFFECTIVE_HEALTH: return getEffectiveHealth();
            case MENDING: return getMending();
            case UNDEAD_DEFENSE: return getUndeadDefense();

            case INTELLIGENCE: return getIntelligence();
            case ABILITY_DAMAGE: return (int) getAbilityDamage();
            case SWING_RANGE: return getSwingRange();

            case SPEED: return getSpeed();
            case MAGIC_FIND: return getMagicFind();
            case PET_LUCK: return getPetLuck();

            case FARMING_FORTUNE: return getFarmingFortune();
            case MINING_FORTUNE: return getMiningFortune();
            case FORAGING_FORTUNE: return getForagingFortune();
            case MINING_SPEED: return getMiningSpeed();
            case FISHING_SPEED: return getFishingSpeed();
            case PRISTINE: return getPristine();
            case SWEEP: return getSweep();

            case SEA_CREATURE_CHANCE: return getSeaCreatureChance();

            case COMBAT_WISDOM: return getCombatWisdom();
            case MINING_WISDOM: return getMiningWisdom();
            case FARMING_WISDOM: return getFarmingWisdom();
            case FORAGING_WISDOM: return getForagingWisdom();
            case FISHING_WISDOM: return getFishingWisdom();
            case ENCHANTING_WISDOM: return getEnchantingWisdom();
            case ALCHEMY_WISDOM: return getAlchemyWisdom();
            case SLAYER_WISDOM: return getSlayerWisdom();

            case ATTUNEMENT: return getAttunement();
            case RIFT_TIME: return getRiftTime();
            case HEALTH_PERCENT_REGEN: return getHealthPercentRegeneration();

            default: return 0;
        }
    }
}
