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

package com.dsh105.barapi.bar;

import com.dsh105.barapi.BarAPI;
import com.dsh105.barapi.protocol.wrapper.*;
import com.dsh105.barapi.util.EntityIdGenerator;
import com.dsh105.barapi.util.PlayerIdent;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bar {

    private ArrayList<BarMessage> messages = new ArrayList<BarMessage>();
    private HashMap<String, Integer> playerToViewMap = new HashMap<String, Integer>();
    private final int entityId;
    private BukkitTask task;
    private int secondsBetweenChange = 1;

    public Bar(BarMessage message) {
        this.entityId = EntityIdGenerator.nextId();
        this.setMessages(message);
    }

    public Bar(int secondsBetweenChange, BarMessage... messages) {
        this.secondsBetweenChange = secondsBetweenChange;
        this.entityId = EntityIdGenerator.nextId();
        this.setMessages(messages);
        for (BarMessage m : messages) {
            this.messages.add(m);
        }
        this.beginTask();
    }

    private void beginTask() {
        if (this.task != null) {
            this.task.cancel();
        }
        this.task = new BukkitRunnable() {
            @Override
            public void run() {
                change();
            }
        }.runTaskTimer(BarAPI.getCore(), 20L, 20L * this.secondsBetweenChange);
    }

    private void change() {
        this.incrementAllViews();
        for (Player player : getPlayerViews().keySet()) {
            if (player != null) {
                update(player);
            }
        }
    }

    private void incrementAllViews() {
        for (Map.Entry<String, Integer> entry : playerToViewMap.entrySet()) {
            Player viewer = PlayerIdent.getPlayerOf(entry.getKey());
            if (viewer != null) {
                int index = entry.getValue();
                if (++index >= messages.size()) {
                    index = 0;
                }
                this.store(viewer, index);
            }
        }
    }

    public HashMap<Player, Integer> getPlayerViews() {
        HashMap<Player, Integer> map = new HashMap<Player, Integer>();
        for (Map.Entry<String, Integer> entry : this.playerToViewMap.entrySet()) {
            Player player = PlayerIdent.getPlayerOf(entry.getKey());
            if (player != null) {
                map.put(player, entry.getValue());
            }
        }
        return map;
    }

    public void refreshAllPlayerViews() {
        for (Player p : this.getPlayerViews().keySet()) {
            this.showTo(p);
        }
    }

    public void setMessages(BarMessage... messages) {
        boolean empty = this.messages.isEmpty();
        this.messages.clear();
        for (BarMessage m : messages) {
            this.messages.add(m);
        }
        if (!empty) {
            this.refreshAllPlayerViews();
            if (this.task != null) {
                this.task.cancel();
                this.beginTask();
            }
        }
    }

    public void showTo(Player observer) {
        this.showTo(observer, 0);
    }

    public void showTo(Player observer, int startIndex) {
        if (startIndex >= this.messages.size()) {
            startIndex = 0;
        }
        this.store(observer, startIndex);
        this.generate(observer);
    }

    public void clearFor(Player observer) {
        if (this.getPlayerViews().containsKey(observer)) {
            this.destroy(observer);
            this.playerToViewMap.remove(PlayerIdent.getIdentificationForAsString(observer));
        }
    }

    private void store(Player player, int viewIndex) {
        this.playerToViewMap.put(PlayerIdent.getIdentificationForAsString(player), viewIndex);
    }

    private void store(Player player, BarMessage view) {
        this.store(player, this.getIndexOf(view));
    }

    public BarMessage getMessage(int index) {
        if (index >= this.messages.size()) {
            throw new IndexOutOfBoundsException("Frame " + index + "doesn't exist.");
        }
        return this.messages.get(index);
    }

    public int getIndexOf(BarMessage message) {
        return messages.indexOf(message);
    }

    private BarMessage getCurrentMessage(Player observer) {
        if (!this.getPlayerViews().containsKey(observer)) {
            return null;
        }
        return this.getMessage(this.getPlayerViews().get(observer));
    }

    private WrappedDataWatcher generateMeta(BarMessage message) {
        WrappedDataWatcher dataWatcher = new WrappedDataWatcher();
        // Set invisible
        dataWatcher.initiate(0, (byte) 0x20);
        // Set the health
        dataWatcher.initiate(6, message.getHealthToShow());
        // Set the custom name
        dataWatcher.initiate(10, message.getMessage());
        // Set custom name visible
        dataWatcher.initiate(11, Byte.valueOf((byte) 1));
        return dataWatcher;
    }

    private void generate(Player observer) {
        WrapperPacketSpawnEntityLiving spawn = new WrapperPacketSpawnEntityLiving();
        spawn.setEntityId(this.entityId);
        spawn.setEntityType(EntityType.ENDER_DRAGON.getTypeId());
        spawn.setX(observer.getLocation().getX());
        spawn.setY(observer.getLocation().getY() - 300);
        spawn.setZ(observer.getLocation().getZ());
        spawn.setMetadata(this.generateMeta(this.getCurrentMessage(observer)));
        spawn.send(observer);
    }

    private void destroy(Player observer) {
        WrapperPacketEntityDestroy destroy = new WrapperPacketEntityDestroy();
        destroy.setEntities(this.entityId);
        destroy.send(observer);
    }

    private void update(Player observer) {
        WrapperPacketEntityMetadata meta = new WrapperPacketEntityMetadata();
        meta.setEntityId(this.entityId);
        meta.setMetadata(this.generateMeta(this.getCurrentMessage(observer)));
        meta.send(observer);
    }

    private void teleport(Player observer) {
        WrapperPacketEntityTeleport teleport = new WrapperPacketEntityTeleport();
        teleport.setEntityId(this.entityId);
        teleport.setX(observer.getLocation().getX());
        teleport.setY(observer.getLocation().getY() - 300);
        teleport.setZ(observer.getLocation().getZ());
        teleport.send(observer);
    }
}