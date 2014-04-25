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