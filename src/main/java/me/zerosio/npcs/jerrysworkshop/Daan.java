package me.zerosio.npcs.jerrysworkshop;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.zerosio.npcs.build.AbstractNPC;

    public class Daan extends AbstractNPC {

        public Daan() {
            super("");
        }

        @Override
        public void onInteract(Player player) {
            if (isFirstInteraction(player)) {
                // TODO: Handle first-time interaction
            }
        }

        @Override
        public Location getInitialLocation() {
            return new Location(Bukkit.getWorld("jerrys_workshop"), 80, 78.5, 110);
        }

        @Override
        public List<String> getHologramLines() {
            return Arrays.asList("§fDaan", "§e§lCLICK");
        }

        @Override
        public String getTexture() {
            return "ewogICJ0aW1lc3RhbXAiIDogMTU5MzgzOTkxOTUyNSwKICAicHJvZmlsZUlkIiA6ICJiNzQ3OWJhZTI5YzQ0YjIzYmE1NjI4MzM3OGYwZTNjNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTeWxlZXgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmVhODY0N2E2MWRhMjUwOTFlNzE3N2VhZDg4Mjk5ZDdkN2I5NGJiNDM5MjQwNDAxYjJlN2EyZGU4M2Q2OTM4NSIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";
        }

        @Override
        public String getSignature() {
            return "xwFDv9Yaf59YdWOeZ1uT28QMEtIQeL9eb54In8PXqDrXMx3bva/r6VlNe3czCtChgfPUezFv2yLtu7AtmivM/MZPlI8ybHPFjn9H1w1vfFjop5IhWJz2+W9peyNTUsYP5OibasUHr02zlfscZI0U3T9OaMWXM+o3mwQaAr0q+1rec+w032YF0IbLv+8WmIWKT/y4Wou1UTF2OzrDCJMsjHXVDOZKCKfNG0oDXbYZXrI3jpyKKLR3SvMWoJyKrWSzxWh5foYNVkhAQjBVpOI30LnwGQMtgHYaLEnLlx95OzH1Jlqc69RTW2qAHBwD5x/pU6iuOsvYFZOoJ5Z8OU9ddZpxOqEA52V98G+rSiYldfYjJ1AloneriC/cM+sFGf+ZsbOVcrlOBcySciUcXWvWDGtEzpU6rzofwcjdIS6gPmeqi6+UFSubLpQVBQYtMfKzvKM5WwdpMRGTjGUotsl+emxZVOqgp/804NWK61JMGvQqpX4XCbz6gKEfsnODiwzV/Gx3vDbMXc3ZAO9L39jChf+n7ezZFoFfXatwM18MPRTL1BEyv0SmA4Z95XX1Gf/qbGEAzHQ1BcYQ+aZ83LE+Tk5BtFr4qMxm2SPGTYUDO5LBXwTq3QXr4yS4xblgxzxn9VHwJfSwtXUN2YhXAixWNJ8VSRGU25SZeFedV8/0fto=";
        }

}
