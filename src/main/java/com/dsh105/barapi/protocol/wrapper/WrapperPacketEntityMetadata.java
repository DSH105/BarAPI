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