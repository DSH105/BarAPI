package com.dsh105.barapi.reflection.utility;

import com.dsh105.barapi.BarAPI;
import com.dsh105.barapi.reflection.FieldAccessor;
import com.dsh105.barapi.reflection.SafeField;
import org.bukkit.entity.Player;

public class CommonFields {

    private static volatile FieldAccessor<Object> CONNECTION_ACCESSOR;
    private static volatile FieldAccessor<Object> NETWORK_ACCESSOR;

    private CommonFields() {
        super();
    }

    public static Object getNetworkManager(Player player) {
        Object vanillaPlayer = CommonMethods.getVanillaObject(player);

        if (NETWORK_ACCESSOR == null) {
            NETWORK_ACCESSOR = new SafeField<Object>(CommonReflection.getPlayerConnectionClass(), "networkManager");

            if (!NETWORK_ACCESSOR.getField().getType().equals(CommonReflection.getNetworkManagerClass())) {
                BarAPI.getCore().LOGGER_REFLECTION.warning("Failed to retrieve a valid NetworkManager!");
                NETWORK_ACCESSOR = null;
                return null;
            }
        }
        return NETWORK_ACCESSOR.get(vanillaPlayer);
    }

    public static Object getPlayerConnection(Player player) {
        Object vanillaPlayer = CommonMethods.getVanillaObject(player);

        if (CONNECTION_ACCESSOR == null) {
            CONNECTION_ACCESSOR = new SafeField<Object>(CommonReflection.getEntityPlayerClass(), "playerConnection");

            if (!CONNECTION_ACCESSOR.getField().getType().equals(CommonReflection.getPlayerConnectionClass())) {
                BarAPI.getCore().LOGGER_REFLECTION.warning("Failed to retrieve a valid PlayerConnection!");
                CONNECTION_ACCESSOR = null;
                return null;
            }
        }

        return CONNECTION_ACCESSOR.get(vanillaPlayer);
    }
}
