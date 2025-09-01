package me.zerosio.npcs.mayors;

import me.zerosio.mayors.Mayor;
import me.zerosio.mayors.Perk;
import me.zerosio.npcs.build.AbstractNPC;
import me.zerosio.npcs.build.dialogue.Dialogue;
import me.zerosio.npcs.build.dialogue.VoiceType;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class SmartyXD extends AbstractNPC implements Mayor {

    public SmartyXD() {
        super("");
    }

    @Override
    public void onInteract(Player player) {
        new Dialogue()
          .voice(VoiceType.MALE)
          .addMessage("§e[NPC] §cSmarty XD§f: I AM A §8NIGGER §fWHO SUCKS §8ASHBRON§f'S §8DICK", 0)
          .addMessage("§e[NPC] §cSmaty XD§f: PLEASE §6RAPE §fME PLZ I BEG YOU!!!", 30)
          .start(player);
    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), 0, 0, 0);
    }

    @Override
    public List<String> getHologramLines() {
        if (isCandidate()) {
        return Arrays.asList(getDisplayName(), "§bCandidate", "§e§lCLICK");
        } else if (isMayor()) {
        	return Arrays.asList("§fMayor Smarty XD", "§e§lCLICK");
        } else if (isMinister()) {
        	return Arrays.asList("§fMinister Smarty XD");
        } else {
        	return Arrays.asList(getDisplayName(), "§e§lCLICK");
        }
    }

    @Override
    public String getTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYyMjgxMDcyNzc5NiwKICAicHJvZmlsZUlkIiA6ICI0ODBkMmFiMmY1ZTk0OWQ0YWM2Mzk0NDdmNjIzYTYwYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJIb3RhbXBhIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFiNTljNDNkOGRiY2NmZDdlYzZlNjM5NGI2MzA0YjcwZDRlZDMxNWFkZDA0OTRlZTc3YzczM2Y0MTgxOGM3M2EiCiAgICB9CiAgfQp9";
    }

    @Override
    public String getSignature() {
        return "HUQ6kEWFl4QcdU56wEv4nj8HIopxp44ivtgTeeRhtjioZ7/yvMfwHYBnPNfNC6jkbHh43UiSCXX0nNfEcdLet0auccdkdYb+ESzBqnHKCw55PcikdjJ9D387e5Bpaqwj8RDtqvykNFZoKZjJ+pMGh+/Vh3FumGqC1O7K3AXYPOrZ7Fh9s1VWR3D2MP+lBzfhrC+EXTGKwS3e5PD+eHxY3JnrCd5nwjpVl3+nZaqxtSE7vHusZXkx6hxcxoOucXyZPh+oVwRQdSrdetBi/imdXDHaJ2RAwz2EGeC62q3GB2AN35DdIDrfZvVtH/VlyRzucWKxZnMUsUjQnjdYfG9LKXV2vy4cikO7HvSWfJFc5d/UZrvIJO/hES+l3WINbBQIBJY9Pop4+0jbCGHuS0vO8NI5mKIbLQYuHWqqG2B+Ub8nS+or7i4Ixo6/ylG37zFSZJNhbrUTR3vxpUq+B59FRsJDmN+pekAERUEbZUNnpD+XpyGBDT/YVp/4Raa6ZFDlds6r0oNnj+SiwpqjA3wPw73AAUa7C6Stdsyd60/JbRmgqjzcEBJ5gDSfFg/XqYzFpD3OQ9gtTlwhTpRU6ty0yXXAZwQDmx/cFgdL9nPI0ZjU2jpE+M1Jah9rGlUncVVuMVZHchwYz/dzBiOL6Cj+nxQoTPrf4jEztPf5gagJJMA=";
    }

    @Override
    public List<Perk> getPerks() {
        return Arrays.asList(
                new Perk("§dShitting and Fucking all over §askyblock", Arrays.asList("§cSmarty_XD §7Commonly known to fuck up", "§7the smallest of things, §cshits §7around", "§7the §centire map §7in §askyblock", "§7making you want to §cshoot §7yourself."), mayor -> {}),
                new Perk("§dUnlimited §6Rape", Arrays.asList("§cSmarty_XD §7Being one of the most", "§crapable §7niggers, you can now rape", "§7him until the end of time.", "§7He cannot be §ckilled §7and", "§7he §ccannot escape§7."), mayor -> {}),
                new Perk("§cAsbron Protocol", Arrays.asList("§cAsbron§7, commonly known as the", "§cSmarty §3dickrider§7. Everytime §cSmarty_XD", "§7is §6raped §7by a player, §cAsbron", "§7spawns to suck §csmarty§7's", "§61cm dick §7to make him feel good", "§7about himself."), mayor -> {}),
                new Perk("§cNIGGER", Arrays.asList("§cNiggers §7break all laws, §cSmarty_XD", "§7being a §cnigger §6encourages §7breaking", "§7rules so all rules in the", "§7game are invalid."), mayor -> {})
        );
    }

    @Override
    public String getDisplayName() {
        return "§cSmarty XD";
    }

    @Override
    public void onActive() {
        // TODO
    }

    @Override
    public AbstractNPC createNPC() {
        return this;
    }
}
