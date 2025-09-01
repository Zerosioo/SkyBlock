package me.zerosio.commands;

import org.bukkit.entity.Player;
import me.zerosio.commands.builder.CommandBase;
import me.zerosio.rank.PlayerRank;
import me.zerosio.user.User;
import me.zerosio.user.profiledata.UserRank;
import me.zerosio.chat.ActiveChat;

import java.util.Arrays;
import java.util.List;

public class ChatCommand extends CommandBase {

    @Override
    public String getName() {
        return "chat";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("c");
    }

    @Override
    public PlayerRank getRequiredRank() {
        return PlayerRank.DEFAULT;
    }

    @Override
    public String getDescription() {
        return "Toggle between all/guild chat.";
    }

    @Override
    public String getUsage() {
        return "/chat all || /chat guild";
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("§cUsage: " + getUsage());
            return;
        }

        User user = User.getUser(player);
        String input = args[0].toLowerCase();

        switch (input) {
            case "a":
            case "all":
                user.setData("active_chat", ActiveChat.ALL.name());
                player.sendMessage("§aYou are now chatting in §bALL §achannel.");
                break;

            case "g":
            case "guild":
                user.setData("active_chat", ActiveChat.GUILD.name());
                player.sendMessage("§aYou are now chatting in §2GUILD §achannel.");
                break;
            case "s":
            case "staff":
                if (UserRank.getPlayerRank(player).isStaff()) {
                	user.setData("active_chat", ActiveChat.GUILD.name());
                	player.sendMessage("§aYou are now chatting in §bSTAFF §achannel.");
                } else {
                	player.sendMessage("§cInvalid chat type! Use /chat all or /chat guild.");
                }
                break;
            default:
                player.sendMessage("§cInvalid chat type! Use /chat all or /chat guild.");
                if (UserRank.getPlayerRank(player).isStaff()) {
                	player.sendMessage("§eStaff members can use §b/chat s §eor §b/chat staff §eas well.");
                }
                break;
        }
    }
}
