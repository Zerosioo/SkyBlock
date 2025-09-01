package me.zerosio.services.bank;

public enum BankTier {
    NONE(0.02, 250_000, 50_000_000),
    GOLD(0.025, 500_000, 100_000_000),
    PLATINUM(0.03, 750_000, 250_000_000),
    EMERALD(0.035, 1_000_000, 1_000_000_000);

    private final double interestRate;
    private final double interestCap;
    private final double balanceCap;

    BankTier(double interestRate, double interestCap, double balanceCap) {
        this.interestRate = interestRate;
        this.interestCap = interestCap;
        this.balanceCap = balanceCap;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getInterestCap() {
        return interestCap;
    }

    public double getBalanceCap() {
        return balanceCap;
    }

    public static BankTier fromString(String name) {
        try {
            return BankTier.valueOf(name.toUpperCase());
        } catch (Exception e) {
            return NONE;
        }
    }
}
