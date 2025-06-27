package dev.zerosio.chat;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MessageUtil {

    public static void sendClickableMessageWithoutHover(Player player, String text, ClickActionType actionType, String actionData) {
        sendClickableMessage(player, text, actionType, actionData, null);
    }

    public static void sendClickableMessage(Player player, String text, ClickActionType actionType, String actionData, List<String> hoverLines) {
        TextComponent component = new TextComponent(ChatColor.translateAlternateColorCodes('&', text));

        if (actionType != ClickActionType.NONE) {
            ClickEvent.Action action;
            switch (actionType) {
                case SUGGEST_COMMAND:
                    action = ClickEvent.Action.SUGGEST_COMMAND;
                    break;
                case OPEN_URL:
                    action = ClickEvent.Action.OPEN_URL;
                    break;
                case COPY_TO_CLIPBOARD:
                    try {
                        action = ClickEvent.Action.valueOf("COPY_TO_CLIPBOARD");
                    } catch (IllegalArgumentException e) {
                        action = ClickEvent.Action.SUGGEST_COMMAND;
                    }
                    break;
                case RUN_COMMAND:
                default:
                    action = ClickEvent.Action.RUN_COMMAND;
                    break;
            }
            component.setClickEvent(new ClickEvent(action, actionData));
        }

        if (hoverLines != null && !hoverLines.isEmpty()) {
            StringBuilder hoverBuilder = new StringBuilder();
            for (String line : hoverLines) {
                hoverBuilder.append(ChatColor.translateAlternateColorCodes('&', line)).append("\n");
            }
            component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder(hoverBuilder.toString().trim()).create()));
        }

        player.spigot().sendMessage(component);
    }

    public static List<String> hover(String... lines) {
        List<String> list = new ArrayList<>();
        for (String line : lines) list.add(line);
        return list;
    }

    public static void broadcastClickable(String text, ClickActionType type, String action, List<String> hover) {
        for (Player player : org.bukkit.Bukkit.getOnlinePlayers()) {
            sendClickableMessage(player, text, type, action, hover);
        }
    }
}
