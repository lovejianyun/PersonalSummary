package com.qijy.sumspeed;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/*
 * @ Description   :  线程统计任务
 * @ Author        :  qijy
 * @ CreateDate    :  2020/12/8 10:20
 */
public class SumSpeedTask implements Runnable {
    // 上一次时间
    private long pretime;
    // 上一次数量统计
    private ConcurrentHashMap<String, Long> pre_speed_count = new ConcurrentHashMap<>();
    @Override
    public void run() {
        // 当前时间
        long currentTime = System.currentTimeMillis();
        double period = (currentTime - pretime)/1000d;
        ConcurrentHashMap<String, AtomicLong> speed_count = SpeedCache.INSTANCE.getSpeed_count();
        ConcurrentHashMap<String, Long> speed_count_1 = getSpeed_count(speed_count);
        Set<Map.Entry<String, Long>> entries = speed_count_1.entrySet();
        for (Map.Entry<String, Long> entry : entries) {
            String key = entry.getKey();
            Long value = entry.getValue();
            if(speed_count_1.containsKey(key) && this.pre_speed_count.containsKey(key)){
                double v = (value - pre_speed_count.get(key)) / period;
                String format = String.format("%.2f", v);
                if (v>=0){
                    SpeedCache.INSTANCE.putSpeed(key,String.valueOf(format));
                }else {
                    SpeedCache.INSTANCE.putSpeed(key,String.valueOf(0));
                }
            }
        }
        pre_speed_count.putAll(speed_count_1);
        pretime = currentTime;
    }


    private ConcurrentHashMap<String,Long> getSpeed_count(ConcurrentHashMap<String, AtomicLong> speed_count){
        ConcurrentHashMap<String,Long> concurrentHashMap = new ConcurrentHashMap<>();
        for (Map.Entry<String, AtomicLong> entry : speed_count.entrySet()) {
            AtomicLong value = entry.getValue();
            if(value != null){
                concurrentHashMap.put(entry.getKey(),value.get());
            }
        }
        return  concurrentHashMap;
    }
}
