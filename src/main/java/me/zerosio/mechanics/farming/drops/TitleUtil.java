package me.zerosio.mechanics.farming.drops;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TitleUtil {

    public static void sendTitle(Player player, String title, String subtitle) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object connection = handle.getClass().getField("playerConnection").get(handle);

            Class<?> packetPlayOutTitle = Class.forName("net.minecraft.server.v1_8_R3.PacketPlayOutTitle");
            Class<?> enumTitleAction = Class.forName("net.minecraft.server.v1_8_R3.PacketPlayOutTitle$EnumTitleAction");
            Class<?> iChatBaseComponent = Class.forName("net.minecraft.server.v1_8_R3.IChatBaseComponent");
            Class<?> chatSerializer = Class.forName("net.minecraft.server.v1_8_R3.IChatBaseComponent$ChatSerializer");

            Method a = chatSerializer.getMethod("a", String.class);
            Object titleComponent = a.invoke(null, "{\"text\":\"" + title + "\"}");
            Object subtitleComponent = a.invoke(null, "{\"text\":\"" + subtitle + "\"}");

            Constructor<?> titleConstructor = packetPlayOutTitle.getConstructor(enumTitleAction, iChatBaseComponent);
            Object packetTitle = titleConstructor.newInstance(Enum.valueOf((Class<Enum>) enumTitleAction, "TITLE"), titleComponent);
            Object packetSubtitle = titleConstructor.newInstance(Enum.valueOf((Class<Enum>) enumTitleAction, "SUBTITLE"), subtitleComponent);

            Method sendPacket = connection.getClass().getMethod("sendPacket", Class.forName("net.minecraft.server.v1_8_R3.Packet"));
            sendPacket.invoke(connection, packetTitle);
            sendPacket.invoke(connection, packetSubtitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendTimings(Player player, int fadeIn, int stay, int fadeOut) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object connection = handle.getClass().getField("playerConnection").get(handle);

            Class<?> packetPlayOutTitle = Class.forName("net.minecraft.server.v1_8_R3.PacketPlayOutTitle");
            Class<?> enumTitleAction = Class.forName("net.minecraft.server.v1_8_R3.PacketPlayOutTitle$EnumTitleAction");

            Constructor<?> timingConstructor = packetPlayOutTitle.getConstructor(enumTitleAction, Class.forName("net.minecraft.server.v1_8_R3.IChatBaseComponent"), int.class, int.class, int.class);
            Object timingPacket = timingConstructor.newInstance(
                    Enum.valueOf((Class<Enum>) enumTitleAction, "TIMES"),
                    null, fadeIn, stay, fadeOut
            );

            Method sendPacket = connection.getClass().getMethod("sendPacket", Class.forName("net.minecraft.server.v1_8_R3.Packet"));
            sendPacket.invoke(connection, timingPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
