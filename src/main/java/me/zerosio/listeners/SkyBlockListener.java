package me.zerosio.listeners;

import me.zerosio.Core;
import me.zerosio.chat.ClickActionType;
import me.zerosio.chat.MessageUtil;
import me.zerosio.garden.GardenAlgorithm;
import me.zerosio.instance.InstanceID;
import me.zerosio.instance.InstanceType;
import me.zerosio.instance.functions.HubInstance;
import me.zerosio.items.itemtype.ItemStatistics;
import me.zerosio.items.itemtype.SItem;
import me.zerosio.kuudra.tentacles.Tentacle;
import me.zerosio.schematic.devworld.DevWorld;
import me.zerosio.user.AdminDebug;
import me.zerosio.user.User;
import me.zerosio.user.statistics.StatManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class SkyBlockListener implements Listener {

	@EventHandler
	public void onWorldLoad(WorldLoadEvent event) {
		World world = event.getWorld();
		InstanceID.register(world);
		System.out.println("[SkyBlock] Registered world instance: " + world.getName());
	}

	@EventHandler
	public void onWorldInit(WorldInitEvent event) {
		World world = event.getWorld();
		InstanceID.register(world);
		System.out.println("[SkyBlock] Registered world instance: " + world.getName());
	}

	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent event) {
		Player player = event.getPlayer();
		User user = User.getUser(player);
		World world = player.getWorld();

		if (InstanceType.isInsideInstance(world, InstanceType.HUB)) {
			HubInstance.start();
		}

		player.sendMessage("");
		player.sendMessage("§aYou are playing on profile: §e" + user.getProfileData("name"));
		MessageUtil.sendClickableMessage(player, "§8Profile ID: " + user.getActiveProfileId(),
										 ClickActionType.COPY_TO_CLIPBOARD, user.getActiveProfileId().toString(),
										 Arrays.asList("§8Click to copy to clipboard!"));
		AdminDebug.debug(player, "You are currently in " + InstanceType.getInstance(world).name().replace("_", " ") + " instance.");

		if (DevWorld.isInDevWorld(player) && !player.getWorld().getName().startsWith("dev_")) {
			DevWorld.handleLeave(player);
		}

		new BukkitRunnable() {
			@Override
			public void run() {
				GardenAlgorithm.unloadGardenIfEmpty(event.getPlayer().getUniqueId());
			}
		} .runTaskLater(Core.getInstance(), 60L);
	}

	@EventHandler
	public void onItemHeld(PlayerItemHeldEvent event) {
		Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class),
										   () -> StatManager.recalculate(event.getPlayer()), 1L);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player)) return;

		int slot = event.getSlot();
		if (slot >= 36 && slot <= 39) {
			Player player = (Player) event.getWhoClicked();
			Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class),
											   () -> StatManager.recalculate(player), 1L);
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		if (item == null) return;

		Material type = item.getType();
		if (type.name().contains("HELMET") || type.name().contains("CHESTPLATE") ||
				type.name().contains("LEGGINGS") || type.name().contains("BOOTS")) {
			Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class),
											   () -> StatManager.recalculate(player), 1L);
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		StatManager.recalculate(event.getPlayer());
	}

	@EventHandler
	public void onLeabBreh(PlayerQuitEvent e) {
		new BukkitRunnable() {
			@Override
			public void run() {
				GardenAlgorithm.unloadGardenIfEmpty(e.getPlayer().getUniqueId());
			}
		} .runTaskLater(Core.getInstance(), 60L);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block originBlock = event.getBlock();
		Material type = originBlock.getType();

		if (type != Material.LOG) return;

		ItemStack itemStack = player.getItemInHand();
		SItem sItem = SItem.getItem(itemStack);
		if (!(sItem instanceof ItemStatistics)) return;

		int sweep = ((ItemStatistics) sItem).getSweep();
		if (sweep <= 0) return;

		event.setCancelled(true); // cancel default, we will break manually

		Set<Location> visited = new HashSet<>();
		Queue<Location> queue = new LinkedList<>();

		queue.add(originBlock.getLocation());
		visited.add(originBlock.getLocation());

		new BukkitRunnable() {
			int broken = 0;

			@Override
			public void run() {
				int brokenThisTick = 0;

				while (!queue.isEmpty() && broken < sweep && brokenThisTick < 5) {
					Location loc = queue.poll();
					Block block = loc.getBlock();

					if (block.getType() == Material.LOG) {
						block.breakNaturally(itemStack);
						broken++;
						brokenThisTick++;

						// Add 6 face-connected neighbors
						for (int dx = -1; dx <= 1; dx++) {
							for (int dy = -1; dy <= 1; dy++) {
								for (int dz = -1; dz <= 1; dz++) {
									if (Math.abs(dx) + Math.abs(dy) + Math.abs(dz) != 1) continue;

									Location neighbor = loc.clone().add(dx, dy, dz);
									if (!visited.contains(neighbor) && neighbor.getBlock().getType() == Material.LOG) {
										queue.add(neighbor);
										visited.add(neighbor);
									}
								}
							}
						}
					}
				}

				if (broken >= sweep || queue.isEmpty()) cancel();
			}
		} .runTaskTimer(Core.getPlugin(Core.class), 0L, 2L);

		// For hub protection
		if (InstanceType.isInsideInstance(player.getWorld(), InstanceType.HUB)) {
			if (type == Material.WHEAT || type == Material.POTATO || type == Material.CROPS || type == Material.LOG) {
				return;
			}
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void hungerEvent(FoodLevelChangeEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (e.getFoodLevel() < 15) {
				p.setFoodLevel(20);
			}
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void onBlockBurn(BlockBurnEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onBlockRedstone(BlockRedstoneEvent e) {
		e.setNewCurrent(0);
	}

	@EventHandler
	public void onBlockDecay(LeavesDecayEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onBlockPhysics(BlockPhysicsEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onEntityDamoog(EntityDamageEvent event) {
		if (event.getEntity() instanceof MagmaCube) {
			MagmaCube cube = (MagmaCube) event.getEntity();

			if (Tentacle.hasTag(cube, "TentacleSegment", "true")) {
				event.setCancelled(true);
			}
		}
	}
}
