package com.qijy.threads.juc;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/*
 * @ Description   :  栏栅
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/5 9:10
 */
public class Cyclicbarrier {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(1000);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(run, "线程" + i);
            thread.start();
        }
    }

    static Runnable run = () -> {
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
            System.out.println(Thread.currentThread().getName() + "任务启动!");
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + "任务继续执行!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    };


}
