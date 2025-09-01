package me.zerosio.chat;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ChatOptionUtil {

    public static void sendOptions(Player player, String label, Runnable yesAction, Runnable noAction) {
        String yesId = ActionHandler.registerAction(yesAction);
        String noId = ActionHandler.registerAction(noAction);

        TextComponent base = new TextComponent(label + " ");

        TextComponent yes = new TextComponent("§a§l[YES]");
        yes.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/blahgydydbd " + yesId));
        yes.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§eClick to select!").create()));

        TextComponent space = new TextComponent(" ");

        TextComponent no = new TextComponent("§c§l[NO]");
        no.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/blahgydydbd " + noId));
        no.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§eClick to select!").create()));

        base.addExtra(yes);
        base.addExtra(space);
        base.addExtra(no);

        player.spigot().sendMessage(base);
    }
}


//ChatOptionUtil.sendOptions(player, "§eDo you want to continue?",
//    () -> {
//        player.sendMessage("§aYou selected YES!");
//        // perform positive action
//    },
//    () -> {
//        player.sendMessage("§cYou selected NO!");
//        // perform cancel logic
//    }
//);
