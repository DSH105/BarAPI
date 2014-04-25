package com.dsh105.barapi.reflection;

import com.dsh105.barapi.BarAPI;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SafeConstructor<T> {

    private Constructor<T> constructor;
    private Class[] params;

    public SafeConstructor(Constructor constructor) {
        setConstructor(constructor);
    }

    public SafeConstructor(Class<?> coreClass, Class<?>... params) {
        try {
            Constructor constructor = coreClass.getConstructor(params);
            setConstructor(constructor);
        } catch (NoSuchMethodException e) {
            BarAPI.getCore().LOGGER_REFLECTION.warning("No such constructor!");
        }
    }

    protected void setConstructor(Constructor constructor) {
        if (constructor == null) {
            throw new UnsupportedOperationException("Cannot create a new constructor!");
        }
        this.constructor = constructor;
        this.params = constructor.getParameterTypes();
    }

    public Constructor getConstructor() {
        return this.constructor;
    }

    public T newInstance(Object... params) {
        try {
            return (T) this.getConstructor().newInstance(params);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
