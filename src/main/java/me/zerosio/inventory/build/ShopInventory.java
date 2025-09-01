//package me.zerosio.inventory.build;

//import me.zerosio.inventory.build.InventoryBuilder;
//import me.zerosio.inventory.build.ItemBuilder;
//import org.bukkit.ChatColor;
//import org.bukkit.Material;
//import org.bukkit.Sound;
//import org.bukkit.entity.Player;
//import org.bukkit.event.inventory.ClickType;
//import org.bukkit.event.inventory.InventoryClickEvent;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;

//import java.util.*;

//public abstract class SkyblockShopInventory extends InventoryBuilder {

//    protected final Player player;

//    private static final Map<UUID, Stack<ItemStack>> BUYBACK_HISTORY = new HashMap<>();
//    private static final Map<UUID, Long> DAILY_SELL_TOTAL = new HashMap<>();
//    private static final Map<UUID, Long> LAST_SELL_RESET = new HashMap<>();
//    private static final long SELL_LIMIT = 200_000_000L;

//    private ItemStack lastInteractedItem;

//    public SkyblockShopInventory(Player player, String title, int rows) {
//        super(title, rows);
//        this.player = player;

//        fillBorders();
//        addCloseButton();
//        addBuybackSlot();
//    }

//    private void fillBorders() {
//        ItemStack filler = new ItemBuilder(Material.STAINED_GLASS_PANE)
//                .setDurability(7)
//                .setName(" ")
//                .hideAllFlags()
//                .build();
//        for (int i = 0; i < 54; i++) {
//            if (i < 9 || i >= 45 || i % 9 == 0 || i % 9 == 8) {
//                setItem(i, filler);
//            }
//        }
//    }

//    private void addCloseButton() {
//        setGuiSlot(54, ItemBuilder.CLOSE_ITEM, e -> player.closeInventory());
//    }

//    private void addBuybackSlot() {
//        UUID uuid = player.getUniqueId();
//        Stack<ItemStack> buyback = BUYBACK_HISTORY.get(uuid);

//        if (buyback == null || buyback.isEmpty()) {
//            setGuiSlot(49, new ItemBuilder(Material.HOPPER)
//                    .setName("§aSell Item")
//                    .addLore("§7Click items in your inventory to", "§7sell them to this shop!")
//                    .hideAllFlags()
//                    .build(), e -> {});
//            return;
//        }

//        ItemStack last = buyback.peek().clone();
//        int cost = getSellPrice(last);

//        ItemMeta meta = last.getItemMeta();
//        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
//        lore.add("");
//        lore.add("§6Cost: §e" + cost + " Coins");
//        lore.add("");
//        lore.add("§eClick to buyback!");
//        meta.setLore(lore);
//        last.setItemMeta(meta);

//        setGuiSlot(49, last, e -> {
//            if (getCoins() < cost) {
//                player.sendMessage("§cYou don't have enough coins!");
//                return;
//            }

//            if (player.getInventory().firstEmpty() == -1) {
//                player.sendMessage("§cFree up inventory space!");
//                return;
//            }

//            player.getInventory().addItem(buyback.pop().clone());
//            subCoins(cost);

//            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1f, 2f);
//            newInstance().open(player);
//        });
//    }

//    private void resetDailyIfNeeded(UUID uuid) {
//        long now = System.currentTimeMillis();
//        if (!LAST_SELL_RESET.containsKey(uuid) || now - LAST_SELL_RESET.get(uuid) >= 86_400_000L) {
//            LAST_SELL_RESET.put(uuid, now);
//            DAILY_SELL_TOTAL.put(uuid, 0L);
//        }
//    }

//    private boolean canSell(UUID uuid, long value) {
//        resetDailyIfNeeded(uuid);
//        return DAILY_SELL_TOTAL.getOrDefault(uuid, 0L) + value <= SELL_LIMIT;
//    }

//    private void addToDailySell(UUID uuid, long value) {
//        resetDailyIfNeeded(uuid);
//        DAILY_SELL_TOTAL.put(uuid, DAILY_SELL_TOTAL.getOrDefault(uuid, 0L) + value);
//    }

//    public void handleSellFromInventory(InventoryClickEvent e) {
//        ItemStack clicked = e.getCurrentItem();
//        if (clicked == null || clicked.getType() == Material.AIR) return;

//        long value = getSellPrice(clicked);
//        UUID uuid = player.getUniqueId();

//        if (!canSell(uuid, value)) {
//            player.sendMessage("§cYou have reached the §6200M §cdaily sell limit for NPC shops!");
//            return;
//        }

//        BUYBACK_HISTORY.computeIfAbsent(uuid, u -> new Stack<>()).push(clicked.clone());
//        addToDailySell(uuid, value);
//        addCoins(value);

//        player.sendMessage("§aYou sold §ex" + clicked.getAmount() + " " +
//                ChatColor.stripColor(clicked.getItemMeta().getDisplayName()) +
//                " §afor §6" + value + " Coins§a!");
//        player.getInventory().setItem(e.getSlot(), null);
//        player.playSound(player.getLocation(), Sound.NOTE_PLING, 1f, 2f);
//        newInstance().open(player);
//    }

//    public void addShopItem(int guiSlot, ItemStack originalItem, int baseCost) {
//        ItemStack displayItem = originalItem.clone();
//        int amount = displayItem.getAmount();
//        ItemMeta meta = displayItem.getItemMeta();
//        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();

//        lore.add("§6Cost: §e" + (baseCost * amount) + " Coins");
//        lore.add("");
//        lore.add("§eClick to trade");
//        lore.add("§eRight-click for more trading options!");
//        meta.setLore(lore);
//        displayItem.setItemMeta(meta);

//        setGuiSlot(guiSlot, displayItem, e -> {
//            e.setCancelled(true);
//            lastInteractedItem = originalItem.clone();

//            if (e.getClick() == ClickType.RIGHT) {
//                openQuantitySelector(originalItem, baseCost);
//            } else {
//                handlePurchase(originalItem.getAmount(), baseCost);
//            }
//        });
//    }

//    private void openQuantitySelector(ItemStack baseItem, int baseCost) {
//        InventoryBuilder quantityGUI = new InventoryBuilder("§8Choose Quantity", 3).setCancelClicks(true).fill();
//        int[] amounts = {8, 16, 32, 64};

//        for (int i = 0; i < amounts.length; i++) {
//            int amt = amounts[i];
//            ItemStack item = baseItem.clone();
//            item.setAmount(amt);
//            ItemMeta meta = item.getItemMeta();
//            List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
//            lore.add("§6Cost: §e" + (amt * baseCost) + " Coins");
//            meta.setLore(lore);
//            item.setItemMeta(meta);

//            quantityGUI.setGuiSlot(11 + (i * 2), item, e -> {
//                e.setCancelled(true);
//                lastInteractedItem = baseItem.clone();
//                handlePurchase(amt, baseCost);
//                player.closeInventory();
//            });
//        }

//        quantityGUI.setGuiSlot(22, ItemBuilder.CLOSE_ITEM, e -> player.closeInventory());
//        quantityGUI.open(player);
//    }

//    protected void handlePurchase(int amount, int baseCost) {
//        int totalCost = amount * baseCost;

//        if (getCoins() < totalCost) {
//            player.sendMessage("§cYou don't have enough coins!");
//            player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
//            return;
//        }

//        ItemStack item = lastInteractedItem.clone();
//        item.setAmount(amount);

//        Map<Integer, ItemStack> leftovers = player.getInventory().addItem(item);
//        if (!leftovers.isEmpty()) {
//            player.sendMessage("§cNot enough inventory space!");
//            player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
//            return;
//        }

//        subCoins(totalCost);
//        player.sendMessage("§aYou bought §ex" + amount + " " +
//                ChatColor.stripColor(item.getItemMeta().getDisplayName()) +
//                " §afor §6" + totalCost + " Coins§a!");
//        player.playSound(player.getLocation(), Sound.NOTE_PLING, 1f, 2f);
//    }

//    private int getSellPrice(ItemStack item) {
//        // Replace this with your own logic, or add NBT tags later.
//        return 10 * item.getAmount(); // Placeholder price
//    }

//    protected abstract void addCoins(long amount);
//    protected abstract void subCoins(long amount);
//    protected abstract long getCoins();
//    protected abstract SkyblockShopInventory newInstance();
//}
package me.zerosio.inventory.build;

public class ShopInventory {
}