package com.dsh105.barapi.reflection;

public interface MethodAccessor<T> {

    T invoke(Object instance, Object... args);

    public Class<?> getReturnType();

    public Class[] getArguments();
}
