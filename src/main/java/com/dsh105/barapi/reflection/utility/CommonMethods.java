package com.dsh105.barapi.reflection.utility;

import com.dsh105.barapi.reflection.MethodAccessor;
import com.dsh105.barapi.reflection.SafeMethod;
import com.google.common.base.Preconditions;
import org.bukkit.entity.Entity;

public class CommonMethods {

    private CommonMethods() {
        super();
    }

    public static Object getVanillaObject(Entity entity) {
        Preconditions.checkNotNull(entity);

        MethodAccessor<Object> getHandle = new SafeMethod<Object>(CommonReflection.getCraftEntityClass(), "getHandle");

        return getHandle.invoke(entity);
    }
}
