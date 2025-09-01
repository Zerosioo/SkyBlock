package me.zerosio.npcs.hub.merchants;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.zerosio.npcs.build.AbstractNPC;

public class Arthur extends AbstractNPC {

    public Arthur() {
        super("Arthur");
    }

    @Override
    public void onInteract(Player player) {
        if (isFirstInteraction(player)) {
            // TODO: Handle first-time interaction
        }
    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), 51, 71, -136);
    }

    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§fArthur", "§e§lCLICK");
    }

    @Override
    public String getTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYyMDM5NzA2MjE1MSwKICAicHJvZmlsZUlkIiA6ICI0ZGI2MWRkOTM0Mzk0M2M0YjhhOTZiNDQwMWM3MDM1MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJiZWVyYmVsbHltYW4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2MyZTgxOTkwNmViMTc5NDM5YjhkZDU1NTExMzJlNTRlYjQ3MTczZTBmNDU4ODYxYWQyYThjOTM3OTE4Mzg5MSIKICAgIH0KICB9Cn0=";
    }

    @Override
    public String getSignature() {
        return "xco7VCMhfubrOauzhLwCZDAGTmd0RgLK4Vb5rfrTBuP7OtvfTaxKbFMq8UnA+AZ1eH9IJ/JEhuH5ksczulQzpvbVIFvKeb88mAnfRiSD7hs6MDTjKvEf2CjK0UrwZPx2hJQid+1wPwO+Ub1mizB0qi28WZAUq0QmtfA0gCxqETXeYxkDPiBpqluHh/nrtl7sUIRLTGPCSfSI3IpVS5KRc+DZmvENwQ1BXhXVhRlM5/Le3ngD2nPU8hrnSuRlP74MsrdwPd8Xhp6++gkKtqINdiXcdRnguNr2AUy2h3TklPT2dzNeuL6AAMd5lTq9PuARV9OwTfnu3j53yxWRdZkQ3cnjAAKP5odfa1QtJCw1z6HKMpCa0uLtvbRjONmYtxcQl6q5I50pAVbVK6ApLxX2wzWi2a32aq29y08ZPflCVs4nIcLiqr+7tfDpNKuAqV9CMW3/Hl+8dhUbY1tDtOzZn+dyEJLWtr29MXUhA8g12wBOWblDbKMopnNyDpqB6E8S63pgwaFjqjZcDsjCFQeMKolw3AklZvuWRcKQOUWFfNUX1BbqrAdZ8s6ga8MmZVP051a2HxQSaSWTdb/p6a8CK+UcWTeANFq4tqfpwsqW8VKQ/Nl/Ei56Uurd7W2nbmKmBBDzywB76yGgvbGwyNlNgqdHNup/5R2PACRjCs+7Nsg=";
    }
}