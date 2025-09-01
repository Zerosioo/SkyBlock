package me.zerosio.items.itemtype;

public interface SkullItem {

    default boolean isURL() {
        return false;
    }

    String getTexture();
}