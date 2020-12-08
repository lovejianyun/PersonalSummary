package com.qijy.sumspeed;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/*
 * @ Description   :  线程统计缓存
 * @ Author        :  qijy
 * @ CreateDate    :  2020/12/8 10:20
 */
public enum SpeedCache {
    INSTANCE;
    // 数量统计缓存
    private ConcurrentHashMap<String, AtomicLong> speed_count = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String,String> speed = new ConcurrentHashMap<>();
    /*
     * @ Description   :  统计条数
     * @ Author        :  qijy
     * @ CreateDate    :  2020/12/8 10:36
     */
    public void increament(String key){
        if(speed_count.containsKey(key)){
            speed_count.get(key).incrementAndGet();
        }else {
            speed_count.put(key,new AtomicLong(1));
        }
    }
    /*
     * @ Description   :  存储速度
     * @ Author        :  qijy
     * @ CreateDate    :  2020/12/8 10:37
     */
    public void putSpeed(String key,String value){
        speed.put(key,value);
    }

    public ConcurrentHashMap<String,AtomicLong> getSpeed_count(){
        return this.speed_count;
    }

    public ConcurrentHashMap<String,String> getSpeed(){
        return this.speed;
    }

    public void clear(){
        speed_count.clear();
        speed.clear();
    }

}
