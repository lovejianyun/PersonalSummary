package com.qijy.threads.thradUtils.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author qijy
 * @version 1.0
 * @description: 处理任务
 * @date 2023/7/13 14:17
 */
public class DealTask implements Runnable{
    private CountDownLatch countDownLatch;

    public DealTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
           TimeUnit.SECONDS.sleep(new Random(10).nextInt());
            System.out.println("处理中");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
    }
}
