package me.zerosio.garden.visitors.npcs.exclusives;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.zerosio.garden.visitors.build.GardenVisitor;
import me.zerosio.items.itemtype.Rarity;
import me.zerosio.npcs.build.AbstractNPC;

public class SiriusG extends AbstractNPC implements GardenVisitor {

    public SiriusG() {
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
        return new Location(Bukkit.getWorld("world"), 0, 0, 0);
    }

    @Override
    public List<String> getHologramLines() {
        return Arrays.asList(getRarity().getColor() + getName(), "§e§lCLICK");
    }

    @Override
    public String getTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYyMTkyNjMzOTIxNywKICAicHJvZmlsZUlkIiA6ICJjNjc3MGJjZWMzZjE0ODA3ODc4MTU0NWRhMGFmMDI1NCIsCiAgInByb2ZpbGVOYW1lIiA6ICJDVUNGTDE2IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2IzYjNhMzM2YTc3Y2FiOGI3NDQ2ODY4Y2VhMjkyMTkwZTc3Mjk4ZjJiNjBlODM1YjkwNTY4NjI1MDJkNjU0NjAiCiAgICB9CiAgfQp9";
    }

    @Override
    public String getSignature() {
        return "gbIH16YWg05ykuKXq1Whju1CN68gDa3C3TeCr8aNZ+hHsl6aw+sGUJC9a1brV1IzOFhXKyjxe6AcjVQM6/7wltkGCZ0HNh0BiOglWXK2pbv5xZF6v5pM3YPaDNQ37gxEDsnxw8XJkVGHFNKaFz6dNIn/sjUU0uPiwlv1772zX2EN0FcyWB+YgpKDjZ4x0xs+DytxrFQatvdrEIHnEWzq4bGGoDomN8yQccPhqB1rtDGp3TuXvzSfjwDvzzo7aVuFk6qfILW6DctAP+w6bfFE2OpgQoZrAX5/lcgjPGcMbdDi9eWHHh4BFuTJ68ZYzXyXK4j1NHRxtkCPajgdfTKmMzKhyZ5iRcASREaU7wAqErSY2ZBLx99kjfilI8qJ/wDxlnrFrYEbT0iRbLWLjThtO7X0e8+KSAe/1TKp4EdmhQ9YBd9OYTmW7tmDyRU15ukeKHUOA2njIXXDg58Rq6n2+6drb0mPJ6e69+ibu2Gg+Ou1tOxsS5XhFpnquAHCipSbqOBkyZcttR0KCxt23KENVG+66jhCjWTseXEh/yJhUhMvL18kkDQeO3OED0JRh/IG8M/lQEk1V8qT4FTTX3tATsLPbgGQtU5RlvKE4pTusN5v4ZFq+wVEXuZyNiwKgxNt4UUYsifRJ27wcwg/0YH8O7EAnJgUnGWAlurMEaFALow=";
    }

    @Override
    public int getRequiredGardenLevel() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public String getName() {
        return "Sirius";
    }

    @Override
    public AbstractNPC createNpc() {
        return this;
    }
}
