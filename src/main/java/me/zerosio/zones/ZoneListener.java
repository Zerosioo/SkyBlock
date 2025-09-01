package me.zerosio.zones;

import me.zerosio.user.User;
import me.zerosio.utility.EventUtil;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ZoneListener {

    private final ZoneManager zoneManager = new ZoneManager();

    public ZoneListener() {
        EventUtil.onEvent(PlayerMoveEvent.class, event -> {
            if (!event.getFrom().getBlock().equals(event.getTo().getBlock())) {
                User user = User.getUser(event.getPlayer());
                zoneManager.updatePlayerLocation(event.getPlayer(), user);
            }
        });
        
        EventUtil.onEvent(PlayerJoinEvent.class, event -> {
                User user = User.getUser(event.getPlayer());
                zoneManager.updatePlayerLocation(event.getPlayer(), user);
            
        });
    }
}
