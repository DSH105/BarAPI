/*
 * This file is part of BarAPI (1) (com.dsh105.barapi).
 *
 * BarAPI (1) (com.dsh105.barapi) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BarAPI (1) (com.dsh105.barapi) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BarAPI (1) (com.dsh105.barapi).  If not, see <http://www.gnu.org/licenses/>.
 */

package com.dsh105.barapi.protocol.wrapper;

import com.dsh105.barapi.protocol.Packet;
import com.dsh105.barapi.protocol.PacketFactory;
import com.dsh105.barapi.reflection.Constants;
import com.dsh105.barapi.reflection.SafeMethod;

public class WrapperPacketEntityMetadata extends Packet {

    public WrapperPacketEntityMetadata() {
        super(PacketFactory.PacketType.ENTITY_METADATA);
    }

    public void setEntityId(int value) {
        this.write(Constants.PACKET_ENTITYMETADATA_FIELD_ID.getName(), value);
    }

    public int getEntityId() {
        return (Integer) this.read(Constants.PACKET_ENTITYMETADATA_FIELD_ID.getName());
    }

    public void setMetadata(WrappedDataWatcher metadata) {
        Object handle = metadata.getHandle();
        this.write(Constants.PACKET_ENTITYMETADATA_FIELD_META.getName(), new SafeMethod<Void>(handle.getClass(), Constants.PACKET_ENTITYMETADATA_FUNC_PREPARE.getName()).invoke(handle));
    }

    public Object getMetadata() {
        return this.read(Constants.PACKET_ENTITYMETADATA_FIELD_META.getName());
    }
}