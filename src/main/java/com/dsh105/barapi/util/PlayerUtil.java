package com.dsh105.barapi.util;

import com.dsh105.barapi.BarAPI;
import com.dsh105.barapi.reflection.utility.CommonReflection;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PlayerUtil {

    private static final Method sendPacket = ReflectionUtil.getMethod(CommonReflection.getMinecraftClass("PlayerConnection"), "sendPacket", CommonReflection.getMinecraftClass("Packet"));

    public static void sendPacket(Player player, Object packet) {
        Object playerConnection = getPlayerConnection(player);
        try {
            sendPacket.invoke(playerConnection, packet);
        } catch (IllegalAccessException e) {
            BarAPI.getCore().LOGGER_REFLECTION.warning("Failed to retrieve the PlayerConnection of: " + player.getName());
        } catch (IllegalArgumentException e) {
            BarAPI.getCore().LOGGER_REFLECTION.warning("Failed to retrieve the PlayerConnection of: " + player.getName());
        } catch (InvocationTargetException e) {
            BarAPI.getCore().LOGGER_REFLECTION.warning("Failed to retrieve the PlayerConnection of: " + player.getName());
        }
    }

    public static Object playerToEntityPlayer(Player player) {
        Method getHandle = ReflectionUtil.getMethod(player.getClass(), "getHandle");
        try {
            return getHandle.invoke(player);
        } catch (IllegalAccessException e) {
            BarAPI.getCore().LOGGER_REFLECTION.warning("Failed retrieve the NMS Player-Object of:" + player.getName());
            return null;
        } catch (IllegalArgumentException e) {
            BarAPI.getCore().LOGGER_REFLECTION.warning("Failed retrieve the NMS Player-Object of:" + player.getName());
            return null;
        } catch (InvocationTargetException e) {
            BarAPI.getCore().LOGGER_REFLECTION.warning("Failed retrieve the NMS Player-Object of:" + player.getName());
            return null;
        }
    }

    public static Object getPlayerConnection(Player player) {
        return ReflectionUtil.getField(CommonReflection.getMinecraftClass("EntityPlayer"), "playerConnection", playerToEntityPlayer(player));
    }
}
