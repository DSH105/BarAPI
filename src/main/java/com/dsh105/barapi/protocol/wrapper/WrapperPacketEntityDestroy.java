package com.dsh105.barapi.protocol.wrapper;

import com.dsh105.barapi.protocol.Packet;
import com.dsh105.barapi.protocol.PacketFactory;
import com.dsh105.barapi.reflection.Constants;

import java.util.Arrays;
import java.util.List;

public class WrapperPacketEntityDestroy extends Packet {

    public WrapperPacketEntityDestroy() {
        super(PacketFactory.PacketType.ENTITY_DESTROY);
    }

    public void setEntities(int... value) {
        this.write(Constants.PACKET_ENTITYDESTROY_FIELD_IDS.getName(), value);
    }

    public List<Integer> getEntities() {
        return Arrays.asList((Integer[]) this.read(Constants.PACKET_ENTITYDESTROY_FIELD_IDS.getName()));
    }
}