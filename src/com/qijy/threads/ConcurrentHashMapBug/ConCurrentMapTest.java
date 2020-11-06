package com.qijy.threads.ConcurrentHashMapBug;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConCurrentMapTest {
    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
        threadPool.scheduleAtFixedRate(new ConcurrentTask(),0,1, TimeUnit.SECONDS);
        threadPool.execute(new ConcurrentPutTask());
    }
}
