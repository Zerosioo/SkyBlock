package me.zerosio.mayors;

import java.util.List;
import java.util.function.Consumer;

public class Perk {

    private final String title;
    private final List<String> description;
    private final Consumer<Mayor> onActive;

    public Perk(String title, List<String> description, Consumer<Mayor> onActive) {
        this.title = title;
        this.description = description;
        this.onActive = onActive;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getDescription() {
        return description;
    }

    public void activate(Mayor mayor) {
        if (onActive != null) {
            onActive.accept(mayor);
        }
    }
}
