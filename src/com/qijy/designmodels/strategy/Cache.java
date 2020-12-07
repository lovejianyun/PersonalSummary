package com.qijy.designmodels.strategy;

import java.util.concurrent.ConcurrentHashMap;

public enum  Cache {
    INSTANCE;
    private static final ConcurrentHashMap<String,Strategy> CACHE = new ConcurrentHashMap<>();

    public void put(String key,Strategy strategy){
        CACHE.put(key,strategy);
    }

    public Strategy get(String key){
        Strategy strategy = CACHE.get(key);
        return strategy;
    }
}
