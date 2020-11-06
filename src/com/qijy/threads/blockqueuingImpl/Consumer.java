package com.qijy.threads.blockqueuingImpl;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<Integer> blockingQueue;

    public Consumer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(2000);
                Integer i = blockingQueue.take();
                System.out.println("消费线程:"+Thread.currentThread().getName()+"，消费："+i+";队列中的数量:"+blockingQueue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
