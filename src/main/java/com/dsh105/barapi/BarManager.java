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