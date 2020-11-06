package com.qijy.threads.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    private CountDownLatch latch;

    public CountDownLatchTest(CountDownLatch latch) {
        this.latch = latch;
    }

    public void loadcache(){
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("加载redis缓存");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            latch.countDown();
        }
    }
    public void loadfile(){
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("加载文件!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            latch.countDown();
        }
    }
    public void loaddatabase(){
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("加载数据库数据!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            latch.countDown();
        }
    }


    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        try {
            CountDownLatchTest countDownLatchTest = new CountDownLatchTest(latch);
            new Thread(countDownLatchTest::loadcache).start();
            new Thread(countDownLatchTest::loadfile).start();
            new Thread(countDownLatchTest::loaddatabase).start();
            latch.await();
            System.out.println("所有缓存已经加载完毕!,启动成功!!!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
