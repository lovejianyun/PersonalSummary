package com.qijy.algorithm.threads;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class MapCacheThread {
    private static ConcurrentHashMap<String, Map<String,String>> mgateRealCache = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Long> createtime = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Long> updatetime = new ConcurrentHashMap<>();
    int count = 1;

    class xxxx2 implements Runnable{

        @Override
        public void run() {

            List<String> keyList = new ArrayList<>();
            Random random = new Random(1);
            while(true){
                int i = random.nextInt(100);
                long cur = System.currentTimeMillis();
                Map<String,String> map = new HashMap<>();
                map.put("qijy"+i,""+i);
                map.put("createtime",String.valueOf(cur));
                mgateRealCache.put("qijy"+i,map);
                createtime.put("qijy"+i,System.currentTimeMillis());
                keyList.add("qijy"+i);
                if(count>100){
                    break;
                }
                count++;
            }
            System.out.println("大小"+keyList.size());

            while(true){
                for(String key:keyList){
                    createtime.put(key,System.currentTimeMillis());
                }

            }

        }
    }

    class xxa implements Runnable{
        @Override
        public void run() {
            while(true) {
                for (Map.Entry<String, Long> mapEntry : createtime.entrySet()) {
                    String key = mapEntry.getKey();
                    Long ct = mapEntry.getValue();
                    Long ut = updatetime.get(key);
                    if (null == ut) {
                        // 写入redis
                        mgateRealCache.get(key);
                        System.out.println(("第一次写入redis数据有:" + key));
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        updatetime.put(key, System.currentTimeMillis());
                    } else if (ct > ut) {
                        mgateRealCache.get(key);
                        System.out.println(("重复写入redis数据有:" + key));
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        updatetime.put(key, System.currentTimeMillis());

                    } else {
                        System.out.println(("无需更新:" + key));
                    }

                }

                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }



    public static void main(String[] args) {

        MapCacheThread mapCacheThread = new MapCacheThread();
        new Thread(mapCacheThread.new xxa()).start();
        new Thread(mapCacheThread.new xxxx2()).start();

    }

}
