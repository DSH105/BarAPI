/*
 * This file is part of HoloAPI.
 *
 * HoloAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HoloAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with HoloAPI.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.dsh105.barapi.reflection;

import com.dsh105.barapi.util.ReflectionUtil;

public enum Constants {

    PACKET_ENTITYMETADATA_FIELD_ID("a"),
    PACKET_ENTITYMETADATA_FIELD_META("b"),
    PACKET_ENTITYMETADATA_FUNC_PREPARE("c"),

    PACKET_ENTITYDESTROY_FIELD_IDS("a"),

    PACKET_ENTITYTELEPORT_FIELD_ID("a"),
    PACKET_ENTITYTELEPORT_FIELD_X("b"),
    PACKET_ENTITYTELEPORT_FIELD_Y("c"),
    PACKET_ENTITYTELEPORT_FIELD_Z("d"),
    PACKET_ENTITYTELEPORT_FIELD_YAW("e"),
    PACKET_ENTITYTELEPORT_FIELD_PITCH("f"),

    PACKET_SPAWNENTITYLIVING_FIELD_ID("a"),
    PACKET_SPAWNENTITYLIVING_FIELD_TYPE("b"),
    PACKET_SPAWNENTITYLIVING_FIELD_X("c"),
    PACKET_SPAWNENTITYLIVING_FIELD_Y("d"),
    PACKET_SPAWNENTITYLIVING_FIELD_Z("e"),
    PACKET_SPAWNENTITYLIVING_FIELD_MOTX("f"),
    PACKET_SPAWNENTITYLIVING_FIELD_MOTY("g"),
    PACKET_SPAWNENTITYLIVING_FIELD_MOTZ("h"),
    PACKET_SPAWNENTITYLIVING_FIELD_YAW("i"),
    PACKET_SPAWNENTITYLIVING_FIELD_HEADPITCH("j"),
    PACKET_SPAWNENTITYLIVING_FIELD_HEADYAW("k"),
    PACKET_SPAWNENTITYLIVING_FIELD_META("t", "l"),

    DATAWATCHER_FUNC_INITIATE("a"),
    DATAWATCHER_FUNC_WATCH("a"),

    PLAYER_FIELD_CONNECTION("playerConnection"),
    PLAYERCONNECTION_FUNC_SENDPACKET("sendPacket"),
    PLAYERCONNECTION_FIELD_NETWORKMANAGER("networkManager"),;

    private String[] fieldNames;

    private static int[] knownVersions = new int[]{163, 171, 172, 173};
    private static int versionIndex = -1;
    private static boolean hasSet;

    Constants(String... fieldNames) {
        this.fieldNames = fieldNames;

        set();
    }

    private static void set() {
        if (!hasSet) {
            for (int i = 0; i < knownVersions.length; i++) {
                if (ReflectionUtil.MC_VERSION_NUMERIC == knownVersions[i]) {
                    versionIndex = i;
                }
            }
            hasSet = true;
        }
    }

    public String getName() {
        for (String value : fieldNames) {
            for (int version : knownVersions) {
                String versionString = String.valueOf(version);
                if (value.startsWith(versionString)) {
                    return value.replace(versionString, "");
                }
            }
        }

        // If the mapped versions only contains one, we can use that
        // If there's more, but the mapped version isn't specifically supported, use the mappings of the latest supported version
        return (this.versionIndex < 0 || fieldNames.length == 1) ? fieldNames[0] : (this.versionIndex >= fieldNames.length) ? fieldNames[fieldNames.length - 1] : fieldNames[this.versionIndex];
    }
}