package com.dsh105.barapi.util;

public class EntityIdGenerator {

    private volatile static int SHARED_ID = Short.MAX_VALUE;

    public static int nextId() {
        return ++SHARED_ID;
    }
}