package com.dsh105.barapi.reflection;

import java.lang.reflect.Field;

public interface FieldAccessor<T> {

    public Field getField();

    boolean set(Object instance, T value);

    T get(Object instance);

    T transfer(Object from, Object to);

    boolean isPublic();

    boolean isReadOnly();

    void setReadOnly(boolean readOnly);
}
