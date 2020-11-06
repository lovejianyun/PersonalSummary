package com.qijy.threads.threadpools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * @ Description   :  线程池测试
 * @ Author        :  qijy
 * @ CreateDate    :  2020/10/27 9:24
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10000);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(100, 1000, 500, TimeUnit.MILLISECONDS, workQueue);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                    while (!Thread.currentThread().isInterrupted()){
                try {
                        System.out.println("你是一个傻逼");
                        TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                    }
            }
        });
    }
}
