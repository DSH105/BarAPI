package com.dsh105.barapi.reflection;

import com.dsh105.barapi.BarAPI;
import com.dsh105.barapi.reflection.utility.CommonReflection;

public class CBClassTemplate extends ClassTemplate<Object> {

    public CBClassTemplate() {
        setCBClass(getClass().getSimpleName());
    }

    public CBClassTemplate(String className) {
        setCBClass(className);
    }

    protected void setCBClass(String name) {
        Class clazz = CommonReflection.getCraftBukkitClass(name);
        if (clazz == null) {
            BarAPI.getCore().LOGGER_REFLECTION.warning("Failed to find a matching class with name: " + name);
        }
        setClass(clazz);
    }

    public static CBClassTemplate create(String className) {
        return new CBClassTemplate(className);
    }
}
