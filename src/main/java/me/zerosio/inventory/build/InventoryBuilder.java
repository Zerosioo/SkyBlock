package me.zerosio.inventory.build;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import me.zerosio.Core;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class InventoryBuilder implements Listener {

    private final Inventory inventory;
    private final Map<Integer, Consumer<InventoryClickEvent>> clickHandlers = new HashMap<>();
    private final Plugin plugin = Core.getInstance();
    private boolean cancelClicks = true;

    public InventoryBuilder(String title, int rows) {
        this.inventory = Bukkit.createInventory(null, rows * 9, title);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public InventoryBuilder setItem(int slot, ItemStack item, Consumer<InventoryClickEvent> onClick) {
        inventory.setItem(slot, item);
        clickHandlers.put(slot, onClick);
        return this;
    }

    public InventoryBuilder setItem(int slot, ItemStack item) {
        return setItem(slot, item, e -> {});
    }

    public InventoryBuilder setGuiSlot(int guiSlot, ItemStack item, Consumer<InventoryClickEvent> onClick) {
        return setItem(guiSlot - 1, item, onClick);
    }

    public InventoryBuilder setGuiSlot(int guiSlot, ItemStack item) {
        return setItem(guiSlot - 1, item);
    }

    public InventoryBuilder fill() {
        ItemStack item = new ItemBuilder(Material.STAINED_GLASS_PANE)
		         .setDurability((short) 15)
				 .setName("§8")
				 .hideAllFlags()
				 .build();
				 
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null)
                setItem(i, item);
        }
        return this;
    }

    public InventoryBuilder setCancelClicks(boolean cancel) {
        this.cancelClicks = cancel;
        return this;
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getInventory().equals(inventory)) return;
        if (cancelClicks) event.setCancelled(true);
        int slot = event.getRawSlot();
        if (clickHandlers.containsKey(slot)) {
            clickHandlers.get(slot).accept(event);
        }
    }
}
