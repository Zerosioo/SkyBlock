package me.zerosio.npcs.hub.merchants;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.zerosio.npcs.build.AbstractNPC;

public class Anita extends AbstractNPC {

    public Anita() {
        super("Anita");
    }

    @Override
    public void onInteract(Player player) {
        if (isFirstInteraction(player)) {
            // TODO: Handle first-time interaction
        }
    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), 23, 77, -70);
    }

    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§fAnita", "§e§lCLICK");
    }

    @Override
    public String getTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYyMzA3NTc5Njg5NSwKICAicHJvZmlsZUlkIiA6ICJmYzUwMjkzYTVkMGI0NzViYWYwNDJhNzIwMWJhMzBkMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDVUNGTDE3IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzRkNGU5ZDJhOGM4OGQwZWZkNzUwZTg1MjczMmJhMDVmYTNmMzAyZTIwNGUyYmRjODhkYWQ2MDVlMjU1N2UyYTYiCiAgICB9CiAgfQp9";
    }

    @Override
    public String getSignature() {
        return "J3eMBL1C3U3opBTQQaktzsajGk8Jku9svRi/37eV0oXYuGg86tSR9ojkx0FF4TbtNL4qoRaW9U5viyWldY6pMvmMkfD1wFfppEIUaH9NpNJhoh32ZdaLH8ZpXV+S5PcLUdRznritxqHDR4mRalKGqob0Bsb6TR9gyncL7zIJ31nwR3rWKWbd0UyKO3RWkc/wLUWQCK8jbG9q7iDA8ITKRJnUUVl0QZnHVlJIrMwfnKcPuVXAy3NUL0QIWD0YelZsCkT2vGRgAsBNHAqLJPAHYLrQP82pG8l+2mR2q99s+KwZ7jeSIyx2SmbQDR4W2QCgymwICnh+G/yaJmqD2pxBRBoUsIQfsDjNOSFRj3rZSc0cSwOWL6WoPWRIhtsOpurAg0T9lI5dan2cLrGuEehSO/WINXvG6O7upvaBk54Bx2qPe1pbyAwUCEjHZxKGXNcvikj5GK6kjCX3bfsXO/QVMe2od2+ufJu9NCXJRNP66X394R7EsgouLOx0m8Vyn9Fg2rflsSIR89ohhGR/Y9nXJzS7BIAE6+U9Q9mtEj1pFO+obHpRlMh+b4cWXW6ynmAxpzeQVtWBkXCWSkEuY25wrROW5ro4+ggnlcahxXYyYegFL9O2Z1+kxffRejDPw7NeJvWYHTMFGYdb0vk/5rIJ6CZvXpxTiZGfga2hrbmHrCw=";
    }
}