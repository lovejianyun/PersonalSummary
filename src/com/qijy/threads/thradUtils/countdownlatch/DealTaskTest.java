package com.qijy.threads.thradUtils.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author qijy
 * @version 1.0
 * @description: TODO
 * @date 2023/7/13 14:23
 */
public class DealTaskTest {
    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i=0;i<10;i++){
            DealTask dealTask = new DealTask(countDownLatch);
            Thread thread = new Thread(dealTask);
            thread.start();
        }
        countDownLatch.await();
        System.out.println("主线程开始处理了");
    }
}
