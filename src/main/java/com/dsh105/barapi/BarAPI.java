package com.dsh105.barapi;

public class BarAPI {

    private static BarAPICore CORE;

    public static void setCore(BarAPICore core) {
        if (CORE != null) {
            return;
        }
        CORE = core;
    }

    public static BarAPICore getCore() {
        return CORE;
    }

    public static BarManager getManager() {

    }
}