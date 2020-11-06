package com.qijy.forkjoins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CacheInitByForkjoin extends RecursiveTask<List<Boolean>> {
    private String [] caches;
    private ConcurrentHashMap<String,String> cache;

    public CacheInitByForkjoin(String[] caches) {
        this.caches = caches;
        cache = new ConcurrentHashMap<>();
        cache.put("qijy","牛逼!");
        cache.put("nm","sx");
    }

    @Override
    protected List<Boolean> compute() {
        List<Boolean> result = new LinkedList<>();
        if(null == caches|| 0==caches.length){
            result.add(false);
            return result;
        }
        if (1==caches.length){
            result.add(print(cache.get(caches[0])));
        }else{
            List<ForkJoinTask<List<Boolean>>> forkJoinTasks = new ArrayList<>();
            for (int i=0;i<caches.length;i++){
                forkJoinTasks.add(new CacheInitByForkjoin(new String[]{caches[i]}));
            }
            Collection<ForkJoinTask<List<Boolean>>> tasks = invokeAll(forkJoinTasks);
            for (ForkJoinTask<List<Boolean>> task : tasks) {
                List<Boolean> join = task.join();
                result.addAll(join);
            }
        }
        return result;
    }

    private boolean print(String value){
        if(null != value){
        System.out.println("缓存中的值:"+value);
        return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(1);
        CacheInitByForkjoin cacheInitByForkjoin = new CacheInitByForkjoin(new String[]{"qijy","nm"});
        forkJoinPool.invoke(cacheInitByForkjoin);

    }
}
