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
