package com.dsh105.barapi.protocol;

import com.dsh105.barapi.reflection.utility.CommonReflection;
import com.dsh105.barapi.util.ReflectionUtil;

public enum Protocol {

    HANDSHAKE,
    PLAY,
    STATUS,
    LOGIN;

    public static Protocol fromVanilla(Enum<?> enumValue) {
        String name = enumValue.name();

        if ("HANDSHAKING".equals(name))
            return HANDSHAKE;
        if ("PLAY".equals(name))
            return PLAY;
        if ("STATUS".equals(name))
            return STATUS;
        if ("LOGIN".equals(name))
            return LOGIN;

        return null;
    }

    public Object toVanilla() {
        switch (this) {
            case HANDSHAKE:
                return Enum.valueOf((Class<? extends Enum>) CommonReflection.getMinecraftClass("EnumProtocol"), "HANDSHAKING");
            case PLAY:
                return Enum.valueOf((Class<? extends Enum>) CommonReflection.getMinecraftClass("EnumProtocol"), "PLAY");
            case STATUS:
                return Enum.valueOf((Class<? extends Enum>) CommonReflection.getMinecraftClass("EnumProtocol"), "STATUS");
            case LOGIN:
                return Enum.valueOf((Class<? extends Enum>) CommonReflection.getMinecraftClass("EnumProtocol"), "LOGIN");
            default:
                return null;
        }
    }
}
