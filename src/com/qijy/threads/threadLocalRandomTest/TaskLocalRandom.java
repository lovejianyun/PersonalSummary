package com.qijy.threads.threadLocalRandomTest;

import java.util.concurrent.ThreadLocalRandom;

/*
 * @ Description   :  生成并发随机数
 * @ Author        :  qijy
 * @ CreateDate    :  2020/3/9 18:26
 */
public class TaskLocalRandom implements Runnable {
    // 初始化随机数生成器
    public TaskLocalRandom() {
        ThreadLocalRandom.current();
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s:%d",name,ThreadLocalRandom.current().nextInt(10));
        }
    }
}
