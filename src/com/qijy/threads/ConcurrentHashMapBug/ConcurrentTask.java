package com.qijy.threads.ConcurrentHashMapBug;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentTask implements Runnable {

    @Override
    public void run() {
        ConcurrentHashMap<String,String> tmp = new ConcurrentHashMap<>();
        tmp.putAll(Cache.INSTANCE.getCache());
        for (String string : tmp.keySet()) {
            System.out.println("size:" + tmp.size());
        }
        System.out.println("结束");
    }
}
