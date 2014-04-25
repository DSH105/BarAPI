package com.dsh105.barapi.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerIdent {

    public static Object getIdentificationFor(Player player) {
        if (ReflectionUtil.MC_VERSION_NUMERIC >= 172) {
            return player.getUniqueId();
        } else {
            return player.getName();
        }
    }

    public static String getIdentificationForAsString(Player player) {
        if (ReflectionUtil.MC_VERSION_NUMERIC >= 172) {
            return player.getUniqueId().toString();
        } else {
            return player.getName();
        }
    }

    public static Player getPlayerOf(String identification) {
        if (ReflectionUtil.MC_VERSION_NUMERIC >= 172) {
            return Bukkit.getPlayer(UUID.fromString(identification));
        } else {
            return Bukkit.getPlayerExact(identification);
        }
    }
}