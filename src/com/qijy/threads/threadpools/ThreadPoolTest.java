package com.qijy.threads.threadpools;

import java.util.concurrent.*;

/*
 * @ Description   :  线程池测试
 * @ Author        :  qijy
 * @ CreateDate    :  2020/10/27 9:24
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 15, 500, TimeUnit.MILLISECONDS, workQueue, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"傻逼线程");
            }
        });
        for (int i= 0;i<1000;i++){
            pool.execute(runnable);
            pool.submit(runnable);
        }
    }
    static Runnable runnable =  ()-> {
//        while (!Thread.currentThread().isInterrupted()){
            try {
                System.out.println("线程:"+Thread.currentThread().getName()+";      你是一个傻逼");
//                TimeUnit.HOURS.sleep(2);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
//            }
        }
    };
}
