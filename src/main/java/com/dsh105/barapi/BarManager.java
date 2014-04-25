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

package com.dsh105.barapi;

import com.dsh105.barapi.bar.Bar;

import java.util.ArrayList;

public class BarManager {

    private ArrayList<Bar> bars = new ArrayList<Bar>();

    public ArrayList<Bar> getBars() {
        return new ArrayList<Bar>(this.bars);
    }

    public void track(Bar bar) {
        this.bars.add(bar);
    }

    public void stopTracking(Bar bar) {
        this.bars.remove(bar);
    }
}