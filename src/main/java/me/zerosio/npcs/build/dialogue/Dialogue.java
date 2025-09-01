package me.zerosio.npcs.build.dialogue;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import me.zerosio.Core;

import java.util.*;

public class Dialogue {
    private static final Set<UUID> activePlayers = new HashSet<>();

    private final List<DialogueMessage> messages = new ArrayList<>();
    private VoiceType voiceType = VoiceType.MALE;

    public Dialogue() {}

    public Dialogue voice(VoiceType voiceType) {
        this.voiceType = voiceType;
        return this;
    }

    public Dialogue addMessage(String text, int delayTicks) {
        return addMessage(text, delayTicks, (Player p) -> {});
    }

    public Dialogue addMessage(String text, int delayTicks, Runnable after) {
        return addMessage(text, delayTicks, after != null ? p -> after.run() : null);
    }

    public Dialogue addMessage(String text, int delayTicks, java.util.function.Consumer<Player> after) {
        messages.add(new DialogueMessage(text, delayTicks, after));
        return this;
    }

    public void start(Player player) {
        if (activePlayers.contains(player.getUniqueId())) return;
        activePlayers.add(player.getUniqueId());
        runNext(player, 0);
    }

    private void runNext(Player player, int index) {
        if (index >= messages.size()) {
            activePlayers.remove(player.getUniqueId());
            return;
        }

        DialogueMessage current = messages.get(index);
        Bukkit.getScheduler().runTaskLater(Core.getInstance(), () -> {
            player.sendMessage(current.getMessage());
            player.playSound(player.getLocation(), Sound.VILLAGER_YES, 10, voiceType.getPitch());
            current.execute(player);
            runNext(player, index + 1);
        }, current.getDelayTicks());
    }

    public static boolean isInDialogue(Player player) {
        return activePlayers.contains(player.getUniqueId());
    }
}
