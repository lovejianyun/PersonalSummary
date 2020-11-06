package com.qijy.threads.startAndStop;

import java.util.concurrent.TimeUnit;

/*
 * @ Description   :  线程启动和停止
 * @ Author        :  qijy
 * @ CreateDate    :  2020/10/13 14:39
 */
public class ThreadStartStopTest {
    public static void main(String[] args) throws Exception{
        Thread t = new Thread(runnable);
        t.start();
        TimeUnit.MILLISECONDS.sleep(7500);
        t.interrupt();
    }

    static Runnable runnable = () -> {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()){
            try {
                count++;
                TimeUnit.SECONDS.sleep(1);
                System.out.println("count:" + count);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    };
}
