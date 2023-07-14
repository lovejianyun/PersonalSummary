package com.qijy.threads.thradUtils.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author qijy
 * @version 1.0
 * @description: TODO
 * @date 2023/7/13 14:34
 */
public class CyclicBarrierDealTaskTest {

    private static final int THREAD_COUNT = 100;

    public static void main(String[] args) {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_COUNT,()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我们已经全部到齐了");
        });

        // 模拟100个线程同时请求接口
        for (int i = 0; i < THREAD_COUNT; i++) {
            Runnable task = new CyclicBarrierDealTask(cyclicBarrier);
            executorService.execute(task);
        }
        // 关闭线程池
        executorService.shutdown();

    }
}

