package me.zerosio.npcs.build.dialogue;

import org.bukkit.entity.Player;
import java.util.function.Consumer;

public class DialogueMessage {
    private final String message;
    private final int delayTicks;
    private final Consumer<Player> afterAction;

    public DialogueMessage(String message, int delayTicks, Consumer<Player> afterAction) {
        this.message = message;
        this.delayTicks = delayTicks;
        this.afterAction = afterAction;
    }

    public String getMessage() {
        return message;
    }

    public int getDelayTicks() {
        return delayTicks;
    }

    public void execute(Player player) {
        if (afterAction != null) afterAction.accept(player);
    }
}
