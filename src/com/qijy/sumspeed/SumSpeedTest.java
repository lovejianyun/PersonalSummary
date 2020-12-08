package com.qijy.sumspeed;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SumSpeedTest {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        new Thread(()->{
            while (true){
                try {
                    SpeedCache.INSTANCE.increament("qijy");
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"qijy累加线程").start();

        new Thread(()->{
            while (true){
                try {
                    SpeedCache.INSTANCE.increament("songli");
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"songli累加线程").start();
        // 启动速度统计任务
        pool.scheduleAtFixedRate(new SumSpeedTask(),2,2,TimeUnit.SECONDS);

        // 定时清除缓存
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                SpeedCache.INSTANCE.clear();
            }
        },12,12,TimeUnit.HOURS);
        // 打印速度
        new Thread(()->{
            while(true){
                try {
                    ConcurrentHashMap<String, String> speed = SpeedCache.INSTANCE.getSpeed();
                    for (Map.Entry<String, String> entry : speed.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        System.out.println(key+"处理速度:"+value);
                    }
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"读取速度线程").start();
    }
}
