package com.qijy.threads.ConcurrentHashMapBug;



import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentPutTask implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Cache.INSTANCE.getCache().put("qijy","1");
    }
}
