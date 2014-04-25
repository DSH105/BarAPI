/*
 * This file is part of BarAPI.
 *
 * BarAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BarAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BarAPI.  If not, see <http://www.gnu.org/licenses/>.
 */

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