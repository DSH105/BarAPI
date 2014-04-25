package com.dsh105.barapi.reflection;

import com.dsh105.barapi.BarAPI;
import com.dsh105.barapi.reflection.utility.CommonReflection;

public class NMSClassTemplate extends ClassTemplate {

    protected NMSClassTemplate() {
        setNMSClass(getClass().getSimpleName());
    }

    public NMSClassTemplate(String className) {
        setNMSClass(className);
    }

    protected void setNMSClass(String name) {
        Class clazz = CommonReflection.getMinecraftClass(name);
        if (clazz == null) {
            BarAPI.getCore().LOGGER_REFLECTION.warning("Failed to find a matching class with name: " + name);
        }
        setClass(clazz);
    }

    public static NMSClassTemplate create(String className) {
        return new NMSClassTemplate(className);
    }
}
