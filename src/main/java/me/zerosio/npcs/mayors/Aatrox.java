package me.zerosio.npcs.mayors;

import me.zerosio.mayors.Mayor;
import me.zerosio.mayors.Perk;
import me.zerosio.npcs.build.AbstractNPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Aatrox extends AbstractNPC implements Mayor {

    public Aatrox() {
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
        return Arrays.asList(getDisplayName(), "§cCandidate", "§e§lCLICK");
        } else if (isMayor()) {
        	return Arrays.asList("§fMayor Aatrox", "§e§lCLICK");
        } else if (isMinister()) {
        	return Arrays.asList("§fMinister Aatrox");
        } else {
        	return Arrays.asList(getDisplayName(), "§e§lCLICK");
        }
    }

    @Override
    public String getTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYxODY2NDU5NjQwNCwKICAicHJvZmlsZUlkIiA6ICI5ZDQyNWFiOGFmZjg0MGU1OWM3NzUzZjc5Mjg5YjMyZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUb21wa2luNDIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzFiZGY1MDViYjhjMGYxZjMzNjVhMDMwMzJkZTE5MzE2NjNmZjcxYzU3ZTAyMjU1OGRlMzEyYjhmMWI1YzQ0NSIKICAgIH0KICB9Cn0=";
    }

    @Override
    public String getSignature() {
        return "muEXYWKUacFyyQuWofVOuw1XJOex7YPeDAhVbQ73gPK/f2W+CWTrbtxCx22pN2gftrKhqW7tLUOzyjnuYKnHKk7FFmDpEV2BkL7k+0IvlIRvz00+Yxia07IPSNYZDbe37q0W/6FP2aBY+Ge6gbHjKCuP1/pb9DbrlkGMvIzhNAQlkjVc1Uy7vYh5Uny9YNEZduDlU/yaWk+RPvUJOPDKzcQ9wpMyKU2GMefAKO5jUTlfH90WfrMmAnuf7AnpUP2owEmGyxsiQ3wYHaVkmur54ZkbSd24+wNVmxKnNaviwzXGDD4YHShzfgszVu53b++dBDHaHXPJnOJgN5MEPlsnNap4ikOFRZ5kfthFaxcg0fILJI9tRfNMwSJiU1tk8iHbrgsC1VlHwS9WYzF1kG8BptPfQoXjEjMxSyTWgbaSssUPX82JvKgTGzQLUyGy/eyK1QWLHHJtd/HO7Q2+RArfdPjMEinkfo619qEdYWXqnZddvhaOawWhrxn/ZQAmrxuBGmywpTgwcp0fNiJ0JsSVHRd7QKl7Obbx8/SPGZY5eIUULXguLC6hjLbEDwnRZ8P4+YEQb1+XIw5xcpPA31bhBFnuuESqUfoBCzo/rMNRYnayv1Gee+1Fy13w26WJQDqO2OhIEUEdvi9uHA2uR6zHY7wIahBRMnugmjNRwkyWRT8=";
    }

    @Override
    public List<Perk> getPerks() {
        return Arrays.asList(
                new Perk("§dSLASHED Pricing", Arrays.asList("§7Starting slayer quest is §ehalf", "§eprice§7."), mayor -> {}),
                new Perk("§dSlayer XP Buff", Arrays.asList("§7Earn §d25% §7more Slayer XP."), mayor -> {}),
                new Perk("§dPathfinder", Arrays.asList("§7Gain rare drops §d20% §7more often."), mayor -> {})
        );
    }

    @Override
    public String getDisplayName() {
        return "§cAatrox";
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
