package me.zerosio.npcs.build;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.Set;

public class NPCInteractListener implements Listener {
    private static final Set<AbstractNPC> npcs = new HashSet<>();

    public static void register(AbstractNPC npc) {
        npcs.add(npc);
    }

    @EventHandler
    public void onRightClick(NPCRightClickEvent event) {
        for (AbstractNPC npc : npcs) {
            if (npc.getNPC().getId() == event.getNPC().getId()) {
                npc.handleInteract(event.getClicker());
            }
        }
    }
}
