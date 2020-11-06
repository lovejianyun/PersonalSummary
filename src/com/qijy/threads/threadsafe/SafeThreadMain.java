package com.qijy.threads.threadsafe;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SafeThreadMain {
    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(2);
        String content = "qijy";
        threadPool.scheduleAtFixedRate(new SafeThread(content),0,10, TimeUnit.SECONDS);
        threadPool.scheduleAtFixedRate(new SafeThread(content),0,10, TimeUnit.SECONDS);
    }
}
