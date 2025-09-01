package me.zerosio.commands;

import org.bukkit.entity.Player;

import me.zerosio.commands.builder.CommandBase;
import me.zerosio.items.itemtype.SItem;
import me.zerosio.rank.PlayerRank;

import java.util.List;

public class ItemCommand extends CommandBase {

    @Override
    public String getName() {
        return "item";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public PlayerRank getRequiredRank() {
        return PlayerRank.ADMIN;
    }

    @Override
    public String getDescription() {
        return "Used for getting administrator items.";
    }

    @Override
    public String getUsage() {
        return "/item <list|browse|item_id>";
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§cUsage: /item <list|browse|item_id>");
            return;
        }

        String sub = args[0].toLowerCase();

        if (sub.equals("list") || sub.equals("browse") || sub.equals("b") || sub.equals("l")) {
            player.sendMessage("§cOpening item list... (not implemented yet)");
            return;
        }

        SItem item = (SItem) SItem.getItemClass(args[0].toUpperCase());
        if (item == null) {
            player.sendMessage("§cUnknown item!");
            player.sendMessage("§cPlease try again with a valid item_id.");
            player.sendMessage("§cIf you think there is an issue, please contact zerosio.");
            return;
        }

        item.origin = "FROM_COMMAND_ACTOR_" + player.getName();
        player.getInventory().addItem(item.buildItemStack(player));
    }
}
