package me.zerosio.listeners;

import me.zerosio.items.ability.Ability;
import me.zerosio.items.ability.AbilityManager;
import me.zerosio.items.ability.AbilityType;
import me.zerosio.items.itemtype.SItem;
import me.zerosio.user.actionbar.ActionBarManager;
import me.zerosio.user.actionbar.ActionBarOverrideMode;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class AbilityListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        SItem item = SItem.getItem(player.getItemInHand());
        if (item == null) return;

        if (!(item instanceof Ability.Holder)) return;
        Ability.Holder holder = (Ability.Holder) item;

        for (Ability ability : holder.getAbilities()) {
            if (!matches(ability.getType(), event)) continue;

            String id = item.getId() + "_" + ability.getAbilityName();

            if (AbilityManager.isOnCooldown(player, id)) {
                long remaining = AbilityManager.getRemainingCooldown(player, id) / 1000;
                player.sendMessage("§cAbility is on cooldown! §c(" + remaining + "s)");
                return;
            }

            if (!AbilityManager.takeMana(player, ability.getManaCost())) {
                player.sendMessage("§cNot enough mana!");
                ActionBarManager.setTemporary(player, "§cNot enough mana!", 40, ActionBarOverrideMode.HEALTH_AND_CENTRE);
                return;
            }

            AbilityManager.setCooldown(player, id, ability.getCooldownSeconds());
            ActionBarManager.setTemporary(player, "§b-" + ability.getManaCost() + " Mana (§6" + ability.getAbilityName() + "§b)", 40, ActionBarOverrideMode.FULL);
            ability.activate(player, event);
        }
    }

    private boolean matches(AbilityType type, PlayerInteractEvent event) {
        switch (type) {
            case RIGHT_CLICK: return event.getAction().toString().contains("RIGHT") && !event.getPlayer().isSneaking();
            case LEFT_CLICK: return event.getAction().toString().contains("LEFT") && !event.getPlayer().isSneaking();
            case SHIFT_RIGHT_CLICK: return event.getAction().toString().contains("RIGHT") && event.getPlayer().isSneaking();
            case SHIFT_LEFT_CLICK: return event.getAction().toString().contains("LEFT") && event.getPlayer().isSneaking();
            default: return false;
        }
    }
}
