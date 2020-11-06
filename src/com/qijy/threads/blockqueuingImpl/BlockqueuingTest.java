package com.qijy.threads.blockqueuingImpl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockqueuingTest {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new LinkedBlockingQueue(5);
        Producter producter = new Producter(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        Consumer consumer1 = new Consumer(blockingQueue);
        Thread p = new Thread(producter);
        Thread c = new Thread(consumer);
        Thread c1 = new Thread(consumer1);
        p.start();
        c.start();
        c1.start();
    }
}
