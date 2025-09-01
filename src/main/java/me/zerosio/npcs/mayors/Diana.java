package me.zerosio.npcs.mayors;

import me.zerosio.mayors.Mayor;
import me.zerosio.mayors.Perk;
import me.zerosio.npcs.build.AbstractNPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class Diana extends AbstractNPC implements Mayor {

    public Diana() {
        super("");
    }

    @Override
    public void onInteract(Player player) {
        if (isFirstInteraction(player)) {
            // First time interaction logic (optional)
        }
    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), 0, 0, 0);
    }

    @Override
    public List<String> getHologramLines() {
        if (isCandidate()) {
        return Arrays.asList(getDisplayName(), "§2Candidate", "§e§lCLICK");
        } else if (isMayor()) {
        	return Arrays.asList("§fMayor Diana", "§e§lCLICK");
        } else if (isMinister()) {
        	return Arrays.asList("§fMinister Diana");
        } else {
        	return Arrays.asList(getDisplayName(), "§e§lCLICK");
        }
    }

    @Override
    public String getTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTc0OTY5NDE2MDM4MywKICAicHJvZmlsZUlkIiA6ICJmZjQ3NzI5YmQwZDI0YWYwOThiMTFjMGE3ZTFiMGVlZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJtYXRzY2FuIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzgzY2MxY2Y2NzJhNGIyNTQwYmUzNDZlYWQ3OWFjMmQ5ZWQxOWQ5NWI2MDc1YmY5NWJlMGI2ZDBkYTYxMzc3YmUiCiAgICB9CiAgfQp9";
    }

    @Override
    public String getSignature() {
        return "ActkMtMlnReBeZmlIb2S4NYlWz5bNGAgmwzVZ0RYq9Xu06EApzcsTvaaFIY2jlW1200HwgGymUPy48krMpAgYjJWaW+RLW3McqjKUkdOAUJu0c6xy138u28BX9W+BXLMSv8GNLrpwuN8h7kUtl4BRL20ERsUboqOMuqttpdts82fDYexQNcHGsePJeu8GbqvPhoScyMeaD+URP++4MEPs8pYGfmvyhA26P7aEfVNpCBRRdx2vA9O86LD8/kPcH1Sn1nFgxHmbKeuZPmNUNBpKUKtRkfJw+SEu8ji/tnxAMfeu1Sfnz6oUMjAR+gE3z8XugrnIMAwrjVbLJm2mUfXx+wN65F9QsSukyyYeG5MaJzXaKOMrmwukreA0Vb7v4Z7CAXNl7dxxF9qIIsy6nfbB4rLIk2psesGgD+hJ8+Vko/wdIUzAItXGklx1OThHfaZV1RklmoodDV5gSsC2jfFX7GkjlMkw9Toleyq0ysR4/LVPj3nCpYXKJmvFWanf1mZ+pnmj/x0wrQRSKM5h9jlxCTWao+b0HKbN6igvLyCWRervMdNwDUxHgPX5dfOW1CIvrW71VMcv/SzMxSIlaFjWm9xEr5hr+V90magNRjOC4s1sndfppNN79etgCnyZw6juNBBVnblIdnn6X0SHyHls9O1mEGmKIPUOmDFiGwGG2A=";
    }

    @Override
    public List<Perk> getPerks() {
        return Arrays.asList(
                new Perk("§dLucky!", Arrays.asList("§7Gain §d+25 ♣ Pet Luck§7."), mayor -> {}),
                new Perk("§dMythological Ritual", Arrays.asList("§7Mayor Diana will sell the Griffin", "§7pet, which lets you find", "§2Mythological Creatures §7and tons", "§7of §eunique items§7."), mayor -> {}),
                new Perk("§dPet XP Buff", Arrays.asList("§7Gain §d+35% §7more pet XP."), mayor -> {}),
                new Perk("§dSharing is Caring", Arrays.asList("§7Unlocks §a2 §7more §6Exp Share §7slots in", "§7your pets menu and increases the", "§6Exp Share §7rate by §a+10%§7!"), mayor -> {})
        );
    }

    @Override
    public String getDisplayName() {
        return "§2Diana";
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
