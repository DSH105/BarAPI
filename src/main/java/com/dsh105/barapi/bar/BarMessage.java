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

public class BarMessage {

    private static final float DRAGON_MAX_HEALTH = 200F;

    private double percent;
    private String message;
    private float healthToShow;

    public BarMessage(double percent, String message) {
        this.percent = percent;
        this.message = message;
        this.healthToShow = (float) (this.percent / 100F) * DRAGON_MAX_HEALTH;
    }

    public double getPercent() {
        return percent;
    }

    public String getMessage() {
        return message;
    }

    protected float getHealthToShow() {
        return healthToShow;
    }
}