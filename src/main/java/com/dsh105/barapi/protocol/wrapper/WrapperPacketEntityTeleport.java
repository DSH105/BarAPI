package com.dsh105.barapi.protocol.wrapper;

import com.dsh105.barapi.protocol.Packet;
import com.dsh105.barapi.protocol.PacketFactory;
import com.dsh105.barapi.reflection.Constants;

public class WrapperPacketEntityTeleport extends Packet {

    public WrapperPacketEntityTeleport() {
        super(PacketFactory.PacketType.ENTITY_TELEPORT);
    }

    public void setEntityId(int value) {
        this.write(Constants.PACKET_ENTITYTELEPORT_FIELD_ID.getName(), value);
    }

    public int getEntityId() {
        return (Integer) this.read(Constants.PACKET_ENTITYTELEPORT_FIELD_ID.getName());
    }

    public void setX(double value) {
        this.write(Constants.PACKET_ENTITYTELEPORT_FIELD_X.getName(), (int) Math.floor(value * 32.0D));
    }

    public double getX() {
        return (((Integer) this.read(Constants.PACKET_ENTITYTELEPORT_FIELD_X.getName())) / 32.0D);
    }

    public void setY(double value) {
        this.write(Constants.PACKET_ENTITYTELEPORT_FIELD_Y.getName(), (int) Math.floor(value * 32.0D));
    }

    public double getY() {
        return (((Integer) this.read(Constants.PACKET_ENTITYTELEPORT_FIELD_Y.getName())) / 32.0D);
    }

    public void setZ(double value) {
        this.write(Constants.PACKET_ENTITYTELEPORT_FIELD_Z.getName(), (int) Math.floor(value * 32.0D));
    }

    public double getZ() {
        return (((Integer) this.read(Constants.PACKET_ENTITYTELEPORT_FIELD_Z.getName())) / 32.0D);
    }

    public void setYaw(float value) {
        this.write(Constants.PACKET_ENTITYTELEPORT_FIELD_YAW.getName(), (byte) (value * 256.0F / 360.0F));
    }

    public float getYaw() {
        return (((Byte) this.read(Constants.PACKET_ENTITYTELEPORT_FIELD_YAW.getName())) * 360.0F / 256.0F);
    }

    public void setPitch(float value) {
        this.write(Constants.PACKET_ENTITYTELEPORT_FIELD_PITCH.getName(), (byte) (value * 256.0F / 360.0F));
    }

    public float getPitch() {
        return (((Byte) this.read(Constants.PACKET_ENTITYTELEPORT_FIELD_PITCH.getName())) * 360.0F / 256.0F);
    }
}