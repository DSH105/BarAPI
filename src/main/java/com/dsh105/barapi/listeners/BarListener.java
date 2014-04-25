package com.dsh105.barapi.listeners;

import com.dsh105.barapi.BarAPI;
import com.dsh105.barapi.bar.Bar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class BarListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        for (Bar b : BarAPI.getManager().getBars()) {
            if (b.getPlayerViews().containsKey(event.getPlayer())) {
                b.clearFor(event.getPlayer());
            }
        }
    }
}