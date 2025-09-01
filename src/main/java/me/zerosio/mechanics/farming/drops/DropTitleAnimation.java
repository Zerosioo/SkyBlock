package me.zerosio.mechanics.farming.drops;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class DropTitleAnimation extends BukkitRunnable {

    private final Player player;
    private final String itemName;
    private final int amount;
    private final String[] titleColors;
    private final String subTitle;

    private int tick = 0;

    public DropTitleAnimation(Player player, DropRarity rarity, String itemName, int amount) {
        this.player = player;
        this.itemName = itemName;
        this.amount = amount;

        this.subTitle = "§a" + amount + "x " + itemName;

        this.titleColors = new String[] {
                "§l" + rarity.getColor() + rarity.getTitle(),
                "§f§l" + rarity.getTitle()
        };

        // Send timing only once
        TitleUtil.sendTimings(player, 5, 60, 5);
    }

    @Override
    public void run() {
        if (tick >= 40) { // 3s animation (6 * 10 ticks)
            cancel();
            return;
        }

        int frame = (tick / 10) % titleColors.length;
        String title = titleColors[frame];

        TitleUtil.sendTitle(player, title, subTitle);
        tick += 10;
    }
}
