package com.qijy.threads.threadmodels;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/*
 * @ Description   :  线程工厂测试
 * @ Author        :  qijy
 * @ CreateDate    :  2020/10/29 11:16
 */
public class ThreadfactoryTest {
    public static void main(String[] args) {
        ThreadFactory factory = Executors.defaultThreadFactory();
        Thread thread = factory.newThread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                do{
                    System.out.println("qijy");
                    int i = Runtime.getRuntime().availableProcessors();
                    System.out.println("处理器个数:" + i);
                    count++;
                }while (count<10);

            }
        });
        thread.setName("qijy线程");

        thread.start();
    }
}
