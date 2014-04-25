package com.dsh105.barapi;

import org.bukkit.plugin.java.JavaPlugin;

public class BarAPICore extends JavaPlugin {

    public static final ModuleLogger LOGGER = new ModuleLogger("HoloAPI");
    public static final ModuleLogger LOGGER_REFLECTION = LOGGER.getModule("Reflection");

    @Override
    public void onEnable() {
        BarAPI.setCore(this);
    }
}