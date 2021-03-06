package com.dsh105.barapi.protocol.wrapper;

import com.dsh105.barapi.protocol.Packet;
import com.dsh105.barapi.protocol.PacketFactory;
import com.dsh105.barapi.reflection.Constants;
import org.bukkit.util.Vector;

public class WrapperPacketSpawnEntityLiving extends Packet {

    public WrapperPacketSpawnEntityLiving() {
        super(PacketFactory.PacketType.ENTITY_LIVING_SPAWN);
    }

    public Vector getVelocity() {
        return new Vector(this.getMotionX(), this.getMotionY(), this.getMotionZ());
    }

    public void setVelocity(Vector v) {
        this.setMotionX(v.getX());
        this.setMotionY(v.getY());
        this.setMotionZ(v.getZ());
    }

    public void setEntityId(int value) {
        this.write(Constants.PACKET_SPAWNENTITYLIVING_FIELD_ID.getName(), value);
    }

    public int getEntityId() {
        return (Integer) this.read(Constants.PACKET_SPAWNENTITYLIVING_FIELD_ID.getName());
    }

    public void setEntityType(int value) {
        this.write(Constants.PACKET_SPAWNENTITYLIVING_FIELD_TYPE.getName(), value);
    }

    public int getEntityType() {
        return (Integer) this.read(Constants.PACKET_SPAWNENTITYLIVING_FIELD_TYPE.getName());
    }

    public void setX(double value) {
        this.write(Constants.PACKET_SPAWNENTITYLIVING_FIELD_X.getName(), (int) Math.floor(value * 32.0D));
    }

    public double getX() {
        return (((Integer) this.read(Constants.PACKET_SPAWNENTITYLIVING_FIELD_X.getName())) / 32.0D);
    }

    public void setY(double value) {
        this.write(Constants.PACKET_SPAWNENTITYLIVING_FIELD_Y.getName(), (int) Math.floor(value * 32.0D));
    }

    public double getY() {
        return (((Integer) this.read(Constants.PACKET_SPAWNENTITYLIVING_FIELD_Y.getName())) / 32.0D);
    }

    public void setZ(double value) {
        this.write(Constants.PACKET_SPAWNENTITYLIVING_FIELD_Z.getName(), (int) Math.floor(value * 32.0D));
    }

    public double getZ() {
        return (((Integer) this.read(Constants.PACKET_SPAWNENTITYLIVING_FIELD_Z.getName())) / 32.0D);
    }

    public void setMotionX(double value) {
        this.write(Constants.PACKET_SPAWNENTITYLIVING_FIELD_MOTX.getName(), (int) Math.floor(value * 8000.0D));
    }

    public double getMotionX() {
        return (((Integer) this.read(Constants.PACKET_SPAWNENTITYLIVING_FIELD_MOTX.getName())) / 8000.0D);
    }

    public void setMotionY(double value) {
        this.write(Constants.PACKET_SPAWNENTITYLIVING_FIELD_MOTY.getName(), (int) Math.floor(value * 8000.0D));
    }

    public double getMotionY() {
        return (((Integer) this.read(Constants.PACKET_SPAWNENTITYLIVING_FIELD_MOTY.getName())) / 8000.0D);
    }

    public void setMotionZ(double value) {
        this.write(Constants.PACKET_SPAWNENTITYLIVING_FIELD_MOTZ.getName(), (int) Math.floor(value * 8000.0D));
    }

    public double getMotionZ() {
        return (((Integer) this.read(Constants.PACKET_SPAWNENTITYLIVING_FIELD_MOTZ.getName())) / 8000.0D);
    }

    public void setYaw(float value) {
        this.write(Constants.PACKET_SPAWNENTITYLIVING_FIELD_YAW.getName(), (byte) (value * 256.0F / 360.0F));
    }

    public float getYaw() {
        return (((Byte) this.read(Constants.PACKET_SPAWNENTITYLIVING_FIELD_YAW.getName())) * 360.0F / 256.0F);
    }

    public void setHeadPitch(float value) {
        this.write(Constants.PACKET_SPAWNENTITYLIVING_FIELD_HEADPITCH.getName(), (byte) (value * 256.0F / 360.0F));
    }

    public float getHeadPitch() {
        return (((Byte) this.read(Constants.PACKET_SPAWNENTITYLIVING_FIELD_HEADPITCH.getName())) * 360.0F / 256.0F);
    }

    public void setHeadYaw(float value) {
        this.write(Constants.PACKET_SPAWNENTITYLIVING_FIELD_HEADYAW.getName(), (byte) (value * 256.0F / 360.0F));
    }

    public float getHeadYaw() {
        return (((Byte) this.read(Constants.PACKET_SPAWNENTITYLIVING_FIELD_HEADYAW.getName())) * 360.0F / 256.0F);
    }

    public void setMetadata(WrappedDataWatcher metadata) {
        this.write(Constants.PACKET_SPAWNENTITYLIVING_FIELD_META.getName(), metadata.getHandle());
    }

    public Object getMetadata() {
        return this.read(Constants.PACKET_SPAWNENTITYLIVING_FIELD_META.getName());
    }
}