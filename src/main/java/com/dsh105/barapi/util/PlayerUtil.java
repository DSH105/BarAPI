/*
 * This file is part of BarAPI (1) (me.confuser.barapi).
 *
 * BarAPI (1) (me.confuser.barapi) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BarAPI (1) (me.confuser.barapi) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BarAPI (1) (me.confuser.barapi).  If not, see <http://www.gnu.org/licenses/>.
 */

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
