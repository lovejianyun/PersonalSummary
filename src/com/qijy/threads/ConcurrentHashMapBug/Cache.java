package com.qijy.threads.ConcurrentHashMapBug;

import java.util.concurrent.ConcurrentHashMap;

public enum  Cache {
    INSTANCE;
    private ConcurrentHashMap<String,String> Cache = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, String> getCache() {
        return Cache;
    }

    public void setCache(ConcurrentHashMap<String, String> cache) {
        Cache = cache;
    }
}
