package com.dsh105.barapi.protocol.wrapper;

import com.dsh105.barapi.reflection.Constants;
import com.dsh105.barapi.reflection.utility.CommonReflection;
import com.dsh105.barapi.util.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;

public class WrappedDataWatcher extends AbstractWrapper {

    public WrappedDataWatcher() {
        try {
            if (CommonReflection.isUsingNetty()) {
                super.setHandle(CommonReflection.getMinecraftClass("DataWatcher").getConstructor(CommonReflection.getMinecraftClass("Entity")).newInstance(new Object[]{null}));
            } else {
                super.setHandle(CommonReflection.getMinecraftClass("DataWatcher").newInstance());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public WrappedDataWatcher(Object entity) {
        try {
            if (CommonReflection.isUsingNetty()) {
                super.setHandle(CommonReflection.getMinecraftClass("DataWatcher").getConstructor(CommonReflection.getMinecraftClass("Entity")).newInstance(entity));
            } else {
                super.setHandle(CommonReflection.getMinecraftClass("DataWatcher").newInstance());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void initiate(int index, Object value) {
        ReflectionUtil.invokeMethod(ReflectionUtil.getMethod(getHandle().getClass(), Constants.DATAWATCHER_FUNC_INITIATE.getName(), int.class, Object.class), getHandle(), index, value);
    }

    public void watch(int index, Object value) {
        ReflectionUtil.invokeMethod(ReflectionUtil.getMethod(getHandle().getClass(), Constants.DATAWATCHER_FUNC_WATCH.getName(), int.class, Object.class), getHandle(), index, value);
    }
}
