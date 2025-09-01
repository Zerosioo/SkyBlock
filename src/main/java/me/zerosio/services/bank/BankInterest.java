package me.zerosio.services.bank;

import me.zerosio.user.User;
import me.zerosio.user.profiledata.UserEconomy;
import me.zerosio.utility.RunnableUtil;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class BankInterest {

    private static final long CHECK_INTERVAL_TICKS = 20 * 5;
    private static final long INTEREST_COOLDOWN_MS = 111600000;

    public static void startMonitoring(Player player) {
        RunnableUtil.runRepeating(() -> {
            User user = User.getUser(player);
            long lastInterest = (long) user.getProfileData("bank.last_interest");
            long now = System.currentTimeMillis();

            if (now - lastInterest >= INTEREST_COOLDOWN_MS) {
                double balance = UserEconomy.getBankCoins(player);
                String tierName = user.getProfileData("bank.tier").toString();
                BankTier tier = BankTier.fromString(tierName);

                double interest = balance * tier.getInterestRate();

                if (interest > tier.getInterestCap()) {
                    interest = tier.getInterestCap();
                }

                UserEconomy.addBankCoins(player, interest);
                user.setProfileData("bank.last_interest", now);

                player.sendMessage("§aYou received §6" + format(interest) + " coins §aas bank interest! (Tier: " + tier.name() + ")");
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
            }

        }, 0L, CHECK_INTERVAL_TICKS);
    }

    private static String format(double value) {
        return String.format("%,.2f", value);
    }
}
