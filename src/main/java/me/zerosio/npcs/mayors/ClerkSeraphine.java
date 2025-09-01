package me.zerosio.npcs.mayors;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.zerosio.npcs.build.AbstractNPC;
import me.zerosio.npcs.build.dialogue.Dialogue;
import me.zerosio.npcs.build.dialogue.VoiceType;

public class ClerkSeraphine extends AbstractNPC {

    public ClerkSeraphine() {
        super("ClerkSeraphine");
    }

    @Override
    public void onInteract(Player player) {
        new Dialogue()
          .voice(VoiceType.MALE)
          .addMessage("§e[NPC] Clerk Seraphine§f: Welcome to the §bElection Room§f!", 0)
          .addMessage("§e[NPC] Clerk Seraphine§f: Here to learn about §bMayor Elections§f?", 25)
          .addMessage("§e[NPC] Clerk Seraphine§f: Everyone §avotes §ffor their §dfavorite candidate§f...", 25)
          .addMessage("§e[NPC] Clerk Seraphine§f: ...and whoever has the most §avotes §fbecomes §6Mayor §ffor a §bSkyBlock Year§f!", 40)
          .addMessage("§e[NPC] Clerk Seraphine§f: They even offer §aspecial perks §ffor everyone during their time in office, like more §3Skill XP§f, special §aEvents§f, and more!", 60)
          .start(player);
    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), 9.5, 32, -119.5);
    }

    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§fClerk Seraphine", "§e§lCLICK");
    }

    @Override
    public String getTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYyMzA0MDQ3NTQ5NCwKICAicHJvZmlsZUlkIiA6ICIzYzdiZDE4ODc2OTU0OWVlODk0ZmIyMjJiZDc5YWVjYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJZZ2dkcmFzaWw4MCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81MmUxNWMyZDdjZmRkMzAwOGRkNzkwYjJmODkxNzVjNzNkNWU3NzYyN2FmNjUzZmMxOGU5ZmNjOGI5NmFkZjg0IgogICAgfQogIH0KfQ==";
    }

    @Override
    public String getSignature() {
        return "LVMIfsjQBFjAaMwLvQBIjW3j/V1mbCzEbYN5j4nVOATTDXIOdHDqtOXHRiBE9xcFZLqHsM/LF4j8VZ4/HQPgBivK/7v72U/nvnUMwZq3tChVvcGpwrv6V4NgAf0JYoNe7F3aqSEwx25xbWpsGx39qZ6LO6xGRtVEjXhoXGwL7kpaOn+XF3L+E5/d9aMh0CAfvyuM94hhoB9HCG2J9hzsRPm3V7C66IQ2n+AlPSwBYAzOAj3wQRdz6ipWOvyRpk9siQ/1T5tD+RC8Flwy5o2/0zP9AWLORmbpqe3zSf2r06iMx1LLy6odiMe6LeiC/FTNiu96hatQDzv8OQ2VQAg5mEkv1HyXgGvVbYQcGB5SNiepXkxHdtfyshBewEFR0f0XC8nMlN0sgaJl0Iycr+4+oCFnvGTufKNnnzFxQhU9CknSQnWFyxdFhefUtHUPxtCLnxnexwDIqItL40bafbMEpDQhJSOWSu97vcTbc0yDdTUbLZAVTzO+/wmf4M594i6e1ykFn/WtLiOi58/qdCN2Fwja5QX+e0rKflNl7Yci5RGWgGsKzK471FmzyIGesXfwwQTiy1k/rGCID2rstGa57s1HXFOBuh0g7nwCl9gDo/XokHzYXF5khmKIvxrusC6Bstsg2jy1drW7BgKCyN400BYXb+JxS0Dt0V3WNTslMUQ=";
    }
}